package Controller;

import DB.DbConnection;
import View.tm.DshboardAllDetailTableTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DashBoardController {
    public ArrayList<DshboardAllDetailTableTm> getAllDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Payment ");
        ArrayList<DshboardAllDetailTableTm> detailsTms = new ArrayList<>();
        ResultSet set = stm.executeQuery();
        while (set.next()) {
            PreparedStatement stm2 = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Appointment WHERE nIC=?");
            stm2.setObject(1, set.getString(8));
            ResultSet set2 = stm2.executeQuery();
            if (set2.next()) {
                PreparedStatement stm3 = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Patients` WHERE nIC=?");
                stm3.setObject(1, set.getString(8));
                ResultSet set3 = stm3.executeQuery();
                if (set3.next()) {
                    detailsTms.add(new DshboardAllDetailTableTm(
                            set.getString(7),
                            set.getString(8),
                            set3.getString(2),
                            set3.getString(3),
                            set3.getString(8),
                            set2.getString(1),
                            set2.getString(6),
                            set.getDouble(6)
                    ));
                }
            }
        }
        return detailsTms;
    }

}









