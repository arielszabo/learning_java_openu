/**
 * Polygon represented by an array of vertex points.
 * Author : Ariel Szabo
 * Version : 06/12/2020
 *
 */

public class Polygon {

    private Point[] _vertices;
    private int _noOfVertices;
    private final static int MAX_VERTICES = 10;


    /**
     * Constructor for objects of class Polygon.
     * Construct a new empty polygon.
     */
    public Polygon() {
        this._vertices = new Point[MAX_VERTICES];
        this._noOfVertices = 0;
    }

    /**
     * Adds a new point to the polygon vertices list at it's end.
     * @param x the X coordinate of the new Point
     * @param y the Y coordinate of the new Point
     * @return true if the point was added and false if no space found for the point in the vertices list
     */
    public boolean addVertex(double x, double y) {
        if (this._noOfVertices == MAX_VERTICES) {
            return false;
        } else {
            this._vertices[this._noOfVertices] = new Point(x, y);
            this._noOfVertices++;
            return true;
        }
    }

    /**
     * Get the first highest vertex in the polygon.
     * @return the highest vertex
     */
    public Point highestVertex() {
        Point highestVertex = null;
        for (int i = 0; i < this._noOfVertices; i++) {
            Point vertex = this._vertices[i];
            if (!(highestVertex instanceof Point)){
                highestVertex = vertex;
            } else if (highestVertex.isUnder(vertex)) {
                highestVertex = vertex;
            }
        }
        return highestVertex;
    }

    /**
     * Returns a string representation of the polygon's points.
     * @return The polygon's string representation
     */
    public String toString() {
        if (this._noOfVertices == 0) {
            return "The polygon has 0 vertices.";

        } else {

            String string_ = "The polygon has " + this._noOfVertices + " vertices:\n";
            string_ += "(";  // the opening parentheses
            for (int i = 0; i < this._noOfVertices; i++) {
                if (i != 0){  // add a separating comma before each vertex but the first one.
                    string_ += ",";
                }
                string_ += this._vertices[i];
            }
            string_ += ")";  // the closing parentheses
            return string_;
        }
    }

    /**
     * Calculate the polygon's perimeter.
     * @return the calculated polygon's perimeter
     */
    public double calcPerimeter() {
        if (this._noOfVertices < 2) {
            return 0;
        }

        double perimeter = 0;

        // iterate over the vertices (starting with the 2nd vertex) and calculate the distance to the previous vertex.
        Point prevVertex = this._vertices[0];
        for (int i = 1; i < this._noOfVertices; i++) {
            Point vertex = this._vertices[i];
            double distanceFromPrevVertex = prevVertex.distance(vertex);
            perimeter += distanceFromPrevVertex;
            prevVertex = vertex;

        }

        if (this._noOfVertices == 2) { // if only 2 vertices there is no need to close the Polygon's perimeter
            return perimeter;
        }

        // Close the Polygon's perimeter by adding the distance of the last vertex to the first one to
        Point firstVertex = this._vertices[0];
        Point LastVertex = this._vertices[this._noOfVertices - 1];
        double firstToLastVertexDistance = firstVertex.distance(LastVertex);
        perimeter += firstToLastVertexDistance;

        return perimeter;
    }

    /**
     * Calculates the polygon's area.
     * @return the polygon's area
     */
    public double calcArea() {
        if (this._noOfVertices < 3) {
            return 0;
        }
        double area = 0;
        Point originVertex = this._vertices[0];
        for (int j = 1; j < this._noOfVertices - 1; j++){
            Point vertex2 = this._vertices[j];
            Point vertex3 = this._vertices[j + 1];

            double triangleArea = getTriangleArea(originVertex, vertex2, vertex3);
            area += triangleArea;
        }

        return area;
    }

    private double getTriangleArea(Point vertex1, Point vertex2, Point vertex3) {
        // Heron's formula states that the area of a triangle whose sides have lengths a, b, and c is:
        // (s(s-a)(s-b)(s-c))^0.5
        // where s is half of the perimeter (semi perimeter).
        double edge1Length = vertex1.distance(vertex2);
        double edge2Length = vertex1.distance(vertex3);;
        double edge3Length = vertex2.distance(vertex3);
        double perimeter = edge1Length + edge2Length + edge3Length;
        double semiPerimeter = perimeter / 2.0;
        return Math.sqrt(
                semiPerimeter *
                        (semiPerimeter - edge1Length) * (semiPerimeter - edge2Length) * (semiPerimeter - edge3Length)
        );
    }

    /**
     * Determines if this Polygon's area is bigger than the other polygon's area.
     * @param other Polygon to compare
     * @return true if this polygon has bigger area false otherwise
     */
    public boolean isBigger(Polygon other) {
        return this.calcArea() > other.calcArea();
    }

    /**
     * This method finds if a point is one of the polygon's vertices and returns it's index.
     * @param p Point to find in the polygon's vertices
     * @return the index of the point if found, if not -1
     */
    public int findVertex(Point p) {
        for (int i = 0; i < this._noOfVertices; i++) {
            Point vertex = this._vertices[i];
            if (p.equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find and return the next vertex in the polygon.
     * if the point is not found returns null.
     * if the only point is the one given, returns the point itself.
     *
     * @param p point used to find the next one in the polygon's vertices.
     * @return the next vertex in the polygon if exists, null if not found, and the point itself if it's the only one.
     */
    public Point getNextVertex(Point p) {
        int vertexIndex = this.findVertex(p);
        if (vertexIndex == -1) { // vertex not found
            return null;

        } else if (vertexIndex == this._noOfVertices - 1) { // it's the last vertex in the array
            return new Point(this._vertices[0]);

        } else if (vertexIndex == 0 && this._noOfVertices == 1) { // it is the only vertex
            return new Point(this._vertices[0]);

        } else {
            Point nextVertex = this._vertices[vertexIndex + 1];
            return new Point(nextVertex);
        }

    }

    /**
     * Creates the polygon's bounding box polygon object if there are at least 3 vertices.
     * The bounding box is parallel to the X and Y axes.
     *
     * @return the polygon's bounding box polygon object or null if the polygon has less than 3 vertices.
     */
    public Polygon getBoundingBox() {
        if (this._noOfVertices < 3) {
            return null;
        }


        double minimumX = 10000; //
        double minimumY = 10000;
        double maxX = 0;
        double maxY = 0;
        // find the highest and lowest X and Y coordinates:
        // TODO: use Math.max / Math.min
        for (int i = 0; i < this._noOfVertices; i++) {
            Point vertex = this._vertices[i];
            double vertexX = vertex.getX();
            double vertexY = vertex.getY();

            if (vertexX < minimumX) {
                minimumX = vertexX;
            }

            if (vertexX > maxX) {
                maxX = vertexX;
            }


            if (vertexY < minimumY ) {
                minimumY = vertexY;
            }

            if (vertexY > maxY) {
                maxY = vertexY;
            }
        }

        Polygon boundingBox = new Polygon();
        boundingBox.addVertex(minimumX, minimumY);  // left lower corner
        boundingBox.addVertex(maxX, minimumY);     // right lower corner
        boundingBox.addVertex(maxX, maxY);        // right upper corner
        boundingBox.addVertex(minimumX, maxY);   // left upper corner

        return boundingBox;

    }

}
