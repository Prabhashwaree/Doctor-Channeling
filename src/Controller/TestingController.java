package Controller;

import DB.DbConnection;
import Model.TestingDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DB.DbConnection.getInstance;

public class TestingController {
    public static List<TestingDetails> searchTesting(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM `Testing Details` WHERE tDtestId LIKE '%"+value+"%' || tDtestId LIKE '%"+value+"%' ");
        ResultSet rst = pstm.executeQuery();
        List<TestingDetails> test = new ArrayList<>();
        while (rst.next()) {
            test.add(new TestingDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }

        return test;
    }

    public ArrayList<TestingDetails> getAllTesting() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Testing Details`");
        ResultSet rst = stm.executeQuery();
        ArrayList<TestingDetails> test = new ArrayList<>();
        while (rst.next()) {
            test.add(new TestingDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return test;
    }



    public boolean newTesting(TestingDetails t1) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT 1 FROM " +
                "`Testing Details` WHERE tDtestId=?");
        statement.setString(1,t1.getTestId());
        ResultSet rst=statement.executeQuery();
        if(rst.next()){
            return false;
        }
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO `Testing Details` VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,t1.getDate());
        stm.setObject(2,t1.getTime());
        stm.setObject(3,t1.getResult());
        stm.setObject(4,t1.getRang());
        stm.setObject(5,t1.getnIC());
        stm.setObject(6,t1.getTestId());

        return stm.executeUpdate()>0;
    }

    public boolean refrashTesting(TestingDetails p1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Testing Details` SET `range`=? , date=?, time=?, result=? , tDNIC=? WHERE tDtestId=?");
        stm.setObject(1,p1.getDate());
        stm.setObject(2,p1.getTime());
        stm.setObject(3,p1.getResult());
        stm.setObject(4,p1.getRang());
        stm.setObject(5,p1.getnIC());
        stm.setObject(6,p1.getTestId());

        return stm.executeUpdate()>0;
    }

    public boolean removeTesting(String text) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM `Testing Details` WHERE tDtestId='"+text+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public List<String> loadTestingIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = getInstance().getConnection().prepareStatement("SELECT * FROM `Testing Details`").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public TestingDetails getTesing(String nIC) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM `Testing Details` WHERE tDtestId=?");
        stm.setObject(1, nIC);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new TestingDetails(
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
}
