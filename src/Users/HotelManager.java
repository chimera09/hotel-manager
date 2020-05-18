package src.Users;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import src.Exceptions.HotelAlreadyExistsException;
import src.Exceptions.NoHotelsAddedException;
import src.database.Database;

import java.util.ArrayList;

public class HotelManager extends User{
    private ArrayList<Hotel> myHotels;

    public HotelManager(String name) {
        super(name);
    }

    public HotelManager(String name, String mode, String password, String email, String address)  {
        super(name, mode, password, email, address);
        myHotels = new ArrayList<Hotel>();
    }
    public static Hotel insertHotel(String name,String owner,int totalRooms, int bookedRooms) throws HotelAlreadyExistsException {
        if(Database.hotelExists(name))
            throw new HotelAlreadyExistsException("Hotel " + name + " already exists.");
        else return new Hotel(name,owner,totalRooms,bookedRooms);
    }
    public void checkList() throws NoHotelsAddedException {
        if(myHotels.isEmpty()){
            throw new NoHotelsAddedException("No hotels added");
        }
    }
}
