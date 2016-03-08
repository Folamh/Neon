package control;

import java.util.ArrayList;

import gameObject.*;
import map.Grid;
import map.Path;
import processing.core.*;
import userInterface.Button;
import userInterface.MenuObject;

public class GameLoop {
	PApplet p;
	Path path1;
	Path path2;
	Grid grid;
	ArrayList<PVector> gridUsed;
	int money;
	
	PVector off;
	Camera camera;
	
	//ArrayLists for different game objects
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	PImage background;
	PImage building;
	int spawnRate;
	boolean placingTower;
	int data;
	ArrayList<Button> gameMenu;
	
	//The current state of the game
	int gameState;
	
	GameLoop(PApplet p, int gameState){
		this.p = p;
		this.gameState = gameState;
		money = 600000;
		
		//Initializing the path lists
		path1 = new Path(p);
		
		gameEnemies = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		background = new PImage();
		background = p.loadImage("resources/images/backgrounds/background/0.png");
		building = new PImage();
		building = p.loadImage("resources/images/backgrounds/building/0.png");
		spawnRate = 5*60;
		gridUsed = new ArrayList<PVector>();
		placingTower = false;
		data = 10;
		towers = new ArrayList<Tower>();
		
		//Pause Menu Button Image
		PImage bImage = p.loadImage("resources/images/menu/button/0.png");
		//Turret Button Image
		PImage tImage = p.loadImage("resources/images/turret/basicTurret/0.png");
		gameMenu = new ArrayList<Button>();
		gameMenu.add(new Button(p, 5, p.width-50, p.height-50, 300, 150, bImage, "Pause", 20));
		gameMenu.add(new Button(p, 4, p.width-100, 100, 300, 150, tImage, " ",10));
		
		//Adding points to the path
		path1.addPoint(0, 100, p.height-50);
		path1.addPoint(0, p.width-100, p.height-50);
		path1.addPoint(0, p.width-100, 200);
		path1.addPoint(0, 100, 200);
		
		camera = new Camera(p, true);
		camera.setCameraBounds((building.height/2 - p.height/2), -building.height/5, -building.width/8, building.width/8);
		off = new PVector(0,0);
		
		gameEnemies.add(new BasicEnemy(p,500,500,path1));
		//gameEnemies.add(new BasicEnemy(p,700,700,path1));
		towers.add(new PlasmaTower(p,100,100));
		

		grid = new Grid(p, building, 6, 10);
	}
	
	public void update(int gameState){
		//Updating the game state
		this.gameState = gameState;
		System.out.println(gameState);
		camera.update(off,1);
		
		off = camera.getOffSet();
		
		//Checking the gameLoop should be updating
		if(gameState == 4) {
			for(int i = 0; i < gameEnemies.size(); i++){
				gameEnemies.get(i).update();
				
				if(((BasicEnemy) gameEnemies.get(i)).getHealth() == 0) {
					gameEnemies.remove(i);
				}
			}
			
			for(int i = 0; i < towers.size(); i++){
				towers.get(i).update();
				towers.get(i).calculateTargets(gameEnemies);
				if(towers.get(i).size() != 0){
					towers.get(i).calculateLead();
				}
			}
			for(int i = 0; i < gameMenu.size(); i++) 
			{
				MenuObject o = gameMenu.get(i);
				
				o.update();
				
				if(o.getClicked())
				{
					this.gameState = ((Button)o).getValue();
					for(int j = 0; j<gameMenu.size();j++)
					{
						
						Button b = gameMenu.get(j);
						if((b).getValue() == 4)
						{
							placingTower = true;
						}
					}
					break;
				}
			}
			spawnEnemies();
			if(placingTower){
				if(money < 200){
					
				}
				else{
					placeTower();
				}
			}
			loseData();
		}
	}
	
	void spawnEnemies(){
		if(p.millis()%spawnRate == 0){
			p.randomSeed(p.millis());
			int rand = (int) p.random(0,1);
			BasicEnemy enemy;
			if(rand == 0){
				enemy = new BasicEnemy(p, 50, 50, path1);
				gameEnemies.add(enemy);
			}
			else{
				enemy = new BasicEnemy(p, p.width-50, 50, path1);
				gameEnemies.add(enemy);
			}
		}
	}
	
	void placeTower(){
		PVector point = new PVector();
		boolean ok = true;
		if(p.mousePressed){
			point = grid.returnGrid(off);
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
					money -= 200;
				}
			}
		}
	}
	
	void loseData(){
		for(int i = 0; i < gameEnemies.size(); i++){
			if(gameEnemies.get(i).getStoleData()){
				data--;
			}
		}
	}
		
	public void render(){
		//Checking of the gameLoop should be rendered
		p.pushMatrix();
		p.translate(-off.x,-off.y);
		if(gameState == 4 || gameState == 5) {
			p.image(building,p.width/2,p.height/2);
			
			for(int i = 0; i < towers.size(); i++){
				towers.get(i).render();
			}
			
			for(int i = 0; i < gameEnemies.size(); i++){
				gameEnemies.get(i).render();
			}
			if(placingTower){
				grid.showGrid();
			}
		}
		p.popMatrix();
		if(gameState == 4) {
			for(int i = 0; i < gameMenu.size(); i++){
				gameMenu.get(i).render();
			}
		}
	}
	
	//Returning the current state of the game
	public int getGameState() {
		return gameState;
	}
}
