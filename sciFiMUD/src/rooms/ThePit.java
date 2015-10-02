
package rooms;

import Equipment.Item;
import Monsters.CorruptAndroid;
import Monsters.Infestor;
import Monsters.Monster;
import java.util.ArrayList;
import java.util.Random;
import scifimud.ObjectCreator;
import static scifimud.ObjectCreator.createDrink;
import static scifimud.ObjectCreator.createFood;
import static scifimud.ObjectCreator.createShoes;

/**
 *
 * @author jonc
 */
public class ThePit extends Room{
    
    //need to implement max number of items so players can't jump drop infinite amount of items
    private ArrayList<Monster> monster = new ArrayList();
    private ArrayList<Item> items = new ArrayList();
    
    public ThePit(){
        
    }
    public ThePit(String thisLocation, String thisDescription, int id, int x, int y, int z){
        area = thisLocation;
        roomDescription = thisDescription;
        roomID = id;
        xCoordinate = x;
        yCoordinate = y;
        zCoordinate = z;
        //when room is created there is a chance an item will spawn in it
        spawnItem(items);
    }
    
    //spawns monsters in room for players to fight
    public void spawnMonster(int number){
        //seeding random number genator
        Random randomGenerator = new Random(System.currentTimeMillis());
        int value = randomGenerator.nextInt(8);
        switch(value){
            case 1:
                CorruptAndroid android = new CorruptAndroid(xCoordinate, yCoordinate, zCoordinate);
                //check to see if we have to add null first before adding the monster
                //monster. add(null);
                monster.add(android);
                break;
            case 2:
                Infestor infestor = new Infestor(xCoordinate, yCoordinate, zCoordinate);
                monster.add(infestor);
                break;
            default:
                break;
        }
    }
    //spawns items or objects in room for player to interact with
    public void spawnItem(ArrayList<Item> thisItem){
        Random randomGenerator = new Random(System.currentTimeMillis());
        int value = randomGenerator.nextInt(7);
        //this value is use to choose a random item in a category to create
        int random;
        
        switch(value){
            case 1:
                random = randomGenerator.nextInt(ObjectCreator.TOTAL_DRINKS);
               
                thisItem.add(createDrink(ObjectCreator.listofDrinks.get(random)));
                break;
            case 2:
                random = randomGenerator.nextInt(ObjectCreator.TOTAL_FOOD);
              
                thisItem.add(createFood(ObjectCreator.listofFood.get(random)));
                break;
             case 3:
                
                random = randomGenerator.nextInt(ObjectCreator.TOTAL_SHOES);
        
                thisItem.add(createShoes(ObjectCreator.listofShoes.get(random)));
                break;
            default:
                break;
             
             
        }
    }

    /**
     * @return the monster
     */
    @Override
    public ArrayList<Monster> getMonsters() {
        return monster;
    }

    /**
     * @param thisMonster
     */
    public void setMonster(ArrayList<Monster> thisMonster) {
        this.monster = thisMonster ;
    }

 
    /**
     * @return the items
     */
    @Override
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
    @Override
    public void addItem(Item item){
        items.add(item);
    }
    
    
}
