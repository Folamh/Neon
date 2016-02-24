package control;

import java.util.ArrayList;

import gameObject.*;
import processing.core.*;

public class GameLoop {
	PApplet p;
	
	ArrayList<Tile> backgroundObjects;
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	
	GameLoop(){
		
	}
	
	void update(){
		for(int i = 0; i < backgroundObjects.size(); i++){
			backgroundObjects.get(i).update();
		}
		
		for(int i = 0; i < gameEnemies.size(); i++){
			gameEnemies.get(i).update();
		}
		
		for(int i = 0; i < towers.size(); i++){
			towers.get(i).update();
			towers.get(i).calculateTargets(gameEnemies);
			if(towers.get(i).targets.size() != 0){
				towers.get(i).calculateLead();
			}
		}
	}

}
