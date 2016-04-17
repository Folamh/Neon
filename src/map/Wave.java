package map;

import java.util.ArrayList;

public class Wave {
	String line;
	
	ArrayList<int[]> wave;
	
	Wave(String line){
		this.line = line;
		
		wave = new ArrayList<int[]>();
		divideWave();
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

	public int[] getSubWave(int subWave) {
		return wave.get(subWave);
	}

	public int getSubWaveSize() {
		return wave.size();
	}
}
