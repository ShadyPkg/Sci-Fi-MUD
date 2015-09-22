/*
 * Jonathan Chin
 * 9/16/15
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
import java.security.GeneralSecurityException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jonc
 */
public class SciFiMUD {

    /**
     * @param args the command line arguments
     * @throws java.net.URISyntaxException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException, UnsupportedEncodingException, GeneralSecurityException, IOException  {
        // Need to add splash screen login
        System.out.println("Welcome to Digital Wasteland.");
        //reads in name and pass into a text file
        String name;
        Scanner stdin = new Scanner(System.in);     
        while(true){
            System.out.println("What is your name?");
            name = stdin.nextLine();
            //makes sure name has no spaces, numbers, or special charcters and
            //length of name is between 3 to 12 charaters. 
            Pattern pattern = Pattern.compile("[A-Za-z]{3,12}");
            Matcher matcher = pattern.matcher(name);
            if(matcher.matches()){ 
                break;
            }
            else{
                System.out.println("No spaces, numbers, special characters and name must be between length of 3 to 12 characters.");
            }
        }
        //checks to see if the name exists
        if(checkName(name)){
           
            
            String password;
            System.out.println("What is your password?");
            password = stdin.nextLine();
            if(checkPassword(name, password) == 1){
                System.out.println("Welcome " + name);
                connectPlayer(name);
            }
            else{
                //if failed then user is disconnected to prevent bruteforce hack attemps
                System.out.println("Name or password is incorrect.");
                System.exit(0);
            }
        }
        else{
            System.out.println("That is a good name.");
            //makes user enter password twice to confirm password is correct
            System.out.println("Please enter a password.");
            String password;
            
            while(true){

                password = stdin.nextLine();
                String confirmPassword;
                System.out.println("Please enter your password to confirm again.");
                confirmPassword = stdin.nextLine();
                if(password.equals(confirmPassword)){
                    System.out.printf("Success your passwords matched.");
                    break;
                }
                else{
                    System.out.println("Password did not match. Please enter it again.");
                }
            } 
            System.out.println("Please pick your class.");
            System.out.println("The classes are Cyborg, Nanomedic, Cyber Security Architect, , Time Traveler, and BlackHandRogue");
            System.out.println("Type the abbreviations for the class which are cyborg, nano, cyber, time, black.");
            String classChosen = "dummy";
            //makes sure the player picks a valid class using the abbreviations
            //note only Java version 7 or higher has strings for switch cases. Update Java version if its not working
            int correct = 0;
            while(correct==0){
                classChosen = stdin.nextLine();
                switch(classChosen){
                    case "cyborg":
                        classChosen = "Cyborg";
                        correct = 1;
                        break;
                    case "nano":
                        classChosen = "NanoMedic";
                        correct = 1;
                        break;
                    case "cyber":
                        classChosen = "Cyber Security Architect";
                        correct = 1;
                        break;
                    case "time":
                        classChosen = "Time Traveler";
                        correct = 1;
                        break;
                    case "black":
                        classChosen = "Blackhand Rogue";
                        correct = 1;
                        break;
                    default:
                        System.out.println("Please type in the abbreviation correctly for the class you want");
                        break;
                }
            }
            System.out.println("Saving data please wait...");
            //saving player data to a file
            savePlayer(name, password, classChosen);
            System.out.println("Successfully saved!");
            
        }
        
        
                 
        
        
    }
    
    //checks to see if a text file with the name exists
    static boolean checkName(String name) throws URISyntaxException, FileNotFoundException{
        
        //relative file path
        name = "src/PlayerInformation/" + name +".txt";
        
      
        File file = new File(name);
  
        if(file.exists() && !file.isDirectory()){
            return true;
        } 
        return false;
          
    }
    
    //saves name of user, pass, class, and stats into a file. This is function is only for saving
    //a player who is new for the first time
    static void savePlayer(String name, String password, String className) throws URISyntaxException, FileNotFoundException, UnsupportedEncodingException, GeneralSecurityException{
        
        try (PrintWriter writer = new PrintWriter("src/PlayerInformation/" + name + ".txt", "UTF-8")) {
            //name
            writer.println(name);
      
            //encrypting password
            password = EncryptString.encrypt(password);
            writer.println(password);
            //class 
            writer.println(className);
            //level of player
            writer.println("1");
            //experience of player
            writer.println("0");
           
            //map location of player
            writer.println("ThePit");
            //map coordinates where player is located
            writer.printf("%d %d %d\n", 0, 0, 0);
            //amount of gold player is carrying
            writer.printf("%d\n", 0);
            //players health status, ex: healthy, blind, sick, etc. 
            //multiple affliction status will be separated by commas
            writer.println("Normal");
            //players inventory, all items are separated by a comma and will
            //use a strtok to break up the items
            writer.println("Empty");
            
            //players weapon equipment
            writer.println("Nothing");
            //players head equipment
            writer.println("Nothing");
            //players torso equipment
            writer.println("Nothing");
            //players pants equipments
            writer.println("Nothing");
            //players shoe equipment
            writer.println("Nothing");
            
            writer.close();
        }
    }
    
    //checking if players password matches
    static int checkPassword(String name, String password) throws FileNotFoundException, IOException, GeneralSecurityException{
        try(BufferedReader br = new BufferedReader(new FileReader("src/PlayerInformation/" + name + ".txt"))){
            
            String nameInFile = br.readLine();
            String passwordInFile = br.readLine();
            passwordInFile = EncryptString.decrypt(passwordInFile);
            
            if( nameInFile.equals(name) && passwordInFile.equals(password)){
                br.close();
                return 1;
            }
                  
        }
        
        return 0;
        
    }
    //connects player and gets data to setup a player on the map
    static void connectPlayer(String name) throws FileNotFoundException, IOException{
        String nameInFile;
        String className;
        
        try(BufferedReader br = new BufferedReader(new FileReader("src/PlayerInformation/" + name + ".txt"))){
            
            nameInFile = br.readLine();
            String passwordInFile = br.readLine();
            className = br.readLine();
            String level = br.readLine();
            String experience = br.readLine();
            String location = br.readLine();
            String mapCoordinates = br.readLine();
            
            String gold = br.readLine();
            String status = br.readLine();
            String inventory = br.readLine();
            
            String weapon = br.readLine();
            String head = br.readLine();
            String torso = br.readLine();
            String pants = br.readLine();
            String shoes = br.readLine();
            
            br.close();
              
            
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
                
        };
    }
}
