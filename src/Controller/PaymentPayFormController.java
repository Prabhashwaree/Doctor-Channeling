package Controller;

import DB.DbConnection;
import Model.PaymentBill;
import Util.AlertMassage;
import View.tm.PaymentBillTm;
import View.tm.TestingDetailsTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentPayFormController {
    public TextField txtSearch;
    public TableColumn colPatientNIC;
    public TableColumn colEmployeeId;
    public TableColumn colTotalAmount;
    public TableColumn colTestingAmount;
    public TableColumn colChanalingAmount;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colPaymentId;
    public TableView <PaymentBillTm>tblPayment;
    public AnchorPane PaymentBillPane;

    static ArrayList<PaymentBill> paymentList = new ArrayList();
    public Button btnSQLView;
    public Label lblChanalingAmount;
    public Label lblTotalAmount;
    public Label lblTestingAmount;
    Double total=0.0;
    Double testingAmount=0.0;
    Double chanalingAmount=0.0;

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
        URL resource = getClass().getResource("../View/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) PaymentBillPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteReseptionOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PaymentBillTm selectedItem = tblPayment.getSelectionModel().getSelectedItem();
        DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Payment WHERE paymentId='"+selectedItem.getPaymentId()+"'").executeUpdate();
        setAllPaymentToTable(new ReseptionPaymentController().getAllPayment());
    }
    public void setAllPaymentToTable(ArrayList <PaymentBill>allDetails){
        ObservableList <PaymentBillTm>observableList= FXCollections.observableArrayList();
        allDetails.forEach(e->{
            observableList.add(new PaymentBillTm(
                    e.getPaymentId(),e.getDate(),e.getTime(),e.getChanalingAmount(),e.getTestingAmount(),e.getTotal(),e.getEmpId(),e.getnIC()
            ));
            total+=e.getTotal();
            chanalingAmount+=e.getChanalingAmount();
            testingAmount+=e.getTestingAmount();
        });
        tblPayment.setItems(observableList);
        lblTotalAmount.setText(String.valueOf(total));
        lblChanalingAmount.setText(String.valueOf(chanalingAmount));
        lblTestingAmount.setText(String.valueOf(testingAmount));
    }

    public void generateSQLReportMouseEvent(MouseEvent mouseEvent) {
            try {
                JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/Coffee_Landscape3.jrxml"));
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

    public void btnViewChartOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/Coffee_Landscape.jrxml"));
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

    public void paymentMouseClickOnAction(MouseEvent mouseEvent) {
        if(tblPayment.getSelectionModel().getSelectedItem()!=null) {
            PaymentBillTm selectedItem = tblPayment.getSelectionModel().getSelectedItem();


        }else {
            new AlertMassage().errorMassage("Please Select the row where the wants to remove or view all Data.....");
        }

    }

    public void btnViewPatientReportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/Coffee_Landscape4.jrxml"));
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

    public void btnViewEmpReportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/Coffee_Landscape5.jrxml"));
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
}
