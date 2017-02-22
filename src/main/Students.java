package main;

/**
 * Created by isaac on 2/19/2017.
 */
public class Students {
    private String adm;
    private String fname;
    private String lname;
    private String email;

    public Students(){
        this.adm = "";
        this.fname="";
        this.lname="";
        this.email="";
    }

    public Students(String adm, String fname, String lname, String email) {
        this.adm = adm;
        this.fname=fname;
        this.lname=lname;
        this.email=email;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
