package src.GUI.Controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;
import src.database.Database;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CustomerPanelController extends Application {

    @FXML
    public ListView<HBoxCell> listView;

    static class ButtonEventHandler implements EventHandler {
        public ButtonEventHandler(JSONObject hotel) {
            this.hotel = hotel;
        }

        JSONObject hotel;

        @Override
        public void handle(Event event) {
            System.out.println(hotel.toString());
        }
    }

    public static class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button();

        HBoxCell(String labelText, JSONObject hotelData) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            button.setText("View");

            button.setOnAction(new ButtonEventHandler(hotelData));

            this.getChildren().addAll(label, button);
        }
    }

    public void setListView() {
        List<HBoxCell> list = new ArrayList<HBoxCell>();
        JSONObject hotels = Database.getHotels();

        hotels.keySet().forEach(key -> list.add(new HBoxCell((String) key, (JSONObject)hotels.get(key))));

        ObservableList<HBoxCell> myObservableList = FXCollections.observableArrayList(list);

        listView.setItems(myObservableList);
    }

    @FXML
    public void initialize() {
        setListView();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
