/**
*Potion Class which extends Item class and outlines the details of potion objects 
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class Potion extends Item
{
    private int healthEffects;

    /**
    *Constructor Method which initialises objects of the Potion class
    *
    */
    public Potion()
    {
        super("Potion");
        healthEffects = 15;
    }

    /**
    *Method which returns a string containing the details of the potion object
    *
    *@return A string which contains the details of the potion object 
    */
    public String display()
    {
        
        return (super.display() + ", Health Effects: " + healthEffects);
    }

    /**
    *Accessor Method which returns the int healthEffects
    *
    *@return healthEffects  an int which represents the amount of health a potion would restore
    */
    public int getHealthEffects()
    {
        return healthEffects;
    }

    /**
    *Mutator Method to set the healthEffects field 
    *
    *@param healthEffects int value which represents the new value of the healthEffects field
    */
    public void setHealthEffects(int healthEffects)
    {
        this.healthEffects = healthEffects;
    }
}
