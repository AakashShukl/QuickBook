package pay4free.in.quickbook.model;

/**
 * Created by AAKASH on 19-10-2017.
 */

public class Offers {
    private String Name;
    private String Image;
    private String offer;

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public Offers()

    {

    }

    public Offers(String name, String image,String Offer) {

        Name = name;
        Image = image;
        offer=Offer;
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
