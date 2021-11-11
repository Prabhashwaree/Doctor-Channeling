package Controller;

import DB.DbConnection;
import Model.Appointment;
import Model.Patient;
import Model.PaymentBill;
import Util.AlertMassage;
import Util.Validation;
import View.tm.DshboardAllDetailTableTm;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class DashBoardFormController {
    public Label lblDate;
    public Label lblTime;
    public TableColumn colAmount;
    public TableColumn colAppointmentId;
    public TableColumn colAge;
    public TableColumn colContactNo;
    public TableColumn COLName;
    public TableColumn colNIC;
    public ComboBox txtStatus;
    public Label lblApointment;
    public TextField txtDoctorId;
    public TextField txtDisease;
    public TextField txtCal;
    public JFXButton btnDiv;
    public JFXButton btnClear;
    public JFXButton btnequel;
    public JFXButton btnMax;
    public JFXButton btnMinus;
    public JFXButton btnPluse;
    public TextField txtAge;
    public ComboBox cmbGender;
    public TextField txtAddress;
    public TextField TxtContact;
    public TextField txtName;
    public TextField txtNIC;
    public Label lblMenu;
    public Label lblMenuBack;
    public AnchorPane sliderAncorPane;
    public AnchorPane dashBoardAcorPaneMain;
    public AnchorPane Main;
    public Label lblEmpId;
    public TableView <DshboardAllDetailTableTm>tblReseptionDashBoard;
    public TableColumn colEmpId;
    public TableColumn colDoctoerId;
    public Button btnViewReport;
    public TextField txtAppointmentID;
    public TextField txtPaymentId;
    public TextField txtTotal;
    public TextField txtTestingAmount;
    public TextField txtChanalingAmount;
    public Button btnSave;
    public TextField txtBillI;
    private String uName;
    private String password;

    String op="";
    long numberOne;
    long numberTwo;

    LinkedHashMap<TextField, Pattern> validation = new LinkedHashMap<>();
    Pattern nIC = Pattern.compile("^[0-9]{9}[V]$");
    Pattern name = Pattern.compile("^[A-z]{3,30}[ ][A-z]{3,30}$");
    Pattern contact = Pattern.compile("[0][0-9]{9}");
    Pattern address = Pattern.compile("^[A-Z]{1}[0-9]{1,5}[/][0-9]{2}[,][A-z]{6,30}[,][A-z]{6,30}$");
    Pattern age = Pattern.compile("[0-9]{2,3}");
    Pattern disease = Pattern.compile("[A-z]{3,10}");
    Pattern doctor = Pattern.compile("^[D]{1}[-][0-9]{3}$");

    Pattern chanaling = Pattern.compile("^[0-9][0-9][0-9][0-9]*([.])[0-9]{2}?$");
    Pattern testing = Pattern.compile("^[0-9][0-9][0-9][0-9]*([.])[0-9]{2}?$");
    Pattern total = Pattern.compile("^[0-9][0-9][0-9][0-9]*([.])[0-9]{2}?$");
    
    private  void Validate(){
        btnSave.setDisable(false);
        validation.put(txtNIC,nIC);
        validation.put(txtName,name);
        validation.put(TxtContact,contact);
        validation.put(txtAddress,address);
        validation.put(txtAge,age);
        validation.put(txtDisease,disease);
        validation.put(txtDoctorId,doctor);
        validation.put(txtChanalingAmount,chanaling);
        validation.put(txtTestingAmount,testing);
        validation.put(txtTotal,total);

    }

    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object response = Validation.validate(validation, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
    }

    public void initialize() {
        Validate();
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpId.setStyle("-fx-alignment: center");
        colNIC.setCellValueFactory(new PropertyValueFactory<>("ptnNIC"));
        colNIC.setStyle("-fx-alignment: center");
        COLName.setCellValueFactory(new PropertyValueFactory<>("name"));
        COLName.setStyle("-fx-alignment: center");
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colContactNo.setStyle("-fx-alignment: center");
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAge.setStyle("-fx-alignment: center");
        colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        colAppointmentId.setStyle("-fx-alignment: center");
        colDoctoerId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colDoctoerId.setStyle("-fx-alignment: center");
        colAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colAmount.setStyle("-fx-alignment: center");

        try {
            setAllDetailsToTable(new DashBoardController().getAllDetails());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getAppointmentId();
        getPaymenttId();

        cmbGender.getItems().addAll("Female", "Male");
        sliderAncorPane.setTranslateX(-1000);
            lblMenu.setOnMouseClicked(event -> {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.5));
                slide.setNode(sliderAncorPane);

                slide.setToX(0);
                slide.play();

                sliderAncorPane.setTranslateX(-176);

                slide.setOnFinished((ActionEvent e)-> {
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

                slide.setOnFinished((ActionEvent e)-> {
                    lblMenu.setVisible(true);
                    lblMenuBack.setVisible(false);

                });
            });

        loadDateAndTime();

    }

    public void setAllDetailsToTable(ArrayList <DshboardAllDetailTableTm>allDetails){
        System.out.println(allDetails);
        ObservableList <DshboardAllDetailTableTm>observableList= FXCollections.observableArrayList();
        allDetails.forEach(e->{
            observableList.add(new DshboardAllDetailTableTm(
                    e.getEmpId(),e.getPtnNIC(),e.getName(),e.getContactNo(),e.getAge(),e.getAppointmentId(),e.getDoctorId(),e.getTotalAmount()
            ));
        });
        tblReseptionDashBoard.setItems(observableList);
    }
    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + ":" + currentTime.getMinute() +
                            ":" + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/CreateAndLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardAcorPaneMain.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnBinOnAction(ActionEvent actionEvent) {
    }

    public void btnBackLoginOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/CreateAndLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardAcorPaneMain.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnMedicalTestingOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/MedicalTestingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardAcorPaneMain.getChildren().clear();
        dashBoardAcorPaneMain.getChildren().add(load);
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/ReseptionPaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardAcorPaneMain.getChildren().clear();
        dashBoardAcorPaneMain.getChildren().add(load);
    }



    public void btnPationOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/PatientTableForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardAcorPaneMain.getChildren().clear();
        dashBoardAcorPaneMain.getChildren().add(load);
    }
    public void keyOnActionPatient(KeyEvent keyEvent) {

    }

    public void clear(){
        txtNIC.clear();
        txtName.clear();
        TxtContact.clear();
        //cmbGender.clear();
        txtAddress.clear();
        txtAge.clear();
        //lblEmpId.clear();
        txtDisease.clear();
        txtDoctorId.clear();
        txtChanalingAmount.clear();
        txtTestingAmount.clear();
        txtTotal.clear();
    }

    private void setEmployeeId() {
        try {
           lblEmpId.setText(String.valueOf(new EmployeeController().getEmployeeId(uName,password)));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDashbordConfirm(ActionEvent actionEvent){
        Connection connection = null;
        try {
            Patient p=new Patient(txtNIC.getText(),txtName.getText(),TxtContact.getText(),(String)
                    cmbGender.getValue(),txtAddress.getText(),lblDate.getText(),lblTime.getText(),
                    txtAge.getText(),lblEmpId.getText());

            Appointment a=new Appointment(lblApointment.getText(),txtDisease.getText(),lblDate.getText(),
                    lblTime.getText(),txtNIC.getText(), txtDoctorId.getText());

            PaymentBill pa= new PaymentBill(txtPaymentId.getText(),lblDate.getText(),lblTime.getText(),Double.parseDouble(txtChanalingAmount.getText()),
                    Double.parseDouble(txtTestingAmount.getText()),Double.parseDouble(txtTotal.getText()),lblEmpId.getText(),txtNIC.getText());
            setAllDetailsToTable(new DashBoardController().getAllDetails());getAppointmentId();
            connection= DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if(new PatientController().savePatient(p) &new AppointmentController().saveAppointment(a)& new ReseptionPaymentController().savePaymentBill(pa)) {
                connection.commit();
                setAllDetailsToTable(new DashBoardController().getAllDetails());
                //new DashBoardController().confirmTrncfer();
                new AlertMassage().successMassage("Saved Details.......");
                getAppointmentId();
                getPaymenttId();

            }else {
                connection.rollback();
                new AlertMassage().errorMassage("Try Againg.....");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        finally{
           try{
               connection.setAutoCommit(true);
           }catch (SQLException throwables){

           }
        }
        clear();
        //setAllDetailsToTable(new DashBoardController().getAllDetails());
    }


    /*public void btnSaveAllDateToMainTableOnActon(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       DshboardAllDetailTable d=new DshboardAllDetailTable(lblEmpId.getText(),txtNIC.getText(),txtName.getText(),TxtContact.getText(),txtAge.getText(),
                lblApointment.getText(),txtDoctorId.getText(),Double.parseDouble(txtTotal.getText()));

        if(new DashBoardController().saveAppointment(d)) {
            //setPatientToTable(new PatientTableFormController().getAllPatient());
            new Alert(Alert.AlertType.CONFIRMATION, "Saved All Details.....").show();
            getAppointmentId();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again......").show();
        }
        clear();
    }

    public void dashBoardRefrashOnAction(ActionEvent actionEvent) {
    }*/

    public void dashboardRemoveOnAction(ActionEvent actionEvent) {
    }

    public void viewRepoartOnAction(MouseEvent mouseEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/CashReceiptPaymentBill3.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setUserNameAndPassword(String uName,String password){
        this.uName=uName;
        this.password=password;
        setEmployeeId();
    }

    public void ApointmentRefreshOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Appointment a1= new Appointment(txtAppointmentID.getText(),txtDisease.getText(),lblDate.getText(),
                lblTime.getText(),txtNIC.getText(), txtDoctorId.getText()
        );

        if (new AppointmentController().updateAppointment(a1)) {
            new AlertMassage().successMassage("Appointment Details Update is Successfully.......");
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }
    public void billRefrshOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PaymentBill pa= new PaymentBill(txtPaymentId.getText(),lblDate.getText(),lblTime.getText(),Double.parseDouble(txtChanalingAmount.getText()),
                Double.parseDouble(txtTestingAmount.getText()),Double.parseDouble(txtTotal.getText()),lblEmpId.getText(),txtNIC.getText());
        System.out.println(lblTime.getText());
        if(new ReseptionPaymentController().updatePaymentBill(pa)){
            //setAllDetailsToTable(new DashBoardController().getAllDetails());
            //setAllDetailsToTable(new DashBoardController().getAllDetails());
            new AlertMassage().successMassage("All Details Update is Successfully.......");
            getAppointmentId();
        }else {
            new AlertMassage().errorMassage("Try Againg.....");
        }
        clear();
    }

    public void PatientRefrshOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

    }

    public void txtPatientNICOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nIC = txtNIC.getText();

        Patient p1= new PatientController().getPatient(nIC);
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setData(p1);
            new AlertMassage().successMassage("Successfully.......");
        }
        txtName.requestFocus();

    }

    private void setData(Patient p1) {
        txtNIC.setText(p1.getnIC());
        txtName.setText(p1.getName());
        TxtContact.setText(p1.getContactNo());
        cmbGender.setValue(String.valueOf(p1.getGender()));
        txtAddress.setText(p1.getAddress());
        lblDate.setText(p1.getDate());
        lblTime.setText(p1.getTime());
        txtAge.setText(p1.getAge());
        lblEmpId.setText(p1.getEmpId());


    }
    private void setDatas(Appointment a1){
        lblApointment.setText(a1.getAppointmentId());
        txtDisease.setText(a1.getDisease());
        lblDate.setText(a1.getDate());
        lblTime.setText(a1.getTime());
        txtNIC.setText(a1.getnIC());
        txtDoctorId.setText(a1.getDrId());
    }

    public void btnOperationOnAction(ActionEvent actionEvent) {
        String operation = ((Button) actionEvent.getSource()).getText();
        if (!operation.equals("=")) {
            if (!op.equals("")) {
                return;
            }
            op = operation;
            numberOne = Long.parseLong(txtCal.getText());
            txtCal.setText("");
        } else {
            if (op.equals("")) {
                return;
            }
            numberTwo = Long.parseLong(txtCal.getText());
            calculate(numberOne,  numberTwo, op);
            op = "";
        }
    }
    public void calculate(long n1, long n2, String op) {
        switch (op) {

            case "+":
                txtCal.setText(n1 + n2 + "");
                break;
            case "-":
                txtCal.setText(n1 - n2 + "");
                break;
            case "*":
                txtCal.setText(n1 * n2 + "");
                break;
            case "/":
                if (n2 == 0) {
                    txtCal.setText("0");
                    break;
                }
                txtCal.setText(n1 / n2 + "");
                break;
        }
    }

    public void calHandleOnAction(ActionEvent actionEvent) {
        String no = ((Button) actionEvent.getSource()).getText();
        txtCal.setText(txtCal.getText() + no);
    }

    public void AppointmentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nIC = txtAppointmentID.getText();

        Appointment a1= new AppointmentController().getAppointment(nIC);
        if (a1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setDatas(a1);
            new AlertMassage().successMassage("Successfully.......");
        }

    }
    public void btnAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/AppointmentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardAcorPaneMain.getChildren().clear();
        dashBoardAcorPaneMain.getChildren().add(load);
    }
    private void getAppointmentId() {
        try {
            lblApointment.setText(new AppointmentController().getAppointmentId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void getPaymenttId() {
        try {
            txtPaymentId.setText(new ReseptionPaymentController().getPaymentId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtBillIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id =txtPaymentId.getText();

        PaymentBill p1= new ReseptionPaymentController().getPayment(id);
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setDataSet(p1);
            new AlertMassage().successMassage("Successfully.......");
        }
    }

    private void setDataSet(PaymentBill p1) {
        txtPaymentId.setText(p1.getPaymentId());
        lblDate.setText(p1.getDate());
        lblTime.setText(p1.getTime());
        txtChanalingAmount.setText(String.valueOf(p1.getChanalingAmount()));
        txtTestingAmount.setText(String.valueOf(p1.getTestingAmount()));
        txtTotal.setText(String.valueOf(p1.getTotal()));
        lblEmpId.setText(p1.getEmpId());
        txtNIC.setText(p1.getnIC());
    }

    public void btnAllTestingDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/TestingDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardAcorPaneMain.getChildren().clear();
        dashBoardAcorPaneMain.getChildren().add(load);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }


    public void moveToChanaling(ActionEvent actionEvent) {
        txtChanalingAmount.requestFocus();
    }

    public void moveToDoctorId(ActionEvent actionEvent) {
        txtDoctorId.requestFocus();
    }

    public void moveToDisease(ActionEvent actionEvent) {
        txtDisease.requestFocus();
    }

    public void moveToAge(ActionEvent actionEvent) {
        txtAge.requestFocus();
    }

    public void moveToAddress(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void moveToContact(ActionEvent actionEvent) {
        TxtContact.requestFocus();
    }

    public void moveToTotal(ActionEvent actionEvent) {
        txtTotal.requestFocus();
    }

    public void moveToTesting(ActionEvent actionEvent) {
        txtTestingAmount.requestFocus();
    }
}
