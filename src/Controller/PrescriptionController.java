package Controller;

import DB.DbConnection;

import Model.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DB.DbConnection.getInstance;

public class PrescriptionController {
    public List<String> getPrescriptionId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Prescription").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public boolean newPrescription(Prescription p1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Prescription VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,p1.getPresNo());
        stm.setObject(2,p1.getMedicine());
        stm.setObject(3,p1.getDate());
        stm.setObject(4,p1.getTime());
        stm.setObject(5,p1.getDrID());
        stm.setObject(6,p1.getnIC());

        return stm.executeUpdate()>0;
    }

    public Prescription getPrescription(String prescriptionId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Prescription WHERE presNo=?");
        stm.setObject(1,prescriptionId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Prescription(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );

        } else {
            return null;
        }
    }


    public boolean refrashPrescription(Prescription p1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Prescription SET medicine=?, date=?, time=? , drId=?, nIC=? WHERE presNo=?");
        stm.setObject(1,p1.getMedicine());
        stm.setObject(2,p1.getDate());
        stm.setObject(3,p1.getTime());
        stm.setObject(4,p1.getDrID());
        stm.setObject(5,p1.getnIC());
        stm.setObject(6,p1.getPresNo());

        return stm.executeUpdate()>0;
    }

    public boolean removePrescription(String text) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Prescription WHERE presNo='"+text+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Prescription> getAllPrescription() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Prescription");
        ResultSet rst = stm.executeQuery();
        ArrayList<Prescription> prescription = new ArrayList<>();
        while (rst.next()) {
            prescription.add(new Prescription(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return prescription;
    }

    public List<String> loadPrescriptionIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = getInstance().getConnection().prepareStatement("SELECT * FROM Prescription").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }
}
