// Jacob Reinikainen Lindström, jare2473
import java.util.ArrayList;

public class DogRegister {
	private static final String EXIT_COMMAND = "exit";
	
	private ArrayList<Owner> ownerList = new ArrayList<Owner>();
	private ArrayList<Dog> dogList = new ArrayList<Dog>();
	private UserInput input = new UserInput();
	private Output output = new Output();
	
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
	
	private void removeDogsFromOwner(Owner owner) {
		ArrayList<Dog> dogListCopy = new ArrayList<>(dogList);
		for (Dog dog : dogListCopy) {
			if (owner.ownsDog(dog)) {
				owner.removeDog(dog);
				dogList.remove(dog);
			}
		}
	}
	
	private void removeOwner() {
		Owner owner = findOwnerByInput();
		if (owner != null) {
			removeDogsFromOwner(owner);
			ownerList.remove(owner);
			output.println(owner.getName() + " was removed from the register");
		} else {
			output.error("no such owner");
		}
	}
	
	private void removeDogFromOwner(Dog dog) {
		Owner owner = dog.getOwner();
		if (dog.getOwner() != null) {
			owner.removeDog(dog);
			output.println(dog.getName() + " was removed from " + owner.getName());     
		} else {
			output.error(dog.getName() + " is not owned by anyone");
		}
	}
	
	private void removeOwnedDog() {
		Dog dog = findDogByInput();
		if (dog != null) {
			removeDogFromOwner(dog);    
		} else {
			output.print("no such dog");
		}
	}
	
	private Owner findOwnerInRegister(String nameToFind) {
		for (Owner owner : ownerList) {
			if (nameToFind.equalsIgnoreCase(owner.getName())) {
				return owner;
			}
		}
		return null;
	}
	
	private void giveDog() {
		Dog dog = findDogByInput();
		if (dog == null) {
			output.error("no dog with that name");
			return;
		}
		
		if (dog.getOwnerName().length() > 0) {
			output.error("the dog already has an owner");
			return;
		}
		
		Owner owner = findOwnerByInput();
		if (owner == null) {
			output.error("no such owner");
			return;
		}
		
		connectDogAndOwner(owner, dog);
	}
	
	private void connectDogAndOwner(Owner owner, Dog dog) {
		dog.addOwner(owner);
		output.println(dog.getName() + " was given to " + owner.getName());
	}
	
	private void listOwners() {
		if (ownerList.size() == 0) {
			output.error("no owners registered");
		} else {
			for (Owner owner : ownerList) {
				output.println(owner.getName()  + " [" + owner.getDogsString() + "]");
			}
		}
	}
	
	private void registerNewOwner() {
		String name = input.formattedString("Name");
		while (name.length() < 1) {
			output.error("the name can't be empty");
			name = input.formattedString("Name");
		}
		ownerList.add(new Owner(name));
		output.println(name + " added to the register");
	}
	
	private void removeDog() {
		Dog dog = findDogByInput();
		if (dog == null) {
			output.error("no such name");
		} else {
			dog.removeOwner();
			dogList.remove(dog);
			output.println(dog.getName() + " was removed from the register");
		}
	}
	
	private Dog findDogInRegisterByName(String nameToFind) {
		for (Dog dog : dogList) {
			if (nameToFind.equalsIgnoreCase(dog.getName())) {
				return dog;
			}
		}
		return null;
	}
	
	private Dog findDogByInput() {
		String name = input.formattedString("Name of the dog");
		return findDogInRegisterByName(name);
	}
	
	private Owner findOwnerByInput() {
		String ownerName = input.formattedString("Enter the name of the owner");
		return findOwnerInRegister(ownerName);
	}
	
	private void increaseAge() {
		Dog dog = findDogByInput();
		if (dog == null) {
			output.error("no such dog");
		} else {
			dog.increaseAge();
			output.println(dog.getName() + " is now one year older");
		}
	}
	
	private String addDogsToListByTailLength() {
		double smallestTailLength = input.decimal("\nSmallest tail length to display");
		String dogsToList = "";
		for (Dog dog : dogList) {
			if (smallestTailLength <= dog.getTailLength()) {
				dogsToList += "\n* " + dog;
			}
		}
		return dogsToList;
	}
	
	private void listDogs() {
		sortDogs();
		if (dogList.size() == 0) {
			output.error("no dogs registered");
			return;
		}
		
		String dogsToList = addDogsToListByTailLength();
		if (dogsToList.length() == 0) {
			output.error("no dogs with such a large tail");
			return;
		}
		
		output.print("The following dogs has such a large tail:");
		output.println(dogsToList);
	}
	
	private void registerNewDog() {
		String name = input.formattedString("Name");
		String breed = input.formattedString("Breed");
		int age = input.integer("Age");
		int weight = input.integer("Weight");
	
		dogList.add(new Dog(name, breed, age, weight));
		output.println(name + " added to the register");
	}
	
	public void printCommandMenu() {
		output.println(
			"The following commands are available:" +
			"\n* register new dog" +
			"\n* list dogs" +
			"\n* increase age" +
			"\n* remove dog" +
			"\n* register new owner" +
			"\n* give dog" +
			"\n* list owners" +
			"\n* remove owned dog" +
			"\n* remove owner" +
			"\n* exit"
		);
	}
	
	public void sayFarewell() {
		output.println("Välkommen åter!");
	}
	
	private void handleCommand(String userCommand) {
		switch(userCommand) {
			case "register new dog":
				registerNewDog();
				break;
			case "list dogs":
				listDogs();
				break;
			case "increase age":
				increaseAge();
				break;
			case "remove dog":
				removeDog();
				break;
			case "register new owner":
				registerNewOwner();
				break;
			case "give dog":
				giveDog();
				break;
			case "list owners":
				listOwners();
				break;
			case "remove owned dog":
				removeOwnedDog();
				break;
			case "remove owner":
				removeOwner();
				break;
			case EXIT_COMMAND:
				break;
			default:
				output.error("command not valid");
				printCommandMenu();
				break;
		}
	}
	
	private void runCommandLoop() {
		String userCommand;
		do {
			userCommand = input.string("Command");
			handleCommand(userCommand);
		} while (!userCommand.equals(EXIT_COMMAND));
	}
	
	private void sayHello() {
		output.println("\nWelcome!\n");
		printCommandMenu();
	}
	
	private void exitProgram() {
		sayFarewell();
	}
		
	private void run() {
		// addDogs();
		sayHello();
		runCommandLoop();
		exitProgram();
	}
	
	private void addDogs() {
		dogList.add(new Dog("Hans", "Tax", 1, 2));
	}
	
	public static void main(String[] args) {
		new DogRegister().run();
	}
}