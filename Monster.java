/**
*Monster Class which extends GameCharacter class and provides details of the Monster type
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class Monster extends GameCharacter
{

    /**
    *Constructor Method which initialises an object of the class Monster
    *
    */
    public Monster()
    {
        super("monster", 30, 5, 10);
    }

    /**
    *Constructor Method which initialises an object of the class Monster and sets the details of the state of the monster to the arguments passed
    *
    *@param name        String which specifies the monsters name
    *@param health      int which sets the monsters health
    *@param minDamage   int which sets the monsters minDamage
    *@param maxDamage   int which sets the monsters maxDamage
    */
    public Monster(String name, int health, int minDamage, int maxDamage)
    {
        super(name, health, minDamage, maxDamage);
    }

    /**
    *Method to return a String of which details the state of the monster object
    *
    *@return a String of which details the state of the monster object
    */
    public String display()
    {
        return super.display();
    }
}

