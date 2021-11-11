package View.tm;


import javafx.scene.control.Button;

public class PatientTm {
    private String ptnNIC;
    private String name;
    private String contactNo;
    private String gender;
    private String address;
    private String date;
    private String time;
    private String age;
    private String empId;
    private Button btn;

    public PatientTm() {
    }

    public PatientTm(String ptnNIC, String name, String contactNo, String gender, String address, String date, String time, String age, String empId, Button btn) {
        this.setPtnNIC(ptnNIC);
        this.setName(name);
        this.setContactNo(contactNo);
        this.setGender(gender);
        this.setAddress(address);
        this.setDate(date);
        this.setTime(time);
        this.setAge(age);
        this.setEmpId(empId);
        this.setBtn(btn);
    }

    public PatientTm(String ptnNIC, String name, String contactNo, String gender, String address, String date, String time, String age, String empId) {
        this.ptnNIC = ptnNIC;
        this.name = name;
        this.contactNo = contactNo;
        this.gender = gender;
        this.address = address;
        this.date = date;
        this.time = time;
        this.age = age;
        this.empId = empId;
    }

    public String getPtnNIC() {
        return ptnNIC;
    }

    public void setPtnNIC(String ptnNIC) {
        this.ptnNIC = ptnNIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
