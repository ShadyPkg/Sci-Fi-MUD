/*
 * Jonathan Chin
 * 9/16/15
 */
package scifimud;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

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
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException  {
        // Need to add splash screen login
        System.out.println("Welcome to Digital Wasteland.");
        //reads in name and pass into a text file
        System.out.println("What is your name?");
        String name;
        Scanner stdin = new Scanner(System.in);
        name = stdin.nextLine();
        
        //checks to see if the name exists
        if(checkName(name)){
            System.out.println("Welcome " + name);
            connectPlayer();
        }
        else{
            //makes user enter password twice to confirm password is correct
            System.out.println("Hello " + name + ". Please enter a password.");
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
            System.out.println("The classes are Cyborg, Nanomedic, Cyber Security Architect, , Time Traveller, and BlackHandRogue");
            System.out.println("Type the abbreviations for the class");
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
    
    //saves name of user, pass, class, and stats into a file.
    static void saveName(String name, String password, String className) throws URISyntaxException, FileNotFoundException{
        //relative file path
        URL url = SciFiMUD.class.getResource(name);
         
        File file = new File(url.toURI());
        
        Scanner fin;
    
        fin = new Scanner((file));
    }
    
    //once user is authenticated the player will be connected
    static void connectPlayer(){
        
    }
}
