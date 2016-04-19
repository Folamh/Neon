package control;

import java.util.ArrayList;
import animation.Animation;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import gameObject.*;
import map.Grid;
import map.Level;
import map.Path;
import processing.core.*;
import userInterface.*;

//TODO: COMMENT THIS FUCKING SHIT SOON!!!!!!!!!!!!!!
public class GameLoop {
	PApplet p;
	
	//Grid used for the placing of towers
	Grid grid;
	ArrayList<PVector> gridUsed;
	
	//Player 
	int money;
	int health;
	
	//Camera controls
	PVector off;
	Camera camera;
	
	//Music player
	Minim minim;
	AudioPlayer place;
	
	//ArrayLists for different game objects
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	ArrayList<Button> gameMenu;
	PImage background;
	PImage building;
	int spawnRate;
	boolean placingTower, left;
	int data, maxData, prevTime, killCount;
	
	//Elevators
	PImage e;
	
	//Servers
	Animation s;
	
	//The current state of the game
	int gameState;
	
	//Level file
	Level level;
	int curWave;
	int subWave;
	boolean levelComplete;
	
	GameLoop(PApplet p, Minim minim, int gameState, Level level)
	{
		this.p = p;
		this.minim = minim;
		this.gameState = gameState;
		this.level = level;
		money = 1000;
		
		place = this.minim.loadFile("Resources/Audio/PlaceTurret.wav");

		gameEnemies = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		
		background = p.loadImage("Resources\\Images\\Backgrounds\\Background\\0.png");
		building = p.loadImage("Resources\\Images\\Backgrounds\\Building\\0.png");
		grid = new Grid(p, building, 250);//TODO Mess with this placement
		
		spawnRate = 40;
		gridUsed = new ArrayList<PVector>();
		placingTower = false;
		maxData = 10;
		data = maxData;
		prevTime = 0;
		killCount = 0;
		
		//Pause Menu Button Image
		PImage bImage = p.loadImage("resources/images/menu/button/0.png");
		
		//Turret Button Image
		PImage tImage = p.loadImage("resources/images/menu/button/buy.png");
		gameMenu = new ArrayList<Button>();
		gameMenu.add(new Button(p, 5, p.width-250, p.height-50, 150, 75, bImage));
		gameMenu.add(new Button(p, 4, p.width-90, p.height-50, 150, 75, tImage));
		
		//Elevators
		e = p.loadImage("Resources/Images/Backgrounds/Building/1.png");
	
		left = true;
		//Server 
		s = new Animation(p, "Resources/Images/Backgrounds/Server", 10);
		
		//Camera variables 
		camera = new Camera(p);
		camera.setPushBorder(50);
		camera.setPush(false);
		camera.setDrag(true);
		camera.setBoundX(true);
		camera.setBoundY(true);
		camera.setBoundXDim(-building.width/8,building.width/8);
		camera.setBoundYDim(-((building.height/2)-(p.height/2)),building.height/4);
		off = new PVector(0,0);
		
		//Wave variables
		curWave = 0;
		subWave = 0;
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
		camera.setCurOffSet(off);
		camera.calcOffSet();
		off = camera.getOffSet();
		
		//Checking the gameLoop should be updating
		if(gameState == 4) {
			if(levelComplete){
				//TODO Add a level complete
			}
			else{
				for(int i = 0; i < gameEnemies.size(); i++){
					gameEnemies.get(i).update();
					
					if(((BasicEnemy) gameEnemies.get(i)).getHealth() <= 0) {
						money += 100;
						killCount++;
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
				
				for(int i = 0; i < gameMenu.size(); i++) {
					MenuObject o = gameMenu.get(i);
					
					o.update();
					
					if(o.getClicked()){
						this.gameState = ((Button)o).getValue();
						for(int j = 0; j<gameMenu.size();j++){
							Button b = gameMenu.get(j);
							if((b).getValue() == 4){
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
			
				if(data <= 0) {
					this.gameState = 7;
					data = maxData;
					gameEnemies.clear();
					towers.clear();
					money = 1000;
				}
			}
		}
	}
	
	void spawnEnemies(){
		//Getting the current number of milliseconds
		int count = p.millis();
		int time = count/100;
		
		//Checking if count frames have passed
		if(time%spawnRate == 0 && prevTime != time) {
			int[] wave = level.getWave(curWave, subWave);
			for(int i = 0; i < wave[0]; i++){
				//TODO spawn basic enemy
			}
			for(int i = 0; i < wave[1]; i++){
				//TODO spawn fast enemy
			}
			for(int i = 0; i < wave[2]; i++){
				//TODO spawn tank enemy
			}
			prevTime = time;
			subWave++;
			if(subWave >= level.wave.get(curWave).getSubWaveSize()){
				subWave = 0;
				curWave++;
			}
			//Check if all waves have been completed
			if(curWave >= level.wave.size()){
				levelComplete = true;
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
					killCount = 0;
				}
			}
		}
	}
	
	void loseData(){
		for(int i = 0; i < gameEnemies.size(); i++){
			if(gameEnemies.get(i).getStoleData()){
				data--;
				gameEnemies.remove(i);
			}
		}
	}
		
	public void render(){
		//Checking of the gameLoop should be rendered
		p.pushMatrix();
		p.translate(off.x,off.y);
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
				grid.showGrid(gridUsed);
			}
			p.pushMatrix();
			p.translate(p.width/2 + 585, p.height/2 - 338);
			p.scale(3);
			s.displayAnimation();
			p.translate(-50, 0);
			s.displayAnimation();
			p.popMatrix();
		}
		p.popMatrix();
		if(gameState == 4) {
			for(int i = 0; i < gameMenu.size(); i++){
				gameMenu.get(i).render();
				p.noStroke();
				p.textSize(20);
				p.textAlign(PApplet.CENTER);
				p.fill(255);
				p.text("Data: " + data, p.width-100, 20);
				p.text("$: " + money , p.width-100, 50);
				p.text("Kill Count: " + killCount, p.width-100, 80);
			}
		}
	}
	
	//Returning the current state of the game
	public int getGameState() {
		return gameState;
	}
}
