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
		int weight,
		double tailLength,
		String owner
	) {
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.weight = weight;
		this.tailLength = calculateTailLength(age, weight);
		this.owner = owner;
	}
	
	private double calculateTailLength(int age, int weight) {
		String[] dachshundInDifferentLanguages = {"tax", "dachshund", "mäyräkoira", "teckel"};
		for (String d : dachshundInDifferentLanguages) {
			if (getBreed().equalsIgnoreCase(d)) {
				return 3.7;
			}
		}
		System.out.print("LOOK HERE: ");
		System.out.println(age);
		System.out.println(age * (weight / 10.0));
		double tailLength = age * (weight / 10.0);
		return tailLength;
	}
	
	public String toString() {
		if (owner.length() > 0) {
			return (
				name + " " +
				"(" + breed +
				", " + age +
				" years" +
				", " + weight +
				" kilo, " + getTailLengthString() +
				" cm tail, " + owner +
				")"
			);
		} else {
			return (
				name + " " +
				"(" + breed +
				", " + age +
				" years" +
				", " + weight +
				" kilo, " + getTailLengthString() +
				" cm tail, " + "no owner" +
				")"
			);	
		}
	}
	
	public void increaseAge() {
		age++;
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
	
	public String getOwner() {
		return owner;
	}
	
	public String getBreed() {
		return breed;
	}
	
	public void removeOwner() {
		owner = "";
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