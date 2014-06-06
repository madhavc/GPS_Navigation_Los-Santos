package edu.csupomona.cs.cs241.prog_assigmnt_4;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import edu.csupomona.cs.cs240.prog_assgmnt_1.List;

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

/**
 * The Graph class is used to create the set of vertices and edges. 
 * The Graph is constructed as a HashMap that holds Location objects and can be accessed using a String key. The
 * Graph maintains a Priority Queue of Locations that represent unvisited vertices when 
 * Determine the shortest path. Graph also maintains a HashMap of String[] Arrays that 
 * can be accessed using Strings. Each String array holds the names of Locations that
 * are associated with specified keywords. Within the Graph class are two different 
 * methods that mark paths from a starting point to any other point in the Graph.
 */
public class Graph {
	/**
	 * locations represents 
	 */
	HashMap<String, LocationNode> locations = new HashMap<String, LocationNode>();

	public Graph(){
		locations = new HashMap<String, LocationNode>();
	}
	
	/**
	 * The addLocation method is used to add a location(vertex) on the graph.
	 * The method uses the locationNode class to create a location and then it 
	 * puts the location in a HashMap.  Where the name is used as a key and the 
	 * keywords are used as a value. 
	 * 
	 * @param locationName is the name of the location you want to add to the graph.
	 * @param keywords are the keywords which are related to this location.
	 */
	public void addLocation(String name, String[] keywords) {
		LocationNode location = new LocationNode(name, keywords);
		locations.put(name, location);
	}

	/**
	 * addEdge method is used to create an edge(road) between two location. 
	 * The method takes in the name of two locations and then checks if the 
	 * location is in the HashMap. 
	 * 
	 * If the location is in the HashMap, then the edge is created by using the 
	 * Edge class. Else the method throws a new NoSuchElementException.
	 *
	 * @param from represents the location for which you want to create an edge from.
	 * @param to represents the location for which you want to create an edge to.
	 * @param miles is the total distance between the two locations.
	 * @param direction represents the direction to the location.
	 * @param street is the name of the edge(street). 
	 */
	public void addEdge(String from, String to, int miles, String direction, String street){
		LocationNode temp = locations.get(from);
		LocationNode temp1 = locations.get(to);

		if(temp == null || temp1 == null){
			System.out.println("Edge cannot be created becuase no two locations found in the Map.");
			throw new NoSuchElementException();
		}

		Edge edge = new Edge(temp,temp1,miles,direction,street);
		temp.addAdjacentLocation(temp1, edge);
		locations.get(to).addReverseAdjacenctLocation(from);
	} 

	/**
	 * search_by_keyword method is used to search for a location using its 
	 * keyword. 
	 * @param name The name of location that the available locations are to be searched for
	 * @return A list of locations matching the given name.
	 */
	public String[] search_by_keywords(String name){
		ArrayList<String> list = new ArrayList<String>();
		for(String set : locations.keySet()){
			for(String temp : locations.get(set).keywords){
				if(temp.toLowerCase().contains(name.toLowerCase())){
					list.add(set);
					break;
				}
			}
		}
		return list.toArray(new String[0]);
	}

	/**
	 * search_by_name method is used to find a location in the HashMap using the 
	 * name-key. The method uses the HashMap method containsKey() to search. 
	 * If the location exist in the HashMap then the name is returned, 
	 * else returns null.
	 * 
	 * @param name of the location you want to search for.
	 * @return location's name or 
	 * 		   null if the location does not exist in the HashMap. 
	 */
	public String[] search_by_name(String name){
		ArrayList<String> r = new ArrayList<String>();
		for(String s : locations.keySet()){
			if(s.toLowerCase().contains(name.toLowerCase()))
				r.add(s);
		}
		return r.toArray(new String[0]);
	}

	/**
	 * This method uses Dijkstra's Algorithm to determine the shortest path
	 * from the specified starting point.
	 * 
	 * @param from the starting location.
	 * @param to the ending location.
	 * @param index the path number.
	 * @return
	 */
	public String[] shortestPath(String from, String to, int index){
		ArrayList<String> result = new ArrayList<String>();
		result.add(from);
		
		HashMap<String, Integer> locationsWeight = new HashMap<String, Integer>();
		locationsWeight.put(from, 0);
	
		while(!result.isEmpty()){
			for(LocationNode k : locations.get(result.get(0)).adjacencyList.keySet()){
				if(locationsWeight.get(k.locationName) == null || (locationsWeight.get(result.get(0)) + locations.get(result.get(0)).adjacencyList.get(k).getDistance()) < locationsWeight.get(k.locationName)){
					locationsWeight.remove(k.locationName);
					locationsWeight.put(k.getLocationName(), locationsWeight.get(result.get(0)) + locations.get(result.get(0)).adjacencyList.get(k).getDistance());
					result.add(k.locationName);
				}
				
			}
			result.remove(0);
		}
		String location = to;
		ArrayList<String> route = new ArrayList<String>();
		int total;
		if(index < locations.get(location).reverseList.size())
			total = index;
		else
			total = locations.get(location).reverseList.size() - 1;
		int round = 0;
		
		// if the distance between the source and the adjacent vertex is
		// greater than the distance between the source and the current
		// vertex PLUS the weight between the current and adjacent
		// vertex, then we have found a shorter path than already
		// existed
		
		while(!location.equals(from)){
			String shortest = "";
			HashMap<Integer, String> paths = new HashMap<Integer, String>();
			for(String s : locations.get(location).reverseList){
				paths.put(locationsWeight.get(s), s);
			}
			
			Integer[] tempArray = paths.keySet().toArray(new Integer[0]);
			Arrays.sort(tempArray);
			if(round == 0){
				shortest = paths.get(tempArray[total]);
			}
			else{
				shortest = paths.get(tempArray[0]);
			}
			LocationNode temp = locations.get(location);
			Edge path = locations.get(shortest).adjacencyList.get(temp);
			route.add(0, "Drive " + path.getDistance() + " miles" + path.getStreetName() + " on " + path.getPath() + " toward " + location);
			location = shortest;
			round++;
		}
		return route.toArray(new String[0]);
	}
}
