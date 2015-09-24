/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

/**
 *
 * @author jonc
 */
public class ThePit extends Room{
    public ThePit(){
        
    }
    public ThePit(String thisLocation, String thisDescription, int id){
        super.area = thisLocation;
        roomDescription = thisDescription;
        super.roomID = id;
    }

    
}
