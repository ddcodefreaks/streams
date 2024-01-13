package structural.decorator;

public class PizzaShop {

	public static void main(String[] args) {
		Pizza pizza = new PlainPizza();
		//pizza.bake();
		
		//using decorator add cheese topping 
		Pizza pizza1 = new CheesePizzaDecorator(new PlainPizza());
		//pizza1.bake();
		
		//using decorator add tomato topping 
		Pizza pizza2 = new TomatoPizzaDecorator(new PlainPizza());
		//pizza2.bake();
		
		//wrapping decorators
		
		Pizza pizzaWrap = new CheesePizzaDecorator(new TomatoPizzaDecorator(new PlainPizza()));
		pizzaWrap.bake();
		

	}

}
