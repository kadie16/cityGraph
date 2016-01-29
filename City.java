import java.io.File; 
import java.util.*;

/* Vertices of Graph */
public class City {
    	private int population; 
    	private String state; 
    	private String name; 
    	private HashSet<Integer> interstates;  
        public int distance; 
    	
    	public City(int population, String city, String state) {
    		this.population = population; 
    		this.state = state; 
    		interstates = new HashSet<Integer>(); 
    		this.name = city; 
            distance = 0; 
    	}

    	public HashSet<Integer> getInterstates() {
    		return interstates; 
    	}

        public void addInterstate(int i)  {
            interstates.add(i); 
        }

    	public String name() {
    		return name; 
    	}

    	public int population() {
    		return this.population; 
    	}

    	public void setPopulation(int newPopulation) {
    		this.population = newPopulation; 
    	}

    	public String state(){
    		return this.state; 
    	}


    }

