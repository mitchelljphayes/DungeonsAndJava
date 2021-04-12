import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
/**
*FileIO Class which manages opening, closing, reading and writing to the file specified in the constructor
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class FileIO
{
    private String fileName;

    /**
    *Default Constructor Method for instance of class FileIO
    *
    */
    public FileIO()
    {
        fileName = "temp.txt";
    }

    /**
    *Constructor method for instances of calss FileIO which acccepts a file name as an argument 
    *
    *@param fileName a String which specifies the file name used by this instance of FileIO
    */
    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    /**
    *Accessor Method which returns the file name specifiied
    *
    *@return fileName   A string which represents the file name being used
    */
    public String getFileName()
    {
        return fileName;
    }

    /**
    *Method which does the file reading 
    *
    *@throws Exception
    */
    public String readFile()
    {
        String returnString = "";
        try (FileReader reader = new FileReader(fileName))
        {
            try (Scanner fileInput = new Scanner(reader))
            {
                do 
                {
                    String lineContents = fileInput.nextLine();
                    returnString += (lineContents + "\n");

                } while (fileInput.hasNextLine());

            }
            catch (Exception scanner)
            {
                reader.close();
                returnString = "Error reading file";
                System.out.println(returnString);
                System.exit(0);
            }
        
        }
        catch (Exception fileReader)
        {
            returnString = "Error opening file";
            System.out.println(returnString);
            System.exit(0);
        }
        return returnString;
    }

    /**
    *Method which sets the file name used by the FileIO object
    *
    *@param fileName a string which sets the fileName field of the object to the string passed as an argument
    */
    public void setFilename(String fileName)
    {
        this.fileName = fileName;
    }

    /**
    *Method to append a String passed as an argument to the file specified by the object
    *
    *@param appendString a String to be appended to the file specified by the object
    */
    public void writeFile(String appendString)
    {
        try (FileWriter writer = new FileWriter(fileName, true))
        {
            writer.append("\n" + appendString);
        }
        catch (Exception e)
        {
            System.out.println("Error in writing to file! Exiting...");
        }
    }
}
