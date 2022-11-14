import java.util.ArrayList;
import java.util.Scanner;

public class DogProject {
  static Scanner input = new Scanner(System.in);
  public static ArrayList<Owner> owners = new ArrayList<Owner>();
  public static ArrayList<Dog> dogs = new ArrayList<Dog>();
  
  public static void printStartMenu() {
    System.out.println(
      "The following commands are available:" +
      "\n* register new dog" +
      "\n* list dogs" +
      "\n* list owners" +
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
        case "exit":
          programIsRunning = false;
          sayFarewell();
          break;
        case "list dogs":
          listDogs();
          break;
        case "list owners":
          listOwners();
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
    
    dogs.add(new Dog("Dobby", "Cute", 12, 3, 3.30, ""));
    dogs.add(new Dog("Billy boy", "Dalmatian", 8, 2, 23.30, "Klara"));
    
    // Connect dog to owners' data
    for (Owner owner : owners) {
      for (Dog dog : dogs) {
        if (dog.owner.equals(owner.name)) {
          owner.addDog(new Dog("Billy boy", "Dalmatian", 8, 2, 23.30, owner.name));
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