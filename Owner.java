import java.util.ArrayList;

public class Owner {
	public String name;
	public ArrayList<Dog> dogs;
	
	public Owner(String name) {
		this.name = name;
		this.dogs = new ArrayList<Dog>();
	}
	
	public void addDog(Dog dog) {
		dogs.add(dog);
	}
	
	
}