package pay4free.in.quickbook.model;

/**
 * Created by AAKASH on 17-01-2018.
 */

public class PersonalData {
    String aadharcardnumber;
    String name;
    String email;
    String mobile;
    String passsword;
    int nbooked;
    public PersonalData()
    {
    }

    public PersonalData(String aadharcardnumber, String name, String email, String mobile, String passsword,int nbooked) {
        this.aadharcardnumber = aadharcardnumber;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.passsword = passsword;
        this.nbooked=nbooked;
    }

    public int getNbooked() {
        return nbooked;
    }

    public void setNbooked(int nbooked) {
        this.nbooked = nbooked;
    }

    public String getAadharcardnumber() {
        return aadharcardnumber;
    }

    public void setAadharcardnumber(String aadharcardnumber) {
        this.aadharcardnumber = aadharcardnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
}
