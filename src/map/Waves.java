package map;

import java.util.ArrayList;

public class Waves {
	String line;
	
	ArrayList<int[]> wave;
	
	Waves(String line){
		this.line = line;
		
		wave = new ArrayList<int[]>();
	}
	
	void divideWave(){
		String[] split = line.split("|");
		for(String a: split){
			String[] temp = a.split(",");
			int[] creeps = new int[3];
			for(int i = 0; i < creeps.length; i++){
				creeps[i] = Integer.parseInt(temp[i]);
			}
			wave.add(creeps);
		}
	}
}
