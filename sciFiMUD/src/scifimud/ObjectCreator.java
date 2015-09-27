/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scifimud;

import java.util.StringTokenizer;

/**
 *
 * @author root
 */
//this class is responsible for managing players inventory and creating all objects and items in the game
public class ObjectCreator {
    
    //inventory is a string read from file and it parses that string to create all the objects and places it in players inventory
    public static void readInventory(String inventory){
        
        //if the inventory is empty then we set it to null
        if(inventory.equals("empty")){
            
        }
        else{
            StringTokenizer strTok = new StringTokenizer(inventory, ",");
            while(strTok.hasMoreElements()){
                searchInventoryDatabase(strTok.nextToken());

            }
        }
        
    }
    //takes all objects in inventory and saves them into a text file using object names separated by commas.
    public static void saveInventory(Object[] inventory){
        int i;
        if(inventory!=null){
             for(i=0; i<inventory.length; i++){
            //inventory[i].getName();
            }
        }
       
    }
    //searches the inventory database looking for piece of equipment based on its name, if the name is found
    //in the database this function will call createObject to create the item or weapon and it will be placed
    //in the players inventory
    public static void searchInventoryDatabase(String name){
        
    }
    //creates an object or item such as a potion or artifact
    public static void createObject(){
        
    }
    //this is a function that is called to create a weapon
    public static void createWeapon(){
        
    }
    //creates a torso object that a player can wear
    public static void createTorso(){
        
    }
    //creates a head object that a player can wear
    public static void createHead(){
        
    }
    //creates a shoe object that a player can wear
    public static void createShoes(){
        
    }
    public static void createPants(){
        
    }
   
    
}
