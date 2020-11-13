/**
 * 
 * Segment1 represents a line (parallel to the x-axis) using two Points. 
 * Author :
 * Version :
 *
 */
public class Segment1 
{
    private Point _poLeft;
    private Point _poRight;

    /**
     * Constructs a new segment using 4 specified x y coordinates:
     * Two coordinates for the left point and two coordinates for the right point. 
     * If the y coordinates are different, change the y of the right point  to be equal to the y of the left point. 

     * @param leftX X value of left point
     * @param leftY Y value of left point
     * @param rightX X value of right point
     * @param rightY Y value of right point
     */
    public Segment1(double leftX ,double leftY, 
    double rightX ,double rightY)
    {
    }

    /**
     * Constructs a new segment using two Points. 
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment1 (Point left, Point right)
    {
    }

    /**
     * Copy Constructor. 
     * @param other the reference segment
     */
    public Segment1 (Segment1 other)
    {
    }

    /**
     * Returns the left point of the segment.
     * @return The left point of the segment
     */
    public Point getPoLeft()
    {
        return null;
    }

    /**
     * Returns the right point of the segment.
     * @return The right point of the segment
     */
    public Point getPoRight()
    {
        return null;
    }

    /**
     * Returns the segment length.
     * @return The segment length
     */
    public double getLength()
    {
        return 0.0;
    }

    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @return String representation of this segment
     */
    public String toString()
    {
        return "";
    }

    /**
     * Check if the reference segment is equal to this segment.
     * @param other the reference segment
     * @return True if the reference segment is equal to this segment 
     */
    public boolean equals (Segment1 other)
    {
        return false;
    }

    /**
     * Check if this segment is  above a reference segment.
     * @param other the reference segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove (Segment1 other) 
    {
        return false;
    }

    /**
     * Check if this segment is  under a reference segment.
     * @param other the reference segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder (Segment1 other)  
    {
        return false;
    }

    /**
     * Check if this segment is  left of  a received  segment.
     * @param other the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft (Segment1 other)  
    {
        return false;
    }

    /**
     * Check if this segment is  right of  a received  segment.
     * @param other the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight (Segment1 other)  
    {
        return false;
    }

    /**
     * Move the segment horizontally by delta.
     * @param delta the displacement size
     */
    public void moveHorizontal (double delta) 
    {
    }

    /**
     * Move the segment vertically by delta.
     * @param delta the displacement size
     */
    public void moveVertical (double delta)  
    {
    }

    /**
     * Change the segment size by moving the right point by delta. 
     * Will be implemented only for a valid delta: only if the new right point remains the right point.
     * 
     * @param delta the length change 
     */
    public void changeSize (double delta)  
    {
    }

    /**
     * Check if a point is located on the segment.
     * @param p a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment (Point p) 
    {
        return false;
    }

    /**
     * Check if this segment is bigger than a reference segment.
     * @param other the reference segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger (Segment1 other)
    {
        return false;
    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap (Segment1 other)
    {
        return 0.0;
    }

    /**
     * Compute the trapeze perimeter, which is constructed by this segment and a reference segment.
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter (Segment1 other) 
    {
        return 0.0;
    }
}
