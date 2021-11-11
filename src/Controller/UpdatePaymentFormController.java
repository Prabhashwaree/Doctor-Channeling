package Controller;

import Model.Patient;
import Model.PaymentBill;
import Util.AlertMassage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class UpdatePaymentFormController {
    public TextField txtTestingAmount;
    public TextField txtEmpId;
    public TextField txtNIC;
    public TextField txtPaymentId;
    public TextField txtTotalAmount;
    public TextField txtDate;
    public TextField txtTime;
    public TextField txtChanalingAmount;

    private PaymentPayFormController controller;

    public void MoveToNIC(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nIC = txtPaymentId.getText();

        PaymentBill p1= new ReseptionPaymentController().getPayment(nIC);
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setData(p1);
        }
    }

    private void setData(PaymentBill p1) {
        txtPaymentId.setText(p1.getPaymentId());
        txtDate.setText(p1.getDate());
        txtTime.setText(p1.getTime());
        txtChanalingAmount.setText(String.valueOf(p1.getChanalingAmount()));
        txtTestingAmount.setText(String.valueOf(p1.getTestingAmount()));
        txtTotalAmount.setText(String.valueOf(p1.getTotal()));
        txtEmpId.setText(p1.getEmpId());
        txtNIC.setText(p1.getnIC());
        txtEmpId.setText(p1.getEmpId());
    }

    public void btnUpdatePaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PaymentBill c1= new PaymentBill(
                txtPaymentId.getText(),txtDate.getText(),txtTime.getText(),
                txtChanalingAmount.getText(),txtTestingAmount.getText(),txtTotalAmount.getText(),txtEmpId.getText(),
                txtNIC.getText(),txtEmpId.getText()
        );
        if (new ReseptionPaymentController().updatePaymentBill(c1)) {
            // setAllPatientToTable(new PatientController().getAllPatient());
            new AlertMassage().successMassage("Update Details.......");
            //controller.setAllPaymentToTable();
        }else {
            new AlertMassage().errorMassage("Try Againg.....");
        }
    }
}
