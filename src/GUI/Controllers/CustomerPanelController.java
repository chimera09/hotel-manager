package src.GUI.Controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import src.Users.Customer;
import src.Users.Hotel;
import src.Users.HotelManager;
import src.Users.Moderator;
import src.database.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerPanelController extends Application {

    @FXML
    public ListView<HBoxCell> listView;

    Customer user;

    static class ButtonEventHandler implements EventHandler {
        private String hotelName;
        private Button buttonId;

        public ButtonEventHandler(String hotelName, Button buttonId) {
            this.hotelName = hotelName;
            this.buttonId = buttonId;
        }

        @Override
        public void handle(Event event) {
            try {
                Stage stage = new Stage();
                stage.initOwner(buttonId.getScene().getWindow());
                FXMLLoader loader  = new FXMLLoader((getClass().getResource("../FXML/HotelInfoPage.fxml")));
                Parent root = loader.load();
                HotelInfoPageController controller = loader.<HotelInfoPageController>getController();
                controller.initialize(hotelName);
                Scene scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                controller.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button();

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            button.setText("View");

            button.setOnAction(new ButtonEventHandler(labelText, button));

            this.getChildren().addAll(label, button);
        }
    }

    public void setListView() {
        List<HBoxCell> list = new ArrayList<HBoxCell>();
        String hotelName;
        for (int i = 0; i < user.showHotels().size(); i++) {
            hotelName = user.showHotels().get(i).getName();
            list.add(new HBoxCell(hotelName));
        }

        ObservableList<HBoxCell> myObservableList = FXCollections.observableArrayList(list);

        listView.setItems(myObservableList);
    }

    @FXML
    public void initialize() {
        user = (Customer) Moderator.user;
        setListView();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
