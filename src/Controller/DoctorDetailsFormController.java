package Controller;

import Model.Doctor;
import Util.AlertMassage;
import Util.Validation;
import View.tm.DoctorTm;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class DoctorDetailsFormController {
    public TableColumn colEmail;
    public TableColumn colPost;
    public TableColumn colContactNo;
    public TableColumn colDoctorName;
    public TableColumn colDoctorId;
    public TextField txtEmail;
    public TextField txtPost;
    public TextField txtContactNo;
    public TextField txtDrName;
    public TextField txtDoctorId;
    public TableView DoctorTable;
    public AnchorPane doctorPane;
    public Button btnSave;


    LinkedHashMap<TextField, Pattern> validation = new LinkedHashMap<>();
    Pattern iD = Pattern.compile("^[D]{1}[-][0-9]{3}$");
    Pattern name = Pattern.compile("^[A-z]{3,30}[ ][A-z]{3,30}$");
    Pattern contact = Pattern.compile("[0][0-9]{9}");
    Pattern post = Pattern.compile("^[A-z]{2,30}$");
    Pattern email = Pattern.compile("[a-z]{3,20}[0-9]{3,6}[@][a-z]{3,9}[.][a-z]{3,6}");

    private  void Validate(){
        btnSave.setDisable(false);
        validation.put(txtDoctorId,iD);
        validation.put(txtDrName,name);
        validation.put(txtContactNo,contact);
        validation.put(txtPost,post);
        validation.put(txtEmail,email);

    }

    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object response = Validation.validate(validation, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added Details").showAndWait();
            }
        }
    }


    public void btnRefrashEmpInforOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor d1= new Doctor(
                txtDoctorId.getText(),txtDrName.getText(),txtContactNo.getText(),
                txtPost.getText(),txtEmail.getText()
        );
        if (new DoctorController().refrashDoctor(d1)) {
            setDoctorToTable((ArrayList<Doctor>) new DoctorController().getAllDoctor());
            new AlertMassage().successMassage("Updated Doctor Details.......");
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();

    }

    public void btnNewEmpInforOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor d1= new Doctor(
                txtDoctorId.getText(),txtDrName.getText(),txtContactNo.getText(),
                txtPost.getText(),txtEmail.getText()
        );
        if(new DoctorController().newDoctor(d1)) {
            setDoctorToTable((ArrayList<Doctor>) new DoctorController().getAllDoctor());
            new AlertMassage().successMassage("Successfully.......");
        }else {
            new AlertMassage().errorMassage("Empty Result.....");
        }
        clear();
    }

    public void txtRemoveEmpInformOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new DoctorController().removeDoctor(txtDoctorId.getText())){
            setDoctorToTable((ArrayList<Doctor>) new DoctorController().getAllDoctor());
            new AlertMassage().successMassage("Deleted doctor details.......");

        }else{
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }

    private void setDoctorToTable(ArrayList<Doctor> doctor) {
        ObservableList<DoctorTm> obList = FXCollections.observableArrayList();
        doctor.forEach(e->{
            obList.add(
                    new DoctorTm(e.getDrId(),e.getDrName(),e.getContactNo(),e.getPost(),e.getEmail()));
        });
        DoctorTable.setItems(obList);
    }

    public void txtDoctorIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtDoctorId.getText();

        Doctor c1= new DoctorController().getDoctor(customerId);
        if (c1==null) {
            new AlertMassage().successMassage("Empty Result Set.......");
        } else {
            setData(c1);
        }
        txtDrName.requestFocus();
    }
    public void clear(){
        txtDoctorId.clear();
        txtDrName.clear();
        txtContactNo.clear();
        txtPost.clear();
        txtEmail.clear();
    }

    private void setData(Doctor d1) {
        txtDoctorId.setText(d1.getDrId());
        txtDrName.setText(d1.getDrName());
        txtContactNo.setText(d1.getContactNo());
        txtPost.setText(d1.getPost());
        txtEmail.setText(d1.getEmail());
    }
    public void initialize() {
        Validate();
        try {

            colDoctorId.setCellValueFactory(new PropertyValueFactory<>("drId"));
            colDoctorId.setStyle("-fx-alignment: center");
            colDoctorName.setCellValueFactory(new PropertyValueFactory<>("drName"));
            colDoctorName.setStyle("-fx-alignment: center");
            colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
            colContactNo.setStyle("-fx-alignment: center");
            colPost.setCellValueFactory(new PropertyValueFactory<>("post"));
            colPost.setStyle("-fx-alignment: center");
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colEmail.setStyle("-fx-alignment: center");

            setDoctorToTable((ArrayList<Doctor>) new DoctorController().getAllDoctor());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnBackToFactoryOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) doctorPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }

}
