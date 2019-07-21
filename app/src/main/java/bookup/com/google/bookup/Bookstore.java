package bookup.com.google.bookup;

public class Bookstore
{
    private int bstore_id;
    private String bstore_name;
    private String bstore_website;
    private String bstore_contactNo;
    private String bstore_storeHrs;
    private String bstore_email;
    private String bstore_password;
    private String bstore_confirmPasswrord;

    public Bookstore() {
    }

    public int getBstore_id() {
        return bstore_id;
    }

    public void setBstore_id(int bstore_id) {
        this.bstore_id = bstore_id;
    }

    public Bookstore(int bstore_id, String bstore_name, String bstore_website, String bstore_contactNo, String bstore_storeHrs, String bstore_email, String bstore_password, String bstore_confirmPasswrord) {
        this.bstore_name = bstore_name;
        this.bstore_website = bstore_website;
        this.bstore_contactNo = bstore_contactNo;
        this.bstore_storeHrs = bstore_storeHrs;
        this.bstore_email = bstore_email;
        this.bstore_password = bstore_password;
        this.bstore_confirmPasswrord = bstore_confirmPasswrord;
    }

    public String getBstore_name() {
        return bstore_name;
    }

    public void setBstore_name(String bstore_name) {
        this.bstore_name = bstore_name;
    }

    public String getBstore_website() {
        return bstore_website;
    }

    public void setBstore_website(String bstore_website) {
        this.bstore_website = bstore_website;
    }

    public String getBstore_contactNo() {
        return bstore_contactNo;
    }

    public void setBstore_contactNo(String bstore_contactNo) {
        this.bstore_contactNo = bstore_contactNo;
    }

    public String getBstore_storeHrs() {
        return bstore_storeHrs;
    }

    public void setBstore_storeHrs(String bstore_storeHrs) {
        this.bstore_storeHrs = bstore_storeHrs;
    }

    public String getBstore_email() {
        return bstore_email;
    }

    public void setBstore_email(String bstore_email) {
        this.bstore_email = bstore_email;
    }

    public String getBstore_password() {
        return bstore_password;
    }

    public void setBstore_password(String bstore_password) {
        this.bstore_password = bstore_password;
    }

    public String getBstore_confirmPasswrord() {
        return bstore_confirmPasswrord;
    }

    public void setBstore_confirmPasswrord(String bstore_confirmPasswrord) {
        this.bstore_confirmPasswrord = bstore_confirmPasswrord;
    }
}
