import java.util.Scanner;
/**
*Input Class which manages accepting user inputs for the program 
*
*@author Mitchell Hayes
*@since 25 November 2020
*@version 1.0
*/
public class Input
{

    /**
    *Constructor Method for objects of the Input class
    *
    */
    public Input()
    {
    }

    /**
    *Method to accept character inputs from user
    *
    *@param s a String which displays instructions for the user 
    *@param pos an int which specifies the position of the character in the users input
    */
    public char acceptCharInput(String s, int pos)
    {
        System.out.println(s);
        char output;
        Scanner input = new Scanner(System.in);
        try 
        {
            output = input.nextLine().trim().toLowerCase().charAt(pos);
        }
        catch (Exception e) 
        {
            System.out.println("an error occurred");
            output = 'e';
        }
        input.close();
        return output;
    }

    /**
    *Method to accept double inputs from user
    *
    *@param s a String which displays instructions for the user 
    */
    public double acceptDoubleInput(String s)
    {

        System.out.println(s);
        double output; 
        Scanner input = new Scanner(System.in);
        try 
        {
            output = Double.parseDouble(input.nextLine());
        }
        catch (Exception e) 
        {
            System.out.println("an error occurred");
            output = 0.0;
        }
        input.close();
        return output;
    }

    /**
    *Method to accept int inputs from user
    *
    *@param s a String which displays instructions for the user 
    */
    public int acceptIntegerInput(String s)
    {

        System.out.println(s);
        int output;

        Scanner input = new Scanner(System.in);
        try
        {
            output = Integer.parseInt(input.nextLine());
        }
        catch (Exception e) 
        {
            System.out.println("an error occurred");
            output = 0;
        }
        input.close();
        return output;
    }

    /**
    *Method to accept String inputs from user, exits program if user types quit
    *
    *@param s a String which displays instructions for the user 
    */
    public String acceptStringInput(String s)
    {
        System.out.println(s);
        String output = "";
        Scanner input = new Scanner(System.in);
        try
        {
            if (input.hasNextLine())
            {
                output = input.nextLine();
            }
            if (output.toLowerCase().equals("quit"))
            {
                input.close();
                System.exit(0);
            }
        }
        catch (Exception e) 
        {
            System.out.println("an error occurred");
        }
        return output;
    }
}
