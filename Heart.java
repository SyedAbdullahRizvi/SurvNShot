package game;

import java.awt.Graphics;

/**
 * The Heart class containg the inner class Points of Heart that defines what 
 * the heart shape polygon should look like.It also has a paint method defined 
 * that displays the heart on the screen.
 * 
 * @author Syed Abdullah Rizvi, Ashmit Bakul Palwe
 * 
 */

public class Heart extends Polygon{
	
	private static Heart.PointsOfHeart heartPoints = new Heart.PointsOfHeart();
	
	private static Point[] pointsOfShape = heartPoints.pointsOfHeart;


	/**
	 * 
	 * This method constructs a Heart element using the Superclass constructor 
	 * @param inPosition
	 * @param inRotation
	 * 
	 */
	
	public Heart( Point inPosition, double inRotation) {
		super(pointsOfShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The method to display the heart on the screen during the 
	 * game. It creates an array of x-coordinates and an array of y-coordinates
	 * from the array of points used in making the heart shape, and passes them
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
	 * 
	 * This is a private static inner class which for our project stores information
	 * about the shape of heart in an array of Points, which can be accessed 
	 * by our heart class since this is an inner class.
	 * 
	 * 
	 * Creating is as a private class helped us to limit access of this class 
	 * from outside the class and we made the class static since we are keeping 
	 * the shape of the heart same and not changing it, and secondly, we are not accessing 
	 * any instance variables in this class.
	 * 
	 */
	
	private static class PointsOfHeart {
		
		public static Point[] pointsOfHeart = {new Point(25, 10), new Point(20,5),
				new Point(15, 0), new Point(10,5), new Point(5, 10), new Point(15,22.5),
				new Point(25, 35), new Point(35,22.5), new Point(45, 10), new Point(40,5),
				new Point(35, 0), new Point(30,5)};

		/**
		 * 
		 * This is a get method for our pointsOfHeart array
		 * 
		 */
		
		public static Point[] getPointsOfHeart() {
			return pointsOfHeart;
		}
		
	}

}
