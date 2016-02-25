package animation;

import java.util.ArrayList;
import processing.core.*;

public class Images {
	
	PApplet p;
	
	//ArrayList that hold all animations
	ArrayList<ArrayList<PImage>> animations;
	
	//ArrayList that hold frames of an animation
	ArrayList<PImage> anim;
	
	Images(PApplet p) {
		this.p = p;
	}
	
	//Function to load in the animations
	public void loadAnimations() {
		//TODO load in all the animations
	}
	
	//Returning the array list of all animations
	public ArrayList<ArrayList<PImage>> getAnimations() {
		return animations;
	}
	
	
}
