// Jacob Reinikainen Lindström, jare2473

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInput {
	
	private static ArrayList<InputStream> list = new ArrayList<>();
	private Scanner input;
	private StringUtils stringUtils = new StringUtils();
	private Output output = new Output();
	
	public UserInput(InputStream inputStream) {
		if (list.contains(inputStream)) {
			throw new IllegalStateException("Input");
		}
		list.add(inputStream);
		input = new Scanner(inputStream);
	}
	
	public UserInput() {
		this(System.in);
	}
	
	public int integer(String userPrompt) {
		output.print(userPrompt + "?>");
		int userInt = input.nextInt();
		input.nextLine();
		return userInt;
	}
	
	public double decimal(String userPrompt) {
		output.print(userPrompt + "?>");
		double userDouble = input.nextDouble();
		input.nextLine();
		return userDouble;
	}
	
	public String string(String userPrompt) {
		output.print(userPrompt + "?>");
		while (input.hasNextLine()) {
			return input.nextLine();
		}
		return "";
	}
	
	// Gets a string from a user and returns capitalized string without excess whitespace
	public String formattedString(String userPrompt) {
		String string = this.string(userPrompt);
		string = stringUtils.capitalizeAndTrim(string);
		while (string.length() == 0) {
			output.error("the input can't be empty");
			string = this.string(userPrompt);
			string = stringUtils.capitalizeAndTrim(string);
		}
		return string;
	}
	
} 