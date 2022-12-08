// Jacob Reinikainen Lindström, jare2473
import java.util.ArrayList;

public class Owner {
	private String name;
	private ArrayList<Dog> dogs;

	public Owner(String name) {
		this.name = name;
	}
	
	public void addDog(Dog dog) {
		dogs.add(dog);
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}
}
