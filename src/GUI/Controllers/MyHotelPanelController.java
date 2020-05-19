package src.GUI.Controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.PickResult;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import src.Users.HotelManager;
import src.Users.Moderator;
import src.database.Database;

//import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;


public class MyHotelPanelController extends Application {
    HotelManager user;
    GridPane gridPanel;
    HBox hbox;

    public MyHotelPanelController() {
        super();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();
        primaryStage.setTitle("My Hotels");
        Scene scene = new Scene(hbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        //hbox.getScene().getWindow().hide();
    }

    public void initialize() {
        user = (HotelManager) Moderator.user;
        gridPanel = new GridPane();
        Popup popup = new Popup();
        gridPanel.setPrefSize(600, 400);
        ListView listView = new ListView();
        listView.setPrefSize(600, 400);
        listView.setEditable(true);
        gridPanel.add(listView, 0, 0);
        for (int i = 0; i < user.getMyHotels().size(); i++) {
            listView.getItems().add(user.getMyHotels().get(i).getName());

        }
        hbox = new HBox(gridPanel);

    }
}