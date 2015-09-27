/*
 * Jonathan Chin
 * 9/16/15
 */
package scifimud;


import classes.BlackHandRogue;
import classes.CyberSecurityArchitect;
import classes.Cyborg;
import classes.NanoMedic;
import classes.Player;
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
import java.util.StringTokenizer;
import rooms.CentralHub;
import rooms.Factory;
import rooms.Secret;
import rooms.Sewers;
import rooms.ThePit;
import rooms.TrainStation;
import rooms.Wastelands;

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
    
    //will be used as a global reference to the class the player selected
    static Player className;
    
    
    public ConnectPlayer(){
        
    }
    //connects player and gets data to setup a player on the map
    public void connect(String name) throws FileNotFoundException, IOException, URISyntaxException{
        
       
        //setting up players class
        initalizeClass(name);
        
        //setting up the map based on where the player is located
        initializeMap(className.getLocation());
 
        //reading in words, in the future pass in the classname so class skills can be added to the arraylist as well
        readDict();
        
        //loop to read in input of player
        int gameOver = 0;
        Scanner stdin = new Scanner(System.in);
        String command;
        String newCommand;
        //use to break a command if a certain target or thing needs to be parsed after a particular keyword
        String target;
        
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
                    break;
                    
                case "here":
                    break;
                    
                case "inventory":
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
        int j;
        String current_word;

        //intitializing dictionary

        for(i=0; i<TOTAL_WORDS; i++){
            listOfWords.add(null);
        }


        for(j=0; j<TOTAL_WORDS; j++){
            current_word= fDic.next();
            setWord(j, current_word);
        }
        
        fDic.close();
    }
    
    //sets the word in the commands.txt
    public static void setWord(int location, String word){
         listOfWords.set(location, word);
        
    }
    //gets the word in the commands .txt
    public static String getWord(int location){
       return listOfWords.get(location);
    }
    
   
    
    //saves player data to a file
    public static void savePlayer(String name, String password, String className, int level, int experience, String location, int xCoordinate, int yCoordinate, int zCoordinate, int bitcoins, String status, Object[] inventory, String weapon, String torso, String pants, String head, String shoes) throws FileNotFoundException, UnsupportedEncodingException{
     
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
            writer.println("Empty");
            
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
    
    public static void initializeMap(String location){
        
        int i;
        int j;
        int k;
        
        //later on we will inititalize map rooms individually.
        //For now we mass intitalize rooms to get a basic working map
        
        switch(location){
            case "The Pit":
                mainMap = new ThePit[100][100][5];
                className.setArea(mainMap);
                for(i=0; i<100; i++){
                    for(j=0; j<100; j++){
                        for(k=0; k<5; k++){
                            mainMap[i][j][k] = new ThePit("The Pit", "You look in horror as dead bodies lay everywhere. Something horrific must have happened.", (100*i)+(10*j)+k, i, j, k );
                            
                        }
                    }
                    
                }
                System.out.println("You have entered The Pit. Dead bodies of fallen heros lay everywhere. Will you join them or will you survive?");
                
                break;
            case "Central Hub":
                hubMap = new CentralHub[50][200][3];       
                className.setArea(hubMap);
                
                for(i=0; i<50; i++){
                    for(j=0; j<200; j++){
                        for(k=0; k<3; k++){
                            hubMap[i][j][k] = new CentralHub();
                            
                        }
                    }
                    
                }
                System.out.println("You have entered The Hub. The center of information and exchange.");
                break;
                
            case "Factory":
                factoryMap = new Factory[150][80][4];
                className.setArea(factoryMap);
                
                for(i=0; i<150; i++){
                    for(j=0; j<80; j++){
                        for(k=0; k<4; k++){
                            factoryMap[i][j][k] = new Factory();
                            
                        }
                    }
                    
                }
                System.out.println("Levers and cranks churn as you enter the factory.");
                break;
                
            case "Sewers":
                sewerMap = new Sewers[200][200][10];
                className.setArea(sewerMap);
                
                for(i=0; i<200; i++){
                    for(j=0; j<200; j++){
                        for(k=0; k<10; k++){
                            sewerMap[i][j][k] = new Sewers();
                            
                        }
                    }
                    
                }
                System.out.println("This place really reeks! Lets get out of here before you pass out!");
                break;
                
            case "TrainStation":
                trainMap = new TrainStation[200][10][1];
                className.setArea(trainMap);
                
                for(i=0; i<200; i++){
                    for(j=0; j<10; j++){
                        for(k=0; k<1; k++){
                            trainMap[i][j][k] = new TrainStation();
                            
                        }
                    }
                    
                }
                System.out.println("An old underground train station still remains here.");
            case "Wastelands":
                wastelandsMap = new Wastelands[300][300][20];
                className.setArea(wastelandsMap);
                
                for(i=0; i<300; i++){
                    for(j=0; j<300; j++){
                        for(k=0; k<20; k++){
                            wastelandsMap[i][j][k] = new Wastelands();
                            
                        }
                    }
                    
                }
                System.out.println("You are either very brave or very foolish for venturing out here.");
                break;
                
            case "Secret":
                secretMap = new Secret[5][5][5];
                className.setArea(secretMap);
                
                for(i=0; i<5; i++){
                    for(j=0; j<5; j++){
                        for(k=0; k<5; k++){
                            secretMap[i][j][k] = new Secret();
                        }
                    }
                    
                }
                System.out.println("Only a few have ever discovered this place.");
                break;        
                
            default:
                System.out.println("Error intitalizing map. Invalid map name please contact admin.");
                System.exit(0);
                break;
        }
    }
    public static void initalizeClass(String name) throws FileNotFoundException{
        
        String nameInFile;
        String classname;
        String location;
        String passwordInFile;
        int level = 1;
        int experience = 0;
        int xCoordinate = 0;
        int yCoordinate = 0;
        int zCoordinate = 0;
        int bitcoins = 0;
        String status;
        String inventory;
        String weapon;
        String head;
        String torso;
        String pants;
        String shoes;
        
        int health;
        int energy;
            
        try(Scanner sc = new Scanner(new File("src/PlayerInformation/" + name + ".txt"))){
            
            nameInFile = sc.nextLine();
            passwordInFile = sc.nextLine();
            classname = sc.nextLine();
            level = sc.nextInt();
            //used to read in extra newline character that appears after nextInt
            sc.nextLine();
            
            experience = sc.nextInt();
            sc.nextLine();
            location = sc.nextLine();
            xCoordinate = sc.nextInt();
            
            yCoordinate = sc.nextInt();
            
            zCoordinate = sc.nextInt();
            sc.nextLine();
            
            bitcoins = sc.nextInt();
            sc.nextLine();
            status = sc.nextLine();
            inventory = sc.nextLine();
            
            weapon = sc.nextLine();
            head = sc.nextLine();
            torso = sc.nextLine();
            pants = sc.nextLine();
            shoes = sc.nextLine();
            
            sc.close();
            
        }

        //creates new player associated with his/her class
        switch(classname){
            case "Cyborg":
                
                cyborg.setName(name);
                cyborg.setPassword(passwordInFile);
                cyborg.setClassName(classname);
                cyborg.setLevel(level);
                cyborg.setExperience(experience);
                cyborg.setLocation(location);
                cyborg.setxCoordinate(xCoordinate);
                cyborg.setyCoordinate(yCoordinate);
                cyborg.setzCoordinate(zCoordinate);
                
                cyborg.setBitcoins(bitcoins);
                cyborg.setStatus(status);
                //for now inventory will be empty we will implement this later
                cyborg.setInventory(null);

                cyborg.setHealth(100 + (level*30));
                cyborg.setEnergy(50);
                cyborg.setAttack(23);
                cyborg.setSpeed(21);
                cyborg.setDefense(20);
                cyborg.setIntelligence(22);
 
                cyborg.setWeapon(weapon);
                cyborg.setTorso(torso);
                cyborg.setHead(head);
                cyborg.setPants(pants);
                cyborg.setShoes(shoes);
                className = cyborg;
                break;
            
            case "NanoMedic":
                
                nanomedic.setName(name);
                nanomedic.setPassword(passwordInFile);
                nanomedic.setClassName(classname);
                nanomedic.setBitcoins(bitcoins);
                
                nanomedic.setHealth(90 + (level*10));
                nanomedic.setEnergy(70);
                nanomedic.setAttack(19);
                nanomedic.setSpeed(20);
                nanomedic.setDefense(19);
                nanomedic.setIntelligence(23);
                nanomedic.setLevel(level);
                nanomedic.setExperience(experience);
                
                nanomedic.setxCoordinate(xCoordinate);
                nanomedic.setyCoordinate(yCoordinate);
                nanomedic.setzCoordinate(zCoordinate);
                nanomedic.setLocation(location);
                nanomedic.setStatus(status);
                //for now inventory will be empty we will implement this later
                nanomedic.setInventory(null);
                
                nanomedic.setWeapon(weapon);
                nanomedic.setTorso(torso);
                nanomedic.setHead(head);
                nanomedic.setPants(pants);
                nanomedic.setShoes(shoes);
                className = nanomedic;
                break;
            
            case "Cyber Security Architect":
                
                cybersecurityarchitect.setName(name);
                cybersecurityarchitect.setPassword(passwordInFile);
                cybersecurityarchitect.setClassName(classname);
                cybersecurityarchitect.setBitcoins(bitcoins);
                
                cybersecurityarchitect.setHealth(60 + (level*15));
                cybersecurityarchitect.setEnergy(90);
                cybersecurityarchitect.setAttack(18);
                cybersecurityarchitect.setSpeed(21);
                cybersecurityarchitect.setDefense(19);
                cybersecurityarchitect.setIntelligence(25);
                cybersecurityarchitect.setLevel(level);
                cybersecurityarchitect.setExperience(experience);
                
                cybersecurityarchitect.setxCoordinate(xCoordinate);
                cybersecurityarchitect.setyCoordinate(yCoordinate);
                cybersecurityarchitect.setzCoordinate(zCoordinate);
                cybersecurityarchitect.setLocation(location);
                cybersecurityarchitect.setStatus(status);
                //for now inventory will be empty we will implement this later
                cybersecurityarchitect.setInventory(null);
                
                cybersecurityarchitect.setWeapon(weapon);
                cybersecurityarchitect.setTorso(torso);
                cybersecurityarchitect.setHead(head);
                cybersecurityarchitect.setPants(pants);
                cybersecurityarchitect.setShoes(shoes);
                
                className = cybersecurityarchitect;
                break;
            
            case "Time Traveler":
                
                timetraveler.setName(name);
                timetraveler.setPassword(passwordInFile);
                timetraveler.setClassName(classname);
                timetraveler.setBitcoins(bitcoins);
                
                timetraveler.setHealth(70 + (level*20));
                timetraveler.setEnergy(100);
                timetraveler.setAttack(20);
                timetraveler.setSpeed(25);
                timetraveler.setDefense(19);
                timetraveler.setIntelligence(24); 
                timetraveler.setLevel(level);
                timetraveler.setExperience(experience);
                
                timetraveler.setxCoordinate(xCoordinate);
                timetraveler.setyCoordinate(yCoordinate);
                timetraveler.setzCoordinate(zCoordinate);
                timetraveler.setLocation(location);
                timetraveler.setStatus(status);
                
                timetraveler.setInventory(null);
                
                timetraveler.setWeapon(weapon);
                timetraveler.setTorso(torso);
                timetraveler.setHead(head);
                timetraveler.setPants(pants);
                timetraveler.setShoes(shoes);
                className = timetraveler;
                break;
            
            case "Blackhand Rogue":
                
                blackhandrogue.setName(name);
                blackhandrogue.setPassword(passwordInFile);
                blackhandrogue.setClassName(classname);
                blackhandrogue.setBitcoins(bitcoins);
                
                blackhandrogue.setHealth(80 + (level*25));
                blackhandrogue.setEnergy(80);
                blackhandrogue.setAttack(22);
                blackhandrogue.setSpeed(26);
                blackhandrogue.setDefense(19);
                blackhandrogue.setIntelligence(21);
                blackhandrogue.setLevel(level);
                blackhandrogue.setExperience(experience);
                
                blackhandrogue.setxCoordinate(xCoordinate);
                blackhandrogue.setyCoordinate(yCoordinate);
                blackhandrogue.setzCoordinate(zCoordinate);
                blackhandrogue.setLocation(location);
                blackhandrogue.setStatus(status);
                //for now inventory will be empty we will implement this later
                blackhandrogue.setInventory(null);
                
                blackhandrogue.setWeapon(weapon);
                blackhandrogue.setTorso(torso);
                blackhandrogue.setHead(head);
                blackhandrogue.setPants(pants);
                blackhandrogue.setShoes(shoes);
                className = blackhandrogue;
                break;
            //in case the file did not read the class properly it will terminate application
            default:
                System.out.println("Error invalid class or did not read data properly. Contact admin.");
                System.exit(0);
                
        }
    }
    
    
    
    
}
