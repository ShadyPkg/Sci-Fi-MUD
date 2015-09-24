/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scifimud;

import PlayerInformation.EncryptString;
import classes.BlackHandRogue;
import classes.CyberSecurityArchitect;
import classes.Cyborg;
import classes.NanoMedic;
import classes.Player;
import classes.TimeTraveler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

/**
 *
 * @author root
 */
//creates an instance to connect a player
public class ConnectPlayer {
    private static final int TOTAL_WORDS = 21;
    public static ArrayList<String> listOfWords = new ArrayList<>();
    
    
    public ConnectPlayer(){
        
    }
    //connects player and gets data to setup a player on the map
    public void connect(String name) throws FileNotFoundException, IOException, URISyntaxException{
        String nameInFile;
        String className;
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
        
       
        
        try(Scanner sc = new Scanner(new File("src/PlayerInformation/" + name + ".txt"))){
            
            nameInFile = sc.nextLine();
            passwordInFile = sc.nextLine();
            className = sc.nextLine();
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
        switch(className){
            case "Cyborg":
                Cyborg cyborg = new Cyborg();
                cyborg.setHealth(100);
                cyborg.setEnergy(50);
                cyborg.setAttack(23);
                cyborg.setSpeed(21);
                cyborg.setDefense(20);
                cyborg.setIntelligence(22);
                break;
            
            case "NanoMedic":
                NanoMedic nanomedic = new NanoMedic();
                nanomedic.setHealth(90);
                nanomedic.setEnergy(70);
                nanomedic.setAttack(19);
                nanomedic.setSpeed(20);
                nanomedic.setDefense(19);
                nanomedic.setIntelligence(23);
                break;
            
            case "Cyber Security Architect":
                CyberSecurityArchitect cybersecurityarchitect = new CyberSecurityArchitect();
                cybersecurityarchitect.setHealth(60);
                cybersecurityarchitect.setEnergy(90);
                cybersecurityarchitect.setAttack(18);
                cybersecurityarchitect.setSpeed(21);
                cybersecurityarchitect.setDefense(19);
                cybersecurityarchitect.setIntelligence(25);
                break;
            
            case "Time Traveler":
                TimeTraveler timetraveler = new TimeTraveler();
                timetraveler.setHealth(70);
                timetraveler.setEnergy(100);
                timetraveler.setAttack(20);
                timetraveler.setSpeed(25);
                timetraveler.setDefense(19);
                timetraveler.setIntelligence(24);    
                break;
            
            case "Blackhand Rogue":
                BlackHandRogue blackhandrogue = new BlackHandRogue();
                blackhandrogue.setHealth(80);
                blackhandrogue.setEnergy(80);
                blackhandrogue.setAttack(22);
                blackhandrogue.setSpeed(26);
                blackhandrogue.setDefense(19);
                blackhandrogue.setIntelligence(21);
                break;
            //in case the file did not read the class properly it will terminate application
            default:
                System.out.println("Error invalid class or did not read data properly. Contact admin.");
                System.exit(0);
                
        }
        //reading in words
        readDict();
        //intitalizing map
        ThePit[][][] mainMap = null;
        CentralHub[][][] hubMap = null;
        Factory[][][] factoryMap = null;
        Sewers[][][] sewerMap = null;
        TrainStation[][][] trainMap = null;
        Wastelands[][][] wastelandsMap = null;
        Secret[][][] secretMap = null;
        int i;
        int j;
        int k;
        //later on we will inititalize map rooms individually.
        //For now we mass intitalize rooms to get a basic working map
        switch(location){
            case "The Pit":
                mainMap = new ThePit[100][100][5];
                
                for(i=0; i<100; i++){
                    for(j=0; j<100; j++){
                        for(k=0; k<5; k++){
                            mainMap[i][j][k] = new ThePit("The Pit", "You look in horror as dead bodies lay everywhere. Something horrific must have happened.", (100*i)+(10*j)+k );
                        }
                    }
                    
                }
                System.out.println("You have entered The Pit. Dead bodies of fallen heros lay everywhere. Will you join them or will you survive?");
                
                break;
            case "Central Hub":
                hubMap = new CentralHub[50][200][3];
                
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
 
        //loop to read in input of player
        int gameOver = 0;
        Scanner stdin = new Scanner(System.in);
        String command;
        
        while(true){
            if(gameOver == 1){
                System.out.println("Goodbye!");
                System.exit(0);
            }    
            
            
           command = stdin.nextLine();
           //str1.contains(str2)
           command = binarySearch(command); 

            
            switch(command){
                case "bitcoins":
                    System.out.println("You are carrying " + bitcoins + " bitcoins.");
                    break;
                case "down":
                    if(mainMap[xCoordinate][yCoordinate][zCoordinate].isDown()){
                        zCoordinate--;
                        //used for debugging purposes to make sure room is updating
                        //in the future we will add displayRoom objects and descriptions
                        displayRoomCoordinates(xCoordinate, yCoordinate, zCoordinate);
                    }
                    break;
                    
                case "east":
                    if(mainMap[xCoordinate][yCoordinate][zCoordinate].isEast()){
                        xCoordinate++;
                        //used for debugging purposes to make sure room is updating
                        //in the future we will add displayRoom objects and descriptions
                        displayRoomCoordinates(xCoordinate, yCoordinate, zCoordinate);
                    }
                    else{
                        System.out.println("There is no exit there.");
                    }
                    break;
                    
                case "equipment":
                    System.out.println("You are wearing :");
                    System.out.println("Weapon : " + weapon);
                    System.out.println("Head : " + head);
                    System.out.println("Torso : " + torso);
                    System.out.println("Head : " + head);
                    System.out.println("Shoes : " + shoes);
                    break;
                case "examine":
                    break;
                    
                case "here":
                    break;
                    
                case "inventory":
                    break;
                case "level":
                    System.out.println("You are level " + level);
                    break;
                    
                case "location":
                    System.out.println("You are at the " + location);
                    break;
                    
                case "look":
                    System.out.println(ThePit.roomDescription);
                    break;
                    
                case  "north":
                    if(mainMap[xCoordinate][yCoordinate][zCoordinate].isNorth()){
                        yCoordinate++;
                        //used for debugging purposes to make sure room is updating
                        //in the future we will add displayRoom objects and descriptions
                        displayRoomCoordinates(xCoordinate, yCoordinate, zCoordinate);
                    }
                    else{
                        System.out.println("There is no exit there.");
                    }
                    break;
                case "quit":
                    savePlayer(name, passwordInFile, className, level, experience, location, xCoordinate, yCoordinate, zCoordinate, bitcoins, status, inventory, weapon, torso, pants, head, shoes);
                    System.out.println("Goodbye!");
                    System.exit(0);    
                    break;
                    
                case "save":
                    savePlayer(name, passwordInFile, className, level, experience, location, xCoordinate, yCoordinate, zCoordinate, bitcoins, status, inventory, weapon, torso, pants, head, shoes);
                    break;
                    
                case "say":
                    break;
                    
                case "self":
                    break;
                case "south":
                    if(mainMap[xCoordinate][yCoordinate][zCoordinate].isSouth()){
                        yCoordinate--;
                        //used for debugging purposes to make sure room is updating
                        //in the future we will add displayRoom objects and descriptions
                        displayRoomCoordinates(xCoordinate, yCoordinate, zCoordinate);
                    }
                    else{
                        System.out.println("There is no exit there.");
                    }
                    break;
                    
                case "status":
                    System.out.println("You are " + status);
                    break;
                    
                case "tell":
                    break;
                    
                case "up":
                    if(mainMap[xCoordinate][yCoordinate][zCoordinate].isUp()){
                        zCoordinate++;
                        //used for debugging purposes to make sure room is updating
                        //in the future we will add displayRoom objects and descriptions
                        displayRoomCoordinates(xCoordinate, yCoordinate, zCoordinate);
                    }
                    else{
                        System.out.println("There is no exit there.");
                    }
                    break;
                    
                case "west":
                    if(mainMap[xCoordinate][yCoordinate][zCoordinate].isWest()){
                        xCoordinate--;
                        //used for debugging purposes to make sure room is updating
                        //in the future we will add displayRoom objects and descriptions
                        displayRoomCoordinates(xCoordinate, yCoordinate, zCoordinate);
                    }
                    else{
                        System.out.println("There is no exit there.");
                    }
                    break;
                    
                case "yell":
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
            if(listOfWords.get(mid).contains(word)){
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
    
    //displays room coordinates. Useful for debugging
    public static void displayRoomCoordinates(int xCoordinate, int yCoordinate, int zCoordinate){
        System.out.printf("You are located in the room coordinates %d %d %d", xCoordinate, yCoordinate, zCoordinate);
    }
    
    //saves player data to a file
    public static void savePlayer(String name, String password, String className, int level, int experience, String location, int xCoordinate, int yCoordinate, int zCoordinate, int bitcoins, String status, String inventory, String weapon, String torso, String pants, String head, String shoes) throws FileNotFoundException, UnsupportedEncodingException{
     
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
}
