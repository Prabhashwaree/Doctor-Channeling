package View.tm;

public class TestingDetailsTm {
    private String date;
    private String time;
    private String result;
    private String rang;
    private String ptnNIC;
    private String testId;

    public TestingDetailsTm() {
    }

    public TestingDetailsTm(String date, String time, String result, String rang, String ptnNIC, String testId) {
        this.setDate(date);
        this.setTime(time);
        this.setResult(result);
        this.setRang(rang);
        this.setPtnNIC(ptnNIC);
        this.setTestId(testId);
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getPtnNIC() {
        return ptnNIC;
    }

    public void setPtnNIC(String ptnNIC) {
        this.ptnNIC = ptnNIC;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
}
