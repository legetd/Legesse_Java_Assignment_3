import utility.*;
import objects.*;
import java.util.Scanner;

public class Adopt_a_pet {
   private static Scanner input = new Scanner(System.in);
   private static Ink ink = new Ink();
   private static User user;
   private static Shelter shelter = new Shelter();
   private static Pet pet;

   private static int choice;
   private static boolean isDone = false;
   private static boolean goBack = false;

   public static void main(String[] args) {
      
    createUser(); // create the user
    createPets(); // helper pets

    while(!isDone) {
      choice = ink.validateMainMenu();

      switch (choice) {
        case 1: // print available pets
          ink.printAvailablePets(shelter.getPets());
          while(!goBack) {
            int pick = input.nextInt();
            if(pick != 0) {
              ink.printPetDetails(shelter.getPet(pick - 1));
              String answer = input.next();
              if(answer.equalsIgnoreCase("Y")) {
                shelter.adopt(pick - 1, user.getName());
                goBack = !goBack;
              }
              else {
                goBack = !goBack;
              }
            }
            else {
              goBack = !goBack;
            }
          } // while
          break;
        case 2: // print shelter details
          ink.printShelterDetails(shelter);
          while(!goBack) {
            input.nextLine();
            goBack = !goBack;
          } // while
          break;
        case 3: // book an appointment
          bookAppointment();
          break;
        case 4:
          isDone = !isDone;
          break;
      } // switch
      goBack = !goBack; // seems wrong but it is NOT wrong
    } // while(main)

    ink.printGoodday();
  } // main()

  // 100% for test data!
  public static void createPets() {
    pet = new Pet("spot", "dog",
      3, "black", "hound");
    shelter.addPet(pet);
    pet = new Pet("cleveland", "dog",
      7, "brown", "boxer");
    shelter.addPet(pet);
    pet = new Pet("monster", "cat",
      1, "calico", "calico");
    shelter.addPet(pet);
  } // createPets()

  public static void createUser() {
    String name, email;
    int phone;
    System.out.println("What is your name? ");
    name = input.nextLine();
    System.out.println("What is your email? ");
    email = input.nextLine();
    System.out.println("What is your phone? ");
    phone = input.nextInt();
    user = new User(name, email, phone);
  } // createUser()

  public static void bookAppointment() {
    String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    // Assuming shelter hours are the same for each day for simplicity
    String[] hoursOfOperation = {"9:00 AM - 5:00 PM", "9:00 AM - 5:00 PM", "9:00 AM - 5:00 PM", "9:00 AM - 5:00 PM", "9:00 AM - 5:00 PM", "9:00 AM - 5:00 PM", "9:00 AM - 5:00 PM"};

    System.out.println("Please select a day for the appointment:");
    for (int i = 0; i < daysOfWeek.length; i++) {
      System.out.println((i+1) + ". " + daysOfWeek[i]);
    }

    int dayChoice = input.nextInt();
    if (dayChoice >= 1 && dayChoice <= daysOfWeek.length) {
      System.out.println("Available hours for " + daysOfWeek[dayChoice - 1] + ": " + hoursOfOperation[dayChoice - 1]);
      System.out.println("Please choose a time for the appointment (e.g., 9:00 AM): ");
      String appointmentTime = input.next();
      System.out.println("Appointment booked for " + daysOfWeek[dayChoice - 1] + " at " + appointmentTime);
    } else {
      System.out.println("Invalid choice. Please select a valid day.");
    }
  }
} // class
