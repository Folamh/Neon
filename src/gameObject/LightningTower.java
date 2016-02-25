package gameObject;

import processing.core.PApplet;

public class LightningTower extends Tower{
	
	LightningTower(PApplet p, int x, int y){
		super(p, x, y);
	}
	
	public void update(){
		
	}
	
	public void render(){
		p.pushMatrix();
		//turret base goes here
		p.popMatrix();
	}
}
