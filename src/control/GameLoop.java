package control;

import java.util.ArrayList;

import gameObject.*;
import processing.core.*;

public class GameLoop {
	PApplet p;
	
	//ArrayLists for different game objects
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	
	//The current state of the game
	int gameState;
	
	GameLoop(PApplet p, int gameState){
		this.p = p;
		this.gameState = gameState;
	}
	
	public void update(int gameState){
		//Updating the game state
		this.gameState = gameState;
		
		//Checking the gameLoop should be updating
		if(gameState == 4) {
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
