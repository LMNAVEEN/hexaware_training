package com.main;

import com.model.CartItem;
import com.model.User;
import com.enums.UserMembership;
import com.repository.CartItemRepository;
import com.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.config.AppConfig;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserRepository userRepo = context.getBean(UserRepository.class);
        CartItemRepository cartRepo = context.getBean(CartItemRepository.class);

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n======= CART MENU =======");
            System.out.println("1. Add User");
            System.out.println("2. Show All Users");
            System.out.println("3. Add Cart Item");
            System.out.println("4. Show All Cart Items");
            System.out.println("5. Show Items by Username");
            System.out.println("6. Delete Cart Item");
            System.out.println("7. Delete user");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer after nextInt

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Membership (GOLD / SILVER / BASIC): ");
                    String mem = scanner.nextLine().toUpperCase();
                    User user = new User(0, name, UserMembership.valueOf(mem));
                    userRepo.addUser(user);
                    System.out.println("User added!");
                }

                case 2 -> {
                    List<User> users = userRepo.getAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        users.forEach(System.out::println);
                    }
                }

                case 3 -> {
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    BigDecimal price = scanner.nextBigDecimal();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine(); // clear buffer

                    // Show available users to pick from
                    System.out.println("Available Users:");
                    List<User> users = userRepo.getAllUsers();
                    users.forEach(u -> System.out.println(u.getId() + " - " + u.getName()));

                    System.out.print("Enter user id for this item: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // clear buffer

                    // Find the selected user from the list
                    User selectedUser = users.stream()
                            .filter(u -> u.getId() == userId)
                            .findFirst()
                            .orElse(null);

                    if (selectedUser == null) {
                        System.out.println("User not found!");
                    } else {
                        CartItem item = new CartItem(0, name, price, qty, selectedUser);
                        cartRepo.addItem(item);
                        System.out.println("Item added!");
                    }
                }

                case 4 -> {
                    List<CartItem> items = cartRepo.getAllItems();
                    if (items.isEmpty()) {
                        System.out.println("No items found.");
                    } else {
                        items.forEach(System.out::println);
                    }
                }

                case 5 -> {
                    System.out.print("Enter username to search: ");
                    String username = scanner.nextLine();
                    List<CartItem> items = cartRepo.getItemsByUsername(username);
                    if (items.isEmpty()) {
                        System.out.println("No items found for " + username);
                    } else {
                        items.forEach(System.out::println);
                    }
                }

                case 6 -> {
                    System.out.print("Enter item id to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // clear buffer
                    cartRepo.deleteItem(id);
                    System.out.println("Item deleted!");
                }

                case 7->{
                    System.out.println("Enter id to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    userRepo.deleteUser(id);

                }


                case 0 -> System.out.println("Goodbye!");

                default -> System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
        context.close();
    }
}
