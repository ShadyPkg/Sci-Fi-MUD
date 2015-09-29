/*
 * Jonathan Chin
 * 9/16/15
 */
package scifimud;


import Equipment.Item;
import classes.BlackHandRogue;
import classes.CyberSecurityArchitect;
import classes.Cyborg;
import classes.NanoMedic;
import classes.TimeTraveler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import rooms.CentralHub;
import rooms.Factory;
import rooms.Secret;
import rooms.Sewers;
import rooms.ThePit;
import rooms.TrainStation;
import rooms.Wastelands;
import static scifimud.ClassCreator.className;


/**
 *
 * @author jonc
 */
//creates an instance to connect a player
public class ConnectPlayer {
    private static final int TOTAL_WORDS = 31;
    public static ArrayList<String> listOfWords = new ArrayList<>();
    
    //intitalizing maps
    static ThePit[][][] mainMap=null;
    static CentralHub[][][] hubMap = null;
    static Factory[][][] factoryMap = null;
    static Sewers[][][] sewerMap = null;
    static TrainStation[][][] trainMap = null;
    static Wastelands[][][] wastelandsMap = null;
    static Secret[][][] secretMap = null;
    
    
    //initalizing classes
    static Cyborg cyborg = new Cyborg();
    static NanoMedic nanomedic = new NanoMedic();
    static CyberSecurityArchitect cybersecurityarchitect = new CyberSecurityArchitect();
    static TimeTraveler timetraveler = new TimeTraveler();
    static BlackHandRogue blackhandrogue = new BlackHandRogue();
    
    
    
    
    public ConnectPlayer(){
        
    }
    //connects player and gets data to setup a player on the map
    public void connect(String name) throws FileNotFoundException, IOException, URISyntaxException{
        
        
        //setting up players class
        ClassCreator.initalizeClass(name);
        
        //setting up the map based on where the player is located
        MapCreator.initializeMap(className.getLocation());
 
        //reading in words, in the future pass in the classname so class skills can be added to the arraylist as well
        readDict();
        
        //loop to read in input of player
        int gameOver = 0;
        Scanner stdin = new Scanner(System.in);
        String command;
        String newCommand;
        //use to break a command if a certain target or thing needs to be parsed after a particular keyword
        String target;
        Item item = new Item();
        
        while(true){
            if(gameOver == 1){
                System.out.println("Goodbye!");
                System.exit(0);
            }    
                  
           command = stdin.nextLine();
           //ensures log n lookup
           newCommand = binarySearch(command); 

            switch(newCommand){
                
                case "bitcoins":
                    System.out.println("You are carrying " + className.getBitcoins() + " bitcoins.");
                    break;
                case "down":
                    className.down();
                    break;
                case "drink":
                    break;
                    
                case "drop":
                    if(command.length() <= 4){
                        System.out.println("Drop what?");
                        break;
                    }
                    target = command.substring(5);
                   
                    
                    item = className.searchInventory(target, className.getInventory());
                    if(item!=null){
                        //removes item from player inventory
                        className.setInventory(className.removeItem(target, className.getInventory()));
                        //adds the item to the room
                        className.getRoom(className.getxCoordinate(), className.getyCoordinate(), className.getzCoordinate()).addItem(item);
                        System.out.println("You successully drop a(n) " + item.getName());
                    }
                    else{
                        System.out.println("Drop what?");
                    }
                    
                    
                    
                    
                    
                    
                    break;
                    
                case "east":
                    className.east();
                    break;
                case "eat":
                    break;
                 
                    
                case "equipment":
                    className.equipment();
                    break;
                case "examine":
                    if(command.length() <= 7){
                        System.out.println("Examine what?");
                        break;
                    }
                    target = command.substring(8);
                    
                    item = className.searchInventory(target, className.getInventory());
                    if(item!=null){
                        item.displayProperties();
                    }
                    else{
                        System.out.println("Examine what?");
                    }
                    
                    break;
                    
                case "here":
                    break;
                    
                case "inventory":
                    String inventory = ObjectCreator.getInventory(className.getInventory());
                    String[] newInventory  = inventory.split(", ");
                    int i;
                    System.out.println("Inventory : ");
                    for(i=0 ; i<newInventory.length; i++){
                        System.out.println(newInventory[i]);
                    }
                    break;
                case "kill":
                    if(command.length()<=4){
                        System.out.println("Kill whom?");
                        break;
                    }
                    target = command.substring(5);
                    if(isHere(target)){
                        fight(target);
                    }
                    else{
                        System.out.println("Kill whom?");
                    }
                    
                    break;
                case "level":
                    className.getLevel();
                    break;
                    
                case "location":
                    System.out.println("You are at the " + className.getLocation());
                    break;
                    
                case "look":
                    System.out.println(className.getRoom(className.getxCoordinate(), className.getyCoordinate(), className.getzCoordinate()).roomDescription);
                    break;
                    
                case  "north":
                   className.north();
                    break;
                case "quit":
                    savePlayer(className.getName(), className.getPassword(), className.getClassName(), className.getLevel(), className.getExperience(), className.getLocation(), className.getxCoordinate(), className.getyCoordinate(), className.getzCoordinate(), className.getBitcoins(), className.getStatus(), className.getInventory(), className.getWeapon(), className.getTorso(), className.getPants(), className.getHead(), className.getShoes());
                    System.out.println("Goodbye!");
                    System.exit(0);    
                    break;
                    
                case "save":
                    savePlayer(className.getName(), className.getPassword(), className.getClassName(), className.getLevel(), className.getExperience(), className.getLocation(), className.getxCoordinate(), className.getyCoordinate(), className.getzCoordinate(), className.getBitcoins(), className.getStatus(), className.getInventory(), className.getWeapon(), className.getTorso(), className.getPants(), className.getHead(), className.getShoes());
                    break;
                    
                case "say":
                    if(command.length()<=3){
                        System.out.println("Say to whom?");
                        break;
                    }
                    target = command.substring(4);
                    System.out.println("You say " + target);
                    break;
                    
                case "self":
                    break;
                
                case "sleep":
                    sleep();
                    break;
                case "south":
                    className.south();
                    break;
                    
                case "status":
                    System.out.println("Your health is " + className.getHealth());
                    System.out.println("Your energy is " + className.getEnergy());
                    System.out.println("You are " + className.getStatus());
                    break;
                    
                case "take":
                    break;
                    
                case "tell":
                    if(command.length() <= 4){
                        System.out.println("Tell whom?");
                        break;
                    }
                    target = command.substring(4);
                    break;
                    
                case "up":
                    className.up();
                    break;
                
                case "wear":
                    break;
                    
                case "west":
                    className.west();
                    break;
                    
                case "yell":
                    if(command.length()<=4){
                        System.out.println("Yell what?");
                        break;
                    }
                    target = command.substring(4);
                    System.out.println("You yell " + target);
                    break;
                case "health":
                    System.out.println("Health : " + className.getHealth());
                    break;
                case "energy":
                    System.out.println("Energy : " + className.getHealth());
                    break;
                case "statistics":
                    break;
                default:
                    //incorrect string was typed "null"
                    System.out.println("I do not understand.");
                    break;
                    
            }
        }
    }
    
    public static String binarySearch(String word){
        int i;
        
        int low=0;
        int high = TOTAL_WORDS-1;
        int mid;
        
        
        
        while(low<= high){
            
            mid=(high+low)/2;
            //if a partial match is found return the word
            if(listOfWords.get(mid).startsWith(word)){
                
                return listOfWords.get(mid);
            }
            //for cases like "examine chicken" or "say hi"
            if(word.startsWith(listOfWords.get(mid))){
                return listOfWords.get(mid);
            }
            //if the word being searched comes before the word in the dictionary
            //then the you want to search an earlier word
            if(word.compareTo(listOfWords.get(mid))<0){
                high=mid-1;
            }
            //if the word being searched comes after the word in the dictionary 
            //then you wan tto search for a later word
            else if(word.compareTo(listOfWords.get(mid))>0){
                low=mid+1;
            }
            else{
                //if an exact match is found return the word
                return listOfWords.get(mid);
            }
        }
        //if all words in dictionary are checked and none match then return null to indicate no word matching word was found
        return "null";
    }
    
    public static void readDict() throws FileNotFoundException, URISyntaxException{
         URL url = ConnectPlayer.class.getResource("commands.txt");

        File file = new File(url.toURI());
        Scanner fDic;
    
        fDic = new Scanner((file));
        int i;
  
        String current_word;

        //intitializing dictionary

        for(i=0; i<TOTAL_WORDS; i++){
            listOfWords.add(null);
        }


        for(i=0; i<TOTAL_WORDS; i++){
            current_word= fDic.next();
            listOfWords.set(i, current_word);
           
        }
        
        fDic.close();
    }
    
  
    //saves player data to a file
    public static void savePlayer(String name, String password, String className, int level, int experience, String location, int xCoordinate, int yCoordinate, int zCoordinate, int bitcoins, String status, ArrayList<Item> inventory, String weapon, String torso, String pants, String head, String shoes) throws FileNotFoundException, UnsupportedEncodingException{
     
         try (PrintWriter writer = new PrintWriter("src/PlayerInformation/" + name + ".txt", "UTF-8")) {
            //name
            writer.println(name);
            
            writer.println(password);
            //class 
            writer.println(className);
            //level of player
            writer.printf("%d\n", level);
            //experience of player
            writer.printf("%d\n", experience);
            //map location of player
            writer.printf("%s\n", location);
            //map coordinates where player is located
            writer.printf("%d %d %d\n", xCoordinate, yCoordinate, zCoordinate);
            //amount of bitcoins player is carrying
            writer.printf("%d\n", 0);
            //players health status, ex: healthy, blind, sick, etc. 
            //multiple affliction status will be separated by commas
            writer.println(status);
            //players inventory, all items are separated by a comma and will
            //use a strtok to break up the items
            writer.println(ObjectCreator.getInventory(inventory));
            
            //players weapon equipment
            writer.println(weapon);
            //players head equipment
            writer.println(head);
            //players torso equipment
            writer.println(torso);
            //players pants equipments
            writer.println(pants);
            //players shoe equipment
            writer.println(shoes);
            System.out.println("Successfully saved player data");
            writer.close();
        }
    }
    
    //when a player inititates a fight with a monster
    public static void fight(String target){
        System.out.println("You engage a " + target + "!");
        
    }
    
    //checks if an object or person/enemy exists in the room
    public static boolean isHere(String target){
        return true;
    }
    
    //when the player types sleep this function will execute
    //need to add extra health regeneration to player when sleeping
    //also in the future add a function to allow player to sleep on bed if
    //player chooses to do so. This will give a greater boost to health regen.
    public static void sleep(){
        
        System.out.println("You lay down and sleep.");
        Scanner scanner = new Scanner(System.in);
        String response;
        while(true){
            response = scanner.nextLine();
            
            if("wake".equals(response)){
                System.out.println("You woke up.");
                break;
            }
            else{
                System.out.println("You can't do anything while you are asleep");
            }
        }
    }


    
    
    
    
    
    
    
}
