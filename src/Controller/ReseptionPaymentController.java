package Controller;

import DB.DbConnection;
import Model.PaymentBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DB.DbConnection.getInstance;

public class ReseptionPaymentController {

    public static List<PaymentBill> searchPayment(String value) throws SQLException, ClassNotFoundException {
        Connection con = getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Payment WHERE paymentId  LIKE '%" + value + "%' || nIC LIKE '%" + value + "%' ");
        ResultSet rst = pstm.executeQuery();
        List<PaymentBill> paymentBills = new ArrayList<>();
        while (rst.next()) {
            paymentBills.add(new PaymentBill(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }

        return paymentBills;

    }

    public boolean savePaymentBill(PaymentBill pa) throws SQLException, ClassNotFoundException {
        Connection con = getInstance().getConnection();
        String query = "INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, pa.getPaymentId());
        stm.setObject(2, pa.getDate());
        stm.setObject(3, pa.getTime());
        stm.setObject(4, pa.getChanalingAmount());
        stm.setObject(5, pa.getTestingAmount());
        stm.setObject(6, pa.getTotal());
        stm.setObject(7, pa.getEmpId());
        stm.setObject(8, pa.getnIC());

        return stm.executeUpdate() > 0;
    }

    public PaymentBill getPayment(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = getInstance()
                .getConnection().prepareStatement("SELECT * FROM Payment WHERE paymentId=?");
        stm.setObject(1, id);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new PaymentBill(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(7),
                    rst.getString(8)
            );

        } else {
            return null;
        }
    }
    public ArrayList<PaymentBill> getAllPayment() {
        try {
            PreparedStatement stm = getInstance().getConnection().prepareStatement("SELECT * FROM Payment");
            ResultSet rst = stm.executeQuery();
            ArrayList<PaymentBill> paymentBills = new ArrayList<>();
            while (rst.next()) {
                paymentBills.add(new PaymentBill(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getDouble(4),
                        rst.getDouble(5),
                        rst.getDouble(6),
                        rst.getString(7),
                        rst.getString(8)
                ));
            }
            return paymentBills;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> loadPaymentIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = getInstance().getConnection().prepareStatement("SELECT * FROM Payment").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    /*public boolean refrashPrescription(PaymentBill p1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Payment SET date=?, time=?, chanalingAmount=? , testingAmount=?, total=? , empId=?, nIC=? WHERE paymentId=?");
        stm.setObject(1, p1.getDate());
        stm.setObject(2, p1.getTime());
        stm.setObject(3, p1.getChanalingAmount());
        stm.setObject(4, p1.getTestingAmount());
        stm.setObject(5, p1.getTotal());
        stm.setObject(6, p1.getEmpId());
        stm.setObject(7, p1.getnIC());
        stm.setObject(8, p1.getPaymentId());

        return stm.executeUpdate()>0;
    }*/

    public String getPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT * FROM Payment ORDER BY paymentId DESC LIMIT 1"

                ).executeQuery();
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "B-00"+tempId;
            }else if(tempId<99){
                return "B-0"+tempId;
            }else{
                return "B-"+tempId;
            }

        }else {
            return "B-001";

        }
    }

    public boolean updatePaymentBill(PaymentBill pa) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Payment SET date=?, time=?, chanalingAmount=?, testingAmount=?, total=?,empId=?,nIC=? WHERE paymentId=?");
        stm.setObject(1,pa.getDate());
        stm.setObject(2,pa.getTime());
        stm.setObject(3,pa.getChanalingAmount());
        stm.setObject(4,pa.getTestingAmount());
        stm.setObject(5,pa.getTotal());
        stm.setObject(6,pa.getEmpId());
        stm.setObject(7,pa.getnIC());
        stm.setObject(8,pa.getPaymentId());

        return stm.executeUpdate()>0;
    }
}


