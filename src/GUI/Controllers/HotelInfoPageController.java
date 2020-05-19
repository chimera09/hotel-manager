package src.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import src.Users.Hotel;
import src.database.Database;

import java.awt.print.Book;
import java.io.IOException;

public class HotelInfoPageController extends Application {
    @FXML
    public Button bookButton;
    @FXML
    public TextField descriptionField, facilitiesField;
    @FXML
    public Text hotelNameField, totalRoomsField, availableRoomsField;

    private String hotelName;
    private Hotel hotel;
    private JSONObject hotelData;

    public void handleBookButton() {
        try {
            Stage stage = (Stage) bookButton.getScene().getWindow();
            FXMLLoader loader  = new FXMLLoader((getClass().getResource("../FXML/Booking.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            BookingController controller = loader.<BookingController>getController();
            controller.initialize(hotel);
            controller.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(String hotelName) {
        this.hotelName = hotelName;
        hotelNameField.setText(hotelName);
        hotelData = Database.getHotelData(hotelName);
        hotel = new Hotel(hotelName, hotelData);
        descriptionField.setText(hotel.getDescription());
        facilitiesField.setText(hotel.getFacilities());
        totalRoomsField.setText(String.valueOf(hotel.getTotalRooms()));
        availableRoomsField.setText(String.valueOf(hotel.getAvailableRooms()));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.showAndWait();
    }
}
