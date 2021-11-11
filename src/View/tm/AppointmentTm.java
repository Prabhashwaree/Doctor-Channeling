package View.tm;

import javafx.scene.control.Button;

public class AppointmentTm {
    private String appointmentId;
    private String disease;
    private String date;
    private String time;
    private String ptnNIC;
    private String drId;
    private Button btns;
    private Button btn;

    public AppointmentTm() {
    }

    public AppointmentTm(String appointmentId, String disease, String date, String time, String ptnNIC, String drId, Button btns, Button btn) {
        this.setAppointmentId(appointmentId);
        this.setDisease(disease);
        this.setDate(date);
        this.setTime(time);
        this.setPtnNIC(ptnNIC);
        this.setDrId(drId);
        this.setBtns(btns);
        this.setBtn(btn);
    }

    public AppointmentTm(String appointmentId, String disease, String date, String time, String ptnNIC, String drId) {
        this.appointmentId = appointmentId;
        this.disease = disease;
        this.date = date;
        this.time = time;
        this.ptnNIC = ptnNIC;
        this.drId = drId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPtnNIC() {
        return ptnNIC;
    }

    public void setPtnNIC(String ptnNIC) {
        this.ptnNIC = ptnNIC;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public Button getBtns() {
        return btns;
    }

    public void setBtns(Button btns) {
        this.btns = btns;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
