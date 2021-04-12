/**
*GameCharacter Class which lays out the structure of the characters in the game
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class GameCharacter
{

    private String name;
    private int health;
    private final int MAXHEALTH;
    private int minDamage;
    private int maxDamage;

    /**
    *Default Constructor Method for instances of the class GameCharacter
    *
    */
    public GameCharacter()
    {
        name = "anonymous";
        health = 10;
        MAXHEALTH = health;
        minDamage = 1;
        maxDamage = 10;
    }

    /**
    *Constructor Method for instances of the class GameCharacter which set the fields to the parameters passed
    *
    *@param name        a String which sets the characters name
    *@param health      an int which sets the characters health
    *@param minDamage   an int which sets the characters minimum damage
    *@param maxDamage   an int which sets the characters maximum damage
    */
    public GameCharacter(String name, int health, int minDamage, int maxDamage)
    {
        this.name = name;
        this.health = health;
        MAXHEALTH = health;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    /**
    *Method which generates the attack damage between the minDamage and maxDamage
    *
    *@return int between minDamage and maxDamage which is randomly selected 
    */
    public int attack()
    {
        return Dice.random(minDamage, maxDamage);
    }

    /**
    *Method to decrease the health field by the integer passed as an argument
    *
    *@param healthDecrease  an int which is the amount to decrease the health field
    */
    public void decreaseHealth(int healthDecrease)
    {
        setHealth(getHealth() - healthDecrease);
        if (getHealth() <= 0)
        {
            setHealth(0); 
        }
    }

    /**
    *Display method for the GameCharacter class
    *
    *@return a String which displays the state of the fields for the GameCharacter instance
    */
    public String display()
    {
        return ("\nname: " + name + ", health: " + health + ", minDamage: " + minDamage + ", maxDamage " + maxDamage);
    }


    /**
    *Method which returns String which communicates the current health status for the character
    *
    *@return a String containing the status of the name and health fields
    */
    public String displayHealth()
    {
        return (name + " has " + health + " health remaining!\n");
    }

    /**
    *Accessor Method to get access to the health field
    *
    *@return health an int which represents the health of the character 
    */
    public int getHealth()
    {
        return health;
    }

    /**
    *Accessor Method to get access to the maxDamage field
    *
    *@return maxDamage an int which represents the maximum damage the character can inflict
    */
    public int getMaxDamage()
    {
        return maxDamage;
    }

    /**
    *Accessor Method to get access to the minDamage field
    *
    *@return maxDamage an int which represents the minimum damage the character can inflict
    */
    public int getMinDamage()
    {
        return minDamage;
    }

    /**
    *Accessor Method to get access to the name field 
    *
    *@return name a string representing the name of the character 
    */
    public String getName()
    {
        return name;
    }

    /**
    *Method to increase the health field by the value of the int passed as an argument
    *
    *@param healthIncrease int value to increase the health field by
    */
    public void increaseHealth(int healthIncrease)
    {
        setHealth(getHealth() + healthIncrease);
        if (getHealth() > MAXHEALTH)
        {
            setHealth(MAXHEALTH); 
        }
    }

    /**
    *Mutator Method to modify the value of the health field to the value of the int passed as an argument
    *
    *@param health int value which represents the new value of the health field 
    */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
    *Mutator Method to modify the value of the maxDamage field to the value of the int passed as an argument
    *
    *@param maxDamage int value which represents the new value of the maxDamage field 
    */
    public void setMaxDamage(int maxDamage)
    {
        this.maxDamage = maxDamage;
    }

    /**
    *Mutator Method to modify the value of the minDamage field to the value of the int passed as an argument
    *
    *@param minDamage int value which represents the new value of the minDamage field 
    */
    public void setMinDamage(int minDamage)
    {
        this.minDamage = minDamage;
    }

    /**
    *Mutator Method to modify the value of the name field to the value of the String passed as an argument
    *
    *@param name String value which represents the new value of the name field 
    */
    public void setName(String name)
    {
        this.name = name;
    }
}
