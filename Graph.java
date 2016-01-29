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

	/* Finds degree between c1 and c2 
	public int bfsDegree(String startCity, String endCity) {
		if (city1.equals(city2)) {
			return 0; 
		} 
		/* check startCity's interstates */ 
			/* add startCity's interstates to queue */ 
			/* see if endCity is neighbor of each interstate */ 
			/* if yes, return 1 */ 
			/* if no, return 1 + bfsDegree(neighborCity, endCity) 
		LinkedList<City> queue = new LinkedList<City>(); 

	} */ 

	/* Find degrees */ 
	public void dfs(String startCity, int degree) {
		LinkedList<Interstate> queue = new LinkedList<Interstate>(); 
		City start = cities.get(startCity); 
		for (int i : start.getInterstates()) {
			Interstate current = interstates.get(i); 
			if (!current.traversed()) {
				queue.push(current); 
			}
		}
		while (!queue.isEmpty()) {
			Interstate current = queue.getFirst(); 
			current.mark(); 
			for (City c : current.getNeighbors(start)) {
				if (!c.traversed()) {
					System.out.println(c.name() + " " + degree); 
					c.mark(); 
					this.dfs(c.name(), degree + 1); 
				}
			}
			queue.removeFirst(); 
		}
	}

	public void bfs(String startCity) {
		for (City c : cities.values()) {
			c.distance = 9999; 
			c.parent = null; 
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
					System.out.println(c.name() + " " + c.distance); 
					queue.push(c); 
				}
			}
		}

	}

	public void printInterstates() {
		for (Interstate i : interstates.values()) {
			HashSet<City> connected = i.getCities(); 
			System.out.print(i.id () + " : " ); 
			for (City c : connected) {
				System.out.print(" " + c.name() + ","); 
			}
			System.out.println(""); 
		}
	}

    public static void main(String[] args) {
    	Graph graph = new Graph(); 
    	graph.readFile(args[0]);
    	graph.printCities(); 
    	graph.printInterstates(); 
    	graph.bfs("Chicago"); 

    }

    
}
