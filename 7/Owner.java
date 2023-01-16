// Jacob Reinikainen Lindström, jare2473

public class Owner {
	private String name;
	private DogList dogs;

	public Owner(String name) {
		this.name = name;
		this.dogs = new DogList();
	}

	public void addDog(Dog dog) {
		if (dog == null) {
			return;
		}
		if (dog.getOwnerName().length() == 0) {
			dog.setOwner(this);	
		}
		if (!dogs.dogExists(dog) && dog.getOwner().equals(this)) {
			dogs.add(dog);
		}
	}
	
	public void removeDog(Dog dog) {
		if (ownsDog(dog) && dog.getOwner() != null) {
			dog.removeOwner();
		}
		if (ownsDog(dog)) {
			dogs.remove(dog);			
		}
	}

	public String getName() {
		return name;
	}

	public DogList getDogsString() {
		return dogs;
	}
	
	public boolean ownsDog(Dog dog) {
		return dogs.dogExists(dog);
	}
	
	public String toString() {
		return name;
	}
}
