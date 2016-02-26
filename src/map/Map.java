package map;

import java.io.*;
import java.util.*;
import gameObject.Tile;
import processing.core.*;

public class Map {
	
	PApplet p;
	//String for the map name
	String mapName;
	//Image to hold the background
	PImage mapBackground;
	//Array to hold the mapPaths
	ArrayList<Path> mapPaths;
	//ArrayList to hold all of the tiles that will be loaded in
	ArrayList<Tile> mapTiles;
	//Width and height of the tiles
	int tileWidth,tileHeight;
	
	public Map(PApplet p) {
		//Initializing mapPath array list
		mapPaths = new ArrayList<Path>();
		mapTiles = new ArrayList<Tile>();
		this.p = p;
		
		//Initializing the tiles width and height
		tileWidth = 0;
		tileHeight = 0;
		
		//Adding for paths to the array list(i = greatest number of paths per map)
		for(int i = 0; i < 4; i++) {
			mapPaths.add(new Path());
		}
	}
	
	public void loadMap(String mapFile) {
		
		//String to hold the background file
		@SuppressWarnings("unused")
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
				
				//Getting the background file name
				backgroundFile = lines.get(0);
				
				//Getting the width and height of the tiles
				String[] tSize = lines.get(1).split(",");
				tileWidth = Integer.parseInt(tSize[0]);
				tileHeight = Integer.parseInt(tSize[1]);
				
				//Going through each line of the file(first line ignored, holds background file name)
				for(int i = 2; i < lines.size(); i++) {
					//String array to hold each set of tile data in each line
					String[] tileDataSet = lines.get(i).split("\\|");
					
					//Going through each set of tile data
					for(int j = 0; j < tileDataSet.length; j++) {
						
						//Number of cols in the map
						int n = tileDataSet.length;
						
						//Separating the individual bits of data for each tile
						String[] tileData = tileDataSet[j].split(",");
						
						//Getting the tile value
						int tileVal = Integer.parseInt(tileData[0]);
						
						//Getting the x/y of the tile/path point
						//TODO make it so you dont have to manually enter tile/half tile sizes
						int x = (((tileNum%n))*tileWidth) + tileWidth/2; 
						int y = (((tileNum/n))*tileHeight) + tileHeight/2;
						
						//Getting the path points
						for(int k = 1; k < tileData.length; k++) {
							if(tileData[k] != "") {
								int num = Integer.parseInt(tileData[k]);
								mapPaths.get(k-1).addPoint(num, x, y);
							}
						}
						
						mapTiles.add(new Tile(p,tileNum,tileVal,x,y));
						tileNum++;
					}
				}
			
			} finally {
				reader.close();
			}
			
		} catch (IOException e) {
			//Printing the stack trace for the exception
			e.printStackTrace();
		}
	}
	
	//TODO Have no idea if this will actually be used
	public void saveMap() {
		
	}
	
	public ArrayList<Tile> getTiles() {
		return mapTiles;
	}
	
	//TODO dunno if this will be used or not
	public void render(boolean editor) {
		
		for(int i = 0; i < mapTiles.size(); i++) {
			mapTiles.get(i).render();
		}
		
		if(editor) {
			
		}
		
	}
}
