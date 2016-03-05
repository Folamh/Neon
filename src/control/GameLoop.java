package control;

import java.util.ArrayList;

import gameObject.*;
import map.Map;
import processing.core.*;

public class GameLoop {
	PApplet p;
	Map map;
	ArrayList<Tile> backgroundObjects;
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	
	GameLoop(){
		backgroundObjects = new ArrayList<Tile>();
		towers = new ArrayList<Tower>();
		gameEnemies = new ArrayList<Enemy>();
		
	}
	
	public int gameLoop(){
		update();
		render();
		return 2;
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
			if(towers.get(i).size() != 0){
				towers.get(i).calculateLead();
			}
		}
	}
	
	void render(){
		for(int i = 0; i < backgroundObjects.size(); i++){
			backgroundObjects.get(i).render();
		}
		
		for(int i = 0; i < towers.size(); i++){
			towers.get(i).render();
		}
		
		for(int i = 0; i < gameEnemies.size(); i++){
			gameEnemies.get(i).render();
		}
	}

}
