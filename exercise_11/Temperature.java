/*
Converts temperature from one unit (celsius, fahrenheit and kelvin) to all three units.

By: Ariel Szabo
Date: 2020-10-30
*/
import java.util.Scanner;

public class Temperature
{
    public static void main (String [] args)
    {
        // define constants
        final double C_TO_K_INTERCEPT = 273.15;  // absolute zero
        final double F_TO_C_COEFFICIENT = 5 / 9.0;
        final double C_TO_F_COEFFICIENT = 1 / F_TO_C_COEFFICIENT;
        final int C_TO_F_INTERCEPT = 32;
        final int F_TO_C_INTERCEPT = - C_TO_F_INTERCEPT;


        // read input temperature value and identifier from user
        Scanner scan = new Scanner (System.in);
        System.out.println("Please enter a temperature unit identifying character and a value:");

        char unitChar = scan.next().charAt(0);
        double inputTemperature = scan.nextDouble(); 

        // define the temperature converting target variables
        double celsiusTemperature;
        double fahrenheitTemperature;
        double kelvinTemperature;

        if (unitChar == 'F') {
            fahrenheitTemperature = inputTemperature;
            celsiusTemperature = F_TO_C_COEFFICIENT * (fahrenheitTemperature + F_TO_C_INTERCEPT);
            kelvinTemperature = celsiusTemperature + C_TO_K_INTERCEPT;

        } else if (unitChar == 'K'){
            kelvinTemperature = inputTemperature;
            celsiusTemperature = kelvinTemperature - C_TO_K_INTERCEPT;
            fahrenheitTemperature = C_TO_F_COEFFICIENT  * celsiusTemperature + C_TO_F_INTERCEPT;
            
        } else { // unitChar is 'C'
            celsiusTemperature = inputTemperature;
            kelvinTemperature = celsiusTemperature + C_TO_K_INTERCEPT;
            fahrenheitTemperature = C_TO_F_COEFFICIENT  * celsiusTemperature + C_TO_F_INTERCEPT;

       }
        System.out.println(celsiusTemperature + " C");
        System.out.println(fahrenheitTemperature + " F");
        System.out.println(kelvinTemperature + " K");
    } // end of method main
} //end of class Temperature