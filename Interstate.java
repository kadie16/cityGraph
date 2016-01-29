import java.util.*;

/* Edges of the graph */ 
public class Interstate {
	private int number; 
	HashSet<City> connectedCities; 
	private boolean traversed; 

	public Interstate(int i) {
		number = i; 
		connectedCities = new HashSet<City>(); 
		traversed = false; 
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

	public void mark() {
    	traversed = true; 
    }

    public void unMark() {
        traversed = false; 
    }

    public boolean traversed() {
        return traversed; 
    }

}