// Jacob Reinikainen Lindström, jare2473

public class DogList {
	private Dog[] dogList = new Dog[0];
	
	public void add(Dog dogToAdd) {
		if (dogToAdd != null) {
			if (!dogExists(dogToAdd)) {
				Dog[] extendedDogList = new Dog[dogList.length + 1];
				extendedDogList[dogList.length] = dogToAdd;
				System.arraycopy(dogList, 0, extendedDogList, 0, dogList.length);
				dogList = extendedDogList;
			}
		}
	}
	
	// Find dog in array and return index or -1 if dog not found
	private int findDog(String name) {
		for (int i = 0 ; i < dogList.length ; i++) {
			if (dogList[i].getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	// Returns a one index shorter array where a dog is removed
	private Dog[] removeDogFromArray(int index) {
		Dog[] reducedDogList = new Dog[dogList.length - 1];
		boolean indexFound = false;
		
		for (int i = 0; i < dogList.length ; i++) {
			if (i == index) {
				indexFound = true;
			} else {
				if (indexFound) {
					reducedDogList[i - 1] = dogList[i];
				} else {
					reducedDogList[i] = dogList[i];
				}
			}
		}
		return reducedDogList;
	}
	
	public boolean dogExists(Dog dog) {
		for (Dog d : dogList) {
			if (dog.getName().equalsIgnoreCase(d.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public void remove(Dog dogToRemove) {
		if (dogToRemove != null && dogExists(dogToRemove)) {
			dogToRemove.removeOwner();
			int index = findDog(dogToRemove.getName());
			// Copy dogList to reduced dogList without the dog to remove
			if (index == -1) {
				System.out.println("Error: no dog with that name");
			} else {
				dogList = removeDogFromArray(index);
			}		
		}
	}
	
	public void print() {
		for (Dog dog : dogList) {
			System.out.println(dog);
		}
	}
	
	public String toString() {
		String dogListString = "";
		for (int i = 0 ; i < dogList.length ; i++) {
			dogListString += dogList[i].getName();
			if (i != dogList.length - 1) {
				dogListString += ", ";
			}
		}
		return dogListString;
	}
}