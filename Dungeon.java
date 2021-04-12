import java.util.ArrayList;
/**
*Dungeon Class which contains main() and handles most of the game play
*
*@author Mitchell Hayes
*@since 24 November 2020
*@version 1.0
*/
public class Dungeon
{
    private enum Difficulty 
    {
        EASY, 
        MEDIUM, 
        HARD
    }

    public final String INPUT_FILE = "monsters.txt";
    private int dungeonLength;
    private int playerPosition;
    private Player player;
    private boolean gameOver;
    private String gameOutcome;
    private ArrayList<Monster> monsters;
    private Difficulty difficultyMode;

    /**
    *Constructor for objects of the class Dungeon
    *
    */
    public Dungeon()
    {
        player = new Player(acceptPlayerName());
        playerPosition = 0;
        gameOver = false;
        gameOutcome = "";
        acceptDifficulty();
        switch (difficultyMode)
        {
            case EASY:
                createMonsters(5);
                setDungeonLength(30, 50);
                initialiseInventory(3);
                break;
            
            case MEDIUM:
                createMonsters(10);
                setDungeonLength(50, 80);
                initialiseInventory(2);
                break;
            
            case HARD:
                createMonsters(20);
                setDungeonLength(100, 150);
                initialiseInventory(1);
                break;

            default:
                createMonsters(50);
                setDungeonLength(300);
                break;
        }
    }

    /**
    * Method to accept difficulty choice and set Difficulty enum
    *
    */
    private void acceptDifficulty()
    {
        boolean validMode = false;
        int attempts = 0;
        do
        {
            try
            {
                Input in = new Input();
                String mode = in.acceptStringInput("Enter Difficulty Mode: Easy, Medium, or Hard").toUpperCase().trim();
                validMode = Validation.validDifficulty(mode);
                //if the validation works then the enum is set and the method is completed
                if (validMode)
                {
                    difficultyMode = Difficulty.valueOf(mode);
                }
            }
            catch (IllegalArgumentException illegalArgument)
            {
                ++attempts;
                System.out.println(illegalArgument.getMessage());
            }
            finally 
            {
                // after 3 failed attempts program exits
                if (attempts >= 3)
                {   
                    System.out.println("too many failed attempts, exiting program");
                    System.exit(1);
                }
            }
        } while (!validMode);
    }

    /**
    *Method to accept user input for player name
    *
    *@return name a String representing the players name
    */
    public String acceptPlayerName()
    {
        String name;
        boolean validName = false;
        int attempts = 0;
        do
        {
            Input in = new Input();
            name = in.acceptStringInput("Enter your name").trim();
            validName = Validation.lengthWithinRange(name, 3, 12);
            if (!validName)
            {
                ++attempts;
                System.out.println("Please enter name between 3 and 12 characters.");
            }
            // after 3 failed attempts program exits
            if (attempts >= 3)
            {
                System.out.println("too many failed attempts, exiting program");
                System.exit(1);
            }
        } while (!validName);
        return name;
    }

    /**
    *Method which facilitates the attack sequence for both the player and the monsters
    *
    *@param attacker    this is the reference to the GameCharacter doing the attacking
    *@param reciever    this is the reference to the GameCharacter being attacked
    */
    public void attackSequence(GameCharacter attacker, GameCharacter receiver)
    {
                int attackDamage = attacker.attack();
                receiver.decreaseHealth(attackDamage);
                // report attack strength and remaining health
                System.out.println(attacker.getName() + " attacks with strength: " + attackDamage + "\n\n");
                System.out.println(receiver.displayHealth());
    }

    /**
    *Method which manages the control flow of the battle 
    *
    *@param player  reference to an instance of the player class entering the battle
    *@param monster referecne to an instance of the monster class entering the battle
    */
    public void battle(Player player, Monster monster)
    {
        System.out.println("Watch out!!! A " + monster.getName() + " has appeared!");
        Input in = new Input();
        while ((player.getHealth() > 0) && (monster.getHealth() > 0))
        {
            // Player Attacks
            in.acceptStringInput("To Attack hit enter");
            attackSequence(player, monster);
            if (monster.getHealth() <= 0)
            {
                System.out.println("\nSUCCESS!!! You vanquished the monster!\n");
                break;
            }
            // Monster Attacks
            attackSequence(monster, player);
            if (player.getHealth() <= 0)
            {
                System.out.println("Oh no, you died!");
                break;
            }
        } 
    }

    /**
    *Method which generates the ArrayList of monsters based on the Difficulty setting
    *
    *@param numMonsters an int which determines the length of the monsters ArrayList 
    */
    public void createMonsters(int numMonsters)
    {
        String fileContents = readFile();
        monsters = new ArrayList<Monster>();
        // create an array of lines which need to be split by the , delimiter later
        String[] lines = fileContents.split("\n");
        String monsterErrors = ""; // this helps with exception reporting
        int j = 0;
        // loop executes as many times as needed based on spec for each difficulty choice, passed in as numMonsters
        for (int i = numMonsters; i > 0; i--) // i like to decrement because it's conceptually easier to see that you stop at 0 
        {
            // choose a random line from file between 0 the max number of lines
            int monsterSelector = Dice.random(0,(lines.length - 1));
            try
            {
                String[] monsterAttributes = lines[monsterSelector].split(",");
                String name = monsterAttributes[0];
                int health = Integer.parseInt(monsterAttributes[1]);
                int minDamage = Integer.parseInt(monsterAttributes[2]);
                int maxDamage = Integer.parseInt(monsterAttributes[3]);
                //create new monster based on the contents of the line
                Monster monster = new Monster(name, health, minDamage, maxDamage);
                monsters.add(monster);
            }
            catch (Exception e)
            {
                // if theres an error with a monster program records it and tries again (i++) rather than just exiting immediately 
                monsterErrors += ("Something went wrong when spawning monster number " + (numMonsters - i + 1) + " from file " + INPUT_FILE + " line " + (monsterSelector + 1) + "\n");
                i++;
                j++;
                // if there are more than 10 errors then the monsters file is probably very broken so program exits and displays the errors including the lines in the file that caused the errors.
                if (j >= 10)
                {
                    System.out.println("Too many errors spawning Monsers exiting game");
                    System.out.println(monsterErrors);
                    System.exit(1);
                }
            }
        }
        // if the program doesn't generate the right number of monsters program will exit 
        if (!(numMonsters == monsters.size()))
        {
            System.out.println("Something went wrong generating monsters, exiting game");
            System.exit(1);
        }
    }

    /**
    *Method which displays the details of the instance of the Dungeon object
    *
    *@return A single string containing the details of the dungeon objects
    */
    public String display()
    {
        return ("Difficulty Mode: " + difficultyMode 
                + ", Dungeon Length: " + dungeonLength 
                + ", Player Position: " + playerPosition 
                + ", Game Status: " + gameOver 
                + player.display() 
                + displayMonsters());

    }

    /**
    *Method which returns the details of each monster in the monsters ArrayList
    *
    *@return dispMon a string which contains the details of each of the monsters on the ArrayList
    */
    public String displayMonsters()
    {
        String dispMon = "";
        int monsterNumber = 1;
        for (Monster monster : monsters)
        {
            dispMon += ("\nMonster Number " + monsterNumber + ": " + monster.display());
            monsterNumber++;
        }
        return dispMon;
    }

    /**
    *Method which returns a string containing the outcome of the game
    *
    *@return A string which contains the outcome of the game
    */
    public String displayEnd()
    {
        return ("Player Name: " + getPlayer().getName() 
                + ", Game Outcome: " + getGameOutcome() 
                + ", Items in Inventory: " + getPlayer().getInventory().size() 
                + ", Coins collected: " + getPlayer().getCoins() 
                + ", Distance Travelled: " + getPlayerPosition());
    }

    /**
    *Method to manage the setting adjustments, writing to the outcome.txt file and displaying the
    *message at the end of the game. 
    *
    */
    public void endGame()
    {
        Input in = new Input();
        FileIO fileIO = new FileIO("Outcome.txt");
        String outcomeString = displayEnd();
        fileIO.writeFile(outcomeString);
        // System.out.println(outcomeString);
        in.acceptStringInput("\n\n\n\nTo play again hit enter, to quit type quit");
    }

 
    /**
    *Method which handles the choosing of the event type and calls the event managers 
    *
    *@param player  an instance of the player class
    */
    public void eventLauncher(Player player)
    {
        // Use d100 to choose events based on porbabilities specified, could have used d10 but i already wrote this.
        int eventProb = Dice.d100();
        if ((eventProb > 0) && (eventProb <= 20))
        {
            findItem(player);
        }
        // additional condition to allow if statement to fall to "nothing happens" if no more monsters, rather than just letting it run and exit 
        else if ((eventProb > 20) && (eventProb <= 60) && (monsters.size() > 0))
        {
            try
            {
                Monster monster = selectMonster();
                battle(player, monster);
            }
            catch (ArrayIndexOutOfBoundsException IE)
            {
                System.out.println(IE.getMessage());
            }
        }
        else
        {
            System.out.println("There's nothing here, you're safe!");
        }
    }

    /**
    *Method which determines which item is found and passes it to the player
    *
    *@param player  An instance of the Player class which recieves found items or not!
    */
    public void findItem(Player player)
    {
        // Use d100 to choose items based on porbabilities specified, could have used d10 but i already wrote this.
        System.out.println("look you found an item!");
        int itemProb = Dice.d100();
        Item itemFound;
        if ((itemProb > 0) && (itemProb <= 50))
        {
            System.out.println("You found a Potion");
            itemFound = new Potion();
            try
            {
                player.pickUpItem(itemFound);
            }
            catch (InventoryException InventoryFull)
            {
                System.out.println(InventoryFull.getMessage());
            }
        }
        else if ((itemProb > 50) && (itemProb <= 90))
        {
            System.out.println("You found 1000 coins");
            player.addCoins(1000);
        }
        else
        {
            System.out.println("Oh it's just an old shoe!");
        }
    }

    /**
    *Accessor Method to return the Difficulty mode
    *
    *@return difficultyMode a variable containing an enum with the difficulty settings
    */
    public Difficulty getDifficultyMode()
    {
        return difficultyMode;
    }

    /**
    *Accessor Method to return a string with details about the players win conditions
    *
    *@return gameOutcome    A string with the details about weather the player wins or loses
    */
    public String getGameOutcome()
    {
        return gameOutcome;
    }

    /**
    *Accessor Method to return whether the game has met its end of game conditions
    *
    *@return gameOver A boolean which indicates if the end of game conditions have been met
    */
    public boolean getGameOver()
    {
        return gameOver;
    }

    /**
    *Accessor Method to return the length of the dungeon instance
    *
    *@return dungeonLength an int which is set as the length of the dungeon.
    */
    public int getDungeonLength()
    {
        return dungeonLength;
    }

    /**
    *Accessor Method to return the collection of monsters
    *
    *@return monsters an ArrayList<Monster> containing all the monsters
    */
    public ArrayList<Monster> getMonsters()
    {
        return monsters;
    }

    /**
    *Accessor Method to return the player in the dungeon
    *
    *@return player an instance of the Player class 
    */
    public Player getPlayer()
    {
        return player;
    } 

    /**
    *Accessor Method which returns the position of the player in the dungeon
    *
    *@return playerPosition an int which represents the distance a player has travelled in the dungeon
    */
    public int getPlayerPosition()
    {
        return playerPosition;
    }

    /**
    *Method which passes the number of potions specified by the difficulty setting to the player
    *
    *@param numPotions an int representing the potions specified by the game specifications based on the difficulty levels
    */
    private void initialiseInventory(int numPotions)
    {
        try
        {
            player.addPotions(numPotions);
        }
        catch (Exception InventoryFull)
        {
            System.out.println(InventoryFull.getMessage());
        } 
    }

    /**
    *Main method which initialises the an instance of the Dungeon class and runs a loop which manages the game
    *
    *@param args a String[] which recieves commandline arguments 
    */
    public static void main(String[] args)
    {
        //keep players playing until they choose to quit
        while (true)
        {
            System.out.println("\nGet ready to play Dungeons and Java!\nYou can type quit at any time to exit the game.\n");
            Dungeon d = new Dungeon();
            // welcome message for the user
            d.welcome(d.getPlayer());
            while (!d.getGameOver())
            {
                Input in = new Input();
                in.acceptStringInput("\n\nTime to move on... To roll hit enter");
                // roll dice and move player forward
                d.movePlayer(d.getPlayer().rollDice());
                // if the player has made it to the end of the dungeon game over and player wins!!
                if (d.getPlayerPosition() >= d.getDungeonLength())
                {
                    d.setGameOver(true);
                    d.playerWins();
                    break;
                }
                // launch event process
                d.eventLauncher(d.getPlayer());
                // ff the event process finished with player dying then game over and player loses
                if (d.getPlayer().getHealth() <= 0)
                {
                    d.setGameOver(true);
                    d.playerLoses();
                    break;
                }
                // if there are potions in inventory let player drink one
                if (d.getPlayer().getInventory().size() > 0)
                {
                    d.potionOpportunity(d.getPlayer());
                }
            }
            d.endGame();
        }
    }

    /**
    *Method which moves the player position forward by the argument passed in
    *
    *@param moves   an int representing how far the player needs to move.
    */
    public void movePlayer(int moves)
    {
        setPlayerPosition(getPlayerPosition() + moves);
    }

    /**
    *Method which sets the game outcome as lose and displays a game over message.
    *
    */
    public void playerLoses()
    {
        System.out.println("Game Over\n\n");
        setGameOutcome("Lose");
    }

    /**
    *Method which sets the game outcome as win and displays a congratulations for escaping the dungeon
    *
    */
    public void playerWins()
    {
        System.out.println("Congratulations! You have earned your freedom!\n\n");
        setGameOutcome("Win");
    }

    /**
    *Method which manages the end of turn option to drink a potion if available
    *
    *@param player  An instance of the player class
    */
    public void potionOpportunity(Player player)
    {
        Input in = new Input();
        System.out.println("\nYou Have " + player.getHealth() + " Health, and " + player.getInventory().size() + " Potions in Your inventory.");
        System.out.println("Each Potion will restore " + player.getPotionEffects() + " health.");
        String drinkPotion = in.acceptStringInput( "To use a Potion, type potion, otherwise hit enter.").toLowerCase();
        if (drinkPotion.equals("potion"))
        {
            try
            {
                player.usePotion();
                System.out.println("You drank a potion, your health is now: " + player.getHealth());
            }
            catch (InventoryException IE)
            {
                System.out.println(IE.getMessage());
            }
        }
    }

    /**
    *Method which extracts contents of INPUT_FILE and returns it in a single string
    *
    *@return fileContents   A single string which contains the contents of the INPUT_FILE
    */
    public String readFile()
    {
        FileIO fileIO = new FileIO(INPUT_FILE);
        String fileContents = fileIO.readFile();
        return fileContents;
    }

    /**
    *Method which chooses the last monster of the monsters ArrayList if there is one
    *
    *@throws ArrayIndexOutOfBoundsException
    *@return monster    An instance of the Monster class 
    */
    public Monster selectMonster() throws ArrayIndexOutOfBoundsException
    {
        //check that there are monsters left in the ArrayList
        int index = monsters.size() - 1;
        if (index <= 0)
        {
            throw new ArrayIndexOutOfBoundsException("No more Monsters!");
        }
        //obtain Monster object from arraylist
        Monster monster = monsters.get(index);
        monsters.remove(index);
        return monster;
    }

    /**
    *Mutator Method to set the length of the dungeon
    *
    *@param length an int representing how long you want the dungeon to be 
    */
    public void setDungeonLength(int length)
    {
        dungeonLength = length;
    }

    /**
    *Mutator Method to set the dungeon length to a number randomly distributed between two integers passed in as arguments
    *
    *@param min shortest possible length of dungeon
    *@param max longest possible length of dungeon
    */
    public void setDungeonLength(int min, int max)
    {
        dungeonLength = Dice.random(min, max);
    }

    /**
    *Mutator Method to set the gameOutcome field with a string passed in as an argument
    *
    *@param gameOutcome A string with information about the outcome of the game
    */
    public void setGameOutcome(String gameOutcome)
    {
        this.gameOutcome = gameOutcome;
    }

    /**
    *Mutator Method which sets the gameOver boolean
    *
    *@param gameOver a boolean value indicating if the game its over. 
    */
    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    /**
    *Mutator Method which replaces a monster at the index position in the monsters ArrayList
    *
    *@param index   an int representing which monster in the ArrayList should be replaced
    *@param monster an instance of the monster class which will replace the monster previously at the index position
    */
    public void  setMonster(int index, Monster monster)
    {
        monsters.set(index, monster);
    }

    /**
    *Mutator Method which replaces the existing player or add one if none present
    *
    *@param player  An instance of the player class which will be placed in the Dungeon
    */
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    /**
    *Mutator Method which modifies the player position in the dungeon to whatever int is passed as an argument
    *
    *@param position an int which will be used to set the player position in the dungeon
    */
    public void setPlayerPosition(int position)
    {
        playerPosition = position;
    }

    /**
    *Method which welcomes the player to the dungeon and informs them about their state and their maximum values
    *
    *@param player an instance of the Player class whose details are displayed
    */
    public void welcome(Player player)
    {
        System.out.println("\n\nWelcome to the Dungeon " + player.getName() + "!\n");
        System.out.println("You have " + player.getHealth() + " Health and " + player.getInventory().size() + " Potions in your inventory");
        System.out.println("Your Max Health is 100 and Max Items in invenotry is 5");
    }
}
