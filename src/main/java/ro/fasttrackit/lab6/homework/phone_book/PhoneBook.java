package ro.fasttrackit.lab6.homework.phone_book;

import java.util.Scanner;

public class PhoneBook {
    private String[] arrayOfNames = new String[100];
    private Integer[] arrayOfNumbers = new Integer[100];

    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("Displaying the menu:");
            System.out.println("1. Create contact");
            System.out.println("2. Read contacts");
            System.out.println("3. Update contact");
            System.out.println("4. Delete contact");
            System.out.println("5. Search contact");
            System.out.println("6. Exit app");
            System.out.println("Choose an option: ");
            option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Introduce the contact you want to create in the phonebook: ");
                    String userInputForName = getStringFromUserInput();
                    System.out.println("Introduce the number you want to add for the contact created: ");
                    Integer userInputForNumber = getIntegerFromUserInput();
                    phoneBook.createContact(userInputForName, userInputForNumber);
                }
                case 2 -> phoneBook.readContacts();
                case 3 -> {
                    System.out.println("Introduce the contact you want to update: ");
                    String userInputInitialName = getStringFromUserInput();
                    System.out.println("Introduce the new name: ");
                    String userInputNewName = getStringFromUserInput();
                    System.out.println("Introduce the new number if you want to update it, as well");
                    Integer userInputNewNumber = getIntegerFromUserInput();
                    phoneBook.updateContact(userInputInitialName, userInputNewName, userInputNewNumber);

                }
                case 4 -> {
                    System.out.println("Introduce the contact you want to delete: ");
                    String userInputForDeletion = getStringFromUserInput();
                    phoneBook.deleteContact(userInputForDeletion);
                }
                case 5 -> {
                    System.out.println("Introduce the contact you want to search for: ");
                    String userInputForSearching = getStringFromUserInput();
                    phoneBook.searchContact(userInputForSearching);
                }
                case 6 -> System.out.println("Exiting app...");
                default -> System.out.println("Invalid option! Try another one between 1 and 6");
            }

        }
        while (option != 6);
        System.out.println("Bye bye! ");
    }

    private static String getStringFromUserInput() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    private static Integer getIntegerFromUserInput() {
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }

    public void createContact(String newName, Integer phoneNumber) {
        boolean nameCreated = false;
        boolean numberCreated = false;
        for (int i = 0; i < arrayOfNames.length && !nameCreated; i++) {
            for (int j = 0; j < arrayOfNumbers.length && !numberCreated; j++) {
                if (arrayOfNames[i] == null && arrayOfNumbers[j] == null) {
                    arrayOfNames[i] = newName;
                    arrayOfNumbers[j] = phoneNumber;
                    nameCreated = true;
                    numberCreated = true;
                    System.out.println("Contact: " + newName + " with the phone number: "
                            + phoneNumber + ", was created in the phonebook");
                }
            }
        }
    }

    public void readContacts() {
        boolean isEmpty = true;
        System.out.println("Reading contacts from the phonebook: ");
        for (String name : arrayOfNames) {
            for (Integer number : arrayOfNumbers) {
                if (name != null && number != null) {
                    System.out.println(name + " " + number);
                    isEmpty = false;
                }
            }
        }
        if (isEmpty) {
            System.out.println("Nothing to read from the phonebook...");
        }
    }

    public void updateContact(String initialName, String newName, Integer newNumber) {
        boolean isPresent = false;
        boolean numberToUpdate = false;
        for (int i = 0; i < arrayOfNames.length; i++) {
            for (int j = 0; j < arrayOfNumbers.length && !numberToUpdate; j++) {
                Integer initialNumber = arrayOfNumbers[j];
                if (arrayOfNames[i] != null && arrayOfNames[i].equalsIgnoreCase(initialName)) {
                    arrayOfNames[i] = newName;
                    arrayOfNumbers[j] = newNumber;
                    numberToUpdate = true;
                    System.out.println("Updated: " + initialName + ", to: " + newName);
                    if (initialNumber.equals(newNumber)) {
                        System.out.println("It was not necessary for the phone number to be updated");
                    } else {
                        System.out.println("Updated phone number: " + initialNumber + ", to: " + newNumber);
                    }
                    isPresent = true;
                }
            }
        }
        if (!isPresent) {
            System.out.println("Could not find the requested name: " + initialName);
        }
    }

    public void deleteContact(String contactToBeDeleted) {
        boolean isPresent = false;
        for (int i = 0; i < arrayOfNames.length; i++) {
            if (arrayOfNames[i] != null && arrayOfNames[i].equalsIgnoreCase(contactToBeDeleted)) {
                arrayOfNames[i] = null;
                System.out.println("Name: " + contactToBeDeleted + ", was found and consequently deleted");
                isPresent = true;
            }
        }
        if (!isPresent) {
            System.out.println("Could not find the requested name: " + contactToBeDeleted);
        }
    }

    public void searchContact(String contactToBeSearchedFor) {
        boolean isPresent = false;
        int counter = 0;
        for (int i = 0; i < arrayOfNames.length; i++) {
            if (arrayOfNames[i] != null && arrayOfNames[i].equalsIgnoreCase(contactToBeSearchedFor)) {
                isPresent = true;
                counter++;
            }
        }
        if (!isPresent) {
            System.out.println("Could not find " + contactToBeSearchedFor);
        } else {
            System.out.println("Found " + contactToBeSearchedFor + " x " + counter);
        }
    }
}