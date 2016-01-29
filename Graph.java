import java.io.File; 
import java.util.*;
import java.io.FileNotFoundException; 

public class Graph {

	private HashMap<String, City> cities; 
	private HashMap<Integer, Interstate> interstates; 

	public Graph() {
		cities = new HashMap<String, City>(); 
		interstates = new HashMap<Integer, Interstate>(); 

	}

	public void addCity(City c) {
		cities.put(c.name(), c); 
	}

	public void addInterstate(Interstate i) {
		interstates.put(i.id(), i); 
	}



	public void readFile(String fileName) {
		Scanner s; 
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
			System.out.println("INVALID FILE PATH"); 
			return; 
		}
		int population = 0; 
		String cityName; 
		String state; 
		String[] interstateArr; 
		String line[]; 
		while(s.hasNextLine()) { 
			line = s.nextLine().split("\\|"); 
			population = Integer.parseInt(line[0]); 
			cityName = line[1]; 
			state = line[2]; 
			City newCity = new City(population, cityName, state);
			interstateArr = line[3].replaceAll(" ", "").split("\\D+"); 
			for (String str : interstateArr) {
				if (!str.isEmpty()) {
					int id = Integer.parseInt(str); 
					/* If the interstate already exists in the graph */ 
					if (interstates.containsKey(id)) {
						interstates.get(id).addCity(newCity); 
						newCity.addInterstate(id);
					} else {
					/* If the interstate is new to the graph */
						Interstate newInterstate = new Interstate(id); 
						newInterstate.addCity(newCity); 
						this.addInterstate(newInterstate); 
						newCity.addInterstate(id); 
					}
				}
			} 
			/* Add the City to the Graph */ 
			this.addCity(newCity); 
		}
	}

	public static void printDegrees() {
		/*City chicago = cities.get("Chicago"); 
		HashSet<City> neighbors = chicago.findNeighborCities(cities.values()); 
		Iterator<City> iter = neighbors.iterator(); 
		while(iter.hasNext()) {
			System.out.println(iter.next().name()); 
		}
		chicago.findConnectedCities(cities.values()); */
	}

	public void printCities() {
		for (City c : cities.values()) {
			System.out.println(c.name() + "," + c.state() + " "); 
		}
	}


	public void bfs(String startCity) { 
		for (City c : cities.values()) {
			c.distance = 999; 
		}
		City root = cities.get(startCity);
		root.distance = 0; 
		LinkedList<City> queue = new LinkedList<City>(); 
		queue.push(root);
		City current; 

		while(!queue.isEmpty()) {
			current = queue.removeFirst(); 
			HashSet<City> neighbors = new HashSet<City>(); 
			for(int i : current.getInterstates()) {
				neighbors.addAll(interstates.get(i).getNeighbors(current)); 
			}
			for (City c : neighbors) {
				if (c.distance > current.distance + 1) {
					c.distance = current.distance + 1; 
					queue.push(c); 
				}
			}
		}
		this.printBFSResult(); 
	}

	public void printBFSResult() {
		TreeMap<Integer, TreeSet<String>> distanceMap = new TreeMap<Integer, TreeSet<String>>();
		for (City c : cities.values()) {
			if (distanceMap.containsKey(c.distance)) {
				distanceMap.get(c.distance).add(c.name());
				distanceMap.put(c.distance, distanceMap.get(c.distance)); 
			} else {
				TreeSet<String> newSet = new TreeSet<String>(); 
				newSet.add(c.name()); 
				distanceMap.put(c.distance, newSet);  
			}	
		}
		for (int i : distanceMap.descendingKeySet()) {
			for (String city : distanceMap.get(i)) {
				System.out.println(i + " " + city);
			}
		}
	}

	public void printInterstates() {
		for (Interstate i : interstates.values()) {
			HashSet<City> connected = i.getCities(); 
			System.out.print(i.id () + " : " ); 
			for (City c : connected) {
				System.out.print(" " + c.name()); 
			}
			System.out.println(""); 
		}
	}

    public static void main(String[] args) {
    	Graph graph = new Graph(); 
    	graph.readFile(args[0]);
    	graph.bfs("Chicago"); 

    }

    
}
