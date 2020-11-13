/**
 * Represents 2 dimensional  points.
 * Author : EBP
 * Version : 6/11/2020
 *
 */

public class Point
{
    private double _radius;
    private double _alpha;
    /**
     * Constructor for objects of class Point. 
     * Construct a new point with the specified x y coordinates. 
     * If the x coordinate is negative it is set to zero. 
     * If the y coordinate is negative it is set to zero. 
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Point(double x, double y)   //  wdifsoifuosirt
    {
    }

    /**
     * Constructor for objects of class Point. 
     * @param other The point from  which to construct the new object
     */
    public Point(Point other) 
    {
    }

    /**
     * This method returns the x coordinate of the point.
     * @return The x coordinate of the point
     */
    public double getX() 
    {
        return 0.0;
    }

    /**
     * This method returns the y coordinate of the point.
     * @return The y coordinate of the point
     */
    public double getY() 
    {
        return 0.0;
    }

    /**
     * This method sets the x coordinate of the point. 
     * If the new x coordinate is negative the old x coordinate will remain unchanged. 
     * @param x The new x coordinate
     */
    public void setX (double x) 
    {
    }

    /**
     * This method sets the y coordinate of the point. 
     * If the new y coordinate is negative the old y coordinate will remain unchanged. 
     * @param y The new y coordinate
     */
    public void setY (double y) 
    {
    }

    /**
     * Check if the given point is equal to this point.
     * @param other The point to check equality with
     * @return True if the given point is  equal to this point
     */
    public boolean equals(Point other) 
    {
        return false;
    }

    /**
     * Check if this point is  above a received  point.
     * @param other The point to check if this point is above
     * @return True if this point is above the other point
     */
    public boolean isAbove(Point other) 
    {
        return false;
    }

    /**
     * Check if this point is  below a received  point.
     * @param other The point to check if this point is below
     * @return True if this point is below the other point
     */
    public boolean isUnder(Point other) 
    {
        return false;
    }

    /**
     * Check if this point is  left of a received  point.
     * @param other The point to check if this point is left of
     * @return True if this point is left of the other point
     */
    public boolean isLeft(Point other) 
    {
        return false;
    }

    /**
     * Check if this point is  right of a received  point.
     * @param other The point to check if this point is right of
     * @return True if this point is right of the other point
     */
    public boolean isRight(Point other) 
    {
        return false;
    }

    /**
     * Check the distance between this point and a given point.
     * @param other The point to check the distance from
     * @return  The distance
     */
    public double distance(Point other) 
    {
        return 0.0;
    }

    /**
     * Moves a point.  
     * If either coordinate becomes negative the point remains unchanged.
     * @param dx The  difference  to add to  x
     * @param dy The difference  to add to y
     */
    public void move(double dx,double dy) 
    {
    }

    /**
     * Returns a string representation of Point in the format (x,y).
     * @return A String representation of the Point 
     */
    public String toString()
    {
        return "";
    }
}