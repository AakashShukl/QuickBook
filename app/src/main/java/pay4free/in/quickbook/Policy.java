package pay4free.in.quickbook;

import android.app.Activity;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Policy extends Activity {

    private WebView mWebView;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        TextView policy=(TextView)findViewById(R.id.policy);
        policy.setText("Privacy Policy\n" +
                "\u200B\n" +
                "Aakash Shukla built the QwikBook app as a Commercial app. This SERVICE is provided by Aakash Shukla and is intended for use as is.\n" +
                "\n" +
                "This page is used to inform website visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.\n" +
                "\n" +
                "If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy.\n" +
                "\n" +
                "The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at QwikBook unless otherwise defined in this Privacy Policy.\n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "Information Collection and Use\n" +
                "\n" +
                "For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information, such as Name,Email,password,Aadhaar no. So that You can register and enjoy our app inbuilt features.The information that I request is retained on your device and save in database so that u can access your data again.Your data is fully secure with us ,it is not exchange to any other third party.\n" +
                "\n" +
                "User-Data section has all the info of your data,and tells why we need your data.\n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "Link to privacy policy of third party service providers used by the app\n" +
                "\n" +
                "Google Play Services\n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "User Data\n" +
                "\n" +
                "Since Qwik-Book is the vehicle rental booking application thus for accessing our listed vehicle ,user need to register first which need aadhar number so that we can secure booked vehicle.Other information such as user phone number,email are for authenticating user so that he/she can login and access listed vehicle.IT is purely safe for user to share their data,we gurantee that your data is secure with us.\n" +
                "\n" +
                "And if user destroyed their account data will be deleted fully.\n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "Only for vehicle security we are making user to register using aadhaar card..Be secure with our service,your data is fully safe with us.\n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "Log Data\n" +
                "\n" +
                "I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.\n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "Permission\n" +
                "\n" +
                "In order to scan Qrcode or Barcode,i am using Camera of your Phone thus,you must allow Camera Permission for scanning.\n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "Service Providers\n" +
                "\n" +
                "I may employ third-party companies and individuals due to the following reasons:\n" +
                "\n" +
                "To facilitate our Service;\n" +
                "\n" +
                "To provide the Service on our behalf;\n" +
                "\n" +
                "To perform Service-related services; or\n" +
                "\n" +
                "To assist us in analyzing how our Service is used.\n" +
                "\n" +
                "I want to inform users of this Service that these third parties have access to your Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.\n" +
                "\n" +
                "Security\n" +
                "\n" +
                "I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable.\n" +
                "\n" +
                "\u200B\n" +
                "\n" +
                "Children’s Privacy\n" +
                "\n" +
                "These Services do not address anyone under the age of 18. I do not knowingly collect personally identifiable information from children under 18. In the case I discover that a child under 18 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do necessary actions.\n" +
                "\n" +
                "Changes to This Privacy Policy\n" +
                "\n" +
                "I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page. These changes are effective immediately after they are posted on this page.\n" +
                "\n" +
                "Contact Us\n" +
                "\n" +
                "If you have any questions or suggestions about my Privacy Policy, do not hesitate to contact me.");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}

