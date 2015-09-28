/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scifimud;

import java.io.BufferedReader;
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
    public static void createInventory(String inventory) throws IOException{
        
        //if the inventory is empty then we set it to null
        if(inventory.equals("empty")){
            
        }
        else{
            String[] newInventory  = inventory.split(", ");
            int i;
            for(i=0; i<newInventory.length; i++){
                if(searchInventoryDatabase(newInventory[i], "drinks", TOTAL_DRINKS, listofDrinks)  
                   || searchInventoryDatabase(newInventory[i], "food", TOTAL_FOOD, listofFood)
                   || searchInventoryDatabase(newInventory[i], "head", TOTAL_HEAD, listofHead)     
                   || searchInventoryDatabase(newInventory[i], "objects", TOTAL_OBJECTS, listofFood)
                   || searchInventoryDatabase(newInventory[i], "pants", TOTAL_PANTS, listofPants)
                   || searchInventoryDatabase(newInventory[i], "shoes", TOTAL_SHOES, listofShoes)
                   || searchInventoryDatabase(newInventory[i], "torso", TOTAL_TORSO, listofTorso)
                   || searchInventoryDatabase(newInventory[i], "weapons", TOTAL_WEAPONS, listofWeapons)
                        ){
                    //this print statement is used for debugging purposes. This will be removed later on to prevent login spam.
                    System.out.println(newInventory[i] + " was sucessfully found!");
                }
                else{
                    System.out.println("Failed to find " + newInventory[i]);
                }
            }
            
        }
        
    }
    //takes all objects in inventory and returns a string of all the objects.
    public static String saveInventory(ArrayList<Object> inventory){
        int i;
        String newInventory = "";
        if(inventory!=null){
            
            for(i=0; i<inventory.size()-1; i++){
                newInventory = inventory.get(i) + ", ";
            }
            newInventory = newInventory + inventory.get(i);
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
    public static boolean searchInventoryDatabase(String name, String fileName, int total, ArrayList<String> arrayList) throws FileNotFoundException, IOException {
        
        try(BufferedReader br = new BufferedReader(new FileReader("src/Equipment/" + fileName + ".txt"))){
            int i;
        
            int low=0;
            int high = total-1;
            int mid;


            while(low<= high){

                mid=(high+low)/2;

                //if a partial match is found return the word
                if(arrayList.get(mid).startsWith(name)){
                    switch(fileName){
                        case "drinks":
                            createDrink(arrayList.get(mid));
                            break;
                        case "food":
                            createFood(arrayList.get(mid));
                            break;
                        case "head":
                            createHead(arrayList.get(mid));
                            break;
                        case "objects":
                            createObject(arrayList.get(mid));
                            break;
                        case "pants":
                            createPants(arrayList.get(mid));
                            break;
                        case "shoes":
                            createShoes(arrayList.get(mid));
                            break;
                        case "torso":
                            createTorso(arrayList.get(mid));
                            break;
                        case "weapons":
                            createWeapon(arrayList.get(mid));
                            break;
                        default:
                            System.out.println("Error invalid equipment category. Please contact admin.");
                            break;
                    }
                    return true;
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
 
                  
        }
        //if reached here that means no match has been found
        return false;
    }
    //these functions all create objects/items/weapons/equipment based on the String in one of the files
    //these strings need to be parsed
    public static void createDrink(String drink){
        
    }
    public static void createFood(String food){
        
    }
    //creates an object or item such as a potion or artifact
    public static void createObject(String object){
        
    }
    //this is a function that is called to create a weapon
    public static void createWeapon(String weapon){
        
    }
    //creates a torso object that a player can wear
    public static void createTorso(String torso){
        
    }
    //creates a head object that a player can wear
    public static void createHead(String head){
        
    }
    //creates a shoe object that a player can wear
    public static void createShoes(String shoes){
        
    }
    public static void createPants(String pants){
        
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
                current_word= br.next();
                arrayList.set(i, current_word);
            }
            br.close();
        }
        
    }
   
    
}
