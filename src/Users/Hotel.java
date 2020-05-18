package src.Users;

public class Hotel {
    String name;
    String owner;
    int totalRooms;
    int bookedRooms;
    int availbleRooms;

    public Hotel(String name, String owner , int totalRooms, int bookedRooms){
        this.name = name;
        this.owner = owner;
        this.totalRooms = totalRooms;
        this.bookedRooms = bookedRooms;
        this.availbleRooms = totalRooms - bookedRooms;
    }


}
