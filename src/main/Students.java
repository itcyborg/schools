package main;

/**
 * Created by isaac on 2/19/2017.
 */
public class Students {
    private String fname;
    private String lname;
    private String email;

    public Students(){
        this.fname="";
        this.lname="";
        this.email="";
    }

    public Students(String fname,String lname,String email){
        this.fname=fname;
        this.lname=lname;
        this.email=email;
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
