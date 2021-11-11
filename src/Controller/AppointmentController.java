package Controller;

import DB.DbConnection;
import Model.Appointment;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {
    public static String getStatus(String id){
        try {
            ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT status FROM Appointment WHERE appointId='" + id + "'").executeQuery();
            if(resultSet.next()){
                return resultSet.getString(1);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void changeStatusById(String status,String id) {
        try {
            System.out.println(status+" "+id);
            DbConnection.getInstance().getConnection().prepareStatement("UPDATE Appointment SET status='"+status+"' WHERE appointId='"+id+"'").executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveAppointment(Appointment a) throws SQLException, ClassNotFoundException{
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Appointment VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,a.getAppointmentId());
        stm.setObject(2,a.getDisease());
        stm.setObject(3,a.getDate());
        stm.setObject(4,a.getTime());
        stm.setObject(5,a.getnIC());
        stm.setObject(6,a.getDrId());
        stm.setObject(7,"Active");


        return stm.executeUpdate()>0;
    }
    public static List<Appointment> searchAppointment(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Appointment WHERE appointId  LIKE '%"+value+"%' || nIC LIKE '%"+value+"%' ");
        ResultSet rst = pstm.executeQuery();
        List<Appointment> appointments = new ArrayList<>();
        while (rst.next()) {
            appointments.add(new Appointment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }

        return appointments;
    }
    public String getAppointmentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT * FROM Appointment ORDER BY appointId DESC LIMIT 1"

                ).executeQuery();
        if (rst.next()){
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "A-00"+tempId;
            }else if(tempId<99){
                return "A-0"+tempId;
            }else{
                return "A-"+tempId;
            }

        }else {
            return "A-001";

        }

    }


    public boolean updateAppointment(Appointment a1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Appointment SET disease=?, date=?, time=?, nIC=?, drId=? WHERE appointId=?");
        stm.setObject(1,a1.getDisease());
        stm.setObject(2,a1.getDate());
        stm.setObject(3,a1.getTime());
        stm.setObject(4,a1.getnIC());
        stm.setObject(5,a1.getDrId());
        stm.setObject(6,a1.getAppointmentId());

        return stm.executeUpdate()>0;
    }

    public Appointment getAppointment(String aId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Appointment WHERE appointId=?");
        stm.setObject(1, aId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Appointment(
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

    public ArrayList<Appointment> getAllAppointment() {
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Appointment");
            ResultSet rst = stm.executeQuery();
            ArrayList<Appointment> appointment = new ArrayList<>();
            while (rst.next()) {
                appointment.add(new Appointment(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6)
                ));
            }
            return appointment;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean deleteAppointment(String text) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Appointment WHERE nIC='" + text + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
