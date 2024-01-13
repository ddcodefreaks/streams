package structural.decorator;

public class PizzaDecorator implements Pizza {

	private Pizza pizza;
	
	public PizzaDecorator(Pizza pizza) {
		super();
		this.pizza = pizza;
	}



	@Override
	public void bake() {
		pizza.bake();
		
	}

	
	
}
