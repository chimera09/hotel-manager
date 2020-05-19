package src.Users;

import src.Exceptions.NoHotelsAddedException;
import src.Exceptions.PasswordIncorrectException;
import src.Exceptions.UserAlreadyExistsException;
import src.Exceptions.UserNotFoundException;
import src.database.Database;

import java.util.Objects;

public abstract class User {
    protected String name;
    protected String mode;
    protected String password;
    protected String email;
    protected String address;

    public User(String name) {
        this.name = name;
        this.mode = Database.getUserMode(name);
        this.password = Database.getUserPassword(name);
        this.address =Database.getUserAddress(name);
        this.email = Database.getUserMail(name);
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
        if (Database.userExists(name))
            throw new UserAlreadyExistsException("User "+name+" already exists!");
        if (mode.equals("client"))
            return new Customer(name, password, mode, address, email);
        else
            return new HotelManager(name, password, mode, address, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) &&
                Objects.equals(getMode(), user.getMode()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMode(), getPassword(), getEmail(), getAddress());
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
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

    public abstract void checkList() throws NoHotelsAddedException;
}

