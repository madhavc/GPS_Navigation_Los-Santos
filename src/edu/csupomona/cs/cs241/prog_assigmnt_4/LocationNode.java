/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #4
 *
 * This is a text-based navigation program for the city of 
 * Los Santos, that implements Dijkstra's algorithm, Hash Maps, 
 * and Array Lists to provide the user with directions.
 *
 * @author Madhav Chhura
 */
package edu.csupomona.cs.cs241.prog_assigmnt_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is used to create a Vertex, which will represent a 
 * Location on the map.
**/
public class LocationNode {
	

	/**
	 * locationName is a final string variable which holds the name 
	 * of the location. 
	 */
	public final String locationName;
	
	
	/**
	 * keywords is a final string list which will hold the keywords 
	 * related to the {@link #locationName} location.
	 */
	public final ArrayList<String> keywords;
	
	
	/**
	 * The adjacencyList is used to hold all the adjacent locations of the 
	 * {@link #locationName} location.
	 */
	public HashMap<LocationNode, Edge> adjacencyList = new HashMap<LocationNode, Edge>();

	/**
	 * The reverseList is used to hold all the reverse adjacent locations of the 
	 * {@link #locationName} location.
	 */
	public ArrayList<String> reverseList = new ArrayList<String>();
	

	/**
	 * This is a constructor of the class {@link LocationNode} which is used to 
	 * initialize a {@link #locationName} - location and related 
	 * {@link #keywords} - keywords.
	 * 
	 * @param name takes the name of the Location which you want to add.
	 * @param kw holds the array of type String which represents the keywords of
	 * 		  that certain {@link #locationName} location.
	 */
	public LocationNode(String name, String[] kw){
		this.locationName = name;
		this.keywords = new ArrayList<String>(Arrays.asList(kw));
	}


	/**
	 * The addAdjacentLocation adds locations that are adjacent to the {@link #locationName}
	 * location. 
	 * 
	 * @param node is the location which is adjacent to the {@link #locationName} location.
	 * @param edge is a object, which is created using the edge class to represent a road
	 * 		  between both of the locations.  
	 */
	public void addAdjacentLocation(LocationNode node, Edge edge){
		adjacencyList.put(node, edge);
	}
	
	
	/**
	 * The addReverseAdjacentLocation adds the reverse locations that are adjacent to the {@link #locationName}
	 * location. 
	 * @param name
	 */
	public void addReverseAdjacenctLocation(String name){
		reverseList.add(name);
	}


	/**
	 * The getKeywords is a getter method.
	 * @return the keywords
	 */
	public ArrayList<String> getKeywords() {
		return keywords;
	}


	/**
	 * The getLocationName is the getter method.
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	
}
