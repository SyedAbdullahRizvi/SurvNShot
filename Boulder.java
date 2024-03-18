package game;

import java.awt.Graphics;

/**
 * The boulder class is the main class for our obstacle to be avoided in the game.
 * it has a paint method defined in the class, and an inner class called PointsOfBoulder
 * that definies an array of Points that define the shape of the boulder. it also 
 * has a fall method that sets the boulders position further below, making it seem
 * like falling, and a spin method that gives a spin to the falling boulders by rotating them.
 * 
 * @author Syed Abdullah Rizvi, Ashmit Bakul Palwe
 * 
 */

public class Boulder extends Polygon{
	
	private static Boulder.PointsOfBoulder boulderPoints = new Boulder.PointsOfBoulder();
	
	private static Point[] pointsOfShape = boulderPoints.pointsOfBoulder;
	
	/**
	 * This method constructs a Boulder element using the Superclass constructor 
	 * @param inPosition
	 * @param inRotation
	 */
	
	public Boulder(Point inPosition, double inRotation) {
		super(pointsOfShape, inPosition, inRotation);
	}
	
	
	/**
	 * The method to display the boulder on the screen during the 
	 * game. It creates an array of x coordinates and an array of y coordinates
	 * from the array of points used in making the element shape, and passes them
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
		
		brush.fillPolygon(xCoords, yCoords, 8);
		
	}
	
	/**
	 * The method increments the y coordinate of the boulder by 7
	 */
	
	public void fall() {
		
		super.position.setY(super.position.getY() + 7);
		
	}
	
	/**
	 * The method updates the rotation of the element passed in as parameter 
	 * by 30 degrees
	 * 
	 * @param b
	 */
	
	public void spin(Boulder b) {
		
		b.rotation+=30;
	
	}
	
	/**
	 * 
	 * This is a private static inner class which for our project stores information
	 * about the shape of boulder in an array of Points, which can be accessed 
	 * by our boulder class since this is an inner class.
	 * 
	 * Creating is as a private class helped us to limit access of this class 
	 * from outside the class and we made the class static since we are keeping 
	 * the shape of the boulder same and not changing it, and secondly, we are not accessing 
	 * any instance variables in this class.
	 * 
	 */
	
	private static class PointsOfBoulder {
		
		public static Point[] pointsOfBoulder = {new Point(25, 0), new Point(7.5, 10),
				new Point(0, 30), new Point(7.5, 50), new Point(25, 60), new Point(42.5, 50),
				new Point(50, 30),  new Point(42.5, 10)};

		/**
		 * 
		 * This is a get method for our pointsOfBoulder array
		 * 
		 */
		
		public static Point[] getPointsOfBoulder() {
			return pointsOfBoulder;
		}
		
	}

}
