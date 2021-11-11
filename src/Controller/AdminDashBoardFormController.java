package Controller;

import Model.Employee;
import Model.User;
import Util.AlertMassage;
import Util.Validation;
import View.tm.EmployeeTm;
import View.tm.UserTm;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AdminDashBoardFormController {
    public TableColumn colEmpId;
    public TableColumn colPassword;
    public TableColumn colUserName;
    public TableColumn colSalary;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colEmpAddress;
    public TableColumn colEmpName;
    public TableColumn colType;
    public TableColumn colempId;
    public ComboBox cmbEmployeeId;
    public TextField txtPassword;
    public TextField txtUserName;
    public TextField txtSalary;
    public TextField txtEmpAddress;
    public TextField txtEmpName;
    public TextField txtType;
    public TextField txtEmpId;
    public Pane sliderAncorPane;
    public Label lblTime;
    public Label lblDate;
    public Label lblMenuBack;
    public Label lblMenu;
    public AnchorPane adminDashPane;
    public AnchorPane subpane;
    public TableView tblEmployeeInformation;
    public TableView tblUserInformation;
    public TableColumn colUserInformEmpId;
    public TextField txtEp;
    public Button btnSave;
    public Button btnsSave;

    LinkedHashMap<TextField, Pattern> validation = new LinkedHashMap<>();
    Pattern empId = Pattern.compile("^[E]{1}[-][0-9]{3}$");
    Pattern type = Pattern.compile("^[A-z]{3,30}$");
    Pattern name = Pattern.compile("^[A-z]{3,30}[ ][A-z]{3,30}$");
    Pattern address = Pattern.compile("^[A-Z]{1}[0-9]{1,5}[/][0-9]{2}[,][A-z]{6,30}[,][A-z]{6,30}$");
    Pattern salary = Pattern.compile("^[0-9][0-9][0-9][0-9]*([.])[0-9]{2}?$");

    LinkedHashMap<TextField, Pattern> validations = new LinkedHashMap<>();
    Pattern userName = Pattern.compile("^[A-z]{3,30}[ ][A-z]{3,30}$");
    Pattern password = Pattern.compile("^[A-z]{3,8}[A-z0-9]{3,6}$");


    private  void Validate(){
        btnsSave.setDisable(false);
        validation.put(txtEmpId,empId);
        validation.put(txtType,type);
        validation.put(txtEmpName,name);
        validation.put(txtEmpAddress,address);
        validation.put(txtSalary,salary);

    }
    private  void Validates(){
        validations.put(txtUserName,userName);
        validations.put(txtPassword,password);

    }

    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object response = Validation.validate(validation, btnsSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

        public void validateKeyReleasedOnActions (KeyEvent keyEvent){
        Object response = Validation.validate(validations, btnsSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
        }

        public void initialize () throws SQLException, ClassNotFoundException {
            Validate();
            Validates();

            colempId.setCellValueFactory(new PropertyValueFactory<>("empId"));
            colempId.setStyle("-fx-alignment: center");
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colType.setStyle("-fx-alignment: center");
            colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
            colEmpName.setStyle("-fx-alignment: center");
            colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
            colEmpAddress.setStyle("-fx-alignment: center");
            colDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
            colDate.setStyle("-fx-alignment: center");
            colTime.setCellValueFactory(new PropertyValueFactory<>("joinTime"));
            colTime.setStyle("-fx-alignment: center");
            colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
            colSalary.setStyle("-fx-alignment: center");


            colUserInformEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
            colUserInformEmpId.setStyle("-fx-alignment: center");
            colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
            colUserName.setStyle("-fx-alignment: center");
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colPassword.setStyle("-fx-alignment: center");


            try {

                setEmployeeToTable(new EmployeeController().getAllEmployee());
                setUserToTable(new UserController().getAllUser());
                loadEmployeeIds();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            sliderAncorPane.setTranslateX(-1000);
            lblMenu.setOnMouseClicked(event -> {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.5));
                slide.setNode(sliderAncorPane);

                slide.setToX(0);
                slide.play();

                sliderAncorPane.setTranslateX(-176);

                slide.setOnFinished((ActionEvent e) -> {
                    lblMenu.setVisible(false);
                    lblMenuBack.setVisible(true);
                });
            });

            lblMenuBack.setOnMouseClicked(event -> {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(sliderAncorPane);

                slide.setToX(-1000);
                slide.play();

                sliderAncorPane.setTranslateX(0);

                slide.setOnFinished((ActionEvent e) -> {
                    lblMenu.setVisible(true);
                    lblMenuBack.setVisible(false);

                });
            });
            loadDateAndTime();


            //-------------------------User----------------------------

        }
        public void loadEmployeeIds () throws SQLException, ClassNotFoundException {
            List<String> empIds = new EmployeeController().loadEmployeeIds();
            cmbEmployeeId.getItems().addAll(empIds);


        }

        private void setUserToTable (ArrayList < User > user) {
            ObservableList<UserTm> obList = FXCollections.observableArrayList();
            user.forEach(e -> {
                obList.add(
                        new UserTm(e.getEmpId(), e.getUserName(), e.getPassword()));
            });
            tblUserInformation.setItems(obList);
        }

        private void loadDateAndTime () {
            Date date = new Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            lblDate.setText(f.format(date));

            Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                LocalTime currentTime = LocalTime.now();
                lblTime.setText(
                        currentTime.getHour() + " : " + currentTime.getMinute() +
                                " : " + currentTime.getSecond()
                );
            }),
                    new KeyFrame(Duration.seconds(1))
            );
            time.setCycleCount(Animation.INDEFINITE);
            time.play();
        }


        private void setEmployeeToTable (ArrayList < Employee > employee) {
            ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
            employee.forEach(e -> {
                obList.add(
                        new EmployeeTm(e.getEmpId(), e.getType(), e.getEmpName(), e.getEmpAddress(), e.getJoinDate(), e.getJoinTime(), e.getSalary()));
            });
            tblEmployeeInformation.setItems(obList);
        }


        public void btnRefrashEmpInforOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            Employee e1 = new Employee(
                    txtEmpId.getText(), txtType.getText(), txtEmpName.getText(), txtEmpAddress.getText(), lblDate.getText(), lblTime.getText(),
                    Double.parseDouble(txtSalary.getText())

            );
            if (new EmployeeController().refreshEmployee(e1)) {
                setEmployeeToTable(new EmployeeController().getAllEmployee());
                new AlertMassage().successMassage("Updated Employee Details.......");
            } else {
                new AlertMassage().errorMassage("Try Again.....");
            }
            clear();
        }

        public void btnNewEmpInforOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            Employee e1 = new Employee(
                    txtEmpId.getText(), txtType.getText(), txtEmpName.getText(), txtEmpAddress.getText(), lblDate.getText(), lblTime.getText(),
                    Double.parseDouble(txtSalary.getText())
            );

            if (new EmployeeController().newEmployee(e1)) {
                setEmployeeToTable(new EmployeeController().getAllEmployee());
                cmbEmployeeId.getItems().addAll(e1.getEmpId());
                new AlertMassage().successMassage("Add New Employee Details.......");
            } else {
                new AlertMassage().errorMassage("Try Again.....");
            }
            clear();
        }
        public void clear () {
            txtEmpId.clear();
            txtType.clear();
            txtEmpName.clear();
            txtEmpAddress.clear();
            txtSalary.clear();
            txtUserName.clear();
            txtPassword.clear();
        }

        public void btnCreatAccountOnAction (ActionEvent actionEvent) throws IOException {
            URL resource = getClass().getResource("../View/CreateAndLoginForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) adminDashPane.getScene().getWindow();
            window.setScene(new Scene(load));
        }

        public void btnChartReportOnAction (ActionEvent actionEvent) throws IOException {
            URL resource = getClass().getResource("../View/ReprtForm.fxml");
            Parent load = FXMLLoader.load(resource);
            subpane.getChildren().clear();
            subpane.getChildren().add(load);
        }

        public void btnPaymentReportOnAction (ActionEvent actionEvent) throws IOException {
            URL resource = getClass().getResource("../View/PaymentPayForm.fxml");
            Parent load = FXMLLoader.load(resource);
            subpane.getChildren().clear();
            subpane.getChildren().add(load);
        }

        public void btnDoctorDetailsOnAction (ActionEvent actionEvent) throws IOException {
            URL resource = getClass().getResource("../View/DoctorDetailsForm.fxml");
            Parent load = FXMLLoader.load(resource);
            subpane.getChildren().clear();
            subpane.getChildren().add(load);
        }

        public void btnBackToLoginOnAction (ActionEvent actionEvent) throws IOException {
            URL resource = getClass().getResource("../View/CreateAndLoginForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) adminDashPane.getScene().getWindow();
            window.setScene(new Scene(load));
        }

        public void txtEmployeeIdOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String customerId = txtEmpId.getText();
            Employee e1 = new EmployeeController().getEmployee(customerId);
            if (e1 == null) {
                new AlertMassage().errorMassage("Empty Result Set.....");
            } else {
                setData(e1);
            }
            txtType.requestFocus();
            txtType.requestFocus();

        }


        private void setData (Employee e1){
            txtEmpId.setText(e1.getEmpId());
            txtType.setText(e1.getType());
            txtEmpName.setText(e1.getEmpName());
            txtEmpAddress.setText(e1.getEmpAddress());
            lblDate.setText(e1.getJoinDate());
            lblTime.setText(e1.getJoinTime());
            txtSalary.setText(String.valueOf(e1.getSalary()));

        }
        private void setDatas (User p1){
            cmbEmployeeId.setValue(String.valueOf(p1.getEmpId()));
            txtUserName.setText(p1.getUserName());
            txtPassword.setText(p1.getPassword());
        }

        public void txtRemoveEmpInformOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            if (new EmployeeController().RemoveEmployee(txtEmpId.getText())) {
                setEmployeeToTable(new EmployeeController().getAllEmployee());
                new AlertMassage().successMassage("Delete Employee Details.......");
            } else {
                new AlertMassage().errorMassage("Try Again.....");
            }
            clear();
        }

        public void btnRemoveUserOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            if (new UserController().RemoveUser(txtEp.getText())) {
                setUserToTable(new UserController().getAllUser());
                new AlertMassage().successMassage("Delete User Details.......");
            } else {
                new AlertMassage().errorMassage("Try Again.....");
            }
            clear();
        }

        public void btnRefreshUserOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            User p1 = new User(
                    (String) cmbEmployeeId.getValue(), txtUserName.getText(), txtPassword.getText());

            if (new UserController().refrashUser(p1)) {
                setUserToTable(new UserController().getAllUser());
                new AlertMassage().successMassage("Update User Details.......");
            } else {
                new AlertMassage().errorMassage("Try Again.....");
            }
            clear();
        }

        public void bunUserNewEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            User p1 = new User(
                    (String) cmbEmployeeId.getValue(), txtUserName.getText(), txtPassword.getText());

            if (new UserController().newUser(p1)) {
                setUserToTable(new UserController().getAllUser());

                new AlertMassage().successMassage("Add New User Details.......");
            } else {
                new AlertMassage().errorMassage("Try Again.....");
            }
            clear();
        }

    /*public void cmbEmpIdOnAction(ActionEvent actionEvent) {
        String userId = cmbEmployeeId.getItems().toString();

        User c1= new UserController().getUser(userId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(u1);
        }
        txtUserName.requestFocus();
    }
    public void clear(){
        txtUserName.clear();
        txtPassword.clear();
    }*/
        void setUserData (User u){
        /*try {

            cmbEmployeeId.setValue(String.valueOf(new EmployeeController().getEmployeeId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtUserName.setText(u.getUserName());
        txtPassword.setText(u.getPassword());*/
        }

        public void cmbEmpIdOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        }



    public void txtEpOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String userId = txtEp.getText();
            User p1 = new UserController().getUser(userId);
            if (p1 == null) {
                new AlertMassage().errorMassage("Empty Result Set.....");
            } else {
                setDatas(p1);
            }
            txtUserName.requestFocus();
        }

    public void moveToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void moveToUserName(ActionEvent actionEvent) {
        txtUserName.requestFocus();
    }

    public void moveToSlary(ActionEvent actionEvent) {
        txtSalary.requestFocus();
    }

    public void moveToAddress(ActionEvent actionEvent) {
        txtEmpAddress.requestFocus();
    }

    public void moveToName(ActionEvent actionEvent) {
        txtEmpName.requestFocus();
    }
    //--------------------------------------System User -----------------------------------------

    }


