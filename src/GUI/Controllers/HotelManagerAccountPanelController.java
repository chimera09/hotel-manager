package src.GUI.Controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.Exceptions.NoHotelsAddedException;
import src.Users.HotelManager;
import src.Users.Moderator;
import src.Users.User;
import src.database.Database;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HotelManagerAccountPanelController extends Application {
    HotelManager user;
    public Text errMessage;

    private Stage stage;

    public void handleAddHotelButton(){
        try{
            Stage stage = (Stage) errMessage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/AddHotelPanel.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void handleMyHotelsButton(){
        try{
            user.checkList();
        }
        catch (NoHotelsAddedException e){
            //errMessage.setTextContent("No hotels added");
        };
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/MyHotels.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void initialize() {
        user = (HotelManager) Moderator.user;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
