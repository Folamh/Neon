package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;

public class Level {
	PApplet p;
	
	//File to be read from
	File file;
	//Wave spawn rate
	int waveRate;
	int spawnRate;
	
	public ArrayList<Wave> wave;
	
	public Level(File file){
		this.file = file;
		
		waveRate = 30;
		spawnRate = 5;
		
		wave = new ArrayList<Wave>();
		try {
			makeWaves();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void makeWaves() throws IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // Processes the line
		    	wave.add(new Wave(line));
		    }
		}
	}
	
	public int[] getWave(int curWave, int subWave){
		return wave.get(curWave).getSubWave(subWave);
	}
}
