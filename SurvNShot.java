package game;

/*
CLASS: SurvNShot
DESCRIPTION: Extending Game, HeartAttack is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import game.Ship.Bullet;

/**
 * 
 * This class extends the Game class and implements the KeyListner interface.
 * 
 * This game basically involves 3 main elements, a ship, boulders and hearts. 
 * While playing the game, you will be controlling the ship which you will have
 * to save from the falling boulders. The ship also contains bullets, which you
 * will have to shoot at the hearts to make them disappear. Each collision with 
 * heart will increment you score by 10. 
 * 
 * Enjoy!
 * 
 * @author Syed Abdullah Rizvi, Ashmit Bakul Palwe
 * 
 */

class SurvNShot extends Game implements KeyListener{
	static int counter = 0;
	public Ship ship;
	// public Boulder b;
	public ArrayList<Boulder> boulderList;
	private boolean gameOver;
	public ArrayList<Heart> hearts;
	private int score;
	private GameInstructions statusOfGame;
	
	public ArrayList<Ship.Bullet> bullets;
	
	/**
	 * Initializes all the instance variables defined above. Makes a canvas 
	 * with certain attributes. This constructor also creates an anonymous class
	 * which is used to display the instructions of the game later on.
	 * 
	 */
	
  public SurvNShot() {
    super("SurvNShot",800,600);
    this.setFocusable(true);
	this.requestFocus();
	
	ship = new Ship(new Point(300, 500), 90);
	
	this.addKeyListener(this);
	this.boulderList = new ArrayList<Boulder>();
	this.hearts= new ArrayList<Heart>();
	gameOver = false;
	score = 0;
	
	// anonymous class
	this.statusOfGame = new GameInstructions() {
		
		public String gameInstructions() {
			
			return "Avoid the Boulders  ;  "
					+ "Collect the Hearts  ;  "
					+ "Use Arrow Keys in move in respective directions   ;  "
					+ "Use space bar to fire bullets ";
			
		}
		
	};
	
	this.bullets = new ArrayList<Ship.Bullet>();
	
  }
  
  	/**
	 * The animation of our game relies on this method, since this is the method 
	 * that is called every 10 of a second to update the animation.
	 * 
	 * @param brush
	 * 
	 */
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
    	// brush.drawLine(50, 50, 100, 100);
    	// brush.drawRect(50, 175, 24, 42);
    	
    	String scoreRslt= "Score is:"+score;
		brush.drawString(scoreRslt, 10, 30);
    	
		
		if(counter <= 300) {
			
			brush.drawString(statusOfGame.gameInstructions(), 10, 300);
			
		} else {
			
			if(!gameOver) {
	    		
	    		ship.paint(brush);
	        	
	        	Random randomObject = new Random();
	        	
	        	if(counter % 20 == 0) {
	        		
	        		boulderList.add(new Boulder(new Point(
	        				randomObject.nextDouble(10, 700), 10), 0));
	        		
	        		
	        	}
	        	
	        	for(Boulder b : boulderList) {
	        		
	        		b.paint(brush);
	        		b.fall();
	        		b.spin(b);
	        		
	        	}
	        	
	        	for(Boulder b : boulderList) {
	        		
	        		collidesBoulder(b);
	        		
	        	}
	        	
	        	if (counter % 200 == 0) {

					hearts.add(new Heart(new Point(
							randomObject.nextDouble(700), 
							randomObject.nextDouble(100, 400)), 0));

				}

				for (Heart h : hearts) {

					h.paint(brush);

				}
				
				boolean heartCollided;
				
				// lamda expression
				Scoring scoringLambda = (boolean b) -> {
					
					if(b) {
						
						return 10;
						
					} else {
						
						return 0;
						
					}
					
					
				};
				
				for (int i = 0 ; i < hearts.size(); i++) {

					heartCollided = collidesHeart(hearts.get(i));
					
					score += scoringLambda.scoring(heartCollided);
					
					if(heartCollided) {
						
						hearts.remove(i);
						
					}

				}
				
				for(Ship.Bullet blt : bullets) {
					
					blt.paint(brush);
					blt.shoot();
					
				}
				
				boolean bltCollided;
				
				for (int i = 0 ; i < hearts.size(); i++) {

					bltCollided = collidesBullet(hearts.get(i));
					
					score += scoringLambda.scoring(bltCollided);
					
					if(bltCollided) {
						
						hearts.remove(i);
						
					}

				}
	        	
	    		
	    	} else {
	    		
	    		brush.drawString("GAME OVER!", 300, 300);
	    		
	    	}
			
		}
    	
  }
	
	/**
	 * main method
	 * 
	 * @param args
	 * 
	 */
  
	public static void main (String[] args) {
   		SurvNShot a = new SurvNShot();
		a.repaint();
  }
	
	/**
	 * This is a method from the KeyListner Interface which is invoked when a 
	 * key has been typed.
	 * 
	 * @param e
	 * 
	 */

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * This is a method from the KeyListner Interface which is invoked when a 
	 * key has been pressed.
	 * 
	 * @param e
	 * 
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_KP_RIGHT || 
				e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			// ship.setRotation(180);
			ship.moveRight();
			
		} else if(e.getKeyCode() == KeyEvent.VK_KP_LEFT || 
				e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			// ship.setRotation(0);
			ship.moveLeft();
			
		} else if(e.getKeyCode() == KeyEvent.VK_KP_DOWN ||
				e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			ship.moveDown();
			
		} else if(e.getKeyCode() == KeyEvent.VK_KP_UP ||
				e.getKeyCode() == KeyEvent.VK_UP) {
			
			ship.moveUp();
			
		} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			bullets.add(ship.new Bullet(0));
			
		}
		
	}
	
	/**
	 * This is a method from the KeyListner Interface which is invoked when a 
	 * key has been released.
	 * 
	 * @param e
	 * 
	 */

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
//		if(e.getKeyCode() == KeyEvent.VK_KP_RIGHT || 
//				e.getKeyCode() == KeyEvent.VK_RIGHT) {
//			
//			ship.setRotation(90);
//			
//		} else if(e.getKeyCode() == KeyEvent.VK_KP_LEFT || 
//				e.getKeyCode() == KeyEvent.VK_LEFT) {
//			
//			ship.setRotation(90);
//			
//		}
		
	}
	
	/**
	 * This method takes in a boulder as a parameter and goes through each point
	 * of the ship to check if the boulder contains that point in the ship, which
	 * would happen only in the boulder has collided with the ship, which in our
	 * game ends the game, hence setting our gameOver variable to be true. 
	 * 
	 * @param boulder
	 * 
	 */
	
	private void collidesBoulder(Boulder boulder) {
		
		for(Point pt : ship.getPoints()) {
			
			if(boulder.contains(pt)) {
				
				gameOver = true;
				
			}
			
		}
		
	}
	
	/**
	 * This method takes in a heart as a parameter and goes through each point
	 * of the ship to check if the heart contains that point in the ship, which
	 * would happen only in the heart has collided with the ship, which in our
	 * game increments the score. 
	 * 
	 * The method returns true if the collision occurs and false otherwise. 
	 * 
	 * @param heart
	 * 
	 * @return boolean with collision info
	 * 
	 */
	
	private boolean collidesHeart(Heart heart) {

		for (Point pt : ship.getPoints()) {

			if (heart.contains(pt)) {

				// score += 10;
				return true;

			}

		}
		return false;

	}
	

	
	/**
	 * This method takes in a heart as a parameter and goes through every bullet 
	 * in the ArrayList of bullets and then goes through each points of those bullets
	 * to check if the heart contains that point in the bullet, which
	 * would happen only in the heart has collided with the bullet, which in our
	 * game increments the score. 
	 * 
	 * The method returns true if the collision occurs and false otherwise. 
	 * 
	 * @param heart
	 * 
	 * @return boolean with collision info
	 * 
	 */
	
	private boolean collidesBullet(Heart heart) {
		
		for(int i = 0; i < bullets.size(); i++) {
			
			for (Point pt : bullets.get(i).getPoints()) {

				if (heart.contains(pt)) {
					
					bullets.remove(i);
					
					return true;

				}
			}
			
		}
		
		return false;

	}
	
}