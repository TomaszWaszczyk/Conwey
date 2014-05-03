package com.waszczyk.conwey;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle{
	
	public static int blockSize = 10;
	public boolean alive = false;
	
	public Block(int x, int y) {
		setBounds(x * blockSize, y * blockSize, blockSize, blockSize);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		
		if (alive) {
			g.fillRect(x, y, width, height);
		}
	}
}
