

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
    String time;

    public Fees() {
        this.first = "isaac";
        this.last = "tunduny";
        this.admNo = "asa";
        this.name = first + " " + last;
        this.balance = 0.0;
        this.feesAmount = 0.0;
        this.feesPaid = 0.0;
        this.time = null;
    }

    /**
     * @param first
     * @param last
     * @param adm
     * @param date
     * @param feesAmount
     * @param feesPaid
     */
    public Fees(String first, String last, String adm, String date, double feesAmount, double feesPaid) {
        this.first = first;
        this.last = last;
        this.admNo = adm;
        this.name = first + " " + last;
        this.balance = feesAmount - feesPaid;
        this.feesAmount = feesAmount;
        this.feesPaid = feesPaid;
        this.time = date;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return
     */
    public String getAdmNo() {
        return admNo;
    }

    /**
     * @param admNo
     */
    public void setAdmNo(String admNo) {
        this.admNo = admNo;
    }

    /**
     * @return
     */
    public double getFeesPaid() {
        return feesPaid;
    }

    /**
     * @param feesPaid
     */
    public void setFeesPaid(double feesPaid) {
        this.feesPaid = feesPaid;
    }

    /**
     * @return
     */
    public double getFeesAmount() {
        return feesAmount;
    }

    /**
     * @param feesAmount
     */
    public void setFeesAmount(double feesAmount) {
        this.feesAmount = feesAmount;
    }

    /**
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }
}
