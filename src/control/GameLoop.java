package control;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import gameObject.*;
import map.Grid;
import map.Path;
import processing.core.*;
import userInterface.*;

public class GameLoop {
	PApplet p;
	Path path1;
	Path path2;
	Grid grid;
	ArrayList<PVector> gridUsed;
	int money;
	int health;
	PVector off;
	Camera camera;
	Minim minim;
	AudioPlayer place;
	
	//ArrayLists for different game objects
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	ArrayList<Button> gameMenu;
	PImage background;
	PImage building;
	int spawnRate;
	boolean placingTower;
	int data;
	
	//Elevators
	PImage e;
	
	//The current state of the game
	int gameState;
	
	GameLoop(PApplet p, Minim minim, int gameState)
	{
		this.p = p;
		this.minim = minim;
		this.gameState = gameState;
		money = 600;
		
		place = this.minim.loadFile("Resources/Audio/PlaceTurret.wav");
		//Initializing the path lists
		path1 = new Path(p);
		path2 = new Path(p);
		
		gameEnemies = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		
		background = p.loadImage("Resources\\Images\\Backgrounds\\Background\\0.png");
		building = p.loadImage("Resources\\Images\\Backgrounds\\Building\\0.png");
		
		spawnRate = 5*60;
		gridUsed = new ArrayList<PVector>();
		placingTower = false;
		data = 10;
		
		//Pause Menu Button Image
		PImage bImage = p.loadImage("resources/images/menu/button/0.png");
		//Turret Button Image
		PImage tImage = p.loadImage("resources/images/turret/basicTurret/0.png");
		gameMenu = new ArrayList<Button>();
		gameMenu.add(new Button(p, 5, p.width-50, p.height-50, 300, 150, bImage, "Pause", 20));
		gameMenu.add(new Button(p, 4, p.width-100, 100, 300, 150, tImage, " ",10));
		
		//Elevators
		e = p.loadImage("Resources/Images/Backgrounds/Building/1.png");
	
		//Adding points to the path
		path1.addPoint(0, 0, p.height + 40);
		path1.addPoint(0, p.width/2 - 65, p.height + 40);
		path1.addPoint(0, p.width/2 - 65, p.height - 212);
		path1.addPoint(0, p.width/2 - 585, p.height - 212);
		path1.addPoint(0, p.width/2 - 585, p.height - 463);
		path1.addPoint(0, p.width/2 - 440, p.height - 463);
		path1.addPoint(0, p.width/2 - 440, p.height - 713);
		path1.addPoint(0, p.width/2 + 585, p.height - 713);
		
		path2.addPoint(0, p.width, p.height + 40);
		path2.addPoint(0, p.width/2 + 65, p.height + 40);
		path2.addPoint(0, p.width/2 + 65, p.height - 212);
		path2.addPoint(0, p.width/2 + 585, p.height - 212);
		path2.addPoint(0, p.width/2 + 585, p.height - 463);
		path2.addPoint(0, p.width/2 - 440, p.height - 463);
		path2.addPoint(0, p.width/2 - 440, p.height - 713);
		path2.addPoint(0, p.width/2 + 585, p.height - 713);
		
		
		camera = new Camera(p, true);
		camera.setCameraBounds((building.height/2 - p.height/2), -building.height/5, -building.width/8, building.width/8);
		off = new PVector(0,0);
		grid = new Grid(p, building, 250);
	}
	
	//Play placing turret noise
	void playPlace(){
		place.rewind();
		place.play();
	}
	
	public void update(int gameState)
	{
		//Updating the game state
		this.gameState = gameState;
		System.out.println(gameState);
		camera.update(off,1);
		
		off = camera.getOffSet();
		
		//Checking the gameLoop should be updating
		if(gameState == 4) 
		{
			for(int i = 0; i < gameEnemies.size(); i++)
			{
				gameEnemies.get(i).update();
				
				if(((BasicEnemy) gameEnemies.get(i)).getHealth() <= 0) {
					money += 50;
					gameEnemies.remove(i);
				}
			}
			
			for(int i = 0; i < towers.size(); i++)
			{
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
					playPlace();
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
				enemy = new BasicEnemy(p, minim, 0, p.height + 40, path1);
				gameEnemies.add(enemy);
			}
			else{
				enemy = new BasicEnemy(p, minim, p.width, p.height + 40, path2);
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
					PlasmaTower tower = new PlasmaTower(p, minim, point.x, point.y);
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
			p.pushMatrix();
			p.translate(p.width/2, p.height/2);
			p.image(building,0,0);
			//Elevator
			p.image(e, -65, 412);
			p.image(e, 65, 412);
			p.image(e, -65, 161);
			p.image(e, 65, 161);
			
			p.image(e, 585, 161);
			p.image(e, -585, 161);
			p.image(e, 585, -89);
			p.image(e, -585, -89);
			
			p.image(e, -440, -338);
			p.image(e, -440, -89);
			p.popMatrix();
			
			for(int i = 0; i < towers.size(); i++){
				towers.get(i).render();
			}
			
			for(int i = 0; i < gameEnemies.size(); i++)
			{
				if(!gameEnemies.get(i).inElevator){
					gameEnemies.get(i).render();
				}
			}
			if(placingTower){
				grid.showGrid();
			}
		}
		p.popMatrix();
		if(gameState == 4) {
			for(int i = 0; i < gameMenu.size(); i++){
				gameMenu.get(i).render();
				p.noStroke();
				p.fill(255);
				p.rectMode(PApplet.CENTER);
				p.rect(p.width-100, 150, 150, 50);
				p.textSize(20);
				p.textAlign(PApplet.CENTER);
				p.fill(0);
				p.text("$: " + money , p.width-100, 150);
			}
		}
	}
	
	//Returning the current state of the game
	public int getGameState() {
		return gameState;
	}
}
