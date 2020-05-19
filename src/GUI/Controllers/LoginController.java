package src.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.Users.Customer;
import src.Users.HotelManager;
import src.Users.Moderator;
import src.Users.User;
import src.database.PassHashing;
import src.Exceptions.*;

import javax.jws.WebParam;
import java.io.IOException;


public class LoginController extends Application {

    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;


    @FXML
    public void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = PassHashing.getMd5(passwordField.getText());

        if ((username != null) && !username.isEmpty()) {
            if (password == null || password.isEmpty()) {
                loginMessage.setText("Password cannot be empty");
                return;
            }

            if (passwordField.getText().length() < 4) {
                loginMessage.setText("Password/Username too short , you need more than 4 characters");
                return;
            }
            try{
                User user = User.getUser(username,password);
                Moderator.user = user;
                //Moderator.stage = (Stage) loginMessage.getScene().getWindow();
                if(user instanceof Customer) {
                   try {
                        Stage stage = (Stage) loginMessage.getScene().getWindow();
                        FXMLLoader loader  = new FXMLLoader((getClass().getResource("../FXML/CustomerPanel.fxml")));
                        Parent root = loader.load();
                        Scene scene = new Scene(root, 600, 400);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(user instanceof HotelManager){
                    try {
                        Stage stage = (Stage) loginMessage.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader((getClass().getResource("../FXML/HotelManagerAccountPanel.fxml")));
                        Parent root =loader.load();
                        Scene scene = new Scene(root, 600, 400);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (UserNotFoundException e){
                loginMessage.setText("User not found.Please try again");
            } catch (PasswordIncorrectException e){
                loginMessage.setText("Incorrect password.Please try again");
            };

        } else {
            loginMessage.setText("Please type in a username!");
            return;
        }

    }
    public void handleRegisterButtonAction(){
        try {
            Stage stage = (Stage) loginMessage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/Register.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
