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
public class CentralHub {
    //this will be handy in the future when we need to identify a room
    int roomID;
    //what will be displayed in each room when a charater walks in
    String roomDescription;
    //be default all directions are possible
    //when room is actually created we disable directions and doors accordingly
    //secret is false by default as most rooms don't have secret passageways
    boolean north = true;
    boolean south = true;
    boolean east = true;
    boolean west = true;
    boolean up = true;
    boolean down = true;
    boolean northDoor = true;
    boolean southDoor = true;
    boolean eastDoor = true;
    boolean westDoor = true;
    boolean secret = false;
}
