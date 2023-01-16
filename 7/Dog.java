// Jacob Reinikainen Lindström, jare2473

public class Dog {
	private static final double DACHSHUND_TAIL_LENGTH = 3.7;
	
	private String name;
	private String breed;
	private int age;
	private int weight;
	private Owner owner;

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
	
	public int getWeight() {
		return weight;
	}
	
	public Owner getOwner() {
		return this.owner;
	}
	
	public String getOwnerName() {
		if (this.owner != null) {
			return owner.getName();
		}
		return "";
	}
	
	public double getTailLength() {
		String[] dachshundInDifferentLanguages = {"tax", "dachshund", "mäyräkoira", "teckel"};
		String breed = getBreed();
		for (String d : dachshundInDifferentLanguages) {
			if (breed.equalsIgnoreCase(d)) {
				return DACHSHUND_TAIL_LENGTH;
			}
		}
		double tailLength = this.age * (this.weight / 10.0);
		double tailLengthRounded = Math.round(tailLength * 10) / 10.0;
		return tailLengthRounded;
	}
	
	public void increaseAge() {
		age++;
	}
	
	public boolean setOwner(Owner owner) {
		if (this.owner == null && owner != null) {
			this.owner = owner;
			owner.addDog(this);
			return true;
		} else {
			return false;
		}
	}
	
	public void removeOwner() {
		Owner ownerCopy = this.owner;
		this.owner = null;
		
		if (ownerCopy != null && ownerCopy.ownsDog(this)) {
			ownerCopy.removeDog(this);
		}
	}
	
	public String toString() {
		if (owner == null) {
			return
				String.format(
					"%s (%s), %d years, %d cm tail, %.1f, no owner",
					name, breed, age, weight, getTailLength()
				);
		} else {
			return
				String.format(
					"%s (%s), %d, %d years cm tail, %.1f, owned by %s",
					name, breed, age, weight, getTailLength(), owner.getName()
				);
		}
	}
}