import java.util.*;

/* Edges of the graph */ 
public class Interstate {
	private int number; 
	HashSet<City> connectedCities; 

	public Interstate(int i) {
		number = i; 
		connectedCities = new HashSet<City>(); 
	}

	public void addCity(City c) {
		connectedCities.add(c); 
	}

	public int id() {
		return number; 
	}

	public HashSet<City> getNeighbors(City current) {
		HashSet<City> clone = (HashSet<City>) connectedCities.clone(); 
		clone.remove(current); 
		return clone; 
	}

	public HashSet<City> getCities() {
		return connectedCities; 
	}

}