package src.Users;

import src.Exceptions.NoHotelsAddedException;

public class Customer extends User {
    public Customer(String name) {
        super(name);
    }

    public Customer(String name, String mode, String password, String email, String address) {
        super(name, mode, password, email, address);
    }

    @Override
    public void checkList() throws NoHotelsAddedException {

    }
}
