package gameObject;

import ddf.minim.Minim;
import processing.core.PApplet;

public class LightningTower extends Tower{
	
	LightningTower(PApplet p, Minim minim, int x, int y){
		super(p, minim, x, y);
	}
	
	public void update(){
		
	}
	
	public void render(){
		p.pushMatrix();
		//turret base goes here
		p.popMatrix();
	}
}
