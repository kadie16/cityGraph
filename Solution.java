import java.io.File; 
import java.util.*;
import java.io.FileNotFoundException; 

public class Solution {

	public static HashMap<String, City> cities = new HashMap<String, City>(); 

	public static void readFile(String fileName) {
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
		String[] interstates; 
		String line[]; 
		while(s.hasNextLine()) {
			HashSet<Integer> interstateSet = new HashSet<Integer>(); 
			line = s.nextLine().split("\\|"); 
			population = Integer.parseInt(line[0]); 
			cityName = line[1]; 
			state = line[2]; 
			interstates = line[3].replaceAll(" ", "").split("\\D+"); 
			System.out.println(line[3]);
			for (String str2 : interstates) {
				if (!str2.isEmpty()) {
					interstateSet.add(Integer.parseInt(str2));
				}
			}
			City newCity = new City(population, cityName, state, interstateSet);
			cities.put(cityName, newCity); 
		}
	}

	public static void printDegrees() {
		City chicago = cities.get("Chicago"); 
		HashSet<City> neighbors = chicago.findNeighborCities(cities.values()); 
		Iterator<City> iter = neighbors.iterator(); 
		while(iter.hasNext()) {
			System.out.println(iter.next().name()); 
		}
		chicago.findConnectedCities(cities.values()); 
	}

    public static void main(String[] args) {
    	readFile(args[0]);
    	printDegrees(); 

    }

    
}
