// Jacob Reinikainen Lindström, jare2473
import java.util.ArrayList;

public class DogRegister {
	private static final String REGISTER_NEW_DOG_COMMAND = "register new dog";
	private static final String LIST_DOGS_COMMAND = "list dogs";
	private static final String INCREASE_AGE_COMMAND = "increase age";
	private static final String REMOVE_DOG_COMMAND = "remove dog";
	private static final String REGISTER_NEW_OWNER_COMMAND = "register new owner";
	private static final String GIVE_DOG_COMMAND = "give dog";
	private static final String LIST_OWNERS_COMMAND = "list owners";
	private static final String REMOVE_OWNED_DOG_COMMAND = "remove owned dog";
	private static final String REMOVE_OWNER_COMMAND = "remove owner";
	private static final String EXIT_COMMAND = "exit";
	
	private static final int DOG_ONE_HAS_SHORTEST_TAIL = -1;
	private static final int DOG_TWO_HAS_SHORTEST_TAIL = 1;
	private static final int DOGS_HAVE_SAME_TAIL_LENGTH = 0;
	
	private static final int DOG_ONE_SHOULD_BE_FIRST_IN_LIST = -1;
	private static final int DOG_TWO_SHOULD_BE_FIRST_IN_LIST = 1;
	private static final int DOGS_HAVE_SAME_NAME = 0;
	
	private ArrayList<Owner> ownerList = new ArrayList<Owner>();
	private ArrayList<Dog> dogList = new ArrayList<Dog>();
	private UserInput input = new UserInput();
	private Output output = new Output();
	
	private void swapDogs(int indexOne, int indexTwo) {
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
			return DOG_ONE_HAS_SHORTEST_TAIL;
		}
		if (dogOne.getTailLength() > dogTwo.getTailLength()) {
			return DOG_TWO_HAS_SHORTEST_TAIL;
		}
		return DOGS_HAVE_SAME_TAIL_LENGTH;
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
	// Returns -1 if dogOne should be first in list
	// Returns 1 if dogTwo should be first in list
	private int compareDogs(Dog dogOne, Dog dogTwo) {
		int tailLengthComparison = compareTailLength(dogOne, dogTwo);
		switch (tailLengthComparison) {
			case DOG_ONE_HAS_SHORTEST_TAIL -> { return DOG_ONE_SHOULD_BE_FIRST_IN_LIST; }
			case DOG_TWO_HAS_SHORTEST_TAIL -> { return DOG_TWO_SHOULD_BE_FIRST_IN_LIST; }
		}
		int nameComparisonResult = compareDogNames(dogOne, dogTwo);
		boolean dogOneComesFirstAlphabetically = 0 > nameComparisonResult;
		if (dogOneComesFirstAlphabetically) {
			return DOG_ONE_SHOULD_BE_FIRST_IN_LIST;
		}
		return DOG_TWO_SHOULD_BE_FIRST_IN_LIST;
	}
	
	// Finds "smallest" dog in dogList starting from currentIndex
	// Returns index of smallest dog found
	private int findSmallestDog(int currentIndex) {
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
	
	private void sortDogs() {
		for (int i = 0 ; i < dogList.size() ; i++) {
			int smallestDogIndex = findSmallestDog(i);
			if (i != smallestDogIndex) {
				swapDogs(i, smallestDogIndex);
			}
		}
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
		if (owner == null) {
			output.error("no such owner");
			return;
		}
		removeDogsFromOwner(owner);
		ownerList.remove(owner);
		output.println(owner.getName() + " was removed from the register");
	}
	
	private void removeDogFromOwner(Dog dog) {
		Owner owner = dog.getOwner();
		if (dog.getOwner() == null) {
			output.error(dog.getName() + " is not owned by anyone");
			return;
		}
		owner.removeDog(dog);
		output.println(dog.getName() + " was removed from " + owner.getName());
	}
	
	private void removeOwnedDog() {
		Dog dog = findDogByInput();
		if (dog == null) {
			output.print("no such dog");
			return;
		}
		removeDogFromOwner(dog);
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
			return;
		}
		for (Owner owner : ownerList) {
			output.println(owner.getName()  + " [" + owner.getDogsString() + "]");
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
			return;
		}
		dog.removeOwner();
		dogList.remove(dog);
		output.println(dog.getName() + " was removed from the register");
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
			return;
		}
		dog.increaseAge();
		output.println(dog.getName() + " is now one year older");
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
	
	private void printCommandMenu() {
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
	
	private void sayFarewell() {
		output.println("Välkommen åter!");
	}
	
	private void handleCommand(String userCommand) {
		switch(userCommand) {
			case REGISTER_NEW_DOG_COMMAND -> registerNewDog();
			case LIST_DOGS_COMMAND -> listDogs();
			case INCREASE_AGE_COMMAND -> increaseAge();
			case REMOVE_DOG_COMMAND -> removeDog();
			case REGISTER_NEW_OWNER_COMMAND -> registerNewOwner();
			case GIVE_DOG_COMMAND -> giveDog();
			case LIST_OWNERS_COMMAND -> listOwners();
			case REMOVE_OWNED_DOG_COMMAND -> removeOwnedDog();
			case REMOVE_OWNER_COMMAND -> removeOwner();
			case EXIT_COMMAND -> {}
			default -> {
				output.error("command not valid");
				printCommandMenu();
			}
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
		sayHello();
		runCommandLoop();
		exitProgram();
	}
	
	public static void main(String[] args) {
		new DogRegister().run();
	}
}