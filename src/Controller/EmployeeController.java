package Controller;

import Controller.EmployeeServices;
import DB.DbConnection;
import Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements EmployeeServices {

    public Employee getEmployee(String empId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Employee WHERE empId=?");
        stm.setObject(1,empId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7)
            );

        } else {
            return null;
        }

    }

    @Override
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee");
        ResultSet rst = stm.executeQuery();
        ArrayList<Employee> employee = new ArrayList<>();
        while (rst.next()) {
            employee.add(new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7)
            ));
        }
        return employee;
    }

    @Override
    public String getEmployeeId(String userName,String password) throws SQLException, ClassNotFoundException {
        System.out.println(userName+" "+password);
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT empId FROM `User` WHERE userName='"+userName+"' AND password=sha1('"+password+"')").executeQuery();
        while (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public boolean newEmployee(Employee e1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Employee VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, e1.getEmpId());
        stm.setObject(2,e1.getType());
        stm.setObject(3,e1.getEmpName());
        stm.setObject(4,e1.getEmpAddress());
        stm.setObject(5,e1.getJoinDate());
        stm.setObject(6,e1.getJoinTime());
        stm.setObject(7,e1.getSalary());

        return stm.executeUpdate()>0;
    }

    public boolean RemoveEmployee(String text) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Employee WHERE empId='"+text+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean refreshEmployee(Employee e1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Employee SET type=?, empName=?, empAddress=? , joinDate=? , joinTime=? ,salary=? WHERE empId=?");
        stm.setObject(1,e1.getType());
        stm.setObject(2,e1.getEmpName());
        stm.setObject(3,e1.getEmpAddress());
        stm.setObject(4,e1.getJoinDate());
        stm.setObject(5,e1.getJoinTime());
        stm.setObject(6,e1.getSalary());
        stm.setObject(7, e1.getEmpId());

        return stm.executeUpdate()>0;
    }

    public List<String> loadEmployeeIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }
}
