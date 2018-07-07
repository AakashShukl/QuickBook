package pay4free.in.quickbook.qrcode;

/**
 * Created by AAKASH on 25-02-2018.
 */

public class AadharCard
{
    public static String uid;
    public static String name;
    public static String gender;
    public static String yob;
    public static String co;
    public static String house;
    public static String lm;
    public static String loc;
    public static String vtc;
    public static String po;
    public static String dist;
    public static String subdist;
    public static String state;
    public static String pincode;
    public static String dob;
    public static String originalXML;

    public String getFormattedUID() {
        if (uid.length() == 12) {
            String newUIDString = uid.substring(0, 4) + " " + uid.substring(4, 8) + " " + uid.substring(8, 12);
            return newUIDString;
        }
        return uid;
    }

    public static String getAddress() {
        return house + ", " + lm + ", " + loc + ", " + dist + ", " + subdist + ", " + state + ".\nPincode: " + pincode;
    }
}