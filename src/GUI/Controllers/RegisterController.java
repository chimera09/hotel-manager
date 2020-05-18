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
