// Jacob Reinikainen Lindström, jare2473

public class Owner {
	private String name;
	private DogList dogs;

	public Owner(String name) {
		this.name = name;
		this.dogs = new DogList();
	}
	
	//
	public void addDog(Dog dog) {
		if (dog != null) {
			if (dog.getOwnerName().length() == 0) {
				dog.addOwner(this);	
			}
			
			if (!dogs.dogExists(dog) && dog.getOwner().equals(this)) {
				dogs.add(dog);
			}
		}
	}
	
	public String toString() {
		return name;
	}
	
	/*private void listDogs() {
		dogs.print();
	}*/
	
	//
	public String getName() {
		return name;
	}
	
	//
	/*public Dog[] getDogs() {
		return dogs.getDogs();
	}*/
	
	//
	public DogList getDogsString() {
		return dogs;
	}
	
	public boolean ownsDog(Dog dog) {
		boolean dogExists = dogs.dogExists(dog);
		return dogExists;
	}
	
	//
	public void removeDog(Dog dog) {
		if (ownsDog(dog) && dog.getOwner() != null) {
			// System.out.println("HERE WE ARE");
			dog.removeOwner();
		}
		if (ownsDog(dog)) {
			dogs.remove(dog);			
		}
	}
}
