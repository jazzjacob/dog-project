// Jacob Reinikainen Lindström, jare2473

import java.util.ArrayList;

public class AssignmentEightPointOne {
	
	private ArrayList<Owner> ownerList = new ArrayList<Owner>();
	private UserInput input = new UserInput();
	
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
	
	public void registerNewOwner() {
		String name = formatString(input.string("Name"));
		while (name.length() < 1) {
			System.out.println("Error: the name can't be empty");
			name = formatString(input.string("Name"));
		}
		ownerList.add(new Owner(name));
		System.out.println(name + " added to the register");
	}
	
}