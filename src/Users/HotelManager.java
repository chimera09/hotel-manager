package src.Users;

import src.Exceptions.HotelAlreadyExistsException;
import src.Exceptions.NoHotelsAddedException;
import src.database.Database;

import java.util.List;

public class HotelManager extends User{
    private List<Hotel> myHotels;
    public HotelManager(String name) {
        super(name);
        myHotels = Database.getHotelsForOwner(name);
    }

    public HotelManager(String name, String mode, String password, String email, String address)  {
        super(name, mode, password, email, address);
        myHotels = Database.getHotelsForOwner(name);
    }
    public void addHotel(Hotel hotel) throws HotelAlreadyExistsException {
        if(Database.hotelExists(hotel.getName()))
            throw new HotelAlreadyExistsException("hotel already exists");
        else{
            myHotels.add(hotel);
        }
    }

    public List<Hotel> getMyHotels() {
        return myHotels;
    }

    public void checkList() throws NoHotelsAddedException {
        if(myHotels.isEmpty()){
            throw new NoHotelsAddedException("No hotels added");
        }

    }
}
