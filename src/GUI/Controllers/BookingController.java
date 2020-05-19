package src.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.Exceptions.HotelAlreadyExistsException;
import src.Users.Hotel;
import src.database.Database;

import java.io.IOException;

public class BookingController extends Application {
    @FXML
    public Button bookButton;
    @FXML
    public Text displayMessage;
    @FXML
    public DatePicker checkInDate, checkOutDate;

    private Hotel hotel;

    public void handleBookButton() {
        if(checkInDate.getValue() != null && checkOutDate.getValue() != null) {
            displayMessage.setText("Successfully booked!");
            hotel.setBookedRooms(Database.getHotelData(hotel.getName()));
        }
        else displayMessage.setText("Input booking date!");
    }

    public void initialize(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
