package com.waszczyk.conwey;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.renderable.RenderableImage;

import javax.swing.JFrame;

public class Component extends Applet implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static int width = 640;
	public static int height = 640;
	public static JFrame frame;
	public static boolean isRunning = false;
	public Graphics g;
	public Image screen;
	public static Level level;
	
	public void start() {
		init();
		
		isRunning = true;
		Thread thread = new Thread(this);
		
		
		thread.start();
	}
	
	public void init() {
		
		screen = createVolatileImage(width, height);
		level = new Level();
	}
	
	@Override
	public void run() {
		while(isRunning) {
			tick();
			render(g);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void tick() {
		
		width = getWidth();
		height = getHeight();
		level.tick();
	}
	
	public void render(Graphics g) {
		
		screen = createImage(width, height);
		g = screen.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		level.render(g);
		
		g = getGraphics();
		g.drawImage(screen, 0, 0, width, height,null);
	}
	
	public static void main(String[] args) {
		
		Component component = new Component();
		
		frame = new JFrame();
		frame.add(component);
		frame.setSize(width + 6, height + 26);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		component.start();
	}
}
