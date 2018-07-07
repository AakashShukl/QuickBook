package pay4free.in.quickbook.model;

/**
 * Created by AAKASH on 05-01-2018.
 */

public class Cars {
    private String Name;
    private String Image;
    private String City;
    private int VechicleId;
    private boolean booked;

    public String phoneno,Vehicleno;
    public String cid;

    public Cars(String name, String image, String city, int vechicleId, boolean booked, String phoneno, String vehicleno, String cid) {
        Name = name;
        Image = image;
        City = city;
        VechicleId = vechicleId;
       this.booked = booked;
       this.phoneno = phoneno;
        Vehicleno = vehicleno;
        this.cid = cid;
    }



    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getVechicleId() {
        return VechicleId;
    }

    public void setVechicleId(int vechicleId) {
        VechicleId = vechicleId;
    }

    public Cars()
    {

    }

    public Cars(String name, String image) {
        Name = name;
        Image = image;

    }


    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(String image) {
        Image = image;
    }
}

