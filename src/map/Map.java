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
	
	//Path grid width, height, xoffset, yoffset 
	int pCols, pRows, pX, pY;
	
	//Turret grid width, height, xoffset, yoffset 
	int tCols, tRows, tX, tY;
	
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
				String gridDetails[] = lines.get(3).split(",");
				
				tCols = Integer.parseInt(gridDetails[0]);
				tRows = Integer.parseInt(gridDetails[1]);
				tX = Integer.parseInt(gridDetails[2]);
				tY = Integer.parseInt(gridDetails[3]);
				
				//Getting the turret grid details
				gridDetails = lines.get(4).split(","); 
				
				pCols = Integer.parseInt(gridDetails[0]);
				pRows = Integer.parseInt(gridDetails[1]);
				pX = Integer.parseInt(gridDetails[2]);
				pY = Integer.parseInt(gridDetails[3]);
				
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
	
	//Only used for editing purposes
	public void render(int path) {
		mapPaths.get(path).display();
	}
}
