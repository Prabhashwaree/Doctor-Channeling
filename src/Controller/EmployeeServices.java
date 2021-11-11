package Controller;

import Model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeServices {
    public boolean newEmployee(Employee e) throws SQLException, ClassNotFoundException;
    public boolean refreshEmployee(Employee e) throws SQLException, ClassNotFoundException;
    public boolean RemoveEmployee(String empId) throws SQLException, ClassNotFoundException;
    public Employee getEmployee(String empId) throws SQLException, ClassNotFoundException;
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;
    public String getEmployeeId(String userName,String password) throws SQLException, ClassNotFoundException;
}
