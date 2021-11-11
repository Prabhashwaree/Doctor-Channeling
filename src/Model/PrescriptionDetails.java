package Model;

public class PrescriptionDetails {
    private String disease;
    private String prescriptionNo;
    private String testId;

    public PrescriptionDetails() {
    }

    public PrescriptionDetails(String disease, String prescriptionNo, String testId) {
        this.setDisease(disease);
        this.setPrescriptionNo(prescriptionNo);
        this.setTestId(testId);
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPrescriptionNo() {
        return prescriptionNo;
    }

    public void setPrescriptionNo(String prescriptionNo) {
        this.prescriptionNo = prescriptionNo;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
}
