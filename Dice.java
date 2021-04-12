import java.lang.Math;
/**
*Dice Class which manages the varioius random number generated tasks all methods are static
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class Dice 
{

    /**
    *Method which represents a standard six sided dice & returns a number uniformly distributed number between one and six
    *
    *@return random a number uniformly distributed number between one and six
    */
    public static int d6() 
    {
        return random(1,6); 
    }

    /**
    *Method which returns a number uniformly distributed between 1 and 100 used to represent probabilities
    *
    *@return returns a number uniformly distributed between 1 and 100
    */
    public static int d100()
    {
        return random(1, 100); 
    }

    /**
    *Method which returns the sum of two d6() methods reconstructing the probability table provided by two dice
    *We need two dice because the probability that we roll various numbers are not evenly distributed 2 - 12
    *p2 & p12 = 1/36 p3 & p11 = 1/18 p4 & p10 = 1/12 p5 & p9 = 1/9 p6 & p8 = 5/36 p7 = 1/6
    *
    */
    public static int twoD6() 
    {
        return d6() + d6();
    }

    /**
    *Method which does the work for all the other methods in the Dice class 
    *returns a uniformly distributed number between the min and max parameters passed as arguments
    *
    *@param min an int which defines the lower bound of the numbers returned
    *@param max an int which defines the upper bound of the numbers returned
    */
    public static int random(int min, int max)
    {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
