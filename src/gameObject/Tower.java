package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;


public abstract class Tower extends GameObject{
	int range;
	PVector aim;
	PVector defaultPlane;
	
	ArrayList<Projectile> projectiles;
	public ArrayList<Enemy> targets;
	Enemy leadTarget;
	
	Tower(PApplet p, float x, float y){
		super(p, x, y);
		defaultPlane = new PVector(0, 0);
	}
	
	public void calculateTargets(ArrayList<Enemy> gameEnemies){
		if(gameEnemies.size() != 0){
			for(int i = 0; i < gameEnemies.size(); i++){
				if((PVector.dist(gameEnemies.get(i).pos, pos) > range)){
					targets.remove(i);//TODO check this for out of bounds
				}
			}
		}
		
		for(int i = 0; i < gameEnemies.size(); i++){
			if(PVector.dist(gameEnemies.get(i).pos, pos) < range){
				targets.add(targets.get(i));
			}
		}
	}
	
	public void calculateLead(){
		for(int i = 0; i < targets.size(); i++){
			for(int j = i; j < targets.size() - i - 1; j++){
				Enemy temp;
				
				if(PVector.dist(targets.get(j).pos, targets.get(j).nextPathPoint) > PVector.dist(targets.get(j + 1).pos, targets.get(j + 1).nextPathPoint)){
					temp = targets.get(j);
					targets.set(j, targets.get(j + 1));
					targets.set(j + 1, temp);
				}
			}
		}
		leadTarget = targets.get(0);
	}
	
	void cleanProjectiles(){
		for(int i = 0; i < projectiles.size(); i++){
			if(PVector.dist(projectiles.get(i).pos, projectiles.get(i).target.pos) < projectiles.get(i).speed){
				projectiles.remove(i);//TODO check this for out of bounds
			}
		}
	}
}
