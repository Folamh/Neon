package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;


public abstract class Tower extends GameObject{
	int range;//The range that the tower can shoot at.
	PVector aim;//Location to the target being shot at.
	PVector defaultPlane;//0, 0 plane for calculating angles.
	
	ArrayList<Projectile> projectiles;//All projectiles currently in game shot from this tower.
	public ArrayList<Enemy> targets;//All targets within range.
	Enemy leadTarget;//Target closest to the it's next path point.
	
	Tower(PApplet p, float x, float y){
		super(p, x, y);
		defaultPlane = new PVector(0, 0);
	}
	
	public void calculateTargets(ArrayList<Enemy> gameEnemies){//GameLoop passes all map enemies.
		if(targets.size() != 0){
			for(int i = 0; i < targets.size(); i++){
				if((PVector.dist(targets.get(i).pos, pos) > range) || targets.get(i).inElevator){//If the enemy is out of range remove him or in a elevator.
					targets.remove(i);//TODO check this for out of bounds
				}
			}
		}
		
		for(int i = 0; i < gameEnemies.size(); i++){
			if(PVector.dist(gameEnemies.get(i).pos, pos) < range){//If an enemy is in range add him to the list.
				targets.add(targets.get(i));
			}
		}
	}
	
	public void calculateLead(){//Sorts the array in order of the enemies closest to their next path point.
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
		leadTarget = targets.get(0);//Sets the closest to the lead.
	}
	
	void cleanProjectiles(){
		for(int i = 0; i < projectiles.size(); i++){
			if(PVector.dist(projectiles.get(i).pos, projectiles.get(i).target.pos) < projectiles.get(i).speed){
				projectiles.remove(i);//TODO check this for out of bounds
			}
		}
	}
}
