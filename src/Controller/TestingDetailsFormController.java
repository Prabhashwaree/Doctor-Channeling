package Controller;

import DB.DbConnection;
import Model.MedicalTesting;
import Model.Patient;
import Model.TestingDetails;
import Model.User;
import Util.AlertMassage;
import Util.Validation;
import View.tm.EmployeeTm;
import View.tm.PaymentBillTm;
import View.tm.TestingDetailsTm;
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
import javafx.scene.input.MouseEvent;
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

public class TestingDetailsFormController {
    public TextField txtSearch;
    public TableColumn colTestingId;
    public TableColumn colNIC;
    public TableColumn colRange;
    public TableColumn colResult;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableView <TestingDetailsTm>tblTesting;
    public TextField txtRange;
    public TextField txtResult;
    public TextField txtNo;
    public TextField txtType;
    public TextField txtAddress;
    public Label lblTime;
    public Label lblDate;
    public ComboBox cmbTestingID;
    public ComboBox cmbNIC;
    public TextField txtContact;
    public TextField txtName;
    public AnchorPane testingPane;
    public TextField txtPresNo;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public TextField txtPatientNIC;
    public TextField txtNIC;

    int selectedRowRemove = -1;

    LinkedHashMap<TextField,Pattern> validation = new LinkedHashMap<>();
    Pattern rsult = Pattern.compile("^[A-z]{8}$");
    Pattern range = Pattern.compile("^[0-9]{3}$");

    private  void Validate(){
        btnSave.setDisable(false);
        validation.put(txtResult,rsult);
        validation.put(txtRange,range);

    }

    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object response = Validation.validate(validation, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    public void initialize() {

        Validate();
        loadDateAndTime();
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDate.setStyle("-fx-alignment: center");
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTime.setStyle("-fx-alignment: center");
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        colResult.setStyle("-fx-alignment: center");
        colRange.setCellValueFactory(new PropertyValueFactory<>("rang"));
        colRange.setStyle("-fx-alignment: center");
        colNIC.setCellValueFactory(new PropertyValueFactory<>("ptnNIC"));
        colNIC.setStyle("-fx-alignment: center");
        colTestingId.setCellValueFactory(new PropertyValueFactory<>("testId"));
        colTestingId.setStyle("-fx-alignment: center");

        try {
            setTestingToTable(new TestingController().getAllTesting());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadPatientIds();
            loadTestingIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbNIC.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                setPatient((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));

        cmbNIC.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                setTestingId((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));
        tblTesting.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedRowRemove = (int) newValue;
        });
    }

    private void loadTestingIds() throws SQLException, ClassNotFoundException {
        List<String>test = new MedicalTestingController().loadTestingIds();
        cmbTestingID.getItems().addAll(test);
    }

    private void loadPatientIds() throws SQLException, ClassNotFoundException {
        List<String> patient = new PatientController().loadPatientIds();
        cmbNIC.getItems().addAll(patient);
    }

    private void setPatient(String patient) throws SQLException, ClassNotFoundException {
        Patient p=new PatientController().getPatient(patient);
        txtName.setText(String.valueOf(p.getName()));
        txtContact.setText(String.valueOf(p.getContactNo()));
        txtAddress.setText(String.valueOf(p.getAddress()));
    }
    private void setTestingId(String testing) throws SQLException, ClassNotFoundException {
        MedicalTesting medical=new MedicalTestingController().getTesting(testing);
        txtType.setText(String.valueOf(medical.getType()));
        txtNo.setText(String.valueOf(medical.getPresNo()));
    }


    public void btnBackToFactoryOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) testingPane.getScene().getWindow();
        window.setScene(new Scene(load));
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

    private void setTestingToTable(ArrayList<TestingDetails> test) {
        ObservableList<TestingDetailsTm> obList = FXCollections.observableArrayList();
        test.forEach(e->{ obList.add(
                new TestingDetailsTm(e.getDate(),e.getTime(),e.getResult(),e.getRang(),e.getnIC(),e.getTestId()));
        });
        tblTesting.setItems(obList);
    }
    ObservableList<TestingDetailsTm> obList= FXCollections.observableArrayList();

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        TestingDetailsTm selectedItem = tblTesting.getSelectionModel().getSelectedItem();
        DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Testing Details` WHERE tDtestId='"+selectedItem.getTestId()+"'").executeUpdate();
        setTestingToTable(new TestingController().getAllTesting());
    }
    private void search(String value) {
        List<TestingDetails> test = null;
        try {
            test = TestingController.searchTesting(value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<TestingDetailsTm> tableData = FXCollections.observableArrayList();
        for (TestingDetails details :test){
            tableData.add(new TestingDetailsTm(
                    details.getDate(),
                    details.getTime(),
                    details.getResult(),
                    details.getRang(),
                    details.getnIC(),
                    details.getTestId()
            ));
        }
        tblTesting.getItems().setAll(tableData);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        TestingDetails p1= new TestingDetails(
                lblDate.getText(),lblTime.getText(),txtResult.getText(),txtRange.getText(),(String) cmbNIC.getValue(),
                (String) cmbTestingID.getValue());

        if (new TestingController().refrashTesting(p1)) {
            setTestingToTable(new TestingController().getAllTesting());
            new AlertMassage().successMassage("Update User Details.......");
        } else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        TestingDetails t1= new TestingDetails(
                lblDate.getText(),lblTime.getText(),txtResult.getText(),txtRange.getText(),(String) cmbNIC.getValue(),
                (String) cmbTestingID.getValue());

        if(new TestingController().newTesting(t1)) {
            setTestingToTable(new TestingController().getAllTesting());
            new AlertMassage().successMassage("Saved Details.......");
        }else {
            new AlertMassage().errorMassage("All Ready Add.....");
        }
        clear();
    }
    public void clear(){
        txtResult.clear();
        txtRange.clear();
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();

    }


    public void patientNICOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

    }

    public void testingIdOnAction(ActionEvent actionEvent) {
    }

    public void btnComfirmOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchAction(ActionEvent actionEvent) {
        search(txtSearch.getText());
    }
    private void setDatas(TestingDetails p1) {
        lblDate.setText(p1.getDate());
        lblTime.setText(p1.getTime());
        txtResult.setText(p1.getResult());
        txtRange.setText(p1.getRang());
        cmbNIC.setValue(String.valueOf(p1.getnIC()));
        cmbTestingID.setValue(String.valueOf(p1.getTestId()));

    }
    public void tableMouseClick(MouseEvent mouseEvent) {
        if(tblTesting.getSelectionModel().getSelectedItem()!=null) {
            TestingDetailsTm selectedItem = tblTesting.getSelectionModel().getSelectedItem();
            txtResult.setText(selectedItem.getResult());
            System.out.println(selectedItem.getResult());
            txtRange.setText(selectedItem.getRang());
            System.out.println(selectedItem.getRang());
            cmbNIC.setValue(String.valueOf(selectedItem.getPtnNIC()));
            cmbTestingID.setValue(String.valueOf(selectedItem.getTestId()));

        }else {
            new AlertMassage().errorMassage("Please Select the row where the wants to remove or view all Data.....");
        }
    }

    public void patientNICOnActions(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nIC = txtPatientNIC.getText();

        TestingDetails p1= new TestingController().getTesing(nIC);
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setDatas(p1);
            new AlertMassage().successMassage("Successfully.......");
        }
        txtName.requestFocus();

    }

    public void moveToRange(ActionEvent actionEvent) {
        txtRange.requestFocus();
    }
    //new AlertMassage().errorMassage("Please Select the row where the wants to remove or view all Data.....");

}
