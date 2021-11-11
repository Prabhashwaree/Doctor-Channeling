package Model;

public class User {
    private String empId;
    private String userName;
    private String password;

    public User() {
    }

    public User(String empId, String userName, String password) {
        this.setEmpId(empId);
        this.setUserName(userName);
        this.setPassword(password);
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
