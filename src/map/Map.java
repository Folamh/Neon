package map;

import java.io.*;
import java.util.*;
import processing.core.*;

public class Map {
	
	//PApplet for drawing on screen and stuff
	PApplet p;
	
	//String for the map name
	String mapName;
	
	//Strings to hold the background and building file
	String backgroundFile;
	String buildingFile;
	
	//Path grid width, height, xStartLoc, yStartLoc, gridBoxWidth, gridBoxHeight 
	int pCols, pRows, pX, pY, pW, pH;
	
	//Turret grid width, height, xStartLoc, yStartLoc, gridBoxWidth, gridBoxHeight 
	int tCols, tRows, tX, tY, tW, tH;
	
	//Array to hold the mapPaths
	ArrayList<Path> mapPaths;
	
	//Constructor
	public Map(PApplet p) {
		//Initializing mapPath array list
		mapPaths = new ArrayList<Path>();
		this.p = p;
		
		//Max number of paths
		int paths = 4;
		
		//Adding for paths to the array list(paths = greatest number of paths per map)
		for(int i = 0; i < paths; i++) {
			mapPaths.add(new Path(p));
		}
	}
	
	public void loadMap(String mapFile) {
		
		//List to hold all lines of the file
		ArrayList<String> lines = new ArrayList<String>();
		
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
				
				//Getting the background and building paths
				backgroundFile = lines.get(0);
				buildingFile = lines.get(1);
				
				//Getting the path grid details
				String gridDetails[] = lines.get(2).split(",");
				
				tCols = Integer.parseInt(gridDetails[0]);
				tRows = Integer.parseInt(gridDetails[1]);
				tX = Integer.parseInt(gridDetails[2]);
				tY = Integer.parseInt(gridDetails[3]);
				tW = Integer.parseInt(gridDetails[4]);
				tH = Integer.parseInt(gridDetails[5]);
				
				//Getting the turret grid details
				gridDetails = lines.get(3).split(","); 
				
				pCols = Integer.parseInt(gridDetails[0]);
				pRows = Integer.parseInt(gridDetails[1]);
				pX = Integer.parseInt(gridDetails[2]);
				pY = Integer.parseInt(gridDetails[3]);
				pW = Integer.parseInt(gridDetails[4]);
				pH = Integer.parseInt(gridDetails[5]);
				
				//Current slot for the path points
				int ppNum = 0;
				int ppXSlot = 0;
				int ppYSlot = 0;
				
				//Getting all the path points
				for(int i = 5; i < lines.size(); i++) {
					//Getting next row of path points
					String row[] = lines.get(i).split("|");
					
					//Going through each element in the row
					for(int r = 0; r < row.length; r++) {
						
						//Splitting the row up into its columns
						String col[] = row[r].split(",");	
						
						//Getting the current position for the path point
						ppXSlot = ((ppNum%pCols)*pX)+(pX/2);
						ppYSlot = ((ppNum/pCols)*pY)+(pY/2);
						
						for(int c = 0; c < col.length; c++) {
							
							//Adding path points
							if(col[c] != "") {
								
								//Adding path points
								mapPaths.get(c).addPoint(Integer.parseInt(col[c]),ppXSlot,ppYSlot);
							}
						}
						
						//Incrementing the path point number
						ppNum++;
					}
				}
			} 
			
			//Stuff done after map is loaded
			finally {
				
				//Sorting all of the paths
				for(Path path: mapPaths) {
					path.sortPath();
				}
				
				//Closing file
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
	
	//Function for returning the background path
	public String getBackground() {
		return backgroundFile;
	}
	
	//Function for returning the building file path
	public String getBuilding() {
		return buildingFile;
	}
	
	//Function for getting tower grid columns
	public int getTowerGridCol() {
		return tCols;
	}
	
	//Function for getting the tower grid rows
	public int getTowerGridRow() {
		return tRows;
	}
	
	//Function for getting the path grid columns
	public int getPathGridCol() {
		return pCols;
	}
	
	//Function for getting the path grid rows
	public int getPathGridRow() {
		return pRows;
	}
	
	//Function for setting tower grid columns
	public void setTowerGridCol(int x) {
		tCols = x;
		if(tCols < 1) tCols = 1;
	}
	
	//Function for setting the tower grid rows
	public void setTowerGridRow(int x) {
		tRows = x;
		if(tRows < 1) tRows = 1;
	}
	
	//Function for setting the path grid columns
	public void setPathGridCol(int x) {
		pCols = x;
		if(pCols < 1) pCols = 1;
	}
	
	//Function for setting the path grid rows
	public void setPathGridRow(int x) {
		pRows = x;
		if(pRows < 1) pRows = 1;
	}
	
	//Function for getting the x position of the tower grid
	public int getTowerGridGapX() {
		return tX;
	}
	
	//Function for getting the y position of the tower grid
	public int getTowerGridGapY() {
		return tY;
	}
	
	//Function for getting the x position of the path grid
	public int getPathGridGapX() {
		return pX;
	}
	
	//Function for getting the y position of the path grid
	public int getPathGridGapY() {
		return pY;
	}
	
	//Function for setting the x position of the tower grid 
	public void setTowerGridGapX(int x) {
		tX = x;
	}
	
	//Function for setting the y position of the tower grid
	public void setTowerGridGapY(int y) {
		tY = y;
	}
	
	//Function for setting the x position of the path grid
	public void setPathGridGapX(int x) {
		pX = x;
	}
	
	//Function for setting the y position of the path grid
	public void setPathGridGapY(int y) {
		pY = y;
	}
	
	//Function for getting the width of the tower grid
	public int getTowerGridW() {
		return tW;
	}
	
	//Function for getting the height of the tower grid
	public int getTowerGridH() {
		return tH;
	}
	
	//Function for getting the width of the path grid
	public int getPathGridW() {
		return pW;
	}
	
	//Function for getting the height of the path grid
	public int getPathGridH() {
		return pH;
	}
	
	//Setting the width of the tower grid
	public void setTowerGridW(int x) {
		tW = x;
		if(tW < 1) tW = 1;
	}
	
	//Setting the height of the tower grid
	public void setTowerGridH(int x) {
		tH = x;
		if(tH < 1) tH = 1;
	}
	
	//Setting the width of the path grid
	public void setPathGridW(int x) {
		pW = x;
		if(pW < 1) pW = 1;
	}
	
	//Setting the height of the path grid
	public void setPathGridH(int x) {
		pH = x;
		if(pH < 1) pH = 1;
	}
	
	//Function for drawing the grid for towers
	public void drawTowerGrid() {
		//Drawing the vertical lines
		for(int i = 0; i <= tCols; i++) {
			p.line((tW*i)+tX, tY, (tW*i)+tX, (tH*tRows)+tY);
		}
		
		//Drawing the horizontal lines
		for(int i = 0; i <= tRows; i++) {
			p.line(tX, (tH*i)+tY, (tW*tCols)+tX, (tH*i)+tY);
		}
	}
	
	//Function for drawing pathpoint grid
	public void drawPathGrid() {
		//Drawing the vertical lines
		for(int i = 0; i <= pCols; i++) {
			p.line((pW*i)+pX, pY, (pW*i)+pX, (pH*pRows)+pY);
		}
		
		//Drawing the horizontal lines
		for(int i = 0; i <= pRows; i++) {
			p.line(pX, (pH*i)+pY, (pW*pCols)+pX, (pH*i)+pY);
		}
	}
}
