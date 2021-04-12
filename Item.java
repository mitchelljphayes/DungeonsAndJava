/**
*Item Class which allows for subclasses of type Item
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class Item
{
    private String itemType;

    /**
    *Constructor for objects of the class Item
    *
    */
    public Item()
    {
        itemType = "unknown";
    }

    /**
    *Alternative Constructor which sets itemType to the string passed as an argument
    *
    *@param itemType    a String which declares the type of item
    */
    public Item(String itemType)
    {
        this.itemType = itemType;
    }

    /**
    *Method that returns the display string for the item type 
    *
    *@return itemType a string containing the itemType string
    */
    public String display()
    {
        return ("Item Type: " + itemType);
    }

    /**
    *Accessor Method which returns the itemType String
    *
    *@return itemType a String which declares the type of item
    */
    public String getItemType()
    {
        return itemType;
    }

    /**
    *Mutator Method which replaces the itemType String with the String passed as an argument
    *
    */
    public void setItemType(String itemType)
    {
        this.itemType = itemType;
    }
}
