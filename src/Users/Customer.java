package src.Users;

public class Customer extends User {
    public Customer(String name) {
        super(name);
    }

    public Customer(String name, String mode, String password, String email, String address) {
        super(name, mode, password, email, address);
    }
}
