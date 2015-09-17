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
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException  {
        // Need to add splash screen login
        System.out.println("Welcome to Digital Wasteland.");
        //reads in name and pass into a text file
        String name;
        Scanner stdin = new Scanner(System.in);     
        while(true){
            System.out.println("What is your name?");
            name = stdin.nextLine();
            //makes sure name has no spaces, numbers, or special charcters and
            //length of name is between 3 to 12 charaters. Still need to add filtering for brackets and quotation marks
            String namePattern = "([^\\s!@#$%^&*()1234567890-_+=:;',.<>?/|'{}\\/']{3,12})";
            Pattern pattern = Pattern.compile(namePattern);
            Matcher matcher = pattern.matcher(name);
            if(matcher.matches()){
                System.out.println("That is a good name.");
                break;
            }
            else{
                System.out.println("No spaces, numbers, special characters and name must be between length of 3 to 12 characters.");
            }
        }
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
