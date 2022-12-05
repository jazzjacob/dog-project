// Jacob Reinikainen Lindström, jare2473

import java.util.ArrayList;

public class AssignmentSevenPointThree {
	
	private ArrayList<Dog> dogList = new ArrayList<Dog>();
	
	public Dog findDogInRegisterByName(String nameToFind) {
		for (Dog dog : dogList) {
			if (nameToFind.equalsIgnoreCase(dog.getName())) {
				return dog;
			}
		}
		return null;
	}
}