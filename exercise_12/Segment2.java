/**
 * 
 * Segment2 represents a line (parallel to the x-axis) using a center point and length. 
 * Author : Ariel Szabo
 * Version : 13/11/2020
 *
 */
public class Segment2 
{
    private Point _poCenter;
    private double _length;
    private static final double FLOATING_POINT_PRECISION_ACCEPTED_DIFFERANCE = 0.01;

    /**
     * Constructs a new segment using a center point and the segment length.
     * @param poCenter the Center Point
     * @param length the segment length
     */
    public Segment2 (Point poCenter, double length)
    {
        _poCenter = new Point(poCenter);
        _length = length;
    }

    /**
     * Constructs a new segment using two Points. 
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment2 (Point left, Point right)
    {
        // we assume the segment is parallel to the X axes so the length is the x axis diff
        _length = right.getX() - left.getX();

        // If the right point's Y axes is different from the left point's Y axes we would update it to be like the left.
        double centerY = left.getY();
        double centerX = left.getX() + _length / 2.0;
        _poCenter = new Point(centerX, centerY);
    }

    /**
     * Constructs a new segment using 4 specified x y coordinates:
     * two coordinates for the left point and two coordinates for the right point. 
     * @param leftX X value of left point
     * @param leftY Y value of left point
     * @param rightX X value of right point
     * @param rightY Y value of right point
     */
    public Segment2(double leftX ,double leftY, double rightX ,double rightY)
    {
        // we assume the segment is parallel to the X axes so the length is the x axis diff
        _length = rightX - leftX;

        double centerX = leftX + _length / 2.0;

        // If the right point's Y axes is different from the left point's Y axes we would update it to be like the left.
        _poCenter = new Point(centerX, leftY);
    }

    /**
     * Copy Constructor. 
     * Construct a segment using a reference segment.
     * @param other the reference segment
     */
    public Segment2 (Segment2 other)
    {
        _length = other._length;
        _poCenter = new Point(other._poCenter);
    }

    /**
     * Returns the left point of the segment.
     * @return The left point of the segment
     */
    public Point getPoLeft()
    {
        double centerX = _poCenter.getX();
        double leftX = centerX - _length / 2.0;
        Point poLeft = new Point(leftX, _poCenter.getY());
        return poLeft;
    }

    /**
     * Returns the right point of the segment.
     * @return The right point of the segment
     */
    public Point getPoRight()
    {
        double centerX = _poCenter.getX();
        double rightX = centerX + _length / 2.0;
        Point poRight = new Point(rightX, _poCenter.getY());
        return poRight;

    }

    /**
     * Returns the segment length.
     * @return The segment length
     */
    public double getLength()
    {
        return _length;
    }

    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @return String representation of this segment
     */
    public String toString()
    {   
        Point poLeft = this.getPoLeft();
        Point poRight = this.getPoRight();
        return poLeft + "---" + poRight;
    }

    /**
     * Check if the reference segment is equal to this segment.
     * @param other the reference segment
     * @return True if the reference segment is equal to this segment
     */
    public boolean equals (Segment2 other)
    {
        return this._poCenter.equals(other._poCenter) && this._length == other._length;
    }

    /**
     * Check if this segment is  above a reference segment.
     * @param other the reference segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove (Segment2 other)
    {
        return this._poCenter.isAbove(other._poCenter);
    }

    /**
     * Check if this segment is  under a reference segment.
     * @param other the reference segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder (Segment2 other)
    {
        return other.isAbove(this);
    }

    /**
     * Check if this segment is  left of  a received  segment.
     * @param other the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft (Segment2 other)
    {
        // true only if all the segment is left of other
         return this.getPoRight().isLeft(other.getPoLeft());
    }

    /**
     * Check if this segment is  right of  a received  segment.
     * @param other the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight (Segment2 other) 
    {
        // true only if all the segment is right of other
        return this.getPoLeft().isRight(other.getPoRight());
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
        boolean isRemainingInFirstQuadrant = this.getPoLeft().getX() + delta >= 0;
        if ( isRemainingInFirstQuadrant  )
        {
            _poCenter.move(delta, 0 );
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
        boolean isRemainingInFirstQuadrant = this.getPoLeft().getY() + delta >= 0;

        if ( isRemainingInFirstQuadrant )
        {
            _poCenter.move(0 , delta);
        }
    }
    /**
     * Change the segment size by moving the right point by delta. 
     * @param delta the length change 
     */
    public void changeSize (double delta)
    {
        Point poLeft = this.getPoLeft();

        Point poRight = this.getPoRight();
        poRight.move(delta, 0);

        if ( poRight.getX() >= poLeft.getX() ) {
            Segment2 newTemporarySegment = new Segment2(poLeft, poRight);

            this._poCenter = new Point(newTemporarySegment._poCenter);
            this._length = newTemporarySegment._length;
        }

    }

    /**
     * Check if a point is located on the segment.
     * @param p a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment (Point p)
    {
        Point poLeft = this.getPoLeft();
        Point poRight = this.getPoRight();

        boolean isRightOrEqualToLeftPoint = p.isRight(poLeft) || p.equals(poLeft);
        boolean isLeftOrEqualToRightPoint = p.isLeft(poRight) || p.equals(poRight);

        // using this approximation to handle floating-point error
        boolean haveSameYAxis = Math.abs(p.getY() - poLeft.getY()) < FLOATING_POINT_PRECISION_ACCEPTED_DIFFERANCE;

        return isRightOrEqualToLeftPoint && isLeftOrEqualToRightPoint && haveSameYAxis ;
    }

    /**
     * Check if this segment is bigger than a reference segment.
     * @param other the reference segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger (Segment2 other)
    {
        return this._length > other._length;
    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap (Segment2 other)
    {
        double thisLeftX = this.getPoLeft().getX();
        double thisRightX = this.getPoRight().getX();
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
     * Compute the trapeze perimeter, which constructed by this segment and a reference segment.
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter (Segment2 other)
    {
        double rightPointsDistance = this.getPoRight().distance(other.getPoRight());
        double leftPointsDistance  = this.getPoLeft().distance(other.getPoLeft());

        double perimeter = leftPointsDistance + this.getLength() + rightPointsDistance + other.getLength();
        return perimeter;
    }
}
