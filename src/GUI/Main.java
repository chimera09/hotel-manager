package src.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.database.Database;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXML/Login.fxml"));
        primaryStage.setTitle("Hotel Manager");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }



    public static void main(String[] args) {
        Database.setUp();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                Database.saveDatabase();
            }
        }));
        launch(args);

       //Database.insertUser("Mariaaaaaa", "1234", "client", "maria@gmail.com", "Arad, str. Lucian Blaga, nr.52");
        //Database.saveDatabase();
        //System.out.println(Database.getUserPassword("Maria"));
        //Database.insertHotel("Iris", "Dorel", 150);
        Database.saveDatabase();
    }
}
