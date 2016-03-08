package control;

import java.util.ArrayList;

import gameObject.*;
import processing.core.*;

public class GameLoop {
	PApplet p;
	
	//ArrayLists for different game objects
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	
	ArrayList<PVector> path;
	
	//The current state of the game
	int gameState;
	
	GameLoop(PApplet p, int gameState){
		this.p = p;
		this.gameState = gameState;
		
		gameEnemies = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		
		path = new ArrayList<PVector>();
		path.add(new PVector(0,p.height-50));
	}
	
	public void update(int gameState){
		//Updating the game state
		this.gameState = gameState;
		
		//Checking the gameLoop should be updating
		if(this.gameState == 4) {
			
			if(p.frameCount%60 == 0) {
				gameEnemies.add(new BasicEnemy(p,p.width,p.height-50,path));
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
	}
	
	public void render(){
		//Checking of the gameLoop should be rendered
		if(gameState == 4 || gameState == 5) {
			for(int i = 0; i < towers.size(); i++){
				towers.get(i).render();
			}
			
			for(int i = 0; i < gameEnemies.size(); i++){
				gameEnemies.get(i).render();
			}
		}
	}
	
	//Returning the current state of the game
	public int getGameState() {
		return gameState;
	}
}
