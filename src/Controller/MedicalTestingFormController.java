package Controller;

import Model.MedicalTesting;
import Model.PaymentBill;
import Util.AlertMassage;
import Util.Validation;
import View.tm.MedicalTestingTm;
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

public class MedicalTestingFormController {
    public Pane updatePane;
    public Label lblMenuBack;
    public Label lblMenu;
    public Pane removePane;
    public Pane searchPane;
    public Pane addTestingPane;
    public Label lblMenuBack111;
    public Label lblMenu111;
    public Label lblMenuBack11;
    public Label lblMenu11;
    public Label lblMenuBack1;
    public Label lblMenu1;
    public TableColumn colPresNo;
    public TableColumn colPaymentId;
    public TableColumn colAmount;
    public TableColumn colType;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colTestingId;
    public TextField txtAmount;
    public TextField txtType;
    public TextField txtTestingId;
    public AnchorPane testingPane;
    public ComboBox <String>cmbPresNo;
    public ComboBox <String>cmbPaymentId;
    public TableView tblTesting;
    public Label lblTime;
    public Label lblDate;
    public Label lblTesting;
    public TextField txtSearch;
    public Button btnSave;


    LinkedHashMap<TextField, Pattern> validation = new LinkedHashMap<>();
    Pattern type = Pattern.compile("[A-z]{3,20}");

    private  void Validate(){
        btnSave.setDisable(false);
        validation.put(txtType,type);

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
        loadDateAndTime();
        getTestingId();
        Validate();
        colTestingId.setCellValueFactory(new PropertyValueFactory<>("testingId"));
        colTestingId.setStyle("-fx-alignment: center");
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDate.setStyle("-fx-alignment: center");
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTime.setStyle("-fx-alignment: center");
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colType.setStyle("-fx-alignment: center");
        colAmount.setCellValueFactory(new PropertyValueFactory<>("testingPrize"));
        colAmount.setStyle("-fx-alignment: center");
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPaymentId.setStyle("-fx-alignment: center");
        colPresNo.setCellValueFactory(new PropertyValueFactory<>("presNo"));
        colPresNo.setStyle("-fx-alignment: center");

        try {
            setTestingToTable(new MedicalTestingController().getAllTesting());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadPaymentIds();
            loadPrescriptionIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbPaymentId.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                setPayment(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));

        removePane.setTranslateX(+110);
        lblMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(removePane);

            slide.setToX(0);
            slide.play();

            removePane.setTranslateX(-10);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu.setVisible(false);
                lblMenuBack.setVisible(true);
            });
        });

        lblMenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(removePane);

            slide.setToX(+110);
            slide.play();

            removePane.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu.setVisible(true);
                lblMenuBack.setVisible(false);

            });
        });
        
        //--------------------Search-------------------------------

        searchPane.setTranslateX(+110);
        lblMenu1.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(searchPane);

            slide.setToX(0);
            slide.play();

            searchPane.setTranslateX(-10);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu1.setVisible(false);
                lblMenuBack1.setVisible(true);
            });
        });

        lblMenuBack1.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(searchPane);

            slide.setToX(+110);
            slide.play();

            searchPane.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu1.setVisible(true);
                lblMenuBack1.setVisible(false);

            });
        });

        //--------------------Update-------------------------------

        updatePane.setTranslateX(+110);
        lblMenu11.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(updatePane);

            slide.setToX(0);
            slide.play();

            updatePane.setTranslateX(-10);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu11.setVisible(false);
                lblMenuBack11.setVisible(true);
            });
        });

        lblMenuBack11.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(updatePane);

            slide.setToX(+110);
            slide.play();

            updatePane.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu11.setVisible(true);
                lblMenuBack11.setVisible(false);

            });
        });

        //--------------------Save-------------------------------

        addTestingPane.setTranslateX(+110);
        lblMenu111.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(addTestingPane);

            slide.setToX(0);
            slide.play();

            addTestingPane.setTranslateX(-10);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu111.setVisible(false);
                lblMenuBack111.setVisible(true);
            });
        });

        lblMenuBack111.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(addTestingPane);

            slide.setToX(+110);
            slide.play();

            addTestingPane.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu111.setVisible(true);
                lblMenuBack111.setVisible(false);

            });
        });

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

    private void setTestingToTable(ArrayList<MedicalTesting> test) {
        ObservableList<MedicalTestingTm> obList = FXCollections.observableArrayList();
        test.forEach(e->{ obList.add(
                new MedicalTestingTm(e.getTestingId(),e.getDate(),e.getTime(),e.getType(),e.getTestingPrize(),e.getPaymentId(),e.getPresNo()));
        });
        tblTesting.setItems(obList);
    }

    public void btnAddTestingOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        MedicalTesting p1= new MedicalTesting(
                txtTestingId.getText(),lblDate.getText(),lblTime.getText(),txtType.getText(),Double.parseDouble(txtAmount.getText()),
                (String) cmbPaymentId.getValue(),(String) cmbPresNo.getValue());

        if(new MedicalTestingController().newTesting(p1)) {
            setTestingToTable(new MedicalTestingController().getAllTesting());
            new AlertMassage().successMassage("Saved medical testing details.......");
            getTestingId();
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();
    }
    public void clear(){
        txtType.clear();
        txtAmount.clear();
        //cmbPaymentId.getItems().clear();
        //cmbPresNo.getItems().clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        MedicalTesting p1= new MedicalTesting(
                txtTestingId.getText(),lblDate.getText(),lblTime.getText(),txtType.getText(),Double.parseDouble(txtAmount.getText()),
                (String) cmbPaymentId.getValue(),(String) cmbPresNo.getValue());

        if (new MedicalTestingController().refrashTesting(p1)) {
            setTestingToTable(new MedicalTestingController().getAllTesting());
            new AlertMassage().successMassage("Updated Medical testing Details.......");
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();

    }

    public void btnRemoveOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (new MedicalTestingController().removeTesting(txtTestingId.getText())){
            setTestingToTable(new MedicalTestingController().getAllTesting());
            new AlertMassage().successMassage("Deleted Medical testing Details.......");
            setTestingToTable(new MedicalTestingController().getAllTesting());
        }else{
            new AlertMassage().errorMassage("Try Again.....");
        }
        clear();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        search(txtSearch.getText());
    }
    private void search(String value) {
        List<MedicalTesting> test = null;
        try {
            test = MedicalTestingController.searchTesting(value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<MedicalTestingTm> tableData = FXCollections.observableArrayList();
        for (MedicalTesting details :test){
            tableData.add(new MedicalTestingTm(
                    details.getTestingId(),
                    details.getDate(),
                    details.getTime(),
                    details.getType(),
                    details.getTestingPrize(),
                    details.getPaymentId(),
                    details.getPresNo()
            ));
        }
        tblTesting.getItems().setAll(tableData);
    }

    private void setData(MedicalTesting p1) {
        txtTestingId.setText(p1.getTestingId());
        lblDate.setText(p1.getDate());
        lblTime.setText(p1.getTime());
        txtType.setText(p1.getType());
        txtAmount.setText(String.valueOf(p1.getTestingPrize()));
        cmbPaymentId.setValue(String.valueOf(p1.getPaymentId()));
        cmbPresNo.setValue(String.valueOf(p1.getPresNo()));
    }

    public void moveToPresNo(ActionEvent actionEvent) {
    }

    public void moveToType(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String test = txtTestingId.getText();

        MedicalTesting p1= new MedicalTestingController().getTesting(test);
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result set .....");
        } else {
            setData(p1);
        }
        txtType.requestFocus();
    }

    public void btnBackToFactoryOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) testingPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }
    public void loadPaymentIds() throws SQLException, ClassNotFoundException {
        List<String> payment = new ReseptionPaymentController().loadPaymentIds();
        cmbPaymentId.getItems().addAll(payment);
    }
    public void loadPrescriptionIds() throws SQLException, ClassNotFoundException {
        List<String>pres = new PrescriptionController().loadPrescriptionIds();
        cmbPresNo.getItems().addAll(pres);
    }
    private void getTestingId() {
        try {
            txtTestingId.setText(new MedicalTestingController().getTestingId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void setPayment(String payment) throws SQLException, ClassNotFoundException {
        PaymentBill bill=new ReseptionPaymentController().getPayment(payment);
        txtAmount.setText(String.valueOf(bill.getTestingAmount()));
    }

    /*public void setAllTestingToTable(ArrayList <MedicalTesting>allDetails){
        System.out.println(allDetails);
        ObservableList <MedicalTestingTm>observableList= FXCollections.observableArrayList();
        allDetails.forEach(e->{
            observableList.add(new MedicalTestingTm(
                    e.getTestingId(),e.getDate(),e.getTime(),e.getType(),e.getTestingPrize(),e.getPaymentId(),e.getPresNo()
            ));
        });
        tblTesting.setItems(observableList);
    }*/

}
