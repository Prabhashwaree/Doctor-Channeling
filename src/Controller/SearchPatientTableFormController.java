package Controller;

import Model.Patient;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SearchPatientTableFormController {
    public TextField txtAge;
    public TextField txtAddress;
    public TextField TxtContact;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtDoctorId;
    public TextField txtUserId;
    public TextField txtDate;
    public TextField txtTime;
    public TextField TxtGender;
    public TextField txtEmpId;

    public void moveToUserIdOnAction(ActionEvent actionEvent) {
    }

    public void moveToDate(ActionEvent actionEvent) {
    }

    public void moveToGender(ActionEvent actionEvent) {
    }

    public void moveToContact(ActionEvent actionEvent) {
    }

    public void moveToName(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String patientNIC = txtNIC.getText();

        Patient p1= new PatientController().getPatient(patientNIC);
        if (p1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
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

    public void moveToDoctorIdOnAction(ActionEvent actionEvent) {
    }

    public void moveToTime(ActionEvent actionEvent) {
    }

    public void moveToAge(ActionEvent actionEvent) {
    }

    public void btnSearchPatientTableOnAction(ActionEvent actionEvent) {
    }

    public void moveToAdderss(ActionEvent actionEvent) {
    }
}
