package src.Users;

import org.json.simple.JSONObject;
import src.Exceptions.NoHotelsAddedException;
import src.database.Database;

import java.util.List;

public class Customer extends User {
    private boolean booked = false;

    public Customer(String name) {
        super(name);
    }

    public Customer(String name, String mode, String password, String email, String address) {
        super(name, mode, password, email, address);
    }

    public List<Hotel> showHotels() { return Database.getHotels(); }

    public JSONObject getHotelData(String name) { return Database.getHotelData(name); }

    @Override
    public void checkList() throws NoHotelsAddedException {

    }
}
