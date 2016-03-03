package map;

import java.io.*;
import java.util.*;
import processing.core.*;

public class Map {
	
	PApplet p;
	//String for the map name
	String mapName;
	//Array to hold the mapPaths
	ArrayList<Path> mapPaths;
	
	//Spacing between the path points
	int vertSpace, horiSpace;
	
	public Map(PApplet p) {
		//Initializing mapPath array list
		mapPaths = new ArrayList<Path>();
		this.p = p;
		
		//Adding for paths to the array list(i = greatest number of paths per map)
		for(int i = 0; i < 4; i++) {
			mapPaths.add(new Path(p));
		}
	}
	
	public void loadMap(String mapFile) {
		
		//String to hold the background file
		String backgroundFile;
		
		//List to hold all lines of the file
		ArrayList<String> lines = new ArrayList<String>();
		
		//Tile number
		int tileNum = 0;
		
		//Trying to read in the file
		try {
			
			//Buffered reader to read in lines from the file
			BufferedReader reader = new BufferedReader(new FileReader(mapFile));
			
			try {
				//String to hold lines
				String line = "";
				
				//Going through each line of the file and adding it to the array list
				while((line = reader.readLine()) != null) {
					lines.add(line);
				}
				
				String[] temp = lines.get(0).split("\\|");
				
				vertSpace = p.height/lines.size();
				horiSpace = p.width/temp.length;
				
				//Going through each line of the file(first line ignored, holds background file name)
				for(int i = 0; i < lines.size(); i++) {
					//String array to hold each set of tile data in each line
					String[] tileDataSet = lines.get(i).split("\\|");
					
					//Going through each set of tile data
					for(int j = 0; j < tileDataSet.length; j++) {
						
						//Number of cols in the map
						int n = tileDataSet.length;
						
						//Separating the individual bits of data for each tile
						String[] tileData = tileDataSet[j].split(",");
						
						//Getting the x/y of the tile/path point
						//TODO make it so you don't have to manually enter tile/half tile sizes
						int x = (((tileNum%n))*horiSpace);// + horiSpace/2; 
						int y = (((tileNum/n))*vertSpace);// + vertSpace/2;
						
						//Getting the path points
						for(int k = 0; k < tileData.length; k++) {
							if(tileData[k] != "") {
								int num = Integer.parseInt(tileData[k]);
								mapPaths.get(k).addPoint(num, x, y);
							}
						}
						tileNum++;
					}
				}
			
			} finally {
				
				for(Path path: mapPaths) {
					path.sortPath();
				}
				
				reader.close();
			}
			
		} catch (IOException e) {
			//Printing the stack trace for the exception
			e.printStackTrace();
		}
	}
	
	//Returning the map paths
	public ArrayList<Path> getPaths() {
		return mapPaths;
	}
	
	//Will be used when editing the map
	public void saveMap() {
		
	}
	
	//Only used for editing purposes
	public void render(int path) {
		mapPaths.get(path).display();
	}
}
