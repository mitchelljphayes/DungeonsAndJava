import java.util.ArrayList;
/**
*Player Class which outlines the details of the player charcter 
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class Player extends GameCharacter
{
    private int coins;
    private ArrayList<Item> inventory;

    /**
    *Default Constructor Method which initialises object of class Player
    *
    */
    public Player()
    {
        super();
        coins = 0;
        inventory = new ArrayList<>();
    }

    /**
    *Constructor Method which initialises object of class Player
    *
    *@param name    a String which sets the players name
    */
    public Player(String name)
    {
        super(name, 100, 0, 20);
        coins = 0;
        inventory = new ArrayList<>();
    }

    /**
    *Method to add a specified number of potions to the inventory
    *
    *@param     numPotions  an int that determines the number of potions to be added
    *@throws    InventoryException
    */
    public void addPotions(int numPotions) throws InventoryException
    {
        
        for (int i = numPotions; i > 0; i--)
        {
            if (inventory.size() < 5)
            {
                Item potion = new Potion();
                inventory.add(potion);
            }
            else 
            {
                throw new InventoryException ("Your Inventory is full");
            }
        }
    }

    /**
    *Method to add a specified number of coins to the coins field
    *
    *@param numCoins    int that specifies the number of coins to be added
    */
    public void addCoins(int numCoins)
    {
        coins += numCoins;
    }

    /**
    *Method to return a string that contains the details of the player object
    *
    *@return a string that contains the details of the player object
    */
    public String display()
    {
        return ("\nPlayer Status" + super.display() + ", coins: " + coins + ", items in inventory: " + inventory.size());
    }

    /**
    *Accessor Method to get the value in the coins field
    *
    *@return coins  an int representing the value of the coins field
    */
    public int getCoins()
    {
        return coins;
    }

    /**
    *Accessor Method to get access to the inventory ArrayList 
    *
    *@return    ArrayList<Item> inventory collection
    */
    public ArrayList<Item> getInventory()
    {
        return inventory;
    }


    /**
    *Accessor Method to get the value that a potion will restore to a players health
    *
    *@return hp an int representing the value that a potion will restore to a players health
    */
    public int getPotionEffects()
    {
        Potion potion = new Potion();
        int hp = potion.getHealthEffects();
        return hp;
    }

    /**
    *Method to add the Item passed to the players inventory
    *
    *@param     item the Item to be added to players inventory
    *@throws    InventoryException
    */
    public void pickUpItem(Item item) throws InventoryException
    {
        //display inventory.size()
        if (inventory.size() < 5)
        {
            //pick up item
            inventory.add(item);
        }
        else 
        {
        //   cannot pick up item
            throw new InventoryException ("Your Inventory is full");
        }
    }

    /**
    *Method to emulate rolling two physical dice, and report the details to the user
    *
    *@return    roll    int that holds the value of the dice roll
    */
    public int rollDice()
    {
        int roll = Dice.twoD6();
        System.out.println("You rolled: " + roll + ", move forward " + roll + " spaces.\n");
        return roll;
    }

    /**
    *Method that returns a potion from the inventory if one is available
    *
    *@return    potion an instance of the Potion class
    *@throws    InventoryException
    */
    public Potion selectPotion() throws InventoryException
    {
        int i = 0;
        boolean potionFound = false;
        for (Item item : inventory)
        {
            if (item.getItemType().equals("Potion"))
            {
                Potion potion = (Potion) inventory.get(i);
                inventory.remove(i);
                potionFound = true;
                return potion;
            }
            i++;
        }
        if (!potionFound)
        {
            throw new InventoryException ("There are no potions in your inventory!");
        }
        return null;
    }

    /**
    *Mutator Method that 
    *
    *@param coins   int that represents the new value for hte coins field
    */
    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    /**
    *Method that facilitates the use of a potion from the inventory
    *
    */
    public void usePotion() throws InventoryException
    {
        Potion potion = selectPotion();
        increaseHealth(potion.getHealthEffects());
    }
}

