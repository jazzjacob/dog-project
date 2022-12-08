// Jacob Reinikainen Lindström, jare2473

import java.text.DecimalFormat;

public class Dog {
	private String name;
	private String breed;
	private int age;
	private int weight;
	private double tailLength;
	private String owner;

	public Dog(
		String name,
		String breed,
		int age,
		int weight
	) {
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.weight = weight;
		this.tailLength = calculateTailLength(age, weight);
	}

	private double calculateTailLength(int age, int weight) {
		String[] dachshundInDifferentLanguages = {"tax", "dachshund", "mäyräkoira", "teckel"};
		final double DACHSHUND_TAIL_LENGTH = 3.7;
		String breed = getBreed();
		for (String d : dachshundInDifferentLanguages) {
			if (breed.equalsIgnoreCase(d)) {
				return DACHSHUND_TAIL_LENGTH;
			}
		}
		double tailLength = age * (weight / 10.0);
		double tailLengthRoundedIsh = Math.round(tailLength * 10) / 10.0;
		return tailLengthRoundedIsh;
	}

	public String toString() {
		return
			name + " " +
			"(" + breed +
			", " + age +
			" years" +
			", " + weight +
			" kilo, " + tailLength +
			" cm tail, " + "no owner" +
			")";
	}

	public void increaseAge() {
		++age;
		tailLength = calculateTailLength(age, weight);
	}

	public double getTailLength() {
		return this.tailLength;
	}

	public String getTailLengthString() {
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		return numberFormat.format(this.tailLength);
	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public int getAge() {
		return age;
	}
	
	public boolean addOwner(String name) {
		if (owner.length() > 0) {
			return false;
		} else {
			owner = name;
			return true;			
		}
	}
}
