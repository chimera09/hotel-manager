
package src.database;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import src.Users.Hotel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static JSONObject db;
    private static String dbPath = "UsersDB.json";

    public static void setUp() {
        FileReader reader = null;
        try {
            reader = new FileReader(dbPath);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        };

        JSONParser js = new JSONParser();
        Object obj = null;
        try {
            obj = js.parse(reader);
            db = (JSONObject) obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static JSONObject getUserData(String user) {
        JSONObject userEntry = (JSONObject) ((JSONObject) db.get("users")).get(user);
        if (userEntry == null)
            return null;
        return userEntry;
    }

    public static Boolean userExists(String user) {
        if(getUserData(user) == null)
            return false;
        return true;
    }
    public static String getUserPassword(String user) {
        JSONObject userData = getUserData(user);
        if (userData == null)
            return null;
        return (String) userData.get("password");
    }


    public static String getUserMode(String user) {
        JSONObject userData = getUserData(user);
        if (userData == null)
            return null;
        return (String) userData.get("mode");
    }

    public static String getUserMail(String user) {
        JSONObject userData = getUserData(user);
        if (userData == null)
            return null;
        return (String) userData.get("email");
    }

    public static String getUserAddress(String user) {
        JSONObject userData = getUserData(user);
        if (userData == null)
            return null;
        return (String) userData.get("address");
    }


    public static boolean insertUser(String user, String password, String mode, String email, String address) {
        if (userExists(user))
            return false;
        JSONObject userData = new JSONObject();
        userData.put("password", password);
        userData.put("mode", mode);
        userData.put("email", email);
        userData.put("address", address);

        ((JSONObject) db.get("users")).put(user, userData);
        return true;
    }

    private static JSONObject getHotelData(String name) {
        JSONObject hotelEntry = (JSONObject) ((JSONObject) db.get("hotels")).get(name);
        if (hotelEntry == null)
            return null;
        return hotelEntry;
    }

    public static List<Hotel> getHotels(){
        List<Hotel> hotels = new ArrayList<>();
        JSONObject hotelsDb = (JSONObject) db.get("hotels");

        for (Object key : hotelsDb.keySet()) {
            String name = (String) key;
            JSONObject hotelEntry = getHotelData(name);
            Hotel hotel = new Hotel(name, hotelEntry);
                hotels.add(hotel);
        }
        return  hotels;
    }

    public static Boolean hotelExists(String name) {
        if(getHotelData(name) == null)
            return false;
        return true;
    }

    public static boolean insertHotel(String name, String owner,String description,String facilities, int totalRooms) {
        if (hotelExists(name))
            return false;
        JSONObject hotelData = new JSONObject();
        hotelData.put("owner", owner);
        hotelData.put("description",description);
        hotelData.put("facilities",facilities);
        hotelData.put("total_rooms", totalRooms);
        hotelData.put("occupied_rooms", 0);

        ((JSONObject) db.get("hotels")).put(name, hotelData);
        return true;
    }

    public static List<Hotel> getHotelsForOwner(String owner){
        List<Hotel> hotelOfOwner = new ArrayList<>();
        JSONObject hotelsDb = (JSONObject) db.get("hotels");

        for (Object key : hotelsDb.keySet()) {
            String name = (String) key;
            JSONObject hotelEntry = getHotelData(name);
            Hotel hotel = new Hotel(name, hotelEntry);
            if (hotel.getOwner().equals(owner))
                hotelOfOwner.add(hotel);
        }
        return  hotelOfOwner;
    }


    public static void saveDatabase() {
        try {
            FileWriter fw = new FileWriter(dbPath,false);
            fw.write(db.toJSONString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

