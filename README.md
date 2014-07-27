GPS_Navigation_Los-Santos
=========================
GPS_Navigation_Los-Santos is a text-based navigation program that implements Dijkstra's algorithm, Hash Maps to provide the user with directions around the City of Los Santos.

Course: CS 241, Edwin Rodríguez


Once the program loads, the user's initial location is Los Santos City Hall. The user is presented with two options: point-to-point navigation, and find destination.
In the point-to-point navigation, the user is allowed to enter any two locations by name, and the program provides directions from point A to point B. The directions correspond to the shortest path between the two points. For example, if the user entered City Hall and Vinewood Blvd, the program would display something like:
Drive 11 mi east on Centennial Ave.
Turn north on 3rd St.
Drive 6 mi north on 3rd St.
Arrive at Vinewood Blvd.
If the user enters find destination, then the user presented with the option of searching by name or by keyword. If the user searches by keyword, the keyword can be one of: Library, Recreation, Landmark, Sports, Arts, Hospital, Health&Care, Dining, Fast-food, Restaurant, and Fitness. When searching by keyword the user will be presented with a list of matching results, and have the option of selecting one. In both cases, once a location is found the user is presented with two options: get directions, and navigate. The get directions option will give a list of directions similar to the previous example. but the navigate will present instruction by instruction, followed by the prompt Next, upon which the next instruction is given until the destination is reached.
At any point during navigation, the user is presented with, in addition to getting the next instruction, the option to Reroute, upon which the program will compute an alternate route (the second best option from the current point), and present the new next set of instructions.
Finally, when searching locations by keyword the user will be given the option to find closest. For example, find nearest restaurant.
The navigation and directions system is implemented using Dijkstra's Shortest Path Algorithm. As for the lookup search, I used a Hash Map, and use the keywords as keys to store locations. Finally, for the find closest option, I used Breadth-First Search.
