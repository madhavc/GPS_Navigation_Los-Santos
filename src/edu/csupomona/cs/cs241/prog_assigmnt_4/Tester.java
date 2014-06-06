
package edu.csupomona.cs.cs241.prog_assigmnt_4;
import java.util.Scanner;
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
 * This is a Tester class which also contains the main program. 
 * The class instantiates a new Graph called Los Santos and then 
 * calls methods depending on users needs. 
 */
public class Tester {

	/** Instantiated the graph for the city of Los Santos.In the graph class
	 *  each Vertex represent a Location in the city and the edges connected 
	 *  to these vertices represent roads between one location to the other.
	 */
	static Graph Los_Santos = new Graph();

	/** The initial location is set to the Los Santos City Hall */
	static String initialLocation = "Los Santos City Hall";

	/** The static Scanner object is used to get input from the keyboard. */
	static Scanner kb = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		initiateGraph(Los_Santos); //Populates the graph using the method initiateGraph().

		System.out.print("*****Welcome to the city of Los Santos!*****\nYou current location is Los Santos City Hall\n");
		System.out.print("\nTo Find a Destination - enter: 0\n");
		System.out.print("For a Point-to-Point Navigation - enter: 1\n");
		System.out.print("Enter any other number to Quit\n");
		System.out.print("What would you like to do?\n");
		int input = kb.nextInt();
		kb.nextLine();

		switch (input){
			case 0: getDestination();
				break;
			case 1: point_to_point();
				break;
			default:
			System.out.print("Exiting..");
		}
	}
	/**
	 * This method provides point to point direction for the user 
	 * depending on the From and To location which the user is 
	 * asked to enter in.
	 */ 
	private static void point_to_point(){
		System.out.println("**FROM location**");
		String from = searchByName();
		System.out.println("**TO location**");
		String to = searchByName();
		System.out.println("Directions\n"
				+ "From: " + from 
				+ "\nTo: " + to);
		directions(from,to,0);
		System.out.println("Arrive at "+ to);
	}

	/**
	 * This method is called when the user wants to Find a destination on 
	 * the map. There are two options which the user is presented with 
	 * First option is to Search by the name of the Location and 
	 * Second option to enter a location by keyword to search for.
	 * 
	 * After the user chooses there option then they are given a 
	 * choice to either Navigate or to get Directions for that specific 
	 * location they searched for.
	 */
	private static void getDestination() {
		String result[], nameResult = null;
		boolean flag = true;

		System.out.print("To search by a keyword - enter 0\n");
		System.out.print("To search by name - enter 1\n");
		int input = kb.nextInt();
		kb.nextLine();

		if(input == 0){

			while(flag){
				System.out.print("Please enter exact keyword of the location you would like to seacrh for: ");
				String kw = kb.nextLine();
				result = Los_Santos.search_by_keywords(kw);
				if(result.length == 0)
					System.out.println("No such keyword-location exists!");
				else{
					System.out.println("Please select one of the following options");
					System.out.println("0) Select the closest " + kw + " near me.");
					for(int i = 0; i < result.length; i++)
						System.out.println(i + 1 + ") " + result[i]);
					int selection = kb.nextInt();

					while(selection < 0 || selection > result.length){
						System.out.println("The number you have etered is not in the list. Please re-enter a number again.");
						selection = kb.nextInt();
					}
					if(selection == 0) 
						nameResult = result[selection];
					else
						nameResult = result[selection-1];
					flag = false;
				}
			}
		}
		else if(input == 1)
			nameResult = searchByName();
		else 
			System.out.println("The input was incorrect. The program will now terminate.");

		flag = true;
		while(flag){
			System.out.println("To get directions - enter 0 \n" +
					"To Navigate - enter 1\n"+
					"What would you like to do: ");
			int userInput = kb.nextInt();

			if(userInput == 0){
				flag = false;
				System.out.println("Directions\n"
						+ "From: " + initialLocation
						+ "\nTo: " + nameResult);
				directions(initialLocation,nameResult,0);
				System.out.println("Arrive at "+ nameResult);
			}
			else if(userInput == 1){
				System.out.println("Directions\n"
						+ "From: " + initialLocation
						+ "\nTo: " + nameResult);
				navigate(initialLocation,nameResult,0);
				flag = false;
			}
			else
				System.out.println("Not a valid input. Please try again");
		}
	}
	/**
	 * If the user chooses to search for a specific location, then this 
	 * method is called where the user is asked type in the desired location. 
	 * The search engine is case sensitive so the user has to enter the exact same 
	 * location. If the destination's exact syntax matches a Location name, 
	 * then the method returns the name of the of the location to caller.
	 */
	private static String searchByName() {
		boolean flag = true;
		String[] name = null;
		while(flag){
			System.out.print("Please enter exact name of the location: ");
			String location = kb.nextLine();
			name = Los_Santos.search_by_name(location);
			if(name == null)
				System.out.println("Try again.");
			else
				flag = false;
		}
		return name[0];
	}
	

	/**
	 * This method parameters receives the user's starting location and ending location, 
	 * this method then prints out step-by-step directions to get from the start location
	 * to the end location. After each instruction is printed, the user is asked to proceed, 
	 * or to recalculate his or her route. If the user decides to recalculate his or her route, 
	 * a new shortest path is determined from his or her current location to the same desired 
	 * end location.
	 * 
	 * @param from is the starting location of the user.
	 * @param to is the ending location of the user.
	 * @param index is the 
	 */
	private static void navigate(String from, String to, int index) throws ArrayIndexOutOfBoundsException {
		String[] array = Los_Santos.shortestPath(from, to, index);
		try{
			for(String path : array){
				System.out.println(path);
				System.out.println("Enter - 0  for Next\n"
						+  "Enter - 1  to Reroute");
				if(kb.nextInt() == 0)
					break;
				else{
					navigate(Los_Santos.search_by_name(path.split(" ")[6])[0], to, 1);
					initialLocation = Los_Santos.search_by_name(array[array.length - 1].split(" ")[6])[0];
					return;
				}
			}
			initialLocation = Los_Santos.search_by_name(array[array.length - 1].split(" ")[6])[0];
		}
		catch(Exception e){
			System.out.println("Your are at this current location.");
		}
	}
	/**
	 * This method parameters receives the user's starting location and ending location, 
	 * this method then prints out step-by-step directions to get from the start location
	 * to the end location.
	 * @param from is the starting location of the user.
	 * @param to is the ending location of the user.
	 */
	private static void directions(String from, String to, int index) throws ArrayIndexOutOfBoundsException {
		try{
			String[] array = Los_Santos.shortestPath(from, to, index);
			for(String path : array){
				System.out.println(path);
			}
		}catch(Exception e){
			System.out.println("Your are at this current location.");
		}
	}

	/** 
	 * This method is used to populate the graph with location and there specific keywords. 
	 * Also this method creates roads(edges) between all the locations. 
	 * 
	 * The Avenues are 2-way and the Streets are 1-way.
	 */
	private static void initiateGraph(Graph map){
		map.addLocation("Los Santos Public Library", new String[] {"Library", "Recreation", "Landmark"});
		map.addLocation("Los Santos Saints' Stadium", new String[] {"Recreation", "Landmark", "Sports"});
		map.addLocation("Vinewood Boulevard", new String[] {"Recreation", "Landmark"});
		map.addLocation("Los Santos Forum", new String[] {"Recreation", "Landmark", "Sports"});
		map.addLocation("Los Santos City Hall", new String[] {"Landmark"});
		map.addLocation("Centennial Theater", new String[] {"Recreation", "Arts", "Landmark"});
		map.addLocation("All Saints General Hospital", new String[] {"Hospital", "Health&Care"});
		map.addLocation("Richman Country Club", new String[] {"Recreation"});
		map.addLocation("BurgerShot", new String[] {"Dining", "Fast-food", "Rrestaurant"});
		map.addLocation("Los Santos Gym", new String[] {"Fitness", "Health&Care"});
		map.addLocation("Cluckin' Bell", new String[] {"Dining", "Fast-food", "Restaurant"});
		map.addLocation("Pimiento's", new String[] {"Dining", "Restaurant"});
		map.addEdge("Los Santos Public Library", "Los Santos City Hall", 5, "1st St.","south");
		map.addEdge("Centennial Theater", "Los Santos Saints' Stadium",1, "2nd St.", "north");
		map.addEdge("Vinewood Boulevard", "All Saints General Hospital", 6, "3rd St.", "south");
		map.addEdge("Richman Country Club", "Los Santos Forum", 1, "4th St.", "north");
		map.addEdge("BurgerShot", "Centennial Theater", 2, "2nd St.", "north");
		map.addEdge("All Saints General Hospital", "Los Santos Gym", 1, "3rd St.", "south");
		map.addEdge("Cluckin' Bell", "Richman Country Club", 3, "4th St.", "north");
		map.addEdge("Pimiento's", "Cluckin' Bell", 3, "4th St.","north");
		map.addEdge("Los Santos Public Library", "Los Santos Saints' Stadium", 3,  "Main Ave.","east");
		map.addEdge("Los Santos Saints' Stadium", "Los Santos Public Library", 3, "Main Ave.", "west");
		map.addEdge("Los Santos Saints' Stadium", "Vinewood Boulevard", 2, "Main Ave.", "east");
		map.addEdge("Vinewood Boulevard", "Los Santos Saints' Stadium", 2, "Main Ave.", "west");
		map.addEdge("Vinewood Boulevard", "Los Santos Forum", 5, "Main Ave.","east");
		map.addEdge("Los Santos Forum", "Vinewood Boulevard", 5, "Main Ave.",  "west");
		map.addEdge("Los Santos City Hall", "Centennial Theater", 4, "Centennial Ave.", "east");
		map.addEdge("Centennial Theater", "Los Santos City Hall", 4, "Centennial Ave.", "west");
		map.addEdge("Centennial Theater", "All Saints General Hospital", 7, "Centennial Ave.", "east");
		map.addEdge("All Saints General Hospital", "Centennial Theater", 7, "Centennial Ave.","west");
		map.addEdge("All Saints General Hospital", "Richman Country Club", 3, "Centennial Ave.", "east");
		map.addEdge("Richman Country Club", "All Saints General Hospital", 3, "Centennial Ave.", "west");
		map.addEdge("BurgerShot", "Los Santos Gym", 1, "General Ave.", "east");
		map.addEdge("Los Santos Gym", "BurgerShot", 1,  "General Ave.","west");
		map.addEdge("Los Santos Gym", "Cluckin' Bell",  1, "General Ave.",  "east");
		map.addEdge("Cluckin' Bell", "Los Santos Gym",  1, "General Ave.",  "west");
		map.addEdge("Los Santos Gym", "Pimiento's",  1, "Food Alley",  "south-east");
		map.addEdge("Pimiento's", "Los Santos Gym",  1, "Food Alley", "north-west");
	}

}
