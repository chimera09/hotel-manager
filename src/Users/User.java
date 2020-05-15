package src.Users;

import src.Exceptions.PasswordIncorrectException;
import src.Exceptions.UserAlreadyExistsException;
import src.Exceptions.UserNotFoundException;
import src.database.Database;

public abstract class User {
    protected String name;
    protected String mode;
    protected String password;
    protected String email;
    protected String address;

    public User(String name) {
        this.name = name;
        this.mode = Database.getUserMode(name);
        this.mode = Database.getUserPassword(name);
    }

    public User(String name, String mode, String password, String email, String address) {
        this.name = name;
        this.mode = mode;
        this.password = password;
        this.email = email;
        this.address = address;
        Database.insertUser(name, mode, password, email, address);
    }

    public static User getUser(String name, String password) throws UserNotFoundException, PasswordIncorrectException {
        if (!Database.userExists(name))
            throw new UserNotFoundException("User "+name+" not found!");
        if (!password.equals(Database.getUserPassword(name)))
            throw new PasswordIncorrectException("Password incorrect");
        if (Database.getUserMode(name).equals("client"))
            return new Customer(name);
        else
            return new HotelManager(name);
    }

    public static User createUser(String name, String password, String mode, String address, String email) throws UserAlreadyExistsException {
        if (!Database.userExists(name))
            throw new UserAlreadyExistsException("User "+name+" already exists!");
        if (mode.equals("client"))
            return new Customer(name, password, mode, address, email);
        else
            return new HotelManager(name, password, mode, address, email);
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public String getPassword() {
        return password;
    }

}

