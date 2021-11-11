package View.tm;

public class EmployeeTm {
    private String empId;
    private String type;
    private String empName;
    private String empAddress;
    private String joinDate;
    private String joinTime;
    private Double salary;

    public EmployeeTm() {
    }

    public EmployeeTm(String empId, String type, String empName, String empAddress, String joinDate, String joinTime, Double salary) {
        this.setEmpId(empId);
        this.setType(type);
        this.setEmpName(empName);
        this.setEmpAddress(empAddress);
        this.setJoinDate(joinDate);
        this.setJoinTime(joinTime);
        this.setSalary(salary);
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeTm{" +
                "empId='" + empId + '\'' +
                ", type='" + type + '\'' +
                ", empName='" + empName + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", salary=" + salary +
                '}';
    }
}
