package Controller;

import Model.Patient;
import Util.AlertMassage;
import View.tm.DshboardAllDetailTableTm;
import View.tm.PatientTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdatePatientFormController {
    public TextField txtAge;
    public TextField txtAddress;
    public TextField TxtContact;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtDate;
    public TextField txtTime;
    public TextField TxtGender;
    public TextField txtEmpId;

    PatientTableFormController  table = new PatientTableFormController();
    private PatientTableFormController controller;

    public void moveToName(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nIC = txtNIC.getText();

        Patient p1= new PatientController().getPatient(nIC);
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setData(p1);
        }

    }

    private void setData(Patient p1) {
        txtNIC.setText(p1.getnIC());
        txtName.setText(p1.getName());
        TxtContact.setText(p1.getContactNo());
        TxtGender.setText(p1.getGender());
        txtAddress.setText(p1.getAddress());
        txtDate.setText(p1.getDate());
        txtTime.setText(p1.getTime());
        txtAge.setText(p1.getAge());
        txtEmpId.setText(p1.getEmpId());
    }

    public void btnDeletePatientOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Patient c1= new Patient(
                txtNIC.getText(),txtName.getText(),TxtContact.getText(),
                TxtGender.getText(),txtAddress.getText(),txtDate.getText(),txtTime.getText(),
                txtAge.getText(),txtEmpId.getText()
        );
        if (new PatientController().updateCustomer(c1)) {
           // setAllPatientToTable(new PatientController().getAllPatient());
            new AlertMassage().successMassage("Update Details.......");
            controller.loadAllPatient();
        }else {
            new AlertMassage().errorMassage("Try Againg.....");
        }


    }
    /*private void setAllPatientToTable(ArrayList<PatientTm> allPatient) {
        ObservableList<PatientTm> observableList= FXCollections.observableArrayList();
        allPatient.forEach(e->{
            observableList.add(new PatientTm(
                    e.getPtnNIC(),e.getName(),e.getContactNo(),e.getGender(),e.getAddress(),e.getDate(),e.getTime(),e.getAge(),e.getEmpId()
            ));
        });
        table.tblPation.setItems(observableList);
    }*/

    public void setController(PatientTableFormController controller){
        this.controller=controller;


    }

}
