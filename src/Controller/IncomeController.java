package Controller;

import DB.DbConnection;
import Model.*;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IncomeController {
    public static DailyChart getDailyIncome(){
        ArrayList<DailyData> dailyData = new ArrayList<>();
        try {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT time,total FROM Payment WHERE date='"+format.format(date)+"'").executeQuery();
            while (resultSet.next()){
                dailyData.add(new DailyData(
                        resultSet.getString(1),
                        resultSet.getDouble(2)
                        //Double.parseDouble(resultSet.getString(4))
                ));
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        DailyChart dailyChartData = new DailyChart();

        for (DailyData data:dailyData) {
            int i = Integer.parseInt(data.getTime().split(":")[0]);
            if(i>=0 & i<2){
                dailyChartData.setHour2(dailyChartData.getHour2()+data.getCost());
            }else if (i>=2 & i<4){
                dailyChartData.setHour4(dailyChartData.getHour4()+data.getCost());
            }else if (i>=4 & i<6){
                dailyChartData.setHour6(dailyChartData.getHour6()+data.getCost());
            }else if (i>=6 & i<8){
                dailyChartData.setHour8(dailyChartData.getHour8()+data.getCost());
            }else if (i>=8 & i<10){
                dailyChartData.setHour10(dailyChartData.getHour10()+data.getCost());
            }else if (i>=10 & i<12){
                dailyChartData.setHour12(dailyChartData.getHour12()+data.getCost());
            }else if (i>=12 & i<14){
                dailyChartData.setHour14(dailyChartData.getHour14()+data.getCost());
            }else if (i>=14 & i<16){
                dailyChartData.setHour16(dailyChartData.getHour16()+data.getCost());
            }else if (i>=16 & i<18){
                dailyChartData.setHour18(dailyChartData.getHour18()+data.getCost());
            }else if (i>=18 & i<20){
                dailyChartData.setHour20(dailyChartData.getHour20()+data.getCost());
            }else if (i>=20 & i<22){
                dailyChartData.setHour22(dailyChartData.getHour22()+data.getCost());
            }else{
                dailyChartData.setHour24(dailyChartData.getHour24()+data.getCost());
            }
        }
        return dailyChartData;
    }


    public static MonthlyChart getMonthlyIncome(){
        ArrayList<MonthlyData> monthlyData = new ArrayList<>();
        try {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT date,total FROM Payment WHERE date LIKE '" + format.format(date) + "%'").executeQuery();
            while (resultSet.next()){
                monthlyData.add(new MonthlyData(
                        resultSet.getString(1),
                        Double.parseDouble(resultSet.getString(2))
                ));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        MonthlyChart monthlyChartData = new MonthlyChart();

        for (MonthlyData data:monthlyData) {
            int i = Integer.parseInt(data.getDate().split("-")[2]);

            if(i<=7){
                monthlyChartData.setWeek1(monthlyChartData.getWeek1()+data.getCost());
            }else if(i>7 & i<=14){
                monthlyChartData.setWeek2(monthlyChartData.getWeek2()+data.getCost());
            }else if (i>14 & i<=21){
                monthlyChartData.setWeek3(monthlyChartData.getWeek3()+data.getCost());
            }else if(i>21){
                monthlyChartData.setWeek4(monthlyChartData.getWeek4()+data.getCost());
            }
        }

        return monthlyChartData;

    }


    public static AnnuallyDataChart getAnnuallyIncome() {
        ArrayList<AnnuallyDataModel> annuallyData = new ArrayList<>();
        try {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT date,total FROM Payment WHERE date LIKE '" + format.format(date) + "%'").executeQuery();
            while (resultSet.next()){
                annuallyData.add(new AnnuallyDataModel(
                        resultSet.getString(1),
                        Double.parseDouble(resultSet.getString(2))
                ));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AnnuallyDataChart annuallyChartData = new AnnuallyDataChart();

        for (AnnuallyDataModel data:annuallyData) {
            int i = Integer.parseInt(data.getDate().split("-")[1]);
            switch (i){
                case 1:annuallyChartData.setMonth1(annuallyChartData.getMonth1()+data.getCost());break;
                case 2:annuallyChartData.setMonth2(annuallyChartData.getMonth2()+data.getCost());break;
                case 3:annuallyChartData.setMonth3(annuallyChartData.getMonth3()+data.getCost());break;
                case 4:annuallyChartData.setMonth4(annuallyChartData.getMonth4()+data.getCost());break;
                case 5:annuallyChartData.setMonth5(annuallyChartData.getMonth5()+data.getCost());break;
                case 6:annuallyChartData.setMonth6(annuallyChartData.getMonth6()+data.getCost());break;
                case 7:annuallyChartData.setMonth7(annuallyChartData.getMonth7()+data.getCost());break;
                case 8:annuallyChartData.setMonth8(annuallyChartData.getMonth8()+data.getCost());break;
                case 9:annuallyChartData.setMonth9(annuallyChartData.getMonth9()+data.getCost());break;
                case 10:annuallyChartData.setMonth10(annuallyChartData.getMonth10()+data.getCost());break;
                case 11:annuallyChartData.setMonth11(annuallyChartData.getMonth11()+data.getCost());break;
                case 12:annuallyChartData.setMonth12(annuallyChartData.getMonth12()+data.getCost());break;
            }
        }

        return annuallyChartData;

    }


}
