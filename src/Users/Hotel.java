package src.Users;

import org.json.simple.JSONObject;
import src.database.Database;

public class Hotel {
    String name;
    String owner;
    String description;
    String facilities;
    int totalRooms;
    int bookedRooms;
    int availbleRooms;

    public Hotel(String name, String owner,String description ,String facilities , int totalRooms){
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.facilities = facilities;
        this.totalRooms = totalRooms;
        this.bookedRooms = 0;
        this.availbleRooms = totalRooms - bookedRooms;
        Database.insertHotel(name,owner,description,facilities,totalRooms);
    }

    public Hotel(String name, JSONObject hotelEntry) {
        this.owner = (String) hotelEntry.get("owner");
        this.description = (String) hotelEntry.get("description");
        this.facilities = (String) hotelEntry.get("facilities");
        this.totalRooms = (int) hotelEntry.get("total_rooms");
        this.bookedRooms = (int) hotelEntry.get("occupied_rooms");
        this.availbleRooms = totalRooms - bookedRooms;
    }
}
