package Controller;

import View.tm.AppointmentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class StatusButtonOnActionFormController {
    private void setAllStatusToTable(ArrayList<AppointmentTm> allAppointment) {
        ObservableList<AppointmentTm> observableList= FXCollections.observableArrayList();
        allAppointment.forEach(e->{
            observableList.add(new AppointmentTm(
                    e.getAppointmentId(),e.getDate(),e.getTime(),e.getPtnNIC(),e.getDisease(),e.getDrId()
            ));
        });
        //tblReseptionDashBoard.setItems(observableList);
    }
}
