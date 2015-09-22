/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scifimud;

import classes.BlackHandRogue;
import classes.CyberSecurityArchitect;
import classes.Cyborg;
import classes.NanoMedic;
import classes.TimeTraveler;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    
    
    
    public ConnectPlayer(){
        
    }
    //connects player and gets data to setup a player on the map
    public void connect(String name) throws FileNotFoundException, IOException{
        String nameInFile;
        String className;
        String location;
        
        try(BufferedReader br = new BufferedReader(new FileReader("src/PlayerInformation/" + name + ".txt"))){
            
            nameInFile = br.readLine();
            String passwordInFile = br.readLine();
            className = br.readLine();
            String level = br.readLine();
            String experience = br.readLine();
            location = br.readLine();
            String mapCoordinates = br.readLine();
            
            String bitcoins = br.readLine();
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
                
        }
        //intitalizing map
        ThePit[][][] mainMap;
        CentralHub[][][] hubMap;
        Factory[][][] factoryMap;
        Sewers[][][] sewerMap;
        TrainStation[][][] trainMap;
        Wastelands[][][] wastelandsMap;
        Secret[][][] secretMap;
        
        switch(location){
            case "The Pit":
                mainMap = new ThePit[100][100][5];
                System.out.println("You have entered The Pit. Dead bodies of fallen heros lay everywhere. Will you join them or will you survive?");
                
                break;
            case "Central Hub":
                hubMap = new CentralHub[50][200][3];
                System.out.println("You have entered The Hub. The center of information and exchange.");
                break;
                
            case "Factory":
                factoryMap = new Factory[150][80][4];
                System.out.println("Levers and cranks churn as you enter the factory.");
                break;
                
            case "Sewers":
                sewerMap = new Sewers[200][200][10];
                System.out.println("This place really reeks! Lets get out of here before you pass out!");
                break;
                
            case "TrainStation":
                trainMap = new TrainStation[200][10][1];
                System.out.println("An old underground train station still remains here.");
            case "Wastelands":
                wastelandsMap = new Wastelands[300][300][20];
                System.out.println("You are either very brave or very foolish for venturing out here.");
                break;
                
            case "Secret":
                secretMap = new Secret[5][5][5];
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
        }
    }
    
    
}
