package structural.decorator;

public class TomatoPizzaDecorator extends PizzaDecorator {

	public TomatoPizzaDecorator(Pizza pizza) {
		super(pizza);
	}
	
	public void bake() {
		super.bake();
		System.out.println("adding tomato toppings......");
	}

}
