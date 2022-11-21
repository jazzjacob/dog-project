import java.util.ArrayList;
import java.util.Scanner;

public class DogProject {
  static Scanner input = new Scanner(System.in);
  public static ArrayList<Owner> OWNERS = new ArrayList<Owner>();
  public static ArrayList<Dog> DOGS = new ArrayList<Dog>();
  
  // Returns the object of an owner if input name is in an ArryList<Owner>
  public static Owner findOwnerInRegisterByName(String nameToFind, ArrayList<Owner> owners) {
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
    for (Dog dog : dogs) {
      if (nameToFind.equalsIgnoreCase(dog.getName())) {
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
  
  public static void removeDogsFromOwner(Owner owner) {
    for (Dog dog : owner.getDogs()) {
      DOGS.remove(dog);
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
    DOGS.add(new Dog(name, breed, age, weight, ""));
    System.out.println(name + " added to the register.");
  }
  
  public static void increaseAge() {
    System.out.print("Name of the dog?> ");
    String name = input.nextLine();
    Dog dog = findDogInRegisterByName(name, DOGS);
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
      // Find owner by name
      Owner owner = findOwnerInRegisterByName(dog.getOwner(), OWNERS);
      if (owner != null) {
        owner.removeDog(dog);
        System.out.println(dog.getName() + " was removed from " + owner.getName());     
      } else {
        System.out.println("Error: dog's owner does not exist");
      }
    }
  }
  
  public static void listOwners() {
    for (Owner owner : OWNERS) {
      System.out.println(owner.getName()  + " " + owner.getDogs());
    }
  }
  
  public static void listDogs() {
    System.out.println("\nSmallest tail length to display?");
    double smallestTailLength = input.nextDouble();
    input.nextLine();
    
    for (Dog dog : DOGS) {
      if (smallestTailLength <= dog.getTailLength()) {
        System.out.println(dog);        
      }
    }
  }
  
  public static void removeDog() {
    System.out.print("Name of the dog?> ");
    String name = input.nextLine();
    Dog dog = findDogInRegisterByName(name, DOGS);
    
    if (dog != null) {
      removeDogFromOwner(dog, OWNERS);
      DOGS.remove(dog);
      System.out.println(dog.getName() + " was removed from the register");
    } else {
      System.out.println("Error: no such name");
    }
  }
  
  public static void registerNewOwner() {
    System.out.print("Name?> ");
    String name = input.nextLine();
    OWNERS.add(new Owner(name));
    System.out.println(name + " was added to the register.");
  }
  
  public static void giveDog() {
    System.out.print("Name of the dog?> ");
    String dogName = input.nextLine();
    Dog dog = findDogInRegisterByName(dogName, DOGS);
    
    // Check if dog exists and doesn't have owner
    if ((dog != null) && (dog.getOwner().length() == 0)) {
      System.out.print("Name of the owner?> ");
      String ownerName = input.nextLine();
      Owner owner = findOwnerInRegisterByName(ownerName, OWNERS);
      
      if (owner != null) {
        owner.addDog(dog);
        dog.addOwner(owner.getName());
        System.out.println(dog.getName() + " was given to " + owner.getName());
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
    Dog dog = findDogInRegisterByName(dogName, DOGS);
    if (dog != null) {
      removeDogFromOwner(dog);
      dog.removeOwner();      
    } else {
      System.out.println("Error: no such dog");
    }
  }
  
  public static void removeOwner() {
    System.out.print("Name of the owner?> ");
    String ownerName = input.nextLine();
    Owner owner = findOwnerInRegisterByName(ownerName, OWNERS);
    if (owner != null) {
      removeDogsFromOwner(owner);
      OWNERS.remove(owner);
      System.out.println(owner.getName() + " was removed from the register");
      // return;
    } else {
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
  
    OWNERS.add(new Owner("Hassano"));
    OWNERS.add(new Owner("Klara"));
    
    DOGS.add(new Dog("Dobby", "Cute", 12, 3, "Hassano"));
    DOGS.add(new Dog("Billy boy", "Dalmatian", 8, 2, "Klara"));
    DOGS.add(new Dog("Fallsmannen", "Cute", 3, 3, ""));
    
    // Connect dog to OWNERS' data
    for (Owner owner : OWNERS) {
      for (Dog dog : DOGS) {
        if (dog.getOwner().equals(owner.getName())) {
          owner.addDog(dog);
        }
      }
    }
    
    System.out.println("\nThe following data is preloaded:");
    
    System.out.println("\nOWNERS:");
    for (Owner owner : OWNERS) {
      System.out.println(owner.getName()  + " " + owner.getDogs());
    }
    
    System.out.println("\nDOGS:");
    for (Dog dog : DOGS) {
      System.out.println(dog);
    }
    
    runProgram();
    
  }
}