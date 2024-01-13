package structural.adapter;

public class WeatherAdapter {
	
	public int findTemperature(int zipCode) {
		String city = null;
		
		if(zipCode == 201304) {
			city = "Noida"; 
		}
		
		WeatherFinder finder = new WeatherFinderImpl();
		int temperature = finder.find(city);
		
		return temperature;
	}

}
