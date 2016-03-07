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
	PImage background;
	PImage building;
	
	GameLoop(){
		backgroundObjects = new ArrayList<Tile>();
		towers = new ArrayList<Tower>();
		gameEnemies = new ArrayList<Enemy>();
		background = new PImage();
		background = p.loadImage("D:\\Neon\\Resources\\Images\\Backgrounds\\0.png");
		building = new PImage();
		building = p.loadImage("D:\\Neon\\Resources\\Images\\Backgrounds\\1.png");
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
		p.image(background, p.width/2, p.height/2);
		p.image(building, p.width/2, p.height/2);
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
	
	void spawnEnemies(){
		if(wait < spawnRate){
			wait++;
		}
		else{
			gameEnemies.add(BasicEnemy enemy = new BasicEnemy(p, 0, 0, null))
		}
	}
}
