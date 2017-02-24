/**
 * Created by isaac on 2/19/2017.
 */
public class Students {
    private String adm;
    private String fname;
    private String lname;
    private String email;

    /**
     *
     */
    public Students() {
        this.adm = "";
        this.fname = "";
        this.lname = "";
        this.email = "";
    }

    /**
     * @param adm
     * @param fname
     * @param lname
     * @param email
     */
    public Students(String adm, String fname, String lname, String email) {
        this.adm = adm;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    /**
     * @return
     */
    public String getAdm() {
        return adm;
    }

    /**
     * @param adm
     */
    public void setAdm(String adm) {
        this.adm = adm;
    }

    /**
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
