package Controller;



import Model.Prescription;
import Model.PrescriptionDetails;
import Util.AlertMassage;
import Util.Validation;
import View.tm.PrescriptionDetailsTm;
import View.tm.PrescriptionTm;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

public class PrescriptionFormController {
    public TableColumn colTesId;
    public TableColumn colPresDetails;
    public TableColumn colPatientNIC;
    public TableColumn colDrId;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colMedicine;
    public Label lblTime;
    public Label lblDate;
    public AnchorPane doctorDashboardPane;
    public ComboBox cmbPatientId;
    public ComboBox cmbDorId;
    public TableView tblPrescripInfor;
    public ComboBox cmbTestId;
    public ComboBox cmbPrescriptionNo;
    public TableColumn colDisease;
    public TableView tblPDetails;
    public Button btnSave;
    public TextField txtMedicine;
    public TableColumn colPresNo;
    public TextField txtDisease;
    public TextField txtPresNo;
    public Button btnsSave;


    LinkedHashMap<TextField, Pattern> validation = new LinkedHashMap<>();
    Pattern press = Pattern.compile("[P]{1}[-][0-9]{3}");
    Pattern medicin = Pattern.compile("[A-z]{3,10}");

    private  void Validate(){
        btnSave.setDisable(false);
        validation.put(txtPresNo,press);
        validation.put(txtMedicine,medicin);

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
    //--------------------------------------------------------------------------------------


    public void initialize() {
        Validate();
        loadDateAndTime();

        try {
            loadPatientIds();
            loadDoctorIds();
            loadPrescriptionIds();
            loadTestingIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        colPresNo.setCellValueFactory(new PropertyValueFactory<>("presNo"));
        colPresNo.setStyle("-fx-alignment: center");
        colMedicine.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        colMedicine.setStyle("-fx-alignment: center");
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDate.setStyle("-fx-alignment: center");
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTime.setStyle("-fx-alignment: center");
        colDrId.setCellValueFactory(new PropertyValueFactory<>("drID"));
        colDrId.setStyle("-fx-alignment: center");
        colPatientNIC.setCellValueFactory(new PropertyValueFactory<>("ptnNIC"));
        colPatientNIC.setStyle("-fx-alignment: center");

        //------------------------------------------------------------------------------

        colDisease.setCellValueFactory(new PropertyValueFactory<>("disease"));
        colDisease.setStyle("-fx-alignment: center");
        colPresDetails.setCellValueFactory(new PropertyValueFactory<>("prescriptionNo"));
        colPresDetails.setStyle("-fx-alignment: center");
        colTesId.setCellValueFactory(new PropertyValueFactory<>("testId"));
        colTesId.setStyle("-fx-alignment: center");


        try {
            setPrescriptionToTable(new PrescriptionController().getAllPrescription());
            setPrescriptionDetailsToTable(new PrescriptionDetailsController().getAllPrescriptionDetails());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void loadPatientIds() throws SQLException, ClassNotFoundException {
        List<String>patientIds = new PatientController().loadPatientIds();
        cmbPatientId.getItems().addAll(patientIds);
    }
    public void loadDoctorIds() throws SQLException, ClassNotFoundException {
        List<String>doctorIds = new DoctorController().loadDoctorIds();
       cmbDorId.getItems().addAll(doctorIds);
    }
    public void loadPrescriptionIds() throws SQLException, ClassNotFoundException {
        List<String>prescriptionIds = new PrescriptionController().loadPrescriptionIds();
        cmbPrescriptionNo.getItems().addAll(prescriptionIds);
    }
    public void loadTestingIds() throws SQLException, ClassNotFoundException {
        List<String>testingIds = new MedicalTestingController().loadTestingIds();
        cmbTestId.getItems().addAll(testingIds);
    }

    public void btnNewDetailsOnActio(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PrescriptionDetails p1= new PrescriptionDetails(
                txtDisease.getText(),(String) cmbPrescriptionNo.getValue(),(String) cmbTestId.getValue());

        if(new PrescriptionDetailsController().newPrescriptionDetails(p1)) {
            setPrescriptionDetailsToTable(new PrescriptionDetailsController().getAllPrescriptionDetails());

            new AlertMassage().successMassage("Save Prescription details.......");
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }

    public void btnRefrashDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PrescriptionDetails p1= new PrescriptionDetails(
                txtDisease.getText(),(String) cmbPrescriptionNo.getValue(),(String) cmbTestId.getValue());

        if (new PrescriptionDetailsController().refrashPrescriptionDetails(p1)) {
            setPrescriptionDetailsToTable(new PrescriptionDetailsController().getAllPrescriptionDetails());
            new AlertMassage().successMassage("Update Prescription details.......");
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }

    public void btnSearchDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String prescriptionId = txtPresNo.getText();

        PrescriptionDetails p1= new PrescriptionDetailsController().getPrescriptionDetails(prescriptionId );
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setDatas(p1);
        }
        clear();
    }

    private void setDatas(PrescriptionDetails p1) {
        txtDisease.setText(p1.getDisease());
        cmbPrescriptionNo.setValue(String.valueOf(p1.getPrescriptionNo()));
        cmbTestId.setValue(String.valueOf(p1.getTestId()));
    }

    public void btnRemoveDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PrescriptionDetails p1= new PrescriptionDetails();
        if (new PrescriptionDetailsController().removePrescriptionDetails(cmbPrescriptionNo.getValue().toString())){
            setPrescriptionDetailsToTable(new PrescriptionDetailsController().getAllPrescriptionDetails());
            new AlertMassage().successMassage("Delete Prescription details.......");
        }else{
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }

    public void btnNewPresOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Prescription p1= new Prescription(
                txtPresNo.getText(),txtMedicine.getText(),lblDate.getText(),lblTime.getText(),
                (String) cmbDorId.getValue(),(String) cmbPatientId.getValue());

        if(new PrescriptionController().newPrescription(p1)) {
            setPrescriptionToTable(new PrescriptionController().getAllPrescription());

            new AlertMassage().successMassage("Save Prescription details.......");
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }

    public void clear(){
        txtPresNo.clear();
        txtMedicine.clear();
        txtDisease.clear();

    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Prescription p1= new Prescription(
                txtPresNo.getText(),txtMedicine.getText(),lblDate.getText(),lblTime.getText(),
                (String) cmbDorId.getValue(),(String) cmbPatientId.getValue()
        );
        if (new PrescriptionController().refrashPrescription(p1)) {
            setPrescriptionToTable(new PrescriptionController().getAllPrescription());
            new AlertMassage().successMassage("Update Prescription details.......");
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new PrescriptionController().removePrescription(txtPresNo.getText())){
            setPrescriptionToTable(new PrescriptionController().getAllPrescription());
            new AlertMassage().successMassage("Delete Prescription details.......");
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clear();
    }
    private void setPrescriptionToTable(ArrayList<Prescription> prescription) {
        ObservableList<PrescriptionTm> obList = FXCollections.observableArrayList();
        prescription.forEach(e->{ obList.add(
                new PrescriptionTm(e.getPresNo(),e.getMedicine(),e.getDate(),e.getTime(),e.getDrID(),e.getnIC()));
        });
        tblPrescripInfor.setItems(obList);
    }
    private void setPrescriptionDetailsToTable(ArrayList<PrescriptionDetails> prescriptionDetails) {
        ObservableList<PrescriptionDetailsTm> obList = FXCollections.observableArrayList();
        prescriptionDetails.forEach(e->{ obList.add(
                new PrescriptionDetailsTm(e.getDisease(),e.getPrescriptionNo(),e.getTestId()));
        });
        tblPDetails.setItems(obList);
    }

    public void btnBackToLoginOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/CreateAndLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) doctorDashboardPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String prescriptionId = txtPresNo.getText();

        Prescription p1= new PrescriptionController().getPrescription(prescriptionId );
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setData(p1);
        }
        clear();

    }
    private void loadDateAndTime(){
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


    public void prescriptionNoOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String prescriptionId = txtPresNo.getText();

        Prescription p1= new PrescriptionController().getPrescription(prescriptionId);
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setData(p1);
        }
        txtMedicine.requestFocus();
    }

    private void setData(Prescription p1) {
        txtPresNo.setText(p1.getPresNo());
        txtMedicine.setText(p1.getMedicine());
        lblDate.setText(p1.getDate());
        lblTime.setText(p1.getTime());
        cmbDorId.setValue(String.valueOf(p1.getDrID()));
        cmbPatientId.setValue(String.valueOf(p1.getnIC()));
    }
//--------------------------------------------------------------------------------------------------


    public void cmbTestingIDOnAction(ActionEvent actionEvent) {
    }

    public void cmbPrescriptionNoOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String prescriptionId = txtPresNo.getText();

        PrescriptionDetails p1= new PrescriptionDetailsController().getPrescriptionDetails(prescriptionId);
        if (p1==null) {
            //new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setDatas(p1);
        }
        txtDisease.requestFocus();
    }

    public void diseaseOnAction(ActionEvent actionEvent) {
    }

    public void validateKeyReleasedOnActions(KeyEvent keyEvent) {
    }
}
