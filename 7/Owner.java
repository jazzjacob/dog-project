// Jacob Reinikainen Lindström, jare2473

public class Owner {
	private String name;
	private DogList dogs;

	public Owner(String name) {
		this.name = name;
		this.dogs  = new DogList();
	}
	
	public void addDog(Dog dog) {
		if (dog != null) {
			if (dog.getOwnerName().length() == 0) {
				dog.addOwner(this);	
			}
			
			if (!dogs.dogExists(dog) && dog.getOwner().equals(this)) {
				// System.out.println("Dogs owner: " + dog.getOwner());
				// System.out.println("This: " + this);
				dogs.add(dog);
			}
		}
		// Add owner to dog if dog already exists and does not have owner yet.
		/*if (dogs.dogExists(dog) && dog.getOwnerNameName().length() == 0) {
			dog.addOwner(this);	
		}*/
		
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
