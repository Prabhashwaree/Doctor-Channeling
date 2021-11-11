package Model;

public class AnnuallyDataModel {
    private String date;
    private double cost;

    public AnnuallyDataModel() {
    }

    public AnnuallyDataModel(String date, double cost) {
        this.setDate(date);
        this.setCost(cost);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "AnnuallyDataModel{" +
                "date='" + date + '\'' +
                ", cost=" + cost +
                '}';
    }
}
