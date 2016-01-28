import java.io.File; 
import java.util.*;
import java.io.FileNotFoundException; 

public class City {
    	private int population; 
    	private String state; 
    	private String name; 
    	private HashSet<Integer> interstates; 
    	private HashSet<City> neighborCities = new HashSet<City>(); 
    	
    	public City(int inPopulation, String inCity, String inState, HashSet<Integer> inInterstates) {
    		population = inPopulation; 
    		state = inState; 
    		interstates = inInterstates; 
    		name = inCity; 
    	}

    	public HashSet<Integer> getInterstates() {
    		return interstates; 
    	}

    	public String name() {
    		return name; 
    	}

    	public int getPopulation() {
    		return this.population; 
    	}

    	public void setPopulation(int newPopulation) {
    		this.population = newPopulation; 
    	}

    	public String getState(){
    		return this.state; 
    	}

    	public void addNeighbor(City neighbor) {
    		neighborCities.add(neighbor); 
    	}

    	public boolean areKnownNeighbors(City city) {
            if (neighborCities.isEmpty()) {
                return false; 
            }
    		return neighborCities.contains(city);
    	}

    	public HashSet<City> findNeighborCities(Collection<City> allCities){
    		Iterator<City> cityIter = allCities.iterator(); 
    		while (cityIter.hasNext()) {
    			checkIfNeighbors(cityIter.next()); 
    		}	
    		return this.neighborCities; 
    	}

    	public boolean checkIfNeighbors(City city) {
    		if (areKnownNeighbors(city)) {
    			return true; 
    		} else {
    			Iterator<Integer> interstateIter = city.getInterstates().iterator();
    			while (interstateIter.hasNext()){ 
                    int currInterstate = interstateIter.next();
    				if(this.interstates.contains(currInterstate)) {
    					city.addNeighbor(this); 
    					neighborCities.add(city); 
                        System.out.println(this.name + " shares " + currInterstate + " with " + city.name);
    					return true; 
    				}
    			}
    		}
    		return false; 	
    	}
    }