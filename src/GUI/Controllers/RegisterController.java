package src.GUI.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.Users.Customer;
import src.Users.HotelManager;
import src.Users.User;
import src.database.PassHashing;
import src.Exceptions.*;

import java.io.IOException;

public class RegisterController extends Application {
    @FXML
    public TextField usernameField;
    @FXML
    public TextField mailField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField accountField;
    @FXML
    public TextField passwordField;
    @FXML
    public Text submitMessage;
    @FXML
    public Button submitButton;

    public void handleSubmitButtonAction(){
        String username = usernameField.getText();
        String mail = mailField.getText();
        String address = addressField.getText();
        String mode = accountField.getText();
        String password = PassHashing.getMd5(passwordField.getText());

        if ((username != null) && !username.isEmpty()){
            if(username.length() < 4 || passwordField.getText().length() < 4){
                submitMessage.setText("Password/Username too short.Please enter more than 4 characters");
                return;
            }
            if(mail.isEmpty() || mode.isEmpty() || address.isEmpty() || password.isEmpty()){
                submitMessage.setText("Please fill all the informations");
                return;
            }
            try{
                User user = User.createUser(username,password,mode,address,mail);
                Stage stage = (Stage) submitMessage.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../FXML/HotelManagerAccountPanel.fxml"));
                Scene scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                if(user instanceof Customer) {
                    try {
                        Stage stage = (Stage) submitButton.getScene().getWindow();
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
                        Stage stage = (Stage) submitButton.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader((getClass().getResource("../FXML/HotelManagerAccountPanel.fxml")));
                        Parent root =loader.load();
                        Scene scene = new Scene(root, 600, 400);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch (UserAlreadyExistsException e){

                submitMessage.setText("User " + username +" already exits" );
            }
        }
        else{
            submitMessage.setText("Please enter username");
            return;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
