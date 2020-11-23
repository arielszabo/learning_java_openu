/**
 * 
 * Segment1 represents a line (parallel to the x-axis) using two Points. 
 * Author : Ariel Szabo
 * Version : 13/11/2020
 *
 */
public class Segment1 
{
    private Point _poLeft;
    private Point _poRight;
    private static final double FLOATING_POINT_PRECISION_ACCEPTED_DIFFERANCE = 0.01;

    /**
     * Constructs a new segment using 4 specified x y coordinates:
     * Two coordinates for the left point and two coordinates for the right point. 
     * If the y coordinates are different, change the y of the right point  to be equal to the y of the left point. 

     * @param leftX X value of left point
     * @param leftY Y value of left point
     * @param rightX X value of right point
     * @param rightY Y value of right point
     */
    public Segment1(double leftX ,double leftY, double rightX ,double rightY)
    {
        _poLeft = new Point(leftX, leftY);
        _poRight = new Point(rightX, leftY); // using the leftY, instead of updating the rightY if it's different from leftY
    }

    /**
     * Constructs a new segment using two Points. 
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment1 (Point left, Point right)
    {
        _poLeft = new Point(left);
        _poRight = new Point(right);

        // updating right point's Y axes now is equivalent to updating only if different from the left point's Y axes
        _poRight.setY(_poLeft.getY());
    }

    /**
     * Copy Constructor. 
     * @param other the reference segment
     */
    public Segment1 (Segment1 other)
    {
        _poLeft = new Point(other._poLeft);
        _poRight = new Point(other._poRight);
    }

    /**
     * Returns the left point of the segment.
     * @return The left point of the segment
     */
    public Point getPoLeft()
    {
        return new Point(_poLeft);
    }

    /**
     * Returns the right point of the segment.
     * @return The right point of the segment
     */
    public Point getPoRight()
    {
        return new Point(_poRight);
    }

    /**
     * Returns the segment length.
     * @return The segment length
     */
    public double getLength()
    {
        return _poRight.distance(_poLeft);
    }

    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @return String representation of this segment
     */
    public String toString()
    {
        return _poLeft + "---" + _poRight;
    }

    /**
     * Check if the reference segment is equal to this segment.
     * @param other the reference segment
     * @return True if the reference segment is equal to this segment 
     */
    public boolean equals (Segment1 other)
    {
        return this._poRight.equals(other._poRight) && this._poLeft.equals(other._poLeft);
    }

    /**
     * Check if this segment is  above a reference segment.
     * @param other the reference segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove (Segment1 other) 
    {
        if (this._poLeft.isAbove(other._poLeft) && this._poRight.isAbove(other._poRight)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if this segment is  under a reference segment.
     * @param other the reference segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder (Segment1 other)  
    {
        return other.isAbove(this);
    }

    /**
     * Check if this segment is  left of  a received  segment.
     * @param other the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft (Segment1 other)  
    {
        // true only if all the segment is left of other
        return this._poRight.isLeft(other._poLeft);
    }

    /**
     * Check if this segment is  right of  a received  segment.
     * @param other the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight (Segment1 other)  
    {
        // true only if all the segment is right of other
        return this._poLeft.isRight(other._poRight);
    }

    /**
     * Move the segment horizontally by delta.
     * @param delta the displacement size
     */
    public void moveHorizontal (double delta) 
    {
        // There is only need to check whether the left point passes the 0 or not. If it not then neither the right
        // and if it is than the moment is not allowed ( it doesn't mather if the right point did)

        // Movement is allowed only if coordinates remain in the first quadrant.
        boolean isRemainingInFirstQuadrant = _poLeft.getX() + delta >= 0;

        if ( isRemainingInFirstQuadrant  )
        {
            _poRight.move(delta, 0);
            _poLeft.move(delta, 0);
        }
    }

    /**
     * Move the segment vertically by delta.
     * @param delta the displacement size
     */
    public void moveVertical (double delta)  
    {
        // There is only need to check whether one of points passes the 0 or not because we assume they are
        // parallel to the X axes

        // movement is allowed only if coordinates remain in the first quadrant.
        boolean isRemainingInFirstQuadrant = _poLeft.getY() + delta >= 0;

        if ( isRemainingInFirstQuadrant )
        {
            _poRight.move(0, delta);
            _poLeft.move(0, delta);
        }
    }


    /**
     * Change the segment size by moving the right point by delta. 
     * Will be implemented only for a valid delta: only if the new right point remains the right point.
     * 
     * @param delta the length change 
     */
    public void changeSize (double delta)  
    {
        boolean isMovementAllowed = _poRight.getX() + delta >= _poLeft.getX();
        if ( isMovementAllowed ){
            _poRight.move(delta, 0);
        }
    }

    /**
     * Check if a point is located on the segment.
     * @param p a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment (Point p) 
    {
        boolean isRightOrEqualToLeftPoint = p.isRight(_poLeft) || p.equals(_poLeft);
        boolean isLeftOrEqualToRightPoint = p.isLeft(_poRight) || p.equals(_poRight);

        // using the following approximation to handle floating-point error (instead of p.getY() == _poLeft.getY()):
        boolean haveSameYAxis = Math.abs(p.getY() - _poLeft.getY()) < FLOATING_POINT_PRECISION_ACCEPTED_DIFFERANCE;

        return isRightOrEqualToLeftPoint && isLeftOrEqualToRightPoint && haveSameYAxis ;
    }

    /**
     * Check if this segment is bigger than a reference segment.
     * @param other the reference segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger (Segment1 other)
    {
        return this.getLength() > other.getLength();
    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap (Segment1 other) {
        double thisLeftX = this._poLeft.getX();
        double thisRightX = this._poRight.getX();
        double otherLeftX = other.getPoLeft().getX();
        double otherRightX = other.getPoRight().getX();


        if (otherLeftX <= thisLeftX && thisRightX <= otherRightX) {
            // this:     l____r
            // other: l__________r
            return thisRightX - thisLeftX;

        else if (otherLeftX <= thisLeftX && thisRightX > otherRightX) {
            // this:     l_____r
            // other: l_____r
            return otherRightX - thisLeftX;

        } else if (thisLeftX <= otherLeftX && otherRightX <= thisRightX) {
            // this: l__________r
            // other:   l____r
            return otherRightX - otherLeftX;

        } else if ( thisLeftX <= otherLeftX && otherRightX > thisRightX ) {
            // this: l_____r
            // other:   l____r
            return thisRightX - otherLeftX;

        } else {
            //  l_____r  l_____r
            return 0.0;
        }
    }

    /**
     * Compute the trapeze perimeter, which is constructed by this segment and a reference segment.
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter (Segment1 other) 
    {
        double rightPointsDistance = this._poRight.distance(other._poRight);
        double leftPointsDistance  = this._poLeft.distance(other._poLeft);

        double perimeter = leftPointsDistance + this.getLength() + rightPointsDistance + other.getLength();
        return perimeter;
    }
}
