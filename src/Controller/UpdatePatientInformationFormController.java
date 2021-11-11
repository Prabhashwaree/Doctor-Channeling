package Controller;

import Model.Appointment;
import Util.AlertMassage;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;


public class UpdatePatientInformationFormController {
    public TextField txtNIC;
    public TextField txtDate;
    public TextField txtTime;
    public TextField txtAppointID;
    public TextField txtDisease;
    public TextField txtDoctorId;

    private AppointmentFormController controller;

    public void moveToAge(ActionEvent actionEvent) {
    }

    public void moveToGender(ActionEvent actionEvent) {
    }

    public void moveToContact(ActionEvent actionEvent) {
    }

    public void moveToName(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nIC = txtAppointID.getText();

        Appointment p1= new AppointmentController().getAppointment(nIC);
        if (p1==null) {
            new AlertMassage().errorMassage("Empty Result Set.....");
        } else {
            setData(p1);
        }

    }

    private void setData(Appointment p1) {
        txtAppointID.setText(p1.getAppointmentId());
        txtDate.setText(p1.getDate());
        txtTime.setText(p1.getTime());
        txtNIC.setText(p1.getnIC());
        txtDisease.setText(p1.getDisease());
        txtDoctorId.setText(p1.getDrId());
    }

    public void moveToAdderss(ActionEvent actionEvent) {
    }

    public void moveToTime(ActionEvent actionEvent) {
    }

    public void txtUpDateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Appointment a1= new Appointment(
                txtAppointID.getText(),txtDisease.getText(),txtDate.getText(),txtTime.getText(),txtNIC.getText(),
                txtDoctorId.getText()
        );

        if (new AppointmentController().updateAppointment(a1)) {
            new AlertMassage().successMassage("Update Details.......");
            controller.loadAllAppointment();
        }else {
            new AlertMassage().errorMassage("Try Again.....");
        }
    }

    public void setController(AppointmentFormController appointmentFormController) {
        this.controller=appointmentFormController;
    }
}
