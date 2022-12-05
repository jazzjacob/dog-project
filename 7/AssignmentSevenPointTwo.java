// Jacob Reinikainen Lindström, jare2473

import java.util.ArrayList;

// LIST ALL DOGS
public class AssignmentSevenPointTwo {
	
	private ArrayList<Dog> dogList = new ArrayList<Dog>();
	private UserInput input = new UserInput();
	
	public void listDogs() {
		if (dogList.size() == 0) {
			System.out.println("Error: nog dogs in register");
		} else {
			double smallestTailLength = input.decimal("Smallest tail length to display");
			
			boolean dogFound = false;
			for (Dog dog : dogList) {
				if (smallestTailLength <= dog.getTailLength()) {
					if (!dogFound) {
						System.out.println("The following dogs has such a large tail:");
					}
					System.out.println(dog);
					dogFound = true;
				}
			}
			
			if (!dogFound) {
				System.out.println("Error: no dog have a tail that long");
			}
		}
	}
	
	/*
	public static void main(String[] args) {
		System.out.println("Hello there");
	}
	*/
}