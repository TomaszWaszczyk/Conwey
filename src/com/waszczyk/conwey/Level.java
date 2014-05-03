package com.waszczyk.conwey;

import java.awt.Graphics;
import java.util.Random;

public class Level {

	public static Block[][] block;
	public static Block[][] block2;
	
	public Level() {
		
		block = new Block[Component.width / Block.blockSize][Component.height / Block.blockSize];
		block2 = new Block[Component.width / Block.blockSize][Component.height / Block.blockSize];
		
		for(int x = 0; x < block.length; x++) {
			for(int y = 0; y < block[0].length; y++) {
					block[x][y] = new Block(x, y);
					block2[x][y] = new Block(x, y);
					block[x][y].alive = new Random().nextInt(4) == 0;
			}
		
			
		}
	}
	
	public void tick() {
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[0].length; y++) {
				block2[x][y].alive = block[x][y].alive;
			}
		}
		
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[0].length; y++) {
				block[x][y].alive = false;
			}
		}
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[0].length; y++) {
				int n = 0;
				try {
				if(block2[x-1][y].alive) n++;
				} catch(Exception e) {}
				try {
				if(block2[x-1][y-1].alive) n++;
				} catch(Exception e) {}
				try {
				if(block2[x][y-1].alive) n++;
				} catch(Exception e) {}
				try {
				if(block2[x+1][y-1].alive) n++;
				} catch(Exception e) {}
				try {
				if(block2[x+1][y].alive) n++;
				} catch(Exception e) {}
				try {
				if(block2[x+1][y+1].alive) n++;
				} catch(Exception e) {}
				try {
				if(block2[x][y+1].alive) n++;
				} catch(Exception e) {}
				try {
				if(block2[x-1][y+1].alive) n++;
				} catch(Exception e) {}
				
				boolean b = false;
				
				if (block2[x][y].alive) {
					switch(n) {
					case 0:
					case 1:
						b = false;
						break;
					case 2:
					case 3: 	
						b = true;
						break;
					default:
						b = false;
						break;
					}
				}
				else {
					
					switch(n) {
					case 3: 	
						b = true;
						break;
					default:
						b = false;
						break;
					}
				}
				
				block[x][y].alive = b;
			}
		}
	}
	
	public void render(Graphics g) {
		
		for(int x=0; x < block.length; x++) {
			for (int y = 0; y < block[0].length; y++) {
				block[x][y].render(g);
			}
		}
	}
	
	
}
