package pay4free.in.quickbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import info.hoang8f.widget.FButton;
import pay4free.in.quickbook.model.Bill;
import pay4free.in.quickbook.model.PersonalData;
import pay4free.in.quickbook.model.Phone;
import pay4free.in.quickbook.qrcode.AadharCard;
import pay4free.in.quickbook.qrcode.AadharXmlParse;
import pay4free.in.quickbook.qrcode.Qrcode;

public class Main3Activity extends AppCompatActivity {
    EditText inputname, textView, scan, inputaadhar, email, password;
    FButton button, scanaadhar;
    FirebaseDatabase database;
    DatabaseReference Request;
    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        database = FirebaseDatabase.getInstance();
        Request = database.getReference("PersonalDetails");

        inputaadhar = (EditText) findViewById(R.id.input_aadhar);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        inputname = (EditText) findViewById(R.id.input_name);
        textView = (EditText) findViewById(R.id.Number);
        scan = (EditText) findViewById(R.id.scan);
        textView.setText(Phone.getPhone());
        button = (FButton) findViewById(R.id.create);
        scanaadhar = (FButton) findViewById(R.id.aadharscan);
        scanaadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this, Qrcode.class);
                //intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(TextUtils.isEmpty(textView.getText()) || TextUtils.isEmpty(inputaadhar.getText()) || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(password.getText()) || TextUtils.isEmpty(inputname.getText()) || TextUtils.isEmpty(textView.getText()) || TextUtils.isEmpty(scan.getText()))) {
                    if (s.equals(inputaadhar.getText().toString())) {
                        PersonalData personalData = new PersonalData(s, inputname.getText().toString(), email.getText().toString(), textView.getText().toString(), password.getText().toString(), 0);
                        Request.child(textView.getText().toString()).setValue(personalData);

                        Bill bill = new Bill(inputname.getText().toString(), email.getText().toString());
                        startActivity(new Intent(Main3Activity.this, Main2Activity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter a valid aadhar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Enter all details please", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Intent intent = getIntent();
        s = intent.getStringExtra("aadhar");
        scan.setText(s);


    }



    }

