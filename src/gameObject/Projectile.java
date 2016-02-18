package gameObject;

public abstract class Projectile extends GameObject{
	Enemy target;
	
	Projectile(int x, int y, Enemy target){
		super(x, y);
		this.target = target;
	}
}
