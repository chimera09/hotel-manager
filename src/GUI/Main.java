package src.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.database.Database;

import javax.xml.crypto.Data;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
        primaryStage.setTitle("Appul Nostru");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }



    public static void main(String[] args) {
        Database.setUp();
        launch(args);

        //Database.insertUser("Maria", "1234", "client", "maria@gmail.com", "Arad, str. Lucian Blaga, nr.52");
        //Database.saveDatabase();
        //System.out.println(Database.getUserPassword("Maria"));
        //Database.insertHotel("Iris", "Dorel", 150);
        //Database.saveDatabase();
    }
}
