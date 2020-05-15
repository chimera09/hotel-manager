package src.Users;

import java.util.ArrayList;

public class HotelManager extends User{
    ArrayList<Hotel> myHotels;

    public HotelManager(String name) {
        super(name);
    }

    public HotelManager(String name, String mode, String password, String email, String address)  {
        super(name, mode, password, email, address);
    }


    public ArrayList<Hotel> getMyHotels() {
        return myHotels;
    }

    public void setMyHotels(ArrayList<Hotel> myHotels) {
        this.myHotels = myHotels;
    }
}
