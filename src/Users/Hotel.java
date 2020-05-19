package src.Users;

import org.json.simple.JSONObject;
import src.database.Database;

public class Hotel {
    String name;
    String owner;
    String description;
    String facilities;
    long totalRooms;
    long bookedRooms;
    long availbleRooms;

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
        this.name = name;
        this.owner = (String) hotelEntry.get("owner");
        this.description = (String) hotelEntry.get("description");
        this.facilities = (String) hotelEntry.get("facilities");
        this.totalRooms = (long) hotelEntry.get("total_rooms");
        this.bookedRooms = (long) hotelEntry.get("occupied_rooms");
        this.availbleRooms = totalRooms - bookedRooms;
    }

    public void setBookedRooms(JSONObject hotelEntry) {
        hotelEntry.put("occupied_rooms", bookedRooms + 1);
    }

    public String getName() {
        return name;
    }
    public String getOwner() { return owner;}
    public String getDescription() {
        return description;
    }
    public String getFacilities() { return facilities; }
    public long getTotalRooms() { return totalRooms; }
    public long getAvailableRooms() { return availbleRooms; }
}
