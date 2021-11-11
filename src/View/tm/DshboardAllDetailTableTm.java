package View.tm;

public class DshboardAllDetailTableTm {
    private String empId;
    private String ptnNIC;
    private String name;
    private String contactNo;
    private String age;
    private String appointmentId;
    private String doctorId;
    private Double totalAmount;

    public DshboardAllDetailTableTm() {
    }

    public DshboardAllDetailTableTm(String empId, String ptnNIC, String name, String contactNo, String age, String appointmentId, String doctorId, Double totalAmount) {
        this.setEmpId(empId);
        this.setPtnNIC(ptnNIC);
        this.setName(name);
        this.setContactNo(contactNo);
        this.setAge(age);
        this.setAppointmentId(appointmentId);
        this.setDoctorId(doctorId);
        this.setTotalAmount(totalAmount);
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "DshboardAllDetailTableTm{" +
                "empId='" + empId + '\'' +
                ", nIC='" + ptnNIC + '\'' +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", age='" + age + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
