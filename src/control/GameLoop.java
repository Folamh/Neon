package control;

import java.util.ArrayList;

import gameObject.*;
import map.Grid;
import map.Map;
import map.Path;
import processing.core.*;
import userInterface.*;

public class GameLoop {
	PApplet p;
	Path path1;
	Path path2;
	Grid grid;
	ArrayList<PVector> gridUsed;
	
	//ArrayLists for different game objects
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	ArrayList<MenuObject> gameMenu;
	ArrayList<PVector> path;
	PImage background;
	PImage building;
	int wait;
	int spawnRate;
	boolean placingTower;
	int data;
	
	//The current state of the game
	int gameState;
	
	GameLoop(PApplet p, int gameState){
		this.p = p;
		this.gameState = gameState;
		
		gameEnemies = new ArrayList<Enemy>();
		background = new PImage();
		background = p.loadImage("resources\\Images\\Backgrounds\\0.png");
		building = new PImage();
		building = p.loadImage("Resources\\Images\\Backgrounds\\1.png");
		wait = 0;
		spawnRate = 5*60;
		gridUsed = new ArrayList<PVector>();
		placingTower = false;
		data = 10;
		towers = new ArrayList<Tower>();

		gameMenu = new ArrayList<MenuObject>();
		path = new ArrayList<PVector>();
		path.add(new PVector(0,p.height-50));
		
		PImage bImage = p.loadImage("resources/images/menu/button/0.png");
		//Game Menu
		gameMenu.add(new Button(p,0,p.width-100,100,200,100,bImage, "Basic Turret", 40));
		gameMenu.add(new Button(p,5,p.width-100,p.height-100,200,100,bImage, "Pause", 40));
	}
	
	public void update(int gameState)
	{
		//Updating the game state
		this.gameState = gameState;
		
		//Checking the gameLoop should be updating

		if(gameState == 4)
		{
			
			if(p.frameCount%60 == 0)
			{
				//gameEnemies.add(new BasicEnemy(p,p.width,p.height-50,path));
			}
			
			for(int i = 0; i < gameEnemies.size(); i++)
			{
				gameEnemies.get(i).update();
			}
			
			for(int i = 0; i < towers.size(); i++)
			{
				towers.get(i).update();
				towers.get(i).calculateTargets(gameEnemies);
				if(towers.get(i).size() != 0){
					towers.get(i).calculateLead();
				}
				
			}
			for(int i = 0; i<gameMenu.size();i++)
			{
				MenuObject o = gameMenu.get(i);
				
				o.update();
				
				if(o.getClicked())
				{
					this.gameState = ((Button)o).getValue();
					break;
				}
			}
		}
		spawnEnemies();
		if(placingTower){
			placeTower();
		}
		loseData();
	}
	
	void spawnEnemies(){
		if(wait < spawnRate){
			wait++;
		}
		else{
			p.randomSeed(p.millis());
			int rand = (int) p.random(0,1);
			BasicEnemy enemy;
			if(rand == 0){
				enemy = new BasicEnemy(p, 0, 0, path1);
				gameEnemies.add(enemy);
			}
			else{
				enemy = new BasicEnemy(p, 0, 0, path2);
				gameEnemies.add(enemy);
			}
		}
	}
	
	void placeTower(){
		PVector point = new PVector();
		boolean ok = true;
		if(p.mousePressed){
			point = grid.returnGrid();
			if(point.x != 0 && point.y != 0){
				for(int i = 0; i < gridUsed.size(); i++){
					if(point == gridUsed.get(i)){
						ok = false;
					}
				}
				if(ok == true){
					gridUsed.add(point);
					PlasmaTower tower = new PlasmaTower(p, point.x, point.y);
					towers.add(tower);
					placingTower = false;
				}
			}
		}
	}
	
	void loseData(){
		for(int i = 0; i < gameEnemies.size(); i++){
			if(gameEnemies.get(i).path.getCurPoint() == gameEnemies.get(i).path.getLastPoint()){
				gameEnemies.get(i).gotData = true;
			}
			if(gameEnemies.get(i).gotData){
				if(gameEnemies.get(i).path.getCurPoint() == gameEnemies.get(i).path.getFirstPoint()){
					data--;
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
