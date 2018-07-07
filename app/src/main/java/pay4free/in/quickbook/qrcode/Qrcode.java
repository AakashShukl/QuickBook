package pay4free.in.quickbook.qrcode;

/**
 * Created by AAKASH on 16-01-2018.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;

import com.google.zxing.Result;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import pay4free.in.quickbook.Main3Activity;


public class Qrcode extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    static final int requestcamera=1;
    String scanResult="";
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(checkpermission())
            {
                Toast.makeText(getApplicationContext(),"Permission is Granted",Toast.LENGTH_SHORT).show();
            }
            else
            {
                requestPermissions(new String[]{CAMERA}, requestcamera);
            }
        }
    }

    private void requestpermissions() {
        ActivityCompat.requestPermissions(this,new String[]{CAMERA}, Qrcode.requestcamera);

    }
    public void onRequestPermissionResult(int requestCode,String permission[],int grantresult[])
    {
        switch (requestCode) {
            case requestcamera:
                if(grantresult.length>0)
                {
                    boolean cameraAccepted=grantresult[0]== PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted)
                    {
                        Toast.makeText(Qrcode.this,"Permission granted",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Qrcode.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                        {
                            if(shouldShowRequestPermissionRationale(CAMERA))
                            {
                                displayalertdialog("You need to allow access for both permissions", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{CAMERA},requestcamera);
                                        }
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }
    public void displayalertdialog(String message, DialogInterface.OnClickListener listener)
    {
        new AlertDialog.Builder(Qrcode.this)
                .setMessage(message).setPositiveButton("OK",listener).setNegativeButton("CANCEL",null).create().show();
    }
    private boolean checkpermission()
    {
        return (ContextCompat.checkSelfPermission(Qrcode.this, CAMERA)== PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(checkpermission())
            {
                if(scannerView==null)
                {
                    scannerView=new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else
            {
                requestpermissions();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

         scanResult=""+result.getText();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Your AadharCard ");
        if(scanResult.length()>12) {
             scanResult = scanResult.substring(68,80);
        }
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Qrcode.this, Main3Activity.class);
                intent.putExtra("aadhar",scanResult);
                startActivity(intent);
                finish();

            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert=builder.create();
        alert.show();

    }



}
