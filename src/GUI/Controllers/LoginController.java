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
import src.Users.User;
import src.database.PassHashing;
import src.Exceptions.*;

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
                try {
                    Stage stage = (Stage) loginMessage.getScene().getWindow();
                    Parent viewStudentsRoot = FXMLLoader.load(getClass().getResource("../FXML/CustomerPanel.fxml"));
                    Scene scene = new Scene(viewStudentsRoot, 600, 400);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
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
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getResource("../FXML/Register.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
