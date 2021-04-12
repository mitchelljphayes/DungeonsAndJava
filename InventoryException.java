/**
*InventoryException Class which outlines the custom InventoryExecption
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class InventoryException extends Exception
{
    /**
     * Declares serialVersionUID
     */
    private static final long serialVersionUID = 4330165462065158575L;
    private String reportString;

    /**
    *Constructor Method which implements an object of the InventoryException class
    *
    *@param reportString a String passed as an argument when exception is thrown
    */
    public InventoryException(String reportString)
    {
        this.reportString = reportString;
    }

    /**
    *Method to return the reportString
    *
    *@return reportString a String which can be displayed when the error is thrown
    */
    public String getMessage()
    {
        return reportString;
    }
}
