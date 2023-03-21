package Factories;

public class FactoryProducer {
	public FactoryProducer() {}
	
	public static AbstractFactory createFactory(boolean isPlayer) {
		if(isPlayer) {
			return new PlayerFactory();
		} else {
			return new ObstacleFactory();
		}
	}
	
}
