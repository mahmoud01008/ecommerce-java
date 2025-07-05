public class Product {
    String name;
    double price;
    int quantity;
    boolean isExpired;
    boolean requiresShipping;
    double weight;

    Product(String name, double price, int quantity, boolean isExpired, boolean requiresShipping, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isExpired = isExpired;
        this.requiresShipping = requiresShipping;
        this.weight = weight;
    }
}
