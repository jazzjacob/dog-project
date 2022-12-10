// Jacob Reinikainen Lindström, jare2473

import java.util.ArrayList;
import java.util.Collections;

public class AssignmentSevenPointSeven {
	private ArrayList<Dog> dogList = new ArrayList<Dog>();
	
	public void swapDogs(int indexOne, int indexTwo) {
		Dog placeholder = dogList.get(indexOne);
		dogList.set(indexOne, dogList.get(indexTwo));
		dogList.set(indexTwo, placeholder);
	}
	
	// Compares tail length of dogOne and dogTwo
	// Returns -1 if dogOne has shortest tail
	// Returns 0 if they have the same tail length
	// Returns 1 if dogTwo has shortest tail
	private int compareTailLength(Dog dogOne, Dog dogTwo) {
		if (dogOne.getTailLength() < dogTwo.getTailLength()) {
			return -1;
		} else if (dogOne.getTailLength() > dogTwo.getTailLength()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	// Compare dog names alphabetically
	// Returns -1 if dogOne comes first alphabetically
	// Returns 0 if the names are the same
	// Returns 1 if dogTwo is first alphabetically
	private int compareDogNames(Dog dogOne, Dog dogTwo) {
		String dogNameOne = dogOne.getName();
		String dogNameTwo = dogTwo.getName();
		
		return dogNameOne.compareToIgnoreCase(dogNameTwo);
	}
	
	// Compares two dogs
	// Returns -1 if dogOne should be first in list, or if the dogs are equal
	// Returns 1 if dogTwo should be first in list
	private int compareDogs(Dog dogOne, Dog dogTwo) {
		int tailLengthComparison = compareTailLength(dogOne, dogTwo);
		switch (tailLengthComparison) {
			case -1:
				return -1;
			case 1:
				return 1;
		}
		int nameComparisonResult = compareDogNames(dogOne, dogTwo);
	 	if (0 < nameComparisonResult) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// Finds "smallest" dog in dogList starting from currentIndex
	// Returns index of smallest dog found
	public int findSmallestDog(int currentIndex) {
		Dog currentMin = dogList.get(currentIndex);
		int currentMinIndex = currentIndex;
		for (int i = currentIndex + 1 ; i < dogList.size() ; i++) {
			if (compareDogs(currentMin, dogList.get(i)) == 1) {
				currentMin = dogList.get(i);
				currentMinIndex = i;
			}
		}
		return currentMinIndex;
	}
	
	public void swapDogsWithCollections(int indexOne, int indexTwo) {
		Collections.swap(dogList, indexOne, indexTwo);
	}
	
	public int sortDogs() {
		int numberOfSwaps = 0;
		for (int i = 0 ; i < dogList.size() ; i++) {
			int smallestDogIndex = findSmallestDog(i);
			if (i != smallestDogIndex) {
				swapDogs(i, smallestDogIndex);
				numberOfSwaps++;
			}
		}
		return numberOfSwaps;
	}
	
	public void addDog(Dog dog) {
		dogList.add(dog);
		System.out.println(dog.getName() + " was added to register.");
	}
	
	public void listDogs() {
		for (Dog dog : dogList) {
			System.out.println(dog.getName());
		}
	}
	
	public void listOwners() {
		for (Owner owner : ownerList) {
			System.out.println(owner.getName()  + " " + owner.getDogs());
		}
	}
}