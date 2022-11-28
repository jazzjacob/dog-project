// Jacob Reinikainen Lindström, jare2473
import java.util.ArrayList;

public class AssignmentSevenPointOne {
	private static ArrayList<Dog> DOGS = new ArrayList<Dog>();
	private static UserInput input = new UserInput();
	
	private static String formatString(String string) {
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
	
	private static boolean stringIsEmpty(String string) {
		if (string.length() > 0) {
			return false;
		}
		return true;
	}
	
	private static void printError(String errorMessage) {
		System.out.println("Error: " + errorMessage);
	}
	
	private static String getNameFromUser(String type) {
		String name = input.string(type);
		name = formatString(name);
		while (stringIsEmpty(name)) {
			printError("the name can't be empty");
			name = input.string(type);
			name = formatString(name);
		}
		return name;
	}
	
	
	public static void registerNewDog() {
		String name = getNameFromUser("Name");
		String breed = getNameFromUser("Breed");
		int age = input.integer("Age");
		int weight = input.integer("Weight");
		
		DOGS.add(new Dog(name, breed, age, weight, ""));
		System.out.println(name + " added to the register");
	}
	
	public static ArrayList<Dog> getDogs() {
		return DOGS;
	}
	

	public static void main(String[] args) {
		registerNewDog();
		System.out.println(formatString("   HElLLo there    "));
		System.out.println(stringIsEmpty("   "));
	}
}