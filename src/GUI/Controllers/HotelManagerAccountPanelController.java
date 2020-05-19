package src.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


import java.io.IOException;

import javafx.scene.control.Button;

public class HotelManagerAccountPanelController extends Application {
    @FXML
    private Button showHotelsBtn;

    HotelManager user;
    @FXML
    public Text errMessage;

    private Stage stage;

    public void setUser(User user){
        this.user  = (HotelManager)user;
    }

    public void handleAddHotelButton(){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/AddHotelPanel.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.initOwner(errMessage.getScene().getWindow());
            stage.setScene(scene);
            stage.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void handleMyHotelsButton(){
        try{
            //user.checkList();
            Stage stage = new Stage();
            stage.initOwner(errMessage.getScene().getWindow());
            MyHotelPanelController mhpn = new MyHotelPanelController();
            mhpn.start(stage);
        }
        catch (NoHotelsAddedException | IOException e){
            errMessage.setText("No hotels added");
        } catch (Exception e) {
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
