package map;

import java.util.ArrayList;

public class Wave {
	String line;
	
	ArrayList<int[]> subWave;
	
	Wave(String line){
		this.line = line;
		
		subWave = new ArrayList<int[]>();
		divideWave();
	}
	
	void divideWave(){
		String[] split = line.split("|");// Divides up the lines for each sub wave.
		for(String a: split){
			String[] temp = a.split(",");// Splits up the spawn number in the sub wave.
			int[] creeps = new int[3];
			for(int i = 0; i < creeps.length; i++){
				creeps[i] = Integer.parseInt(temp[i]);// Changes the String to an integer.
			}
			subWave.add(creeps);
		}
	}

	public int[] getSubWave(int subWaveIndex) {// Returns the sub wave.
		return subWave.get(subWaveIndex);
	}

	public int getSubWaveSize() {// Returns the number of sub waves.
		return subWave.size();
	}
}
