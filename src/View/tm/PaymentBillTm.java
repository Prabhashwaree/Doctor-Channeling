package View.tm;

public class PaymentBillTm {
    private String paymentId;
    private String date;
    private String time;
    private Double chanalingAmount;
    private Double testingAmount;
    private Double total;
    private String empId;
    private String ptnNIC;

    public PaymentBillTm() {
    }

    public PaymentBillTm(String paymentId, String date, String time, Double chanalingAmount, Double testingAmount, Double total, String empId, String ptnNIC) {
        this.setPaymentId(paymentId);
        this.setDate(date);
        this.setTime(time);
        this.setChanalingAmount(chanalingAmount);
        this.setTestingAmount(testingAmount);
        this.setTotal(total);
        this.setEmpId(empId);
        this.setPtnNIC(ptnNIC);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getChanalingAmount() {
        return chanalingAmount;
    }

    public void setChanalingAmount(Double chanalingAmount) {
        this.chanalingAmount = chanalingAmount;
    }

    public Double getTestingAmount() {
        return testingAmount;
    }

    public void setTestingAmount(Double testingAmount) {
        this.testingAmount = testingAmount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPtnNIC() {
        return ptnNIC;
    }

    public void setPtnNIC(String ptnNIC) {
        this.ptnNIC = ptnNIC;
    }
}
