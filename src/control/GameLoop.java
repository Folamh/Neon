package control;

import java.util.ArrayList;

import gameObject.*;
import map.Grid;
import map.Map;
import map.Path;
import processing.core.*;

public class GameLoop {
	PApplet p;
	Path path1;
	Path path2;
	Grid grid;
	ArrayList<PVector> gridUsed;
	ArrayList<Tile> backgroundObjects;
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	PImage background;
	PImage building;
	int wait;
	int spawnRate;
	boolean placingTower;
	int data;
	
	GameLoop(){
		backgroundObjects = new ArrayList<Tile>();
		towers = new ArrayList<Tower>();
		gameEnemies = new ArrayList<Enemy>();
		background = new PImage();
		background = p.loadImage("D:\\Neon\\Resources\\Images\\Backgrounds\\0.png");
		building = new PImage();
		building = p.loadImage("D:\\Neon\\Resources\\Images\\Backgrounds\\1.png");
		wait = 0;
		spawnRate = 5*60;
		gridUsed = new ArrayList<PVector>();
		placingTower = false;
		data = 10;
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
		spawnEnemies();
		if(placingTower){
			placeTower();
		}
		loseData();
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
}
