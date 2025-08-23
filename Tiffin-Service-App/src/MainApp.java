import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Menu> menus = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static int customerIdCounter = 1;
    private static int menuIdCounter = 1;
    private static int orderIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nTiffin Service Management System");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Add Menu Item");
            System.out.println("4. View Menu");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Update Delivery Status");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCustomer(scanner);
                case 2 -> viewCustomers();
                case 3 -> addMenuItem(scanner);
                case 4 -> viewMenu();
                case 5 -> placeOrder(scanner);
                case 6 -> viewOrders();
                case 7 -> updateDeliveryStatus(scanner);
                case 8 -> {
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Subscription Type (Daily/Weekly/Monthly): ");
        String subscriptionType = scanner.nextLine();

        Customer customer = new Customer(customerIdCounter++, name, phone, address, subscriptionType);
        customers.add(customer);
        System.out.println("Customer added successfully!");
    }

    private static void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        customers.forEach(System.out::println);
    }

    private static void addMenuItem(Scanner scanner) {
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        Menu menu = new Menu(menuIdCounter++, itemName, price);
        menus.add(menu);
        System.out.println("Menu item added successfully!");
    }

    private static void viewMenu() {
        if (menus.isEmpty()) {
            System.out.println("No menu items found.");
            return;
        }
        menus.forEach(System.out::println);
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Menu ID: ");
        int menuId = scanner.nextInt();

        Order order = new Order(orderIdCounter++, customerId, menuId, "Pending");
        orders.add(order);
        System.out.println("Order placed successfully!");
    }

    private static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        orders.forEach(System.out::println);
    }

    private static void updateDeliveryStatus(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Delivery Status (Delivered/Pending): ");
        String status = scanner.nextLine();

        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setDeliveryStatus(status);
                System.out.println("Order status updated successfully!");
                return;
            }
        }
        System.out.println("Order ID not found.");
    }
}
