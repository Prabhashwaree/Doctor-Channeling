package Model;

public class Appointment {
    private String appointmentId;
    private String disease;
    private String date;
    private String time;
    private String nIC;
    private String drId;

    public Appointment() {
    }

    public Appointment(String appointmentId, String disease, String date, String time, String nIC, String drId) {
        this.appointmentId = appointmentId;
        this.disease = disease;
        this.date = date;
        this.time = time;
        this.nIC = nIC;
        this.drId = drId;
    }
    /*public Appointment(String appointmentId, String disease, String date, String time, String nIC, String drId) {
        this.setAppointmentId(appointmentId);
        this.setDisease(disease);
        this.setDate(date);
        this.setTime(time);
        this.setnIC(nIC);
        this.setDrId(drId);
    }*/

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

    public String getnIC() {
        return nIC;
    }

    public void setnIC(String nIC) {
        this.nIC = nIC;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }
}
