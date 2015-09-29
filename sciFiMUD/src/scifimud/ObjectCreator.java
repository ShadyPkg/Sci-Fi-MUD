/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scifimud;

import Equipment.Artifact;
import Equipment.Drink;
import Equipment.Food;
import Equipment.Head;
import Equipment.Item;
import Equipment.Pants;
import Equipment.Shoes;
import Equipment.Torso;
import Equipment.Weapons;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author jonc
 */
//this class is responsible for managing players inventory and creating all objects and items in the game
public class ObjectCreator {
    
    //total number of items in each text file
    //everytime you add items in the text file you need to update this.
    //Make sure to alphebetize files
    static int TOTAL_DRINKS = 2;
    static int TOTAL_FOOD = 3;
    static int TOTAL_HEAD = 0;
    static int TOTAL_OBJECTS = 0;
    static int TOTAL_PANTS = 0;
    static int TOTAL_SHOES = 2;
    static int TOTAL_TORSO = 0;
    static int TOTAL_WEAPONS = 4;
    
    public static ArrayList<String> listofDrinks = new ArrayList<>();
    public static ArrayList<String> listofFood = new ArrayList<>();
    public static ArrayList<String> listofHead = new ArrayList<>();
    public static ArrayList<String> listofObjects = new ArrayList<>();
    public static ArrayList<String> listofPants = new ArrayList<>();
    public static ArrayList<String> listofShoes = new ArrayList<>();
    public static ArrayList<String> listofTorso = new ArrayList<>();
    public static ArrayList<String> listofWeapons = new ArrayList<>();
    
    //inventory is a string read from file and it parses that string to create all the objects and places it in players inventory
    public static ArrayList<Item> createInventory(String inventory, ArrayList<Item> playerInventory) throws IOException{
        
        //if the inventory is empty then we set it to null
        if(inventory.equals("empty")){
            playerInventory = null;
            
        }
        else{
            String[] newInventory  = inventory.split(", ");
            int i;
            for(i=0; i<newInventory.length; i++){
                playerInventory = searchInventoryDatabase(newInventory[i], "drinks", TOTAL_DRINKS, listofDrinks, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "food", TOTAL_FOOD, listofFood, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "head", TOTAL_HEAD, listofHead, playerInventory);   
                playerInventory = searchInventoryDatabase(newInventory[i], "artifacts", TOTAL_OBJECTS, listofFood, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "pants", TOTAL_PANTS, listofPants, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "shoes", TOTAL_SHOES, listofShoes, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "torso", TOTAL_TORSO, listofTorso, playerInventory);
                playerInventory = searchInventoryDatabase(newInventory[i], "weapons", TOTAL_WEAPONS, listofWeapons, playerInventory);
                
                    
                }
                
            }
        return playerInventory;
            
    }
        
    
    //takes all objects in inventory and returns a string of all the objects.
    public static  String getInventory(ArrayList<Item> inventory){
        int i;
        String newInventory = "";
        if(inventory!=null){
            
            for(i=0; i<inventory.size(); i++){
                newInventory = newInventory + inventory.get(i).getName() + ", ";
            }
            
            return newInventory;
        }
        //if the inventory is null this will be returned
        return "empty";
       
    }
    //searches the inventory database looking for piece of equipment based on its name, if the name is found
    //in the database this function will call createObject to create the item or weapon and it will be placed
    //in the players inventory. If this function returns true that means the object was sucessfully created
    //else it returns false.All the textfiles will be alphabetically sorted based on 
    //the name of the object so a binary search can be implemented for log n run time.
    // alphabetizer.flap.tv/ is the website that can sort a list in alphebetical order based on each line
    public static ArrayList<Item> searchInventoryDatabase(String name, String fileName, int total, ArrayList<String> arrayList, ArrayList<Item> playerInventory) throws FileNotFoundException, IOException {
        
        

        int low=0;
        int high = total-1;
        int mid;


        while(low<= high){

            mid=(high+low)/2;
            //if a partial match is found return the word
            if(arrayList.get(mid).startsWith(name)){
                switch(fileName){
                    case "drinks":
                        playerInventory.add(createDrink(arrayList.get(mid)));
                        break;
                    case "food":
                        playerInventory.add(createFood(arrayList.get(mid)));
                        break;
                    case "head":
                        playerInventory.add(createHead(arrayList.get(mid)));
                        break;
                    case "objects":
                        playerInventory.add(createArtifact(arrayList.get(mid)));
                        break;
                    case "pants":
                        playerInventory.add(createPants(arrayList.get(mid)));
                        break;
                    case "shoes":
                        playerInventory.add(createShoes(arrayList.get(mid)));
                        break;
                    case "torso":
                        playerInventory.add(createTorso(arrayList.get(mid)));
                        break;
                    case "weapons":
                        playerInventory.add(createWeapon(arrayList.get(mid)));
                        break;
                    default:
                        System.out.println("Error invalid equipment category. Please contact admin.");
                        break;
                }
                
            }
            //if the word being searched comes before the word in the dictionary
            //then the you want to search an earlier word
            if(name.compareTo((String) arrayList.get(mid))<0){
                high=mid-1;
                
            }
            //if the word being searched comes after the word in the dictionary 
            //then you wan tto search for a later word
            else if(name.compareTo((String) arrayList.get(mid))>0){
                low=mid+1;
                
            }

        }
        return playerInventory;
        
    }
    //these functions all create objects/items/weapons/equipment based on the String in one of the files
    //these strings need to be parsed. The item/object is then returned
    public static Item createDrink(String inventory){
        String[] newInventory  = inventory.split(", ");
        Drink drink = new Drink();
        int i = 0;
        drink.setName(newInventory[i]);
        i++;
        drink.setHealth(Integer.parseInt(newInventory[i]));
        i++;
        drink.setEnergy(Integer.parseInt(newInventory[i]));
        i++;
        drink.setSpeed(Integer.parseInt(newInventory[i]));
        i++;
        drink.setAttack(Integer.parseInt(newInventory[i]));
        i++;
        drink.setDefense(Integer.parseInt(newInventory[i]));
        i++;
        drink.setIntelligence(Integer.parseInt(newInventory[i]));
        i++;
        drink.setDuration(Integer.parseInt(newInventory[i]));
        i++;
        drink.setSpecialEffects(newInventory[i]);
        
        System.out.println(drink.getName() + " was successfully created!");
                
        return drink;
    }
    public static Item createFood(String inventory){
        String[] newInventory  = inventory.split(", ");
        Food food = new Food();
        int i = 0;
        food.setName(newInventory[i]);
        i++;
        food.setHealth(Integer.parseInt(newInventory[i]));
        i++;
        food.setEnergy(Integer.parseInt(newInventory[i]));
        i++;
        food.setSpeed(Integer.parseInt(newInventory[i]));
        i++;
        food.setAttack(Integer.parseInt(newInventory[i]));
        i++;
        food.setDefense(Integer.parseInt(newInventory[i]));
        i++;
        food.setIntelligence(Integer.parseInt(newInventory[i]));
        i++;
        food.setDuration(Integer.parseInt(newInventory[i]));
        i++;
        food.setSpecialEffects(newInventory[i]);
        
        System.out.println(food.getName() + " was successfully created!");
        
        return food;
    }
    //creates an object or item such as a potion or artifact
    public static Item createArtifact(String inventory){
        String[] newInventory  = inventory.split(", ");
        Artifact artifact = new Artifact();
        int i = 0;
        artifact.setName(newInventory[i]);
        i++;
        artifact.setHealth(Integer.parseInt(newInventory[i]));
        i++;
        artifact.setEnergy(Integer.parseInt(newInventory[i]));
        i++;
        artifact.setSpeed(Integer.parseInt(newInventory[i]));
        i++;
        artifact.setAttack(Integer.parseInt(newInventory[i]));
        i++;
        artifact.setDefense(Integer.parseInt(newInventory[i]));
        i++;
        artifact.setIntelligence(Integer.parseInt(newInventory[i]));
        i++;
        artifact.setSpecialEffects(newInventory[i]);
        
        System.out.println(artifact.getName() + " was successfully created!");
        
        return artifact;
    }
    //this is a function that is called to create a weapon
    public static Item createWeapon(String inventory){
        String[] newInventory  = inventory.split(", ");
        Weapons weapon = new Weapons();
        int i = 0;
        weapon.setName(newInventory[i]);
        i++;
        weapon.setHealth(Integer.parseInt(newInventory[i]));
        i++;
        weapon.setEnergy(Integer.parseInt(newInventory[i]));
        i++;
        weapon.setSpeed(Integer.parseInt(newInventory[i]));
        i++;
        weapon.setAttack(Integer.parseInt(newInventory[i]));
        i++;
        weapon.setDefense(Integer.parseInt(newInventory[i]));
        i++;
        weapon.setIntelligence(Integer.parseInt(newInventory[i]));    
        i++;
        weapon.setSpecialEffect(newInventory[i]);
        i++;
        weapon.setType(newInventory[i]);
        
        System.out.println(weapon.getName() + " was successfully created!");
        
        return weapon;
    }
    //creates a torso object that a player can wear
    public static Item createTorso(String inventory){
        String[] newInventory  = inventory.split(", ");
        Torso torso = new Torso();
        int i = 0;
        torso.setName(newInventory[i]);
        i++;
        torso.setHealth(Integer.parseInt(newInventory[i]));
        i++;
        torso.setEnergy(Integer.parseInt(newInventory[i]));
        i++;
        torso.setSpeed(Integer.parseInt(newInventory[i]));
        i++;
        torso.setAttack(Integer.parseInt(newInventory[i]));
        i++;
        torso.setDefense(Integer.parseInt(newInventory[i]));
        i++;
        torso.setIntelligence(Integer.parseInt(newInventory[i]));    
        i++;
        torso.setSpecialEffect(newInventory[i]);
        i++;
        torso.setType(newInventory[i]);
        
        System.out.println(torso.getName() + " was successfully created!");
        
        return torso;
    }
    //creates a head object that a player can wear
    public static Item createHead(String inventory){
        String[] newInventory  = inventory.split(", ");
        Head head = new Head();
        int i = 0;
        head.setName(newInventory[i]);
        i++;
        head.setHealth(Integer.parseInt(newInventory[i]));
        i++;
        head.setEnergy(Integer.parseInt(newInventory[i]));
        i++;
        head.setSpeed(Integer.parseInt(newInventory[i]));
        i++;
        head.setAttack(Integer.parseInt(newInventory[i]));
        i++;
        head.setDefense(Integer.parseInt(newInventory[i]));
        i++;
        head.setIntelligence(Integer.parseInt(newInventory[i]));    
        i++;
        head.setSpecialEffect(newInventory[i]);
        i++;
        head.setType(newInventory[i]);
        
        System.out.println(head.getName() + " was successfully created!");
        
        return head;
    }
    //creates a shoe object that a player can wear
    public static Item createShoes(String inventory){
        String[] newInventory  = inventory.split(", ");
        Shoes shoes = new Shoes();
        int i = 0;
        shoes.setName(newInventory[i]);
        i++;
        shoes.setHealth(Integer.parseInt(newInventory[i]));
        i++;
        shoes.setEnergy(Integer.parseInt(newInventory[i]));
        i++;
        shoes.setSpeed(Integer.parseInt(newInventory[i]));
        i++;
        shoes.setAttack(Integer.parseInt(newInventory[i]));
        i++;
        shoes.setDefense(Integer.parseInt(newInventory[i]));
        i++;
        shoes.setIntelligence(Integer.parseInt(newInventory[i]));    
        i++;
        shoes.setSpecialEffect(newInventory[i]);
        i++;
        shoes.setType(newInventory[i]);
        
        System.out.println(shoes.getName() + " was successfully created!");
        
        return shoes;
    }
    public static Item createPants(String inventory){
        String[] newInventory  = inventory.split(", ");
        Pants pants = new Pants();
        int i = 0;
        pants.setName(newInventory[i]);
        i++;
        pants.setHealth(Integer.parseInt(newInventory[i]));
        i++;
        pants.setEnergy(Integer.parseInt(newInventory[i]));
        i++;
        pants.setSpeed(Integer.parseInt(newInventory[i]));
        i++;
        pants.setAttack(Integer.parseInt(newInventory[i]));
        i++;
        pants.setDefense(Integer.parseInt(newInventory[i]));
        i++;
        pants.setIntelligence(Integer.parseInt(newInventory[i]));    
        i++;
        pants.setSpecialEffect(newInventory[i]);
        i++;
        pants.setType(newInventory[i]);
        
        System.out.println(pants.getName() + " was successfully created!");
        
        return pants;
    }
    
    //this function can be used to reach each text file in equipment package
    //and stores all entries into the corresponding arraylist
    public static void readEquipmentFile(String file, int total, ArrayList<String> arrayList) throws FileNotFoundException{
        
        try(Scanner br = new Scanner(new FileReader("src/Equipment/" + file + ".txt"))){
            
            int i;
            String current_word;
            
            for(i=0; i<total; i++){
                arrayList.add(null);
            }

            for(i=0; i<total; i++){
                current_word= br.nextLine();
                arrayList.set(i, current_word);
            }
            br.close();
        }
        
    }
   
    
}
