/**
*Validation Class which manages the validation of inputs for the program
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class Validation
{

    /**
    *Method which verifies that the String passed is not blank
    *
    *@param s a String that is checked by the method
    *@return boolean to declare if the String s is blank or not
    */
    public static boolean isBlank(String s)
    {
        if (s.trim().equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
    *Method to check if String is between the min and max values 
    *
    *@param     s       a String which is to be checked
    *@param     min     a minimum value which the string must be longer than
    *@param     max     a maximum value which the string must be shorter than
    *@return    boolean which states whether s is between min and max 
    */
    public static boolean lengthWithinRange(String s, int min, int max)
    {
        if ((s.trim().length() >= min) && (s.trim().length() <= max))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
    *Method which verifies the choice of difficulty level entered by the user
    *
    *@param mode a String that is checked by the method
    *@return boolean to declare if the String mode a valid selection or not
    *@throws IllegalArgumentException
    */
    public static boolean validDifficulty(String mode) throws IllegalArgumentException
    {
        boolean validMode = false;
        if (mode == null)
        {
            throw new IllegalArgumentException("Selection cannot be Null");
        }
        else if (mode.trim().length() == 0)
        {
            throw new IllegalArgumentException("Selection cannot be blank");
        }
        else if (mode.equals("EASY") | mode.equals("MEDIUM") | mode.equals("HARD"))
        {
            validMode = true;
        }
        else
        {
            throw new IllegalArgumentException ("Selection MUST be one of: Easy, Medium, or Hard");
        }
        return validMode;
    }
}
