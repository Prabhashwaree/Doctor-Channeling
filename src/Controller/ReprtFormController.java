package Controller;

import Model.AnnuallyDataChart;
import Model.DailyChart;
import Model.MonthlyChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public class ReprtFormController {
    public LineChart chartDalyInCome;
    public LineChart AnnualyIncome;
    public LineChart monthlyIncome;

    public void initialize(){
        try {
            loadAnnuallyChart();
            loadMonthlyChart();
            dailyChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void dailyChart() throws SQLException, ClassNotFoundException {
        DailyChart dailyChart = IncomeController.getDailyIncome();
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data<>("2H",dailyChart.getHour2()));
        series.getData().add(new XYChart.Data<>("4H",dailyChart.getHour4()));
        series.getData().add(new XYChart.Data<>("6H",dailyChart.getHour6()));
        series.getData().add(new XYChart.Data<>("8H",dailyChart.getHour8()));
        series.getData().add(new XYChart.Data<>("10H",dailyChart.getHour10()));
        series.getData().add(new XYChart.Data<>("12H",dailyChart.getHour12()));
        series.getData().add(new XYChart.Data<>("14H",dailyChart.getHour14()));
        series.getData().add(new XYChart.Data<>("16H",dailyChart.getHour16()));
        series.getData().add(new XYChart.Data<>("18H",dailyChart.getHour18()));
        series.getData().add(new XYChart.Data<>("20H",dailyChart.getHour20()));
        series.getData().add(new XYChart.Data<>("22H",dailyChart.getHour22()));
        series.getData().add(new XYChart.Data<>("24H",dailyChart.getHour24()));
        chartDalyInCome.getData().add(series);
    }
    private void loadMonthlyChart() {
        MonthlyChart monthlyChart = IncomeController.getMonthlyIncome();

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data<>("Week 1",monthlyChart.getWeek1()));
        series.getData().add(new XYChart.Data<>("Week 2",monthlyChart.getWeek2()));
        series.getData().add(new XYChart.Data<>("Week 3",monthlyChart.getWeek3()));
        series.getData().add(new XYChart.Data<>("Week 4",monthlyChart.getWeek4()));
        monthlyIncome.getData().add(series);
        // totalMonthly.setText(monthlyChartData.getTotal());
    }
    private void loadAnnuallyChart() {
        AnnuallyDataChart annuallyChartData = IncomeController.getAnnuallyIncome();
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data<>("January",annuallyChartData.getMonth1()));
        series.getData().add(new XYChart.Data<>("February",annuallyChartData.getMonth2()));
        series.getData().add(new XYChart.Data<>("March",annuallyChartData.getMonth3()));
        series.getData().add(new XYChart.Data<>("April",annuallyChartData.getMonth4()));
        series.getData().add(new XYChart.Data<>("May",annuallyChartData.getMonth5()));
        series.getData().add(new XYChart.Data<>("June",annuallyChartData.getMonth6()));
        series.getData().add(new XYChart.Data<>("July",annuallyChartData.getMonth7()));
        series.getData().add(new XYChart.Data<>("August",annuallyChartData.getMonth8()));
        series.getData().add(new XYChart.Data<>("September",annuallyChartData.getMonth9()));
        series.getData().add(new XYChart.Data<>("October",annuallyChartData.getMonth10()));
        series.getData().add(new XYChart.Data<>("November",annuallyChartData.getMonth11()));
        series.getData().add(new XYChart.Data<>("December",annuallyChartData.getMonth12()));

        AnnualyIncome.getData().add(series);

        // totalAnnually.setText(annuallyChartData.getTotal());

    }

}
