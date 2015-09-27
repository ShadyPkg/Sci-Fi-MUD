/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import Monsters.CorruptAndroid;
import Monsters.Infestor;
import java.util.Random;

/**
 *
 * @author jonc
 */
public class ThePit extends Room{
    
    //array of monsters that are in the room.
    //For now there is going to be a max of 10 monsters per room and a max of 50 items.
    private Object[] monster = new Object[10];
    private Object[] items = new Object[50];
    
    public ThePit(){
        
    }
    public ThePit(String thisLocation, String thisDescription, int id, int x, int y, int z){
        area = thisLocation;
        roomDescription = thisDescription;
        roomID = id;
        xCoordinate = x;
        yCoordinate = y;
        zCoordinate = z;
    }
    
    //spawns monsters in room for players to fight
    public void spawnMonster(int number){
        //seeding random number genator
        Random randomGenerator = new Random(System.currentTimeMillis());
        int value = randomGenerator.nextInt(8);
        switch(value){
            case 1:
                CorruptAndroid android = new CorruptAndroid(xCoordinate, yCoordinate, zCoordinate);
                getMonster()[number]=android;
                break;
            case 2:
                Infestor infestor = new Infestor(xCoordinate, yCoordinate, zCoordinate);
                getMonster()[number]=infestor;
                break;
            default:
                break;
        }
    }
    //spawns items or objects in room for player to interact with
    public void spawnItem(){
        
    }

    /**
     * @return the monster
     */
    public Object[] getMonster() {
        return monster;
    }

    /**
     * @param monster the monster to set
     */
    public void setMonster(Object[] monster) {
        this.monster = monster;
    }
    
}
