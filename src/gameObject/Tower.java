package gameObject;

import java.util.*;

import processing.core.*;

public  abstract class Tower extends GameObject{
	PVector aim;
	PVector defaultPlane;

	ArrayList<Projectile> projectiles;
	ArrayList<Enemy> targets;
	Enemy leadTarget;
	
	Tower(int x, int y){
		super(x, y);
		defaultPlane = new PVector(0, 0);
		projectiles = new ArrayList<Projectile>();
		targets = new ArrayList<Enemy>();//The game loop should check and add to this list
	}
}
