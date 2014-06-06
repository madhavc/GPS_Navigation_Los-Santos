package edu.csupomona.cs.cs241.prog_assigmnt_4;

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
 * This is class is used to create an between two vertices (locations). 
 */
public class Edge {
	
	/**
	 * The from location of the Edge.
	 */
	private LocationNode locationFrom;
	
	/**
	 * The to location of the Edge.
	 * 
	 */
	private LocationNode locationTo;
	
	/**
	 * The distance in total number of miles between two locations.
	 */
	private int distance;
	
	
	/**
	 * Path between the two location.
	 */
	private String path;
	
	/**
	 * The name of the street.
	 */
	private String streetName;

	/**
	 * This is constructor for the Edge class.
	 * @param from 
	 * @param to
	 * @param miles
	 * @param direction
	 * @param street
	 */
	public Edge(LocationNode from, LocationNode to, int miles, String direction, String street){
	 	
		this.setLocationFrom(from);
		this.setLocationTo(to);
		this.setDistance(miles);
		this.setPath(direction);
		this.setStreetName(street);
	}

	/**
	 * Returns the "FROM" location.
	 * @return the locationFrom
	 */
	public LocationNode getLocationFrom() {
		return locationFrom;
	}

	/**
	 * Sets the "FROM" location.
	 * @param locationFrom
	 */
	public void setLocationFrom(LocationNode locationFrom) {
		this.locationFrom = locationFrom;
	}

	/**
	 * Returns the "TO" location.
	 * @return the locationTo
	 */
	public LocationNode getLocationTo() {
		return locationTo;
	}

	/**
	 * Sets the "TO" location.
	 * @param locationTo 
	 */
	public void setLocationTo(LocationNode locationTo) {
		this.locationTo = locationTo;
	}

	/**
	 * @return the distance in total number of miles between two locations.
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance total number of miles between two locations.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the path from to the location.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path sets the path of the from and to Location.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the streetName of the location.
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName name of the location.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	

}

