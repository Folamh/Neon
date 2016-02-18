package map;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import gameObject.Tile;
import processing.core.*;

public class Map {
	
	//String for the map name
	String mapName;
	//Image to hold the background
	PImage mapBackground;
	//Array to hold the mapPaths
	Path[] mapPaths;
	//ArrayList to hold all of the tiles that will be loaded in
	ArrayList<Tile> mapTiles;
	
	Map() {
		//Initalising mapPath array
		mapPaths = new Path[4];
	}
	
	public void loadMap(String mapFile) {
		
		//String to hold the background file
		String backgroundFile;
		
		int[] curPP = {0,0,0,0};
		
		//Trying to read in the file
		try {
			//List that holds every line of the file
			List<String> lines = Files.readAllLines(Paths.get(mapFile), StandardCharsets.US_ASCII);
			
			//Going through each line of the file(first line ignored, holds background file name)
			for(int i = 1; i < lines.size(); i++) {
				//String array to hold each set of tile data in each line
				String[] tileDataSet = lines.get(i).split("|");
				
				//Getting the background file name
				backgroundFile = lines.get(0); 
				
				//Going through each set of tile data
				for(int j = 0; j < tileDataSet.length; j++) {
					
					//Number of cols in the map
					int n = tileDataSet.length;
					
					//Separating the individual bits of data for each tile
					String[] tileData = tileDataSet[j].split(",");
					
					//Getting the tile number
					int tileNum = (n*(i-1))+(j+1);
					
					//Getting the tile value
					int tileVal = Integer.parseInt(tileData[0]);
					
					//TODO Find good way to read in all the path points
					
					while() {
						//Getting the path points
						for(int k = 1; k < tileData.length; k++) {
							if(Integer.parseInt(tileData[k]) == curPP[k-1]) {
								int x = tileNum%10;
								int y = tileNum - (tileNum%10);
								mapPaths[k].addPoint(x,y);
								curPP[k=1] ++;
							}
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//TODO Have no idea if this will actually be used
	public void saveMap() {
		
	}
	
	public void render() {
		
	}
}
