// Jacob Reinikainen Lindström, jare2473

import java.util.ArrayList;

public class AssignmentEightPointTwo {
	
	private ArrayList<Owner> ownerList = new ArrayList<Owner>();
	
	public Owner findOwnerInRegister(String nameToFind) {
		for (Owner owner : ownerList) {
			if (nameToFind.equalsIgnoreCase(owner.getName())) {
				return owner;
			}
		}
		return null;
	}
	
}