// Jacob Reinikainen Lindström, jare2473

public class Owner {
	private String name;
	private DogList dogs;

	public Owner(String name) {
		this.name = name;
		this.dogs  = new DogList();
	}
	
	public void addDog(Dog dog) {
		if (dogs.dogExists(dog)) {
			dog.addOwner(this);			
		}
		dogs.add(dog);
	}

	public String toString() {
		return name;
	}
	
	public void listDogs() {
		dogs.print();
	}

	public String getName() {
		return name;
	}
	
	public void printCoolThing() {
		System.out.println("Wazzup");
	}
}
