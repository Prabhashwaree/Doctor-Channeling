package Controller;

import DB.DbConnection;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User");
        ResultSet rst = stm.executeQuery();
        ArrayList<User> user = new ArrayList<>();
        while (rst.next()) {
            user.add(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return user;
    }


    public User getUser(String ep) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM User WHERE empId=?");
        stm.setObject(1,ep);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );

        } else {
            return null;
        }
    }

    public boolean newUser(User p1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO User VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,p1.getEmpId());
        stm.setObject(2,p1.getUserName());
        stm.setObject(3,p1.getPassword());


        return stm.executeUpdate()>0;
    }

    public boolean refreshUser(User u) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE User SET userName=?, password=?  WHERE  empId=?");
        stm.setObject(1,u.getUserName());
        stm.setObject(2,u.getPassword());
        stm.setObject(3,u.getEmpId());


        return stm.executeUpdate()>0;
    }

    public boolean RemoveUser(String userId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM User WHERE empId='"+userId+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public User getUserId(String userId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM User WHERE empId=?");
        stm.setObject(1,userId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );

        } else {
            return null;
        }
    }

    //--------------------------Login---------------------------------------------

    public String getUserType(String UserName,String Password) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT empId FROM User WHERE userName=? AND password=sha1(?)");
        stm.setObject(1,UserName);
        stm.setObject(2,Password);
        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT type FROM Employee WHERE empId=?");
            preparedStatement.setObject(1,rst.getString(1));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString(1);
            }
        }
        return null;
    }

    public List<String> loadUserIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public boolean refrashUser(User p1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE User SET userName=?, password=?  WHERE  empId=?");
        stm.setObject(1,p1.getUserName());
        stm.setObject(2,p1.getPassword());
        stm.setObject(3,p1.getEmpId());

        return stm.executeUpdate()>0;
    }
}















