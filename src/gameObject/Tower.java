package gameObject;

import java.util.*;

import processing.core.*;

public abstract class Tower extends GameObject{
	PVector aim;
	PVector defaultPlane;

	ArrayList<Projectile> projectiles;
	ArrayList<Enemy> targets;
	Enemy leadTarget;
	
	Tower(float x, float y){
		super(x, y);
		defaultPlane = new PVector(0, 0);
	}
}
