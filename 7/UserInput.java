// Jacob Reinikainen Lindström, jare2473

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInput {

	private static ArrayList<InputStream> list = new ArrayList<>();
	private Scanner input;

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
		System.out.print(userPrompt + "?>");
		int userInt = input.nextInt();
		input.nextLine();
		return userInt;
	}

	public double decimal(String userPrompt) {
		System.out.print(userPrompt + "?>");
		double userDouble = input.nextDouble();
		input.nextLine();
		return userDouble;
	}

	public String string(String userPrompt) {
		System.out.print(userPrompt + "?>");
		while (input.hasNextLine()) {
			return input.nextLine();
		}
		return "";
	}
}
