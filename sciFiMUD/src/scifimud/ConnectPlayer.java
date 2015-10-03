/*
 * Jonathan Chin
 * 9/16/15
 */
package scifimud;

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
import static scifimud.ConsoleApplet.console;

/**
 *
 * @author jonc
 */
//creates an instance to connect a player

public class ConnectPlayer {
     
    private static final int TOTAL_WORDS = 32;
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
        //Scanner stdin = new Scanner(System.in);
        String command;
        String newCommand;
        //use to break a command if a certain target or thing needs to be parsed after a particular keyword
        String target;
        
        while(true){  
                  
           command = console.getln();
           //ensures log n lookup
           newCommand = binarySearch(command); 

            switch(newCommand){
                
                case "bitcoins":
                    console.putln("You are carrying " + className.getBitcoins() + " bitcoins.");
                    break;
                case "down":
                    className.down();
                    break;
                case "drink":
                    break;
                    
                case "drop":
                    if(command.length() <= 4){
                        console.putln("Drop what?");
                        break;
                    }
                    target = command.substring(5);
                    className.drop(target);
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
                        console.putln("Examine what?");
                        break;
                    }
                    target = command.substring(8);
                    className.examine(target);
                    
                    
                    break;
                    
                case "here":
                    className.here();
                    break;
                    
                case "inventory":
                    className.inventory();
                    break;
                case "kill":
                    if(command.length()<=4){
                        console.putln("Kill whom?");
                        break;
                    }
                    target = command.substring(5);
                    if(isHere(target)){
                        fight(target);
                    }
                    else{
                        console.putln("Kill whom?");
                    }
                    
                    break;
                case "level":
                    className.getLevel();
                    break;
                    
                case "location":
                    console.putln("You are at the " + className.getLocation());
                    break;
                    
                case "look":
                    className.look();
                    break;
                    
                case  "north":
                   className.north();
                    break;
                case "quit":
                    savePlayer();
                    console.putln("Goodbye!");
                    System.exit(0);    
                    break;
                
                case "remove":
                    if(command.length() <= 7){
                        console.putln("Remove what?");
                        break;
                    }
                    target = command.substring(7);
                    className.remove(target);
                    break;
                    
                case "save":
                    savePlayer();
                    break;
                    
                case "say":
                    if(command.length()<=3){
                        console.putln("Say to whom?");
                        break;
                    }
                    target = command.substring(4);
                    console.putln("You say " + target);
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
                    console.putln("Your health is " + className.getHealth());
                    console.putln("Your energy is " + className.getEnergy());
                    console.putln("You are " + className.getStatus());
                    break;
                    
                case "take":
                    if(command.length() <= 4){
                        console.putln("Take what?");
                        break;
                    }
                    target = command.substring(5);
                    className.take(target);
                    break;
                    
                case "tell":
                    if(command.length() <= 4){
                        console.putln("Tell whom?");
                        break;
                    }
                    target = command.substring(5);
                    break;
                    
                case "up":
                    className.up();
                    break;
                
                case "wear":
                    if(command.length() <= 4){
                        console.putln("Wear what?");
                        break;
                    }
                    target = command.substring(5);
                    className.wear(target);
                    
                    break;
                    
                case "west":
                    className.west();
                    break;
                    
                case "yell":
                    if(command.length()<=4){
                        console.putln("Yell what?");
                        break;
                    }
                    target = command.substring(5);
                    console.putln("You yell " + target);
                    break;
                case "health":
                    console.putln("Health : " + className.getHealth());
                    break;
                case "energy":
                    console.putln("Energy : " + className.getHealth());
                    break;
                case "statistics":
                    break;
                default:
                    //incorrect string was typed "null"
                    console.putln("I do not understand.");
                    break;
                    
            }
        }
    }
    
    public static String binarySearch(String word){
        
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
    public static void savePlayer  () throws FileNotFoundException, UnsupportedEncodingException{
     
         try (PrintWriter writer = new PrintWriter("src/PlayerInformation/" + className.getName() + ".txt", "UTF-8")) {
            //name
            writer.println(className.getName());
            
            writer.println(className.getPassword());
            //class 
            writer.println(className.getClassName());
            //level of player
            writer.printf("%d\n", className.getLevel());
            //experience of player
            writer.printf("%d\n", className.getExperience());
            //map location of player
            writer.printf("%s\n", className.getLocation());
            //map coordinates where player is located
            writer.printf("%d %d %d\n", className.getxCoordinate(), className.getyCoordinate(), className.getzCoordinate());
            //amount of bitcoins player is carrying
            writer.printf("%d\n", className.getBitcoins());
            //players health status, ex: healthy, blind, sick, etc. 
            //multiple affliction status will be separated by commas
            writer.println(className.getStatus());
            //players inventory, all items are separated by a comma and will
            //use a strtok to break up the items
            writer.println(ObjectCreator.getInventory(className.getInventory()));
            
            //players weapon equipment
            writer.println(className.getWeapon().getName());
            //players head equipment
            writer.println(className.getHead().getName());
            //players torso equipment
            writer.println(className.getTorso().getName());
            //players pants equipments
            writer.println(className.getPants().getName());
            //players shoe equipment
            writer.println(className.getShoes().getName());
            console.putln("Successfully saved player data");
            writer.close();
        }
    }
    
   
    
    //when a player inititates a fight with a monster
    public static void fight(String target){
        console.putln("You engage a " + target + "!");
        
    }
    
    //checks if an object or person/enemy exists in the room
    public static boolean isHere(String target){
        return true;
    }
    
    //when the player types sleep this function will execute
    //need to add extra health regeneration to player when sleeping
    //also in the future add a function to allow player to sleep on bed if
    //player chooses to do so. This will give a greater boost to health regen.
    public static void sleep() {
        
        console.putln("You lay down and sleep.");
        //Scanner scanner = new Scanner(System.in);
        String response;
        while(true){
            response = console.getln();
            
            if("wake".equals(response)){
                console.putln("You woke up.");
                break;
            }
            else{
                console.putln("You can't do anything while you are asleep");
            }
        }
    }

}
