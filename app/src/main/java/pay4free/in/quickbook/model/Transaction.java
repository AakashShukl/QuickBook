package pay4free.in.quickbook.model;

/**
 * Created by AAKASH on 27-01-2018.
 */

public class Transaction {
    private boolean status;
    private String mobile,transaction_id,name;
    private boolean isFromOrder;
    private int id;
    public Transaction()
    {

    }

    public Transaction(boolean status, String mobile, String transaction_id, int id, boolean isFromOrder, String name) {
        this.status = status;
        this.mobile = mobile;
        this.transaction_id = transaction_id;
        this.id = id;
        this.isFromOrder = isFromOrder;
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsFromOrder() {
        return isFromOrder;
    }

    public void setIsFromOrder(Boolean isFromOrder) {
        this.isFromOrder = isFromOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
