/*
Given rectangle's left-upper point and right-bottom point coordinates,
calcualte it's excircle and the incircle radius, area and perimeter.

By: Ariel Szabo
Date: 2020-10-30
*/
import java.util.Scanner;

public class Circle
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the areas " +
            "and the perimeters of the excircle and the incircle " +
            "of a given rectangle ");
        System.out.print ("Please enter the two coordinates of the " +
            "left-upper point of the rectangle");
        int leftUpX = scan.nextInt();
        int leftUpY = scan.nextInt();
       
        System.out.print ("Please enter the two coordinates of the " +
            "right-bottom point of the rectangle");
        int rightBottomX = scan.nextInt();
        int rightBottomY = scan.nextInt();

        // Calculate incircle radius, area and perimeter
        // the incircle radius is half of the bounding rectangle height
        double incircleRadius =  (leftUpY - rightBottomY) / 2.0;
        double incircleArea =  Math.PI * Math.pow(incircleRadius, 2);
        double incirclePerimeter =  2 * Math.PI * incircleRadius;

        // Calculate excircle diameter using Pythagoras's theorem
        // d = ( (x1-x2)^2 + (y1-y2)^2 )^0.5
        // The bound rectangle diagonal is the excircle diameter
        double xDiffSecondPower = Math.pow(leftUpX - rightBottomX, 2);
        double yDiffSecondPower =  Math.pow(leftUpY - rightBottomY, 2);
        double excircleDiameter = Math.sqrt(xDiffSecondPower + yDiffSecondPower);        

        // Calculate excircle radius, area and perimeter
        double excircleRadius = excircleDiameter / 2.0;
        double excircleArea =  Math.PI * Math.pow(excircleRadius, 2);
        double excirclePerimeter =  2 * Math.PI * excircleRadius;

        System.out.println("Incircle: radius = " + incircleRadius + ", area = " + incircleArea + ", perimeter = " + incirclePerimeter);
        System.out.println("Excircle: radius = " + excircleRadius + ", area = " + excircleArea + ", perimeter = " + excirclePerimeter);
    } // end of method main
} //end of class Circle

