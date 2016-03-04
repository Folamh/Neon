package control;

import java.util.ArrayList;

import gameObject.*;
import processing.core.*;

public class GameLoop {
	PApplet p;
	
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	
	GameLoop(){
		
	}
	
	public void update(){
		
		for(int i = 0; i < gameEnemies.size(); i++){
			gameEnemies.get(i).update();
		}
		
		for(int i = 0; i < towers.size(); i++){
			towers.get(i).update();
			towers.get(i).calculateTargets(gameEnemies);
			if(towers.get(i).size() != 0){
				towers.get(i).calculateLead();
			}
		}
	}
	
	public void render(){
		
		for(int i = 0; i < towers.size(); i++){
			towers.get(i).render();
		}
		
		for(int i = 0; i < gameEnemies.size(); i++){
			gameEnemies.get(i).render();
		}
	}

}
