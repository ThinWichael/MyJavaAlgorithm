package Quiz.LamdaExpression;

import java.util.List;
import java.util.stream.Stream;

/* There is a class Country that has methods getContinent() and getPopulatoin().
 * Write a function int getPopulation(List<Country> countries, String continent ) that computes the
 * total population of a given continent, given a list of all countries and the name of a continent, 
 * given a list of all countries and name of a continent.
 * 
 * */

public class Quiz13_7and13_8 {

	/* solution WITHOUT lamda expression */

	int getPopulation(List<Country> countries, String continent) {
		int sum = 0;
		
		for (Country c: countries) {
			if(c.continent.equalsIgnoreCase(continent))
				sum += c.population;
			}
		
		return sum;
	}

	/* Lamda Expression : solution 1 */
	int getPopulation_(List<Country> countries, String continent) {
		/* Filter*/
		
		Stream<Country> sublist = countries.stream().filter(
				country -> {return country.continent.equalsIgnoreCase(continent);}
		);
		
		Stream<Integer> populations = sublist.map(
				c -> c.population);
		
		int sum = populations.reduce(0, (a,b) -> a + b);
		
		return sum;
	}
	
	/* Lamda Expression : solution 2 */
	
	int getPopulation_2 (List<Country> countries, String continent) {
		Stream<Integer> populations =  countries.stream().map(
				country -> country.continent.equalsIgnoreCase(continent)? country.population : 0
		);
	
		return populations.reduce(0, (a,b) -> a + b);
	}
	
	public static class Country {
		String continent;
		int population;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
