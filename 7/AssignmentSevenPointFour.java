// Jacob Reinikainen Lindström, jare2473

import java.util.ArrayList;

public class AssignmentSevenPointFour {
	
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
	
	public void increaseAge() {
		String name = input.string("Name of the dog");
		Dog dog = findDogInRegisterByName(name);
		if (!(dog == null)) {
			dog.increaseAge();
			System.out.println(dog.getName() + " is now one year older");
		} else {
			System.out.println("Error: no such dog");
		}
	}
}