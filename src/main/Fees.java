package main;

import java.util.Date;

/**
 * Created by isaac on 2/20/2017.
 */
public class Fees {
    String first;
    String last;
    String name;
    String admNo;
    double feesPaid;
    double feesAmount;
    double balance;
    Date time;
    public Fees() {
        this.first="isaac";
        this.last="tunduny";
        this.admNo="asa";
        this.name=first+" "+last;
        this.balance=0.0;
        this.feesAmount=0.0;
        this.feesPaid=0.0;
        this.time=null;
    }
    public Fees(String first,String last,String adm,Date date,double feesAmount,double feesPaid) {
        this.first=first;
        this.last=last;
        this.admNo=adm;
        this.name=first+" "+last;
        this.balance=feesAmount-feesPaid;
        this.feesAmount=feesAmount;
        this.feesPaid=feesPaid;
        this.time=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getAdmNo() {
        return admNo;
    }

    public void setAdmNo(String admNo) {
        this.admNo = admNo;
    }

    public double getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(double feesPaid) {
        this.feesPaid = feesPaid;
    }

    public double getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(double feesAmount) {
        this.feesAmount = feesAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
