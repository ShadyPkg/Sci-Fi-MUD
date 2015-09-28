/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scifimud;

import classes.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static scifimud.ConnectPlayer.blackhandrogue;
import static scifimud.ConnectPlayer.cybersecurityarchitect;
import static scifimud.ConnectPlayer.cyborg;
import static scifimud.ConnectPlayer.nanomedic;
import static scifimud.ConnectPlayer.timetraveler;

/**
 *
 * @author root
 */
public class ClassCreator {
    //will be used as a global reference to the class the player selected
    static Player className;
    public static void initalizeClass(String name) throws FileNotFoundException, IOException{
        
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
        //stores all possible items in memory for faster access
        ObjectCreator.readEquipmentFile("drinks", ObjectCreator.TOTAL_DRINKS, ObjectCreator.listofDrinks);
        ObjectCreator.readEquipmentFile("food", ObjectCreator.TOTAL_FOOD, ObjectCreator.listofFood);
        ObjectCreator.readEquipmentFile("head", ObjectCreator.TOTAL_HEAD, ObjectCreator.listofHead);
        ObjectCreator.readEquipmentFile("objects", ObjectCreator.TOTAL_OBJECTS, ObjectCreator.listofObjects);
        ObjectCreator.readEquipmentFile("pants", ObjectCreator.TOTAL_PANTS, ObjectCreator.listofPants);
        ObjectCreator.readEquipmentFile("shoes", ObjectCreator.TOTAL_SHOES, ObjectCreator.listofShoes);
        ObjectCreator.readEquipmentFile("torso", ObjectCreator.TOTAL_TORSO, ObjectCreator.listofTorso);
        ObjectCreator.readEquipmentFile("weapons", ObjectCreator.TOTAL_WEAPONS, ObjectCreator.listofWeapons);
        //creating players inventory and the objects in the inventory
        ObjectCreator.createInventory(inventory);
        
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