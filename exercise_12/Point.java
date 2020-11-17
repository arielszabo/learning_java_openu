/**
 * Represents 2 dimensional  points.
 * Author : Ariel Szabo
 * Version : 13/11/2020
 *
 */

public class Point
{
    private double _radius;
    private double _alpha;

    private final static double MINIMAL_CARTESIAN_SYSTEM_VALUE = 0.0;
    private final static double DEFAULT_CARTESIAN_SYSTEM_VALUE = 0.0;
    private final static double RADIANS_TO_DEGREES_COEFFICIENT = 180.0 / Math.PI;
    private final static double DEGREES_TO_RADIANS_COEFFICIENT = 1.0 / RADIANS_TO_DEGREES_COEFFICIENT;
    private final static double ROUND_PRECISION_MULTIPLIER = 10000.0;
    private final static double DEFAULT_ALPHA_IN_DEGREES = 90.0;

    /**
     * Constructor for objects of class Point. 
     * Construct a new point with the specified x y coordinates. 
     * If the x coordinate is negative it is set to zero. 
     * If the y coordinate is negative it is set to zero. 
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Point(double x, double y)
    {
        if (x < MINIMAL_CARTESIAN_SYSTEM_VALUE) {
            x = DEFAULT_CARTESIAN_SYSTEM_VALUE;
        }

        if (y < MINIMAL_CARTESIAN_SYSTEM_VALUE) {
            y = DEFAULT_CARTESIAN_SYSTEM_VALUE;
        }
        
        _radius = this.getRadius(x, y);
        _alpha = this.getAlphaInDegrees(x, y);

    }

    /**
     * Constructor for objects of class Point. 
     * @param other The point from  which to construct the new object
     */
    public Point(Point other) 
    {
        _radius = other._radius;
        _alpha = other._alpha;
    }

    private double getAlphaInDegrees(double x, double y) 
    {

        if (x == MINIMAL_CARTESIAN_SYSTEM_VALUE){
            return DEFAULT_ALPHA_IN_DEGREES;
        }
        double alphaInRadians = Math.atan(y / x);
        double alphaInDegrees = alphaInRadians * RADIANS_TO_DEGREES_COEFFICIENT;
        return alphaInDegrees;
    }

    private double getAlphaInRadians()
    {
        return this._alpha * DEGREES_TO_RADIANS_COEFFICIENT;
    }

    private double getRadius(double x, double y)
    {
        // using distance based on pythagoras theorem with (0,0) as the other point
        return Math.sqrt(x * x + y * y);
    }
    

    /**
     * This method returns the x coordinate of the point.
     * @return The x coordinate of the point
     */
    public double getX() 
    {
        // cosine alpha is the ratio between the adjacent side and the hypotenuse.     
        double alphaInRadians = this.getAlphaInRadians();  // Math.cos expects degree in radians
        double x = Math.cos(alphaInRadians) * this._radius;
        return x;
    }

    /**
     * This method returns the y coordinate of the point.
     * @return The y coordinate of the point
     */
    public double getY() 
    {
        // sine alpha it is the ratio of the length of the side that is opposite that angle, to the length of the hypotenuse. 
        double alphaInRadians = getAlphaInRadians(); // Math.cos expects degree in radians
        double y = Math.sin(alphaInRadians) * this._radius;
        return y;
    }

    /**
     * This method sets the x coordinate of the point. 
     * If the new x coordinate is negative the old x coordinate will remain unchanged. 
     * @param x The new x coordinate
     */
    public void setX (double x) 
    {
        if (x >= MINIMAL_CARTESIAN_SYSTEM_VALUE) {
            double y = this.getY();
            _radius = this.getRadius(x, y);
            _alpha = this.getAlphaInDegrees(x, y);
        }
    }

    /**
     * This method sets the y coordinate of the point. 
     * If the new y coordinate is negative the old y coordinate will remain unchanged. 
     * @param y The new y coordinate
     */
    public void setY (double y) 
    {
        if (y >= MINIMAL_CARTESIAN_SYSTEM_VALUE) {
            double x = this.getX();
            _radius = this.getRadius(x, y);
            _alpha = this.getAlphaInDegrees(x, y); 
        }
    }

    /**
     * Check if the given point is equal to this point.
     * @param other The point to check equality with
     * @return True if the given point is  equal to this point
     */
    public boolean equals(Point other) 
    {
        return this._alpha == other._alpha && this._radius == other._radius;
    }

    /**
     * Check if this point is  above a received  point.
     * @param other The point to check if this point is above
     * @return True if this point is above the other point
     */
    public boolean isAbove(Point other) 
    {
        return this.getY() > other.getY();
    }

    /**
     * Check if this point is  below a received  point.
     * @param other The point to check if this point is below
     * @return True if this point is below the other point
     */
    public boolean isUnder(Point other) 
    {
        return other.isAbove(this);
    }

    /**
     * Check if this point is  left of a received  point.
     * @param other The point to check if this point is left of
     * @return True if this point is left of the other point
     */
    public boolean isLeft(Point other) 
    {
        return this.getX() < other.getX();
    }

    /**
     * Check if this point is  right of a received  point.
     * @param other The point to check if this point is right of
     * @return True if this point is right of the other point
     */
    public boolean isRight(Point other) 
    {
        return other.isLeft(this);
    }

    /**
     * Check the distance between this point and a given point.
     * @param other The point to check the distance from
     * @return  The distance
     */
    public double distance(Point other) 
    {
        double xDiffInSecondPower = Math.pow(this.getX() - other.getX(), 2);
        double yDiffInSecondPower = Math.pow(this.getY() - other.getY(), 2);
        double pointsDistance = Math.sqrt(xDiffInSecondPower + yDiffInSecondPower);
        return pointsDistance;
    }

    /**
     * Moves a point.  
     * If either coordinate becomes negative the point remains unchanged.
     * @param dx The  difference  to add to  x
     * @param dy The difference  to add to y
     */
    public void move(double dx,double dy) 
    {
        double newX = this.getX() + dx;
        double newY = this.getY() + dy;
        if (newX >= MINIMAL_CARTESIAN_SYSTEM_VALUE && newY >= MINIMAL_CARTESIAN_SYSTEM_VALUE) {
            _radius = this.getRadius(newX, newY);
            _alpha = this.getAlphaInDegrees(newX, newY); 
        }
    }

    /**
     * Returns a string representation of Point in the format (x,y).
     * @return A String representation of the Point 
     */
    public String toString()
    {
        double x = this.roundNumber(this.getX());
        double y = this.roundNumber(this.getY());

        return "(" + x + "," + y + ")";
    }

    private double roundNumber(double number) {
        return Math.round(number * ROUND_PRECISION_MULTIPLIER) / ROUND_PRECISION_MULTIPLIER;
    }
}