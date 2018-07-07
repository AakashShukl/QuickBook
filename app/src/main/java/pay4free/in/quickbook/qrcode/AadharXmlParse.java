package pay4free.in.quickbook.qrcode;

/**
 * Created by AAKASH on 25-02-2018.
 */


import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AadharXmlParse {
    // We don't use namespaces
    private static final String ns = null;



    public void parse(String xmlContent) throws XmlPullParserException, IOException {
        InputStream in = new ByteArrayInputStream(xmlContent.getBytes());

        AadharCard.originalXML = xmlContent;
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            readFeed(parser);
        } finally {
            in.close();
        }
    }

    private void readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {

    	/*
    	 * PrintLetterBarcodeData uid=""
name=""
gender="M"
yob="1991"
house=""
street=""
vtc=""
dist=""
state=""
pc=""
    	 */
        parser.require(XmlPullParser.START_TAG, ns, "PrintLetterBarcodeData");
        AadharCard.uid = parser.getAttributeValue(null, "uid");//
        AadharCard.name =""+parser.getAttributeValue(null, "name");// F  L
        AadharCard.gender = parser.getAttributeValue(null, "gender"); // M F
        AadharCard.yob = parser.getAttributeValue(null, "yob");// Year
        AadharCard.co =""+parser.getAttributeValue(null, "co");
        AadharCard.house =""+parser.getAttributeValue(null, "house"); //
        AadharCard.lm = parser.getAttributeValue(null, "lm");
        AadharCard.loc = parser.getAttributeValue(null, "loc");
        AadharCard.vtc = parser.getAttributeValue(null, "vtc"); //
        AadharCard.po = parser.getAttributeValue(null, "po");
        AadharCard.dist =""+parser.getAttributeValue(null, "dist"); //
        AadharCard.subdist = parser.getAttributeValue(null, "subdist");
        AadharCard.state = parser.getAttributeValue(null, "state"); //
        AadharCard.pincode =""+parser.getAttributeValue(null, "pc"); //
        AadharCard.dob =""+parser.getAttributeValue(null, "dob");
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
