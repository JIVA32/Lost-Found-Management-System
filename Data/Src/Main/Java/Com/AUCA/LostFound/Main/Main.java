package Com.AUCA.LostFound.Main;

import Com.AUCA.LostFound.Model.Item;
import Com.AUCA.LostFound.Repository.ItemRepository;
import Com.AUCA.LostFound.Repository.ItemRepositoryImpl;
import Com.AUCA.LostFound.Service.ItemService;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Centralized Lost & Found Management System.
 */
public class Main {
    public static void main(String[] args) {
        ItemRepository itemRepository = new ItemRepositoryImpl();
        ItemService itemService = new ItemService(itemRepository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("Centralized Lost & Found Management System");
        System.out.println("=====================================");

        while (true) {
            System.out.println("\nPlease select your role:");
            System.out.println("1. Staff");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Enter choice (1-3): ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); 

            if (roleChoice == 3) {
                System.out.println("Thank you for using the system. Goodbye!");
                break;
            }

            String role = (roleChoice == 1) ? "Staff" : "Student";
            System.out.println("\nWelcome, " + role + "!");

            boolean continueMenu = true;
            while (continueMenu) {
                System.out.println("\nMenu:");
                if (role.equals("Staff")) {
                    System.out.println("1. Add Lost Item");
                    System.out.println("2. Add Found Item");
                    System.out.println("3. View All Items");
                    System.out.println("4. Search Item by ID");
                    System.out.println("5. Delete Item by ID");
                    System.out.println("6. Back to Role Selection");
                    System.out.print("Enter choice (1-6): ");
                } else {
                    System.out.println("1. Report Lost Item");
                    System.out.println("2. Report Found Item");
                    System.out.println("3. View All Items");
                    System.out.println("4. Search Item by ID");
                    System.out.println("5. Back to Role Selection");
                    System.out.print("Enter choice (1-5): ");
                }
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (role.equals("Staff")) {
                    switch (choice) {
                        case 1:
                            addItem(scanner, itemService, "LOST");
                            break;
                        case 2:
                            addItem(scanner, itemService, "FOUND");
                            break;
                        case 3:
                            viewAllItems(itemService);
                            break;
                        case 4:
                            searchItem(scanner, itemService);
                            break;
                        case 5:
                            deleteItem(scanner, itemService);
                            break;
                        case 6:
                            continueMenu = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    switch (choice) {
                        case 1:
                            addItem(scanner, itemService, "LOST");
                            break;
                        case 2:
                            addItem(scanner, itemService, "FOUND");
                            break;
                        case 3:
                            viewAllItems(itemService);
                            break;
                        case 4:
                            searchItem(scanner, itemService);
                            break;
                        case 5:
                            continueMenu = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }

        scanner.close();
    }

    private static void addItem(Scanner scanner, ItemService itemService, String status) {
        System.out.println("\n--- " + (status.equals("LOST") ? "Report Lost Item" : "Report Found Item") + " ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        Item item = new Item(id, name, description, location, status);
        itemService.addItem(item);
        System.out.println("Item reported successfully!");
    }

    private static void viewAllItems(ItemService itemService) {
        System.out.println("\n--- All Reported Items ---");
        List<Item> items = itemService.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items reported yet.");
        } else {
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    private static void searchItem(Scanner scanner, ItemService itemService) {
        System.out.println("\n--- Search Item by ID ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        Item item = itemService.getItemById(id);
        if (item != null) {
            System.out.println("Item found: " + item);
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void deleteItem(Scanner scanner, ItemService itemService) {
        System.out.println("\n--- Delete Item by ID ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        Item item = itemService.getItemById(id);
        if (item != null) {
            itemService.deleteItemById(id);
            System.out.println("Item deleted successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }
}