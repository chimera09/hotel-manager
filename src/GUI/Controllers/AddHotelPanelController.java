package src.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import src.Exceptions.HotelAlreadyExistsException;
import src.Users.Hotel;
import src.Users.HotelManager;
import src.Users.Moderator;
import src.Users.User;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class AddHotelPanelController extends Application {
    HotelManager user;
    @FXML
    public TextField nameField;
    @FXML
    public TextField descriptionField;
    @FXML
    public TextField facilitiesField;
    @FXML
    public TextField roomsField;
    @FXML
    public Text submitMessage;


    public void handleSubmitButtonAction() {
        String name = nameField.getText();
        String descr = descriptionField.getText();
        String facilities = facilitiesField.getText();
        String rooms = roomsField.getText();
        if(descr.isEmpty() || facilities.isEmpty() || rooms.isEmpty() ){
            submitMessage.setText("Please fill all the informations");
            return;
        }
        int no_rooms = Integer.parseInt(rooms);
        Hotel hotel = new Hotel(name,user.getName(),descr,facilities,no_rooms);

        if(name != null || !name.isEmpty()){
            try{
                user.addHotel(hotel);
                Stage stage = new Stage();
                stage.initOwner(submitMessage.getScene().getWindow());
                MyHotelPanelController mhpn = new MyHotelPanelController();
                mhpn.start(stage);
            }
            catch(HotelAlreadyExistsException e){
                submitMessage.setText("Hotel already exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            submitMessage.setText("Please enter hotel name");
        }
    }
    public void initialize(){
        user = (HotelManager) Moderator.user;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
