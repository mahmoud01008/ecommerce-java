import java.util.*;

public class SimpleEcommerceSystem {
    static List<Product> storeProducts = new ArrayList<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        storeProducts.add(new Product("Cheese", 100, 5, false, true, 0.2));
        storeProducts.add(new Product("Biscuits", 150, 4, false, true, 0.7));
        storeProducts.add(new Product("TV", 3000, 2, false, true, 4.0));
        storeProducts.add(new Product("Mobile Card", 50, 10, false, false, 0.0));
        storeProducts.add(new Product("Expired Milk", 70, 3, true, true, 1.0));
        Customer customer = new Customer(5000);
        int option;
        while (true) {
            System.out.println("\n1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
             option = input.nextInt();

            if (option == 1) {
                printProducts();
            } else if (option == 2) {
                printProducts();
                System.out.print("Enter product number: ");
                int index = input.nextInt() - 1;
                if (index < 0 || index >= storeProducts.size()) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                Product selected = storeProducts.get(index);

                if (selected.isExpired) {
                    System.out.println("Error: Product is expired.");
                    continue;
                }

                System.out.print("Enter quantity: ");
                int qty = input.nextInt();

                if (qty <= 0 || qty > selected.quantity) {
                    System.out.println("Error: Not enough quantity in stock.");
                    continue;
                }

                selected.quantity -= qty;
                customer.cart.add(new CartItem(selected, qty));
                System.out.println("Product added to cart.");
            } else if (option == 3) {
                if (customer.cart.isEmpty()) {
                    System.out.println("Error: Cart is empty.");
                    continue;
                }

                double subtotal = 0;
                double totalWeight = 0;

                for (CartItem item : customer.cart) {
                    subtotal += item.getTotalPrice();
                    if (item.product.requiresShipping) {
                        totalWeight += item.product.weight * item.quantity;
                    }
                }

                double shippingFee = totalWeight * 30;
                double total = subtotal + shippingFee;

                if (total > customer.balance) {
                    System.out.println("Error: Not enough balance.");
                    continue;
                }

                customer.balance -= total;


                if (shippingFee > 0) {
                    System.out.println("\n** Shipment notice **");
                    for (CartItem item : customer.cart) {
                        if (item.product.requiresShipping) {
                            int grams = (int) (item.product.weight * 1000);
                            System.out.println(item.quantity + "x " + item.product.name + " " + grams + "g");
                        }
                    }
                    System.out.printf("Total package weight: %.1fkg\n", totalWeight);
                }

                System.out.println("\n** Checkout receipt **");
                for (CartItem item : customer.cart) {
                    System.out.println(item.quantity + "x " + item.product.name + " " + (int) item.getTotalPrice());
                }
                System.out.println("----------------------");
                System.out.println("Subtotal " + (int) subtotal);
                System.out.println("Shipping " + (int) shippingFee);
                System.out.println("Amount " + (int) total);
                System.out.println("Balance left " + (int) customer.balance);


                return;
            } else if (option == 4) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    static void printProducts() {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < storeProducts.size(); i++) {
            Product p = storeProducts.get(i);
            System.out.printf("%d. %s - %.0f LE - Qty: %d %s\n",
                    i + 1,
                    p.name,
                    p.price,
                    p.quantity,
                    p.isExpired ? "[Expired]" : "");
        }
    }
}
