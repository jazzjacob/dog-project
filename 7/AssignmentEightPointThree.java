// Jacob Reinikainen Lindström, jare2473

import java.util.ArrayList;

public class AssignmentEightPointThree {
	
	private ArrayList<Owner> ownerList = new ArrayList<Owner>();
	private ArrayList<Dog> dogList = new ArrayList<Dog>();
	private UserInput input = new UserInput();
	
	public void giveDog() {
		String dogName = input.string("Enter the name of the dog");
		Dog dog = findDogInRegisterByName(dogName, DOGS);
		
		// Check if dog exists and doesn't have owner
		if (dog == null) {
			System.out.println("Error: no dog with that name");
		} else if (dog.getOwner().length() > 0) {
			System.out.println("Error: the dog already has an owner");
		} else {
			String ownerName = input.string("Enter the name of the owner");
			Owner owner = findOwnerInRegisterByName(ownerName, OWNERS);
			
			if (owner != null) {
				owner.addDog(dog);
				dog.addOwner(owner.getName());
				System.out.println(dog.getName() + " was given to " + owner.getName());
			} else {
				System.out.println("Error: no such owner");
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
	
}