package gameObject;

public abstract class Projectile extends GameObject{
	Enemy target;
	
	Projectile(float x, float y, Enemy target){
		super(x, y);
		this.target = target;
	}
}
