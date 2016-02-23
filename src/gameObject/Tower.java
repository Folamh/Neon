package gameObject;

import java.util.*;

import processing.core.*;

public abstract class Tower extends GameObject{
	int range;
	PVector aim;
	PVector defaultPlane;
	
	PVector nextPathPoint;

	ArrayList<Projectile> projectiles;
	ArrayList<Enemy> targets;
	Enemy leadTarget;
	
	Tower(PApplet p, float x, float y){
		super(p, x, y);
		defaultPlane = new PVector(0, 0);
	}
	
	void calculateTargets(ArrayList<Enemy> gameEnemies){
		for(int i = 0; i < gameEnemies.size(); i++){
			if((gameEnemies.get(i).pos.x < pos.x - range) && (gameEnemies.get(i).pos.x > pos.x + range)){
				targets.add(gameEnemies.get(i));
			}
		}
	}
	
	Enemy calculateLead(ArrayList<Enemy> enemies, PVector nextPathPoint){
		Enemy leadTarget;
		for(int i = 0; i < enemies.size(); i++){
			for(int j = i; j < enemies.size() - i - 1; j++){
				Enemy temp;
				
				if(Math.abs(enemies.get(j).pos.x - nextPathPoint.x) > Math.abs(enemies.get(j + 1).pos.x - nextPathPoint.x)){
					temp = enemies.get(j);
					enemies.set(j, enemies.get(j + 1));
					enemies.set(j + 1, temp);
				}
			}
		}
		leadTarget = enemies.get(0);
		return leadTarget;
	}
}
