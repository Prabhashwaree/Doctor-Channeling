package Model;

public class AnnuallyDataChart {
    private double month1=0.0;
    private double month2=0.0;
    private double month3=0.0;
    private double month4=0.0;
    private double month5=0.0;
    private double month6=0.0;
    private double month7=0.0;
    private double month8=0.0;
    private double month9=0.0;
    private double month10=0.0;
    private double month11=0.0;
    private double month12=0.0;

    public AnnuallyDataChart() {
    }

    public AnnuallyDataChart(double month1, double month2, double month3, double month4, double month5, double month6, double month7, double month8, double month9, double month10, double month11, double month12) {
        this.setMonth1(month1);
        this.setMonth2(month2);
        this.setMonth3(month3);
        this.setMonth4(month4);
        this.setMonth5(month5);
        this.setMonth6(month6);
        this.setMonth7(month7);
        this.setMonth8(month8);
        this.setMonth9(month9);
        this.setMonth10(month10);
        this.setMonth11(month11);
        this.setMonth12(month12);
    }

    public String getTotal() {
        return String.valueOf(getMonth1() + getMonth2() + getMonth3() + getMonth4() + getMonth5() + getMonth6() + getMonth7() + getMonth8() + getMonth9() + getMonth10() + getMonth11() + getMonth12());
    }

    public double getMonth1() {
        return month1;
    }

    public void setMonth1(double month1) {
        this.month1 = month1;
    }

    public double getMonth2() {
        return month2;
    }

    public void setMonth2(double month2) {
        this.month2 = month2;
    }

    public double getMonth3() {
        return month3;
    }

    public void setMonth3(double month3) {
        this.month3 = month3;
    }

    public double getMonth4() {
        return month4;
    }

    public void setMonth4(double month4) {
        this.month4 = month4;
    }

    public double getMonth5() {
        return month5;
    }

    public void setMonth5(double month5) {
        this.month5 = month5;
    }

    public double getMonth6() {
        return month6;
    }

    public void setMonth6(double month6) {
        this.month6 = month6;
    }

    public double getMonth7() {
        return month7;
    }

    public void setMonth7(double month7) {
        this.month7 = month7;
    }

    public double getMonth8() {
        return month8;
    }

    public void setMonth8(double month8) {
        this.month8 = month8;
    }

    public double getMonth9() {
        return month9;
    }

    public void setMonth9(double month9) {
        this.month9 = month9;
    }

    public double getMonth10() {
        return month10;
    }

    public void setMonth10(double month10) {
        this.month10 = month10;
    }

    public double getMonth11() {
        return month11;
    }

    public void setMonth11(double month11) {
        this.month11 = month11;
    }

    public double getMonth12() {
        return month12;
    }

    public void setMonth12(double month12) {
        this.month12 = month12;
    }
}
