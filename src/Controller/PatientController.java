package Controller;

import DB.DbConnection;
import Model.Appointment;
import Model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PatientController {
    public boolean savePatient(Patient p) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO `Patients` VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, p.getnIC());
        stm.setObject(2, p.getName());
        stm.setObject(3, p.getContactNo());
        stm.setObject(4, p.getGender());
        stm.setObject(5, p.getAddress());
        stm.setObject(6, p.getDate());
        stm.setObject(7, p.getTime());
        stm.setObject(8, p.getAge());
        stm.setObject(9, p.getEmpId());


        return stm.executeUpdate() > 0;
    }

    public Patient getPatient(String nIC) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM `Patients` WHERE nIC=?");
        stm.setObject(1, nIC);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Patient(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            );

        } else {
            return null;
        }
    }

    public boolean deletePatient(String text) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Patients` WHERE nIC='" + text + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Patient> getAllPatient() {
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Patients`");
            ResultSet rst = stm.executeQuery();
            ArrayList<Patient> patient = new ArrayList<>();
            while (rst.next()) {
                patient.add(new Patient(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getString(7),
                        rst.getString(8),
                        rst.getString(9)
                ));
            }
            return patient;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePatient(Patient p2) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Patients` SET name=?, contactNo=?, gender=?, address=?, date=?, time=?, age=?, empId=? WHERE nIC=?");
        stm.setObject(1, p2.getName());
        stm.setObject(2, p2.getContactNo());
        stm.setObject(3, p2.getGender());
        stm.setObject(4, p2.getAddress());
        stm.setObject(5, p2.getDate());
        stm.setObject(6, p2.getTime());
        stm.setObject(7, p2.getAge());
        stm.setObject(8, p2.getEmpId());
        stm.setObject(9, p2.getnIC());

        return stm.executeUpdate() > 0;
    }

    public List<String> getPatientId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM `Patients`").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }


    public List<String> loadPatientIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Patients`").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public static List<Patient> searchPatient(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM `Patients` WHERE nIC  LIKE '%" + value + "%' || name LIKE '%" + value + "%' ");
        ResultSet rst = pstm.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while (rst.next()) {
            patients.add(new Patient(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }

        return patients;
    }

    public boolean updateCustomer(Patient c1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Patients` SET name=?, contactNo=?, gender=?, address=?,date=?,time=?,age=?,empId=? WHERE nIC=?");
        stm.setObject(1, c1.getName());
        stm.setObject(2, c1.getContactNo());
        stm.setObject(3, c1.getGender());
        stm.setObject(4, c1.getAddress());
        stm.setObject(5, c1.getDate());
        stm.setObject(6, c1.getTime());
        stm.setObject(7, c1.getAge());
        stm.setObject(8, c1.getEmpId());
        stm.setObject(9, c1.getnIC());

        return stm.executeUpdate()>0;
    }
}