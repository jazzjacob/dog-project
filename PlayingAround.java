public class PlayingAround {
	public static void main(String[] args) {
		UserInput input = new UserInput();
		int integer = input.integer("Enter int");
		System.out.println(integer);
	
		double decimal = input.decimal("Enter decimal");
		System.out.println(decimal);
	
		String string = input.string("Enter string");
		System.out.println(string);
	}
}