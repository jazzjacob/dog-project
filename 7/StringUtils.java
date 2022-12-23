// Jacob Reinikainen Lindström, jare2473

public class StringUtils {
	
	public String capitalizeAndTrim(String string) {
		// Remove whitespace from before first letter and after last letter
		string = string.trim();
		return capitalize(string);
	}
	
	public String capitalize(String string) {
		if (string.length() > 1) {
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
		} else if (string.length() == 1) {
			string = string.toUpperCase();
		}
		return string;
	}
}