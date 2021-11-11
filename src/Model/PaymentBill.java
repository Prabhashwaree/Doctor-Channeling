package Model;

public class PaymentBill {
   private String paymentId;
   private String date;
   private String time;
   private Double chanalingAmount;
   private Double testingAmount;
   private Double total;
   private String empId;
   private String nIC;

   public PaymentBill(String text, String txtDateText, String txtTimeText, String txtChanalingAmountText, String txtTestingAmountText, String txtTotalAmountText, String txtEmpIdText, String txtNICText, String empIdText) {
   }

   public PaymentBill(String paymentId, String date, String time, Double chanalingAmount, Double testingAmount, Double total, String empId, String nIC) {
      this.setPaymentId(paymentId);
      this.setDate(date);
      this.setTime(time);
      this.setChanalingAmount(chanalingAmount);
      this.setTestingAmount(testingAmount);
      this.setTotal(total);
      this.setEmpId(empId);
      this.setnIC(nIC);
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

   public String getnIC() {
      return nIC;
   }

   public void setnIC(String nIC) {
      this.nIC = nIC;
   }

   @Override
   public String toString() {
      return "PaymentBill{" +
              "paymentId='" + paymentId + '\'' +
              ", date='" + date + '\'' +
              ", time='" + time + '\'' +
              ", chanalingAmount=" + chanalingAmount +
              ", testingAmount=" + testingAmount +
              ", total=" + total +
              ", empId='" + empId + '\'' +
              ", nIC='" + nIC + '\'' +
              '}';
   }
}
