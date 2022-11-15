public class Dog {
	public String name;
	String breed;
	int age;
	int weight;
	double tailLength;
	public String owner;
	
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
		this.tailLength = tailLength;
		this.owner = owner;
	}
	
	public String toString() {
		if (owner.length() > 0) {
			return (
				name + " " +
				"(" + breed +
				", " + age +
				" years" +
				", " + weight +
				" kilo, " + tailLength +
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
				" kilo, " + tailLength +
				" cm tail, " + "no owner" +
				")"
			);	
		}
	}
	
	public void increaseAge() {
		age++;
	}
	
	public double getTailLength() {
		return tailLength;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void removeOwner() {
		owner = "";
	}
}