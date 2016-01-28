import java.util.*;

/* Edges of the graph */ 
public class Interstate {
	int number; 
	HashSet<City> connectedCities; 

	public Interstate(int i) {
		number = i; 
		connectedCities = new HashSet<City>(); 
	}

	public addCity(City c) {
		connectedCities.add(City); 
	}

	public HashSet<City> getNeighbors(City current) {
		HashSet<City> clone = connectedCities.clone(); 
		clone.remove(current); 
		return clone; 
	}

}