package gameObject;


public  abstract class Tower extends GameObject{
	
	Tower(int x, int y){
		super(x, y);
	}
	
	abstract void update();
	
	abstract void render();
}
