package pay4free.in.quickbook.model;

/**
 * Created by AAKASH on 22-01-2018.
 */

public class Bill {
    public static String name;
    public static String email;

    public Bill(String name,String email) {


        Bill.name=name;
        Bill.email=email;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Bill.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Bill.email = email;
    }
}
