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

    public static void main(String[] args) {
    	Graph graph = new Graph(); 
    	graph.readFile(args[0]);

    }

    
}
