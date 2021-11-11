package Model;

public class Patient {
    private String nIC;
    private String name;
    private String contactNo;
    private String gender;
    private String address;
    private String date;
    private String time;
    private String age;
    private String empId;


    public Patient(String nIC, String name, String contactNo, String gender, String address, String date, String time, String age, String empId) {
        this.setnIC(nIC);
        this.setName(name);
        this.setContactNo(contactNo);
        this.setGender(gender);
        this.setAddress(address);
        this.setDate(date);
        this.setTime(time);
        this.setAge(age);
        this.setEmpId(empId);
    }
    public String getPatientId() {
        return nIC;
    }
    public String getnIC() {
        return nIC;
    }

    public void setnIC(String nIC) {
        this.nIC = nIC;
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
    @Override
    public String toString() {
        return "Patient{" +
                "nIC='" + getnIC() + '\'' +
                ", name='" + getName() + '\'' +
                ", contactNo='" + getContactNo() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", date='" + getDate() + '\'' +
                ", time='" + getTime() + '\'' +
                ", age='" + getAge() + '\'' +
                ", userId='" + empId + '\'' +
                '}';
    }


}