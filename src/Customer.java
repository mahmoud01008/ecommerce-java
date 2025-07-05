import java.util.ArrayList;
import java.util.List;

public class Customer {
    double balance;
    List<CartItem> cart;

    Customer(double balance) {
        this.balance = balance;
        this.cart = new ArrayList<>();
    }
}
