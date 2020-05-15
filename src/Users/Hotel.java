package src.Users;

public class Hotel {
    String name;
    String owner;
    int totalRooms;
    int bookedRooms;
    int availbleRooms;

    public Hotel(){
        this.name = null;
        this.owner = null;
        this.totalRooms = 0;
        this.bookedRooms = 0;
        this.availbleRooms = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms() {
        this.bookedRooms = this.totalRooms - this.availbleRooms;
    }

    public int getAvailbleRooms() {
        return availbleRooms;
    }

    public void setAvailbleRooms() {
        this.availbleRooms = availbleRooms;
    }
}
