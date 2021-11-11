package Controller;


import Model.PaymentBill;
import View.tm.PaymentBillTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReseptionPaymentFormController {

    public TextField txtSearch;
    public TableColumn colTestingAmount;
    public TableColumn colChanalingAmount;
    public TableColumn colEmployeeId;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colPatientNIC;
    public TableColumn colTotalAmount;
    public TableColumn colPaymentId;
    public AnchorPane PaymentBillPane;
    public TableView tblPayment;
    static ArrayList<PaymentBill> paymentList = new ArrayList();


    public void initialize(){

        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPaymentId.setStyle("-fx-alignment: center");
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDate.setStyle("-fx-alignment: center");
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTime.setStyle("-fx-alignment: center");
        colChanalingAmount.setCellValueFactory(new PropertyValueFactory<>("chanalingAmount"));
        colChanalingAmount.setStyle("-fx-alignment: center");
        colTestingAmount.setCellValueFactory(new PropertyValueFactory<>("testingAmount"));
        colTestingAmount.setStyle("-fx-alignment: center");
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("total"));
        colTotalAmount.setStyle("-fx-alignment: center");
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmployeeId.setStyle("-fx-alignment: center");
        colPatientNIC.setCellValueFactory(new PropertyValueFactory<>("ptnNIC"));
        colPatientNIC.setStyle("-fx-alignment: center");

        setAllPaymentToTable(new ReseptionPaymentController().getAllPayment());
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        search(txtSearch.getText());
    }

    private void search(String value) {
        List<PaymentBill> paymentBills = null;
        try {
            paymentBills = ReseptionPaymentController.searchPayment(value);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<PaymentBillTm> tableData = FXCollections.observableArrayList();
        for (PaymentBill details :paymentBills){
            tableData.add(new PaymentBillTm(
                    details.getPaymentId(),
                    details.getDate(),
                    details.getTime(),
                    details.getChanalingAmount(),
                    details.getTestingAmount(),
                    details.getTotal(),
                    details.getEmpId(),
                    details.getnIC()
            ));
        }
        tblPayment.getItems().setAll(tableData);
    }

    public void btnBackToFactoryOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) PaymentBillPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteReseptionOnAction(ActionEvent actionEvent) {
    }
    public void setAllPaymentToTable(ArrayList <PaymentBill>allDetails){
        System.out.println(allDetails);
        ObservableList <PaymentBillTm>observableList= FXCollections.observableArrayList();
        allDetails.forEach(e->{
            observableList.add(new PaymentBillTm(
                    e.getPaymentId(),e.getDate(),e.getTime(),e.getChanalingAmount(),e.getTestingAmount(),e.getTotal(),e.getEmpId(),e.getnIC()
            ));
        });
        tblPayment.setItems(observableList);
    }
}
