package gameObject;

import java.util.*;

import processing.core.*;

public abstract class Tower extends GameObject{
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
	
	static Enemy calculateLead(ArrayList<Enemy> enemy, PVector nextPathPoint){
		Enemy leadTarget;
		for(int i = 0; i < enemy.size(); i++){
			for(int j = i; j < enemy.size() - i - 1; j++){
				Enemy temp;
				
				if(Math.abs(enemy.get(j).pos.x - nextPathPoint.x) > Math.abs(enemy.get(j + 1).pos.x - nextPathPoint.x)){
					temp = enemy.get(j);
					enemy.set(j, enemy.get(j + 1));
					enemy.set(j + 1, temp);
				}
			}
		}
		leadTarget = enemy.get(0);
		return leadTarget;
	}
}
