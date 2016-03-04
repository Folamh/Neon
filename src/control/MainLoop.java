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
		//fullScreen();
		size(1000,1021);
	}
	
	Map map = new Map(this);
	ArrayList<PVector> path;
	PImage building, background;
	BasicEnemy enemy, enemy1;
	PlasmaTower tower, t2, t3;
	ArrayList<Tower> towers;
	ArrayList<Enemy> gameEnemies;
	
	Boolean spawn;
	//Only use this for initializing variables
	public void setup() {
		frameRate(60);
		
		spawn = true;
		
		map.loadMap("Resources/Maps/map1.txt");
		int y = 409;
		PVector e = new PVector(100, y);
		path = new ArrayList<PVector>();
		
		path.add(e);
		building = loadImage("Resources\\Images\\Backgrounds\\1.png");
		background = loadImage("Resources\\Images\\Backgrounds\\0.png");
		tower = new PlasmaTower(this, 250, 325);
		t2 = new PlasmaTower(this, 500, 325);
		t3 = new PlasmaTower(this, 750, 325);
		towers = new ArrayList<Tower>();
		gameEnemies = new ArrayList<Enemy>();
		towers.add(tower);
		towers.add(t2);
		towers.add(t3);
		imageMode(CENTER);
	}
	
	public void update(){
		
		System.out.println(frameRate);
		
		if(PApplet.second() % 4 == 0 && spawn) {
			Enemy enemy =  new BasicEnemy(this, 1000, 409, path);
			gameEnemies.add(enemy);
			spawn = !spawn;
		}
		
		if(PApplet.second() % 4 != 0) {
			spawn = true;
		}
		
		for(int i = 0; i < gameEnemies.size(); i++){
			gameEnemies.get(i).update();
		}
		
		for(int i = 0; i < towers.size(); i++){
			towers.get(i).update();
			towers.get(i).calculateTargets(gameEnemies);
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
		//background(0);
		//map.render();
		image(background, width/2, height/2);
		image(building, width/2, height/2);
		update();
		render();
	}
}
