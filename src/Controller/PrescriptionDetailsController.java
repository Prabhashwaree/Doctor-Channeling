package Controller;

import DB.DbConnection;
import Model.Prescription;
import Model.PrescriptionDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionDetailsController {
    public ArrayList<PrescriptionDetails> getAllPrescriptionDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Prescription Detail`");
        ResultSet rst = stm.executeQuery();
        ArrayList<PrescriptionDetails> prescription = new ArrayList<>();
        while (rst.next()) {
            prescription.add(new PrescriptionDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            ));
        }
        return prescription;
    }

    public boolean newPrescriptionDetails(PrescriptionDetails p1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO `Prescription Detail` VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,p1.getDisease());
        stm.setObject(2,p1.getPrescriptionNo());
        stm.setObject(3,p1.getTestId());

        return stm.executeUpdate()>0;
    }

    public boolean refrashPrescriptionDetails(PrescriptionDetails p1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Prescription Detail` SET disease=?, testId=? WHERE presNo=?");
        stm.setObject(1,p1.getDisease());
        stm.setObject(2,p1.getTestId());
        stm.setObject(3,p1.getPrescriptionNo());

        return stm.executeUpdate()>0;
    }

    public boolean removePrescriptionDetails(String toString) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Prescription Detail` WHERE presNo='"+toString+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public PrescriptionDetails getPrescriptionDetails(String prescriptionId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM `Prescription Detail` WHERE presNo=?");
        stm.setObject(1,prescriptionId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new PrescriptionDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );

        } else {
            return null;
        }
    }
}
