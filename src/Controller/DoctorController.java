package Controller;

import DB.DbConnection;
import Model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    public Doctor getDoctor(String doctorId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Doctor WHERE drId=?");
        stm.setObject(1,doctorId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Doctor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );

        } else {
            return null;
        }
    }
    public List<String> getDoctorId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Doctor").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public boolean removeDoctor(String text) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Doctor WHERE drId='"+text+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }

    }


    public Object getAllDoctor() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Doctor");
        ResultSet rst = stm.executeQuery();
        ArrayList<Doctor> doctor = new ArrayList<>();
        while (rst.next()) {
            doctor.add(new Doctor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return doctor;
    }

    public boolean refrashDoctor(Doctor d1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Doctor SET drName=?, contactNo=?, post=? , email=? WHERE drId=?");
        stm.setObject(1,d1.getDrName());
        stm.setObject(2,d1.getContactNo());
        stm.setObject(3,d1.getPost());
        stm.setObject(4,d1.getEmail());
        stm.setObject(5,d1.getDrId());

        return stm.executeUpdate()>0;
    }

    public boolean newDoctor(Doctor d1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Doctor VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,d1.getDrId());
        stm.setObject(2,d1.getDrName());
        stm.setObject(3,d1.getContactNo());
        stm.setObject(4,d1.getPost());
        stm.setObject(5,d1.getEmail());

        return stm.executeUpdate()>0;
    }
    public List<String> loadDoctorIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Doctor").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

}
