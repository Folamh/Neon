
package control;

import processing.core.*;

import java.util.ArrayList;

import gameObject.*;
import map.*;

public class MainLoop extends PApplet{
	
	//DO NOT CHANGE THIS UNDER ANY CIRCUMSTANCES!!!!!!!!!
	public static void main(String[] args) {
		String[] a = {"MAIN"};			
		PApplet.runSketch(a, new MainLoop());
	}
	
	//Settings go here
	public void settings() {
		size(1000,1021);
	}
	
	Map map = new Map(this);
	ArrayList<Tile> tiles;
	ArrayList<PVector> path;
	PImage building;
	BasicEnemy enemy;
	PlasmaTower tower;
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		map.loadMap("Resources/Maps/map1.txt");
		tiles = map.getTiles();
		int y = 409;
		PVector e = new PVector(100, y);
		path = new ArrayList<PVector>();
		
		path.add(e);
		building = loadImage("C:\\Users\\Ross\\Desktop\\ART\\Building.png");
		enemy = new BasicEnemy(this, 900, y, path);
		tower = new PlasmaTower(this, 500, 350);
		towers = new ArrayList<Tower>();
		gameEnemies = new ArrayList<Enemy>();
		towers.add(tower);
		gameEnemies.add(enemy);
		imageMode(CENTER);
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
	
	public void draw() {
		background(0);
		//map.render();
		image(building, width/2, height/2);
		update();
		render();
	}

}
