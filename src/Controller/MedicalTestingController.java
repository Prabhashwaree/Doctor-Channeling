package Controller;

import DB.DbConnection;
import Model.Appointment;
import Model.MedicalTesting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DB.DbConnection.getInstance;

public class MedicalTestingController {


    public static List<MedicalTesting> searchTesting(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM `Medical Testing` WHERE testId  LIKE '%"+value+"%' || paymentId LIKE '%"+value+"%' ");
        ResultSet rst = pstm.executeQuery();
        List<MedicalTesting> test = new ArrayList<>();
        while (rst.next()) {
            test.add(new MedicalTesting(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }

        return test;
    }

    public ArrayList<MedicalTesting> getAllTesting() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Medical Testing`");
        ResultSet rst = stm.executeQuery();
        ArrayList<MedicalTesting> test = new ArrayList<>();
        while (rst.next()) {
            test.add(new MedicalTesting(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return test;
    }

    public boolean newTesting(MedicalTesting p1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO `Medical Testing` VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,p1.getTestingId());
        stm.setObject(2,p1.getDate());
        stm.setObject(3,p1.getTime());
        stm.setObject(4,p1.getType());
        stm.setObject(5,p1.getTestingPrize());
        stm.setObject(6,p1.getPaymentId());
        stm.setObject(7,p1.getPresNo());

        return stm.executeUpdate()>0;
    }

    public boolean refrashTesting(MedicalTesting p1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Medical Testing` SET date=?, time=?, type=? , testPrize=?, paymentId=?, presNo=? WHERE testId=?");
        stm.setObject(1,p1.getDate());
        stm.setObject(2,p1.getTime());
        stm.setObject(3,p1.getType());
        stm.setObject(4,p1.getTestingPrize());
        stm.setObject(5,p1.getPaymentId());
        stm.setObject(6,p1.getPresNo());
        stm.setObject(7,p1.getTestingId());

        return stm.executeUpdate()>0;
    }

    public boolean removeTesting(String text) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Medical Testing` WHERE testId='"+text+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public MedicalTesting getTesting(String test) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM `Medical Testing` WHERE testId=?");
        stm.setObject(1,test);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new MedicalTesting(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7)
            );

        } else {
            return null;
        }
    }

    public String getTestingId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT * FROM `Medical Testing` ORDER BY testId DESC LIMIT 1"

                ).executeQuery();
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "T-00"+tempId;
            }else if(tempId<99){
                return "T-0"+tempId;
            }else{
                return "T-"+tempId;
            }

        }else {
            return "T-001";

        }
    }

    public List<String> loadTestingIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = getInstance().getConnection().prepareStatement("SELECT * FROM `Medical Testing`").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }
}
