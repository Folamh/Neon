package gameObject;


public abstract class Enemy extends GameObject{
	
	Enemy(int x, int y){
		super(x, y);
	}
	
	abstract void update();
	
	abstract void render();

}
