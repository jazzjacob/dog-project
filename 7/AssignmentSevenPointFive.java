// Jacob Reinikainen Lindström, jare2473

import java.util.ArrayList;

public class AssignmentSevenPointFive {
	
	private ArrayList<Dog> dogList = new ArrayList<Dog>();
	private UserInput input = new UserInput();
	
	public Dog findDogInRegisterByName(String nameToFind) {
		for (Dog dog : dogList) {
			if (nameToFind.equalsIgnoreCase(dog.getName())) {
				return dog;
			}
		}
		return null;
	}
	
	public void removeDog() {
		String name = input.string("Enter the name of the dog");
		Dog dog = findDogInRegisterByName(name);
		
		if (dog == null) {
			System.out.println("Error: no such name");
		} else {
			// removeDogFromOwner(dog, OWNERS);
			dogList.remove(dog);
			System.out.println(dog.getName() + " was removed from the register");
		}
	}
}