package game;

import java.awt.Graphics;

/**
 * 
 * This is the main class for the ship element of the game which we control 
 * during the game to collect hearts and avoid boulders. It has two inner classes,
 * the PointsOfShip class and the Bullet class. A ship HAS points of ship and a 
 * ship HAS bullets. The bullets class has all necessary code for defining the 
 * shape of the bullet, defining its paint method, and shooting it. The PointsOfShip
 * class defines the shape of the ship into an array and also has a get method 
 * for returning that array.
 * 
 * @author Syed Abdullah Rizvi, Ashmit Bakul Palwe
 * 
 */

public class Ship extends Polygon {
	
	private static Ship.PointsOfShip shipPoints = new Ship.PointsOfShip();
	
	private static Point[] pointsShape = shipPoints.getPointsOfShip();
	
	private Point currentPosition;
	
	/**
	 * This method constructs a Ship element using the Superclass constructor 
	 * and sets the currentPosition instance variable to the value passed as 
	 * parameter
	 * @param inPosition
	 * @param inRotation
	 */
	
	public Ship(Point inPosition, double inRotation) {
		super(pointsShape, inPosition, inRotation);
		
		this.currentPosition = new Point(inPosition.getX(), inPosition.getY());
		
		
	}
	
	/**
	 * The method to display the ship on the screen during the 
	 * game. It creates an array of x-coordinates and an array of y-coordinates
	 * from the array of points used in making the ship shape, and passes them
	 * as parameters in the fillPolygon method of the brush parameter. 
	 * 
	 * @param brush
	 */
	
	public void paint(Graphics brush) {
		
		Point[] pointsOfShape = super.getPoints();
		int[] xCoords = new int[pointsOfShape.length];
		int[] yCoords = new int[pointsOfShape.length];
		
		for(int i = 0; i < pointsOfShape.length; i++) {
			
			xCoords[i] = (int) pointsOfShape[i].getX();
			yCoords[i] = (int) pointsOfShape[i].getY();
			
		}
		
		brush.fillPolygon(xCoords, yCoords, 12);
		
	}
	
	/**
	 * the method increments the x-coordinate of the ship by 40 and updates 
	 * the current position of the element to changed amount.
	 */
	
	public void moveRight() {
		
		super.position.setX(super.position.getX() + 40);
		
		this.currentPosition = new Point(super.position.getX(), super.position.getY());
		
	}
	
	/**
	 * the method decrements the x-coordinate of the ship by 40 and updates 
	 * the current position of the element to changed amount.
	 */
	
	public void moveLeft() {
		
		
		super.position.setX(super.position.getX() - 40);
		
		this.currentPosition = new Point(super.position.getX(), super.position.getY());
		
	}
	
	/**
	 * the method decrements the y-coordinate of the ship by 40 and updates 
	 * the current position of the element to changed amount.
	 */
	
	public void moveUp() {
		
		super.position.setY(super.position.getY() - 40);
		
		this.currentPosition = new Point(super.position.getX(), super.position.getY());
		
	}
	
	/**
	 * the method increments the y-coordinate of the ship by 40 and updates 
	 * the current position of the element to changed amount.
	 */
	
	public void moveDown() {
		
		super.position.setY(super.position.getY() + 40);
		
		this.currentPosition = new Point(super.position.getX(), super.position.getY());
		
	}
	
	/**
	 * the method updates the rotation and sets it to of t as the parameter passed 
	 * in the method. 
	 * @param degrees
	 */
	
	public void setRotation(double degrees) {
		
		super.rotation = degrees;
		
	}
	
	/**
	 * 
	 * The Bullet inner class extends the polygon class. it has a private 
	 * instance variable of an array of Points, a constructor method using the
	 * superclass constructor, a point method that displays the element on the 
	 * screen when called using the fillPolygon method of the brush object. The 
	 * innerClass also has a public shoot method that decrements the y-coordinate
	 * of the bullet element by 7 when called.
	 * 
	 * Creating the bullet class as an inner class helped us to keep track of the
	 * current position of the ship and use that to create new bullets, and secondly,
	 * making it public helped us to access bullets from anoother classes.
	 * 
	 */
	
	public class Bullet extends Polygon {
		
		private static Point[] pointsOfBullet = {new Point(25,10),new Point(25,15),
				new Point(25,20),new Point(30,20),new Point(35,20),new Point(35,15),
				new Point(35,10),new Point(30,5)};

		
		/**
		 * This method constructs a Bullet element using the Superclass constructor 
		 * 
		 * @param inRotation
		 */
		
		public Bullet(double inRotation) {
			super(pointsOfBullet, currentPosition, inRotation);
			// TODO Auto-generated constructor stub
		}

		/**
		 * The method to display the bullet on the screen during the 
		 * game. It creates an array of x-coordinates and an array of y-coordinates
		 * from the array of points used in making the bullet shape, and passes them
		 * as parameters in the fillPolygon method of the brush parameter. 
		 * 
		 * @param brush
		 */
		
		public void paint(Graphics brush) {

			Point[] pointsOfShape = super.getPoints();
			int[] xCoords = new int[pointsOfShape.length];
			int[] yCoords = new int[pointsOfShape.length];

			for (int i = 0; i < pointsOfShape.length; i++) {

				xCoords[i] = (int) pointsOfShape[i].getX();
				yCoords[i] = (int) pointsOfShape[i].getY();

			}

			brush.fillPolygon(xCoords, yCoords, 8);

		}
		
		/**
		 * the method decrements the y-coordinate of the ship by 7 and updates 
		 * the current position of the element to changed amount.
		 */
		
		public void shoot() {
			super.position.setY(super.position.getY() - 7);
			
		}
		
	}
	
	/**
	 * 
	 * This is a private static inner class which for our project stores information
	 * about the shape of ship in an array of Points, which can be accessed 
	 * by our ship class since this is an inner class.
	 * 
	 * Creating is as a private class helped us to limit access of this class 
	 * from outside the class and we made the class static since we are keeping 
	 * the shape of the ship same and not changing it, and secondly, we are not accessing 
	 * any instance variables in this class.
	 * 
	 */
	
	private static class PointsOfShip {
		
		public static Point[] pointsOfShip = {new Point(50, 0) , new Point(50,22.5), 
				new Point(50,45), new Point(50,67.5), new Point(50, 90), new Point(38.75,78.75),
				new Point(27.5,67.5), new Point(16.25,56.25), new Point(5, 45), new Point(16.25,33.75),
				new Point(27.5,22.5), new Point(38.75,11.25)};

		/**
		 * 
		 * This is a get method for our pointsOfShip array
		 * 
		 */
		
		public static Point[] getPointsOfShip() {
			return pointsOfShip;
		}
		
	}

}
