package Controller;


import Util.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CreateAndLoginFormController {
    public Button loginBtn;
    public AnchorPane CreateLoginPane;
    public PasswordField txtPassword;
    public TextField txtUserName;
    public Label lblWrongLongin;

    LinkedHashMap<TextField, Pattern> validations = new LinkedHashMap<>();
    Pattern userName = Pattern.compile("^[A-z]{3,30}$");
    Pattern password = Pattern.compile("^[0-9]{3,30}$");

    private  void Validates(){
        loginBtn.setDisable(false);
        validations.put(txtUserName,userName);
        validations.put(txtPassword,password);

    }

    public void LoginKeyReleasedOnAction(KeyEvent keyEvent) {
        Object response = Validation.validate(validations, loginBtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }
    public void initialize (){
        Validates();
    }

    public void Login(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        String user = new UserController().getUserType(txtUserName.getText(), txtPassword.getText());
        if (user == null) {
            lblWrongLongin.setText("Please enter your correct Password or user Name.");
        } else {
            if (user.equals("Reseption")) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/DashBoardForm.fxml"));
                Parent load = fxmlLoader.load();
                DashBoardFormController controller = fxmlLoader.getController();
                controller.setUserNameAndPassword(txtUserName.getText(),txtPassword.getText());
                Stage window = (Stage) CreateLoginPane.getScene().getWindow();
                window.setScene(new Scene(load));
                window.show();

            } else if (user.equals("Admin")) {

                URL resource = getClass().getResource("../View/AdminDashBoardForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage window = (Stage) CreateLoginPane.getScene().getWindow();
                window.setScene(new Scene(load));
                window.show();

            } else {
                if (user.equals("Doctor")) {
                    URL resource = getClass().getResource("../View/PrescriptionForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Stage window = (Stage) CreateLoginPane.getScene().getWindow();
                    window.setScene(new Scene(load));
                    window.show();
                }
            }
        }

    }

    public void txtPosswordOnAction(ActionEvent actionEvent) {
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
    }



}
