package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;

public class Level {
	PApplet p;
	
	//File to be read from
	String file;
	//Wave spawn rate
	int waveRate;
	int spawnRate;
	
	ArrayList<Waves> Waves;
	
	Level(String file){
		this.file = file;
		
		waveRate = 30;
		spawnRate = 5;
		
		Waves = new ArrayList<Waves>();
	}
	
	void makeWaves() throws IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
		    	Waves.add(new Waves(line));
		    }
		}
	}
}
