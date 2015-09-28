/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scifimud;

import rooms.CentralHub;
import rooms.Factory;
import rooms.Secret;
import rooms.Sewers;
import rooms.ThePit;
import rooms.TrainStation;
import rooms.Wastelands;
import static scifimud.ClassCreator.className;
import static scifimud.ConnectPlayer.factoryMap;
import static scifimud.ConnectPlayer.hubMap;
import static scifimud.ConnectPlayer.mainMap;
import static scifimud.ConnectPlayer.secretMap;
import static scifimud.ConnectPlayer.sewerMap;
import static scifimud.ConnectPlayer.trainMap;
import static scifimud.ConnectPlayer.wastelandsMap;

/**
 *
 * @author root
 */
public class MapCreator {
    
    public static void initializeMap(String location){
        
        int i;
        int j;
        int k;
        
        //later on we will inititalize map rooms individually.
        //For now we mass intitalize rooms to get a basic working map
        
        switch(location){
            case "The Pit":
                mainMap = new ThePit[100][100][5];
                className.setArea(mainMap);
                for(i=0; i<100; i++){
                    for(j=0; j<100; j++){
                        for(k=0; k<5; k++){
                            mainMap[i][j][k] = new ThePit("The Pit", "You look in horror as dead bodies lay everywhere. Something horrific must have happened.", (100*i)+(10*j)+k, i, j, k );
                            
                        }
                    }
                    
                }
                System.out.println("You have entered The Pit. Dead bodies of fallen heros lay everywhere. Will you join them or will you survive?");
                
                break;
            case "Central Hub":
                hubMap = new CentralHub[50][200][3];       
                className.setArea(hubMap);
                
                for(i=0; i<50; i++){
                    for(j=0; j<200; j++){
                        for(k=0; k<3; k++){
                            hubMap[i][j][k] = new CentralHub();
                            
                        }
                    }
                    
                }
                System.out.println("You have entered The Hub. The center of information and exchange.");
                break;
                
            case "Factory":
                factoryMap = new Factory[150][80][4];
                className.setArea(factoryMap);
                
                for(i=0; i<150; i++){
                    for(j=0; j<80; j++){
                        for(k=0; k<4; k++){
                            factoryMap[i][j][k] = new Factory();
                            
                        }
                    }
                    
                }
                System.out.println("Levers and cranks churn as you enter the factory.");
                break;
                
            case "Sewers":
                sewerMap = new Sewers[200][200][10];
                className.setArea(sewerMap);
                
                for(i=0; i<200; i++){
                    for(j=0; j<200; j++){
                        for(k=0; k<10; k++){
                            sewerMap[i][j][k] = new Sewers();
                            
                        }
                    }
                    
                }
                System.out.println("This place really reeks! Lets get out of here before you pass out!");
                break;
                
            case "TrainStation":
                trainMap = new TrainStation[200][10][1];
                className.setArea(trainMap);
                
                for(i=0; i<200; i++){
                    for(j=0; j<10; j++){
                        for(k=0; k<1; k++){
                            trainMap[i][j][k] = new TrainStation();
                            
                        }
                    }
                    
                }
                System.out.println("An old underground train station still remains here.");
            case "Wastelands":
                wastelandsMap = new Wastelands[300][300][20];
                className.setArea(wastelandsMap);
                
                for(i=0; i<300; i++){
                    for(j=0; j<300; j++){
                        for(k=0; k<20; k++){
                            wastelandsMap[i][j][k] = new Wastelands();
                            
                        }
                    }
                    
                }
                System.out.println("You are either very brave or very foolish for venturing out here.");
                break;
                
            case "Secret":
                secretMap = new Secret[5][5][5];
                className.setArea(secretMap);
                
                for(i=0; i<5; i++){
                    for(j=0; j<5; j++){
                        for(k=0; k<5; k++){
                            secretMap[i][j][k] = new Secret();
                        }
                    }
                    
                }
                System.out.println("Only a few have ever discovered this place.");
                break;        
                
            default:
                System.out.println("Error intitalizing map. Invalid map name please contact admin.");
                System.exit(0);
                break;
        }
    }
}
