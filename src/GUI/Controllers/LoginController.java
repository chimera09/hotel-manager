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
        String password = passwordField.getText();

        if ((username != null) && !username.isEmpty()) {
            if (password == null || password.isEmpty()) {
                loginMessage.setText("Password cannot be empty");
                return;
            }

            if (username.equals("student") && password.equals("student")) {
                loginMessage.setText("Logged in as student!");
                return;
            }

            if (username.equals("teacher") && password.equals("teacher")) {
                    Stage stage = (Stage) loginMessage.getScene().getWindow();
                    return;
            }

            loginMessage.setText("Incorrect login!");
        } else {
            loginMessage.setText("Please type in a username!");
            return;
        }

    }

    public void start(Stage primaryStage) throws Exception {

    }
}
