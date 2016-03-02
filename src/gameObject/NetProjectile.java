package gameObject;

import processing.core.PApplet;

public class NetProjectile extends Projectile {

	public NetProjectile(PApplet p, int x, int y, Enemy target) {
		super(p, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		p.pushMatrix();
		// TODO Auto-generated method stub
		p.popMatrix();
	}

}
