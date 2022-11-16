import java.util.ArrayList;
import java.util.Scanner;

/*

Methods to do:
* Check if owner is in owners ArrayList - findUserInRegisterByName
* Check if dog is in dogs ArrayList - findDogInRegisterByName
- Remove dog from owner - removeDogFromOwner
- Remove dog from dogs ArrayList

*/

public class DogProject {
  static Scanner input = new Scanner(System.in);
  public static ArrayList<Owner> owners = new ArrayList<Owner>();
  public static ArrayList<Dog> dogs = new ArrayList<Dog>();
  
  // Returns the object of an owner if input name is in an ArryList<Owner>
  public static Owner findUserInRegisterByName(String nameToFind, ArrayList<Owner> owners) {
    boolean ownerFound = false;
    for (Owner owner : owners) {
      if (nameToFind.equalsIgnoreCase(owner.getName())) {
        ownerFound = true;
        return owner;
      }
    }
    return null;
  }
  
  public static Dog findDogInRegisterByName(String nameToFind, ArrayList<Dog> dogs) {
    boolean dogFound = false;
    for (Dog dog : dogs) {
      if (nameToFind.equalsIgnoreCase(dog.getName())) {
        dogFound = true;
        return dog;
      }
    }
    return null;
  }
  
  public static void removeDogFromOwner(Dog dog, ArrayList<Owner> owners) {
    if (dog.getOwner().length() > 0) { // Checks if dog has dog owner
      for (Owner owner : owners) {
        if (owner.getName().equalsIgnoreCase(dog.getOwner())) { // Checks if owner's name equals dog's owner's name
          owner.removeDog(dog);
        }
      }
    }
  }
  
  public static void printStartMenu() {
    System.out.println(
      "The following commands are available:" +
      "\n* register new dog" +
      "\n* list dogs" +
      "\n* increase age" +
      "\n* remove dog" +
      "\n* register new owner" +
      "\n* give dog" +
      "\n* list owners" +
      "\n* remove owned dog" +
      "\n* remove owner" +
      "\n* exit"
    );
  }
  
  public static void printMenuSelection() {
    System.out.print("Command?> ");
  }
  
  public static String selectMenuItem() {
    printMenuSelection();
    String chosenMenuItem = input.nextLine();
    return chosenMenuItem;
  }
  
  public static void sayFarewell() {
    System.out.println("Välkommen åter!");
  }
  
  public static void registerNewDog() {
    System.out.print("Name?> ");
    String name = input.nextLine();
    System.out.print("Breed?> ");
    String breed = input.nextLine();
    System.out.print("Age?> ");
    int age = input.nextInt();
    System.out.print("Weight?> ");
    int weight = input.nextInt();
    dogs.add(new Dog(name, breed, age, weight, 4.80, ""));
    System.out.println(name + " added to the register.");
  }
  
  public static void increaseAge() {
    System.out.print("Name of the dog?> ");
    String name = input.nextLine();
    Dog dog = findDogInRegisterByName(name, dogs);
    if (!(dog == null)) {
      dog.increaseAge();
      System.out.println(dog.getName() + " is now one year older");
    } else {
      System.out.println("Error: no such dog");
    }
  }
  
  public static void removeDogFromOwner(Dog dog) {
    boolean dogRemoved = false;
    if (dog.getOwner().length() > 0) { // Checks if dog has dog owner
      for (Owner owner : owners) {
        if (owner.getName().equalsIgnoreCase(dog.getOwner())) { // Checks if owner's name equals dog's owner's name
          owner.removeDog(dog);
          dogRemoved = true;
          System.out.println(dog.getName() + " was removed from " + owner.getName());
        }
      }
    }
    
    if (!dogRemoved) {
      System.out.println("Error: no such dog");
    }
  }
  
  public static void listOwners() {
    for (Owner owner : owners) {
      System.out.println(owner.name  + " " + owner.dogs);
    }
  }
  
  public static void listDogs() {
    System.out.println("\nSmallest tail length to display?");
    double smallestTailLength = input.nextDouble();
    input.nextLine();
    
    for (Dog dog : dogs) {
      if (smallestTailLength <= dog.getTailLength()) {
        System.out.println(dog);        
      }
    }
  }
  
  public static void removeDog() {
    System.out.print("Name of the dog?> ");
    String name = input.nextLine();
    boolean dogFound = false;
    Dog dog = findDogInRegisterByName(name, dogs);
    
    if (dog != null) {
      removeDogFromOwner(dog, owners);
      dogs.remove(dog);
      System.out.println(dog.getName() + " was removed from the register");
    } else {
      System.out.println("Error: no such name");
    }
  }
  
  public static void registerNewOwner() {
    System.out.print("Name?> ");
    String name = input.nextLine();
    owners.add(new Owner(name));
    System.out.println(name + " was added to the register.");
  }
  
  public static void giveDog() {
    System.out.print("Name of the dog?> ");
    boolean dogFound = false;
    String dogName = input.nextLine();
    Dog dogToGiveAway = null;
    for (Dog dog : dogs) {
      // Check if dog exists and has no owner
      if (dog.getName().equalsIgnoreCase(dogName) && dog.getOwner().length() == 0) {
        dogFound = true;
        dogToGiveAway = dog;
      }
    }
    
    if (dogFound) {
      System.out.print("Name of the owner?> ");
      String ownerName = input.nextLine();
      Owner owner = findUserInRegisterByName(ownerName, owners);
      
      if (!(owner == null)) {
        owner.addDog(dogToGiveAway);
        dogToGiveAway.addOwner(owner.getName());
        System.out.println(dogToGiveAway.getName() + " was given to " + owner.getName());
      } else {
        System.out.println("Error: No such owner found");
      }
    } else {
      System.out.println("Error: no such dog or dog already owned");
    }
  }
  
  public static void removeOwnedDog() {
    System.out.print("Name of the dog?> ");
    String dogName = input.nextLine();
    for (Dog dog : dogs) {
      if (dog.getName().equalsIgnoreCase(dogName)) {
        removeDogFromOwner(dog);
        dog.removeOwner();
      }
    }
  }
  
  public static void removeOwner() {
    System.out.print("Name of the owner?> ");
    String ownerName = input.nextLine();
    boolean ownerFound = false;
    for (Owner owner : owners) {
      if (ownerName.equalsIgnoreCase(owner.getName())) {
        // Remove owner's dog(s) from the register
        for (Dog dog : owner.getDogs()) {
          dogs.remove(dog);
        }
        owners.remove(owner);
        System.out.println(owner.getName() + " was removed from the register");
        ownerFound = true;
        break;
      }
    }
    if (!ownerFound) {
      System.out.println("Error: owner doesn't exist");
    }
  }
  
  static void runProgram() {
    boolean programIsRunning = true;
    System.out.println("\nWelcome!\n");
    printStartMenu();
    do {
      String selectedMenuItem = selectMenuItem();
    
      switch(selectedMenuItem) {
        case "register new dog":
          registerNewDog();
          break;
        case "list dogs":
          listDogs();
          break;
        case "increase age":
          increaseAge();
          break;
        case "remove dog":
          removeDog();
          break;
        case "register new owner":
          registerNewOwner();
          break;
        case "give dog":
          giveDog();
          break;
        case "list owners":
          listOwners();
          break;
        case "remove owned dog":
          removeOwnedDog();
          break;
        case "remove owner":
          removeOwner();
          break;
        case "exit":
          programIsRunning = false;
          sayFarewell();
          break;
        default:
          System.out.println("Error");
          printStartMenu();
          break;
      }
    } while (programIsRunning);
  }

  public static void main(String[] args) {
  
    owners.add(new Owner("Hassano"));
    owners.add(new Owner("Klara"));
    
    dogs.add(new Dog("Dobby", "Cute", 12, 3, 3.30, "Hassano"));
    dogs.add(new Dog("Billy boy", "Dalmatian", 8, 2, 23.30, "Klara"));
    dogs.add(new Dog("Fallsmannen", "Cute", 12, 3, 3.30, ""));
    
    // Connect dog to owners' data
    for (Owner owner : owners) {
      for (Dog dog : dogs) {
        if (dog.owner.equals(owner.name)) {
          owner.addDog(dog);
        }
      }
    }
    
    System.out.println("\nThe following data is preloaded:");
    
    System.out.println("\nOWNERS:");
    for (Owner owner : owners) {
      System.out.println(owner.name  + " " + owner.dogs);
    }
    
    System.out.println("\nDOGS:");
    for (Dog dog : dogs) {
      System.out.println(dog);
    }
    
    runProgram();
    
  }
}