package map;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import gameObject.Tile;
import processing.core.*;

import gameObject.Tile;

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
	
	public Map(PApplet p) {
		//Initalising mapPath arraylist
		mapPaths = new ArrayList<Path>();
		mapTiles = new ArrayList<Tile>();
		this.p = p;
		
		//Adding for paths to the arraylist(i = greatest number of paths per map)
		for(int i = 0; i < 4; i++) {
			mapPaths.add(new Path());
		}
	}
	
	public void loadMap(String mapFile) {
		
		//String to hold the background file
		String backgroundFile;
		
		//List to hold all lines of the file
		List<String> lines = null;
		
		//Tile number
		int tileNum = 0;
		
		//Trying to read in the file
		try {
			//List that holds every line of the file
			lines = Files.readAllLines(Paths.get(mapFile), StandardCharsets.US_ASCII);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(lines != null) {
			//Getting the background file name
			backgroundFile = lines.get(0);
			
			//Going through each line of the file(first line ignored, holds background file name)
			for(int i = 1; i < lines.size(); i++) {
				//String array to hold each set of tile data in each line
				String[] tileDataSet = lines.get(i).split("\\|");
				
				//Getting the background file name
				backgroundFile = lines.get(0);
				
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
					int x = (((tileNum%n))*20) + 10; 
					int y = (((tileNum/n))*20) + 10;
					
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
		}
	}
	
	//TODO Have no idea if this will actually be used
	public void saveMap() {
		
	}
	
	public ArrayList<Tile> getTiles() {
		return mapTiles;
	}
	
	//TODO dunno if this will be used or not
	public void render() {
		
		for(int i = 0; i < mapTiles.size(); i++) {
			mapTiles.get(i).render();
		}
		
		for(int i = 0; i < mapPaths.get(0).path.size(); i++) {
			int x = (int) mapPaths.get(0).path.get(i).ppLoc.x;
			int y = (int) mapPaths.get(0).path.get(i).ppLoc.y;
			
			//System.out.println(x + " " + y);
			
			p.fill(255,0,0);
			p.rectMode(PConstants.CENTER);
			p.rect(x,y,10,10);
			
			int c = PApplet.round(p.frameCount%p.frameRate);
			System.out.println(p.frameRate);
			
			if(c % (p.round(p.frameRate/5)) == 0) {
				//System.out.println("LOAD THE NEXT FRAME" + c);
			}
		}
	}
}
