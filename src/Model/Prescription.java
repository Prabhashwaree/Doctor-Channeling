package Model;

public class Prescription {
    private String presNo;
    private String medicine;
    private String date;
    private String time;
    private String drID;
    private String nIC;

    public Prescription(String text, String txtMedicineText, String lblDateText, String lblTimeText, Object value, Object cmbPatientIdValue) {
    }

    public Prescription(String presNo, String medicine, String date, String time, String drID, String nIC) {
        this.setPresNo(presNo);
        this.setMedicine(medicine);
        this.setDate(date);
        this.setTime(time);
        this.setDrID(drID);
        this.setnIC(nIC);
    }

    public String getPresNo() {
        return presNo;
    }

    public void setPresNo(String presNo) {
        this.presNo = presNo;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
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

    public String getDrID() {
        return drID;
    }

    public void setDrID(String drID) {
        this.drID = drID;
    }

    public String getnIC() {
        return nIC;
    }

    public void setnIC(String nIC) {
        this.nIC = nIC;
    }
}
