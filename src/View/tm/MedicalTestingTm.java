package View.tm;

public class MedicalTestingTm {
    private String testingId;
    private String date;
    private String time;
    private String type;
    private Double testingPrize;
    private String paymentId;
    private String presNo;

    public MedicalTestingTm() {
    }

    public MedicalTestingTm(String testingId, String date, String time, String type, Double testingPrize, String paymentId, String presNo) {
        this.setTestingId(testingId);
        this.setDate(date);
        this.setTime(time);
        this.setType(type);
        this.setTestingPrize(testingPrize);
        this.setPaymentId(paymentId);
        this.setPresNo(presNo);
    }

    public String getTestingId() {
        return testingId;
    }

    public void setTestingId(String testingId) {
        this.testingId = testingId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTestingPrize() {
        return testingPrize;
    }

    public void setTestingPrize(Double testingPrize) {
        this.testingPrize = testingPrize;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPresNo() {
        return presNo;
    }

    public void setPresNo(String presNo) {
        this.presNo = presNo;
    }

    @Override
    public String toString() {
        return "MedicalTestingTm{" +
                "testingId='" + testingId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", testingPrize=" + testingPrize +
                ", paymentId='" + paymentId + '\'' +
                ", presNo='" + presNo + '\'' +
                '}';
    }
}
