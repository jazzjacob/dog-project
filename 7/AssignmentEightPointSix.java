// Jacob Reinikainen Lindström, jare2473
import java.util.ArrayList;

public class AssignmentEightPointSix {
	
	private ArrayList<Owner> ownerList = new ArrayList<Owner>();
	private ArrayList<Dog> dogList = new ArrayList<Dog>();
	private UserInput input = new UserInput();
	
	public void removeOwnedDog() {
		String dogName = getFormattedStringFromUser("Enter the name of the dog", "the name can't be empty");
		Dog dog = findDogInRegisterByName(dogName);
		if (dog != null) {
			removeDogFromOwner(dog);
			//dog.removeOwner();      
		} else {
			printError("no such dog");
		}
	}
	
	public void removeDogFromOwner(Dog dog) {
		Owner owner = dog.getOwner();
		if (dog.getOwner() != null) { // Checks if dog has dog owner
			// Find owner by name
			owner.removeDog(dog);
			System.out.println(dog.getName() + " was removed from " + owner.getName());     
			
		} else {
			printError(dog.getName() + " is not owned by anyone");
		}
	}
	
	public void addDogToOwner(Owner owner, Dog dog) {
		//owner.addDog(dog);
	}

	public void setOwnerToDog(Owner owner, Dog dog) {
		dog.addOwner(owner);
	}
	
	private String getFormattedStringFromUser(String userPrompt, String errorMessage) {
		String string = input.string(userPrompt);
		string = formatString(string);
		while (string.length() == 0) {
			printError(errorMessage);
			string = input.string(userPrompt);
			string = formatString(string);
		}
		return string;
	}
	
	public void giveDog() {
		String dogName = getFormattedStringFromUser("Enter the name of the dog", "the name can't be empty");

		Dog dog = findDogInRegisterByName(dogName);
		
		// Check if dog exists and doesn't have owner
		if (dog == null) {
			System.out.println("Error: no dog with that name");
		} else {
			if (dog.getOwnerName().length() > 0) {
				System.out.println("Error: the dog already has an owner");
			} else {
				String ownerName = getFormattedStringFromUser("Enter the name of the owner", "the name can't be empty");
				Owner owner = findOwnerInRegister(ownerName);
				
				if (owner != null) {
					addDogToOwner(owner, dog);
					setOwnerToDog(owner, dog);
					System.out.println(dog.getName() + " was given to " + owner.getName());
				} else {
					System.out.println("Error: no such owner");
				}
			}
		}
	}
	
	public Dog findDogInRegisterByName(String nameToFind) {
		for (Dog dog : dogList) {
			if (nameToFind.equalsIgnoreCase(dog.getName())) {
				return dog;
			}
		}
		return null;
	}
	
	public Owner findOwnerInRegister(String nameToFind) {
		for (Owner owner : ownerList) {
			if (nameToFind.equalsIgnoreCase(owner.getName())) {
				return owner;
			}
		}
		return null;
	}
	
	public void registerNewOwner() {
		String name = formatString(input.string("Name"));
		while (name.length() < 1) {
			System.out.println("Error: the name can't be empty");
			name = formatString(input.string("Name"));
		}
		ownerList.add(new Owner(name));
		System.out.println(name + " added to the register");
	}
	
	

	// KOD FRÅN ASSIGNMENTSEVENPOINTONE
	private String formatString(String string) {
		// Remove whitespace from before first letter and after last letter
		string = string.trim();
	
		// Capitalize first letter
		if (string.length() > 1) {
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		} else if (string.length() == 1) {
			string = string.toUpperCase();
		}
		return string;
	}
	
	private boolean stringIsEmpty(String string) {
		if (string.length() > 0) {
			return false;
		}
		return true;
	}
	
	private void printError(String errorMessage) {
		System.out.println("Error: " + errorMessage);
	}

	private String getNameFromUser(String type) {
		String name = input.string(type);
		name = formatString(name);
		while (stringIsEmpty(name)) {
			printError("the name can't be empty");
			name = input.string(type);
			name = formatString(name);
		}
		return name;
	}

	public void registerNewDog() {
		String name = getNameFromUser("Name");
		String breed = getNameFromUser("Breed");
		int age = input.integer("Age");
		int weight = input.integer("Weight");
	
		dogList.add(new Dog(name, breed, age, weight));
		System.out.println(name + " added to the register");
	}
	
	public void listDogs() {
		if (dogList.size() == 0) {
			printError("no dogs registered");
		} else {
			double smallestTailLength = input.decimal("\nSmallest tail length to display");
			String dogsToList = "";
			for (Dog dog : dogList) {
				if (smallestTailLength <= dog.getTailLength()) {
					dogsToList += "\n* " + dog;
				}
			}
			
			if (dogsToList.length() == 0) {
				printError("no dogs with such a large tail");
			} else {
				System.out.print("The following dogs has such a large tail:");
				System.out.println(dogsToList);
			}		
		}
	}
	
	public void listOwners() {
		if (ownerList.size() == 0) {
			printError("no owners registered");
		} else {
			for (Owner owner : ownerList) {
				System.out.println(owner.getName()  + " [" + owner.getDogs() + "]");
			}	
		}
	}
}