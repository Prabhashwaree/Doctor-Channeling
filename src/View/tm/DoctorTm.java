package View.tm;

public class DoctorTm {
    private String drId;
    private String drName;
    private String contactNo;
    private String post;
    private String email;

    public DoctorTm() {
    }

    public DoctorTm(String drId, String drName, String contactNo, String post, String email) {
        this.setDrId(drId);
        this.setDrName(drName);
        this.setContactNo(contactNo);
        this.setPost(post);
        this.setEmail(email);
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
