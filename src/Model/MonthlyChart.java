package Model;

public class MonthlyChart {
    private double week1=0.0;
    private double week2=0.0;
    private double week3=0.0;
    private double week4=0.0;

    public MonthlyChart() {
    }

    public MonthlyChart(double week1, double week2, double week3, double week4) {
        this.setWeek1(week1);
        this.setWeek2(week2);
        this.setWeek3(week3);
        this.setWeek4(week4);
    }

    public double getWeek1() {
        return week1;
    }

    public void setWeek1(double week1) {
        this.week1 = week1;
    }

    public double getWeek2() {
        return week2;
    }

    public void setWeek2(double week2) {
        this.week2 = week2;
    }

    public double getWeek3() {
        return week3;
    }

    public void setWeek3(double week3) {
        this.week3 = week3;
    }

    public double getWeek4() {
        return week4;
    }

    public void setWeek4(double week4) {
        this.week4 = week4;
    }

    @Override
    public String toString() {
        return "MonthlyChart{" +
                "week1=" + week1 +
                ", week2=" + week2 +
                ", week3=" + week3 +
                ", week4=" + week4 +
                '}';
    }
    public String getTotal(){
        return String.valueOf(week1+week2+week3+week4);
    }
}
