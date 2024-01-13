package structural.adapter;

public class WeatherUI {
	
	public void showTemparature(int zipcode) 
	{
		WeatherAdapter adapter = new WeatherAdapter();
		int temperature = adapter.findTemperature(zipcode);
		System.out.println("The tempertaure is::"+ temperature);
	}
	
	public static void main(String[] args) 
	{
		WeatherUI ui = new WeatherUI();
		ui.showTemparature(201304);
	}

}
