package gameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;


public abstract class Tower extends GameObject{
	int range;//The range that the tower can shoot at.
	PVector aim;//Location to the target being shot at.
	PVector defaultPlane;//0, 0 plane for calculating angles.
	
	ArrayList<Projectile> projectiles;//All projectiles currently in game shot from this tower.
	ArrayList<Enemy> targets;//All targets within range.
	Enemy leadTarget;//Target closest to the it's next path point.
	
	Tower(PApplet p, float x, float y){
		super(p, x, y);
		projectiles = new ArrayList<Projectile>();
		targets = new ArrayList<Enemy>();
		defaultPlane = new PVector(0, 0);
		aim = new PVector(0, 90);
	}
	
	public void calculateTargets(ArrayList<Enemy> gameEnemies){//GameLoop passes all map enemies.
		targets.clear();
		for(int i = 0; i < gameEnemies.size(); i++){
			if((PVector.dist(pos, gameEnemies.get(i).pos) < range) && !gameEnemies.get(i).inElevator){//If an enemy is in range add him to the list.
				targets.add(gameEnemies.get(i));
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
	
	public int size(){
		return targets.size();
	}
}
