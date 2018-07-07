package pay4free.in.quickbook;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import pay4free.in.quickbook.Common.Common;
import pay4free.in.quickbook.Notification.Notification;
import pay4free.in.quickbook.Remote.MyApiService;
import pay4free.in.quickbook.model.MyResponse;
import pay4free.in.quickbook.model.Phone;
import pay4free.in.quickbook.model.RandomID;
import pay4free.in.quickbook.model.Sender;
import pay4free.in.quickbook.model.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Agent extends AppCompatActivity {
    ProgressDialog progressDialog;
    String data;
    MyApiService myApiService;
    TextView note,id,random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        random=(TextView)findViewById(R.id.random);
        id=(TextView)findViewById(R.id.id);
        note=(TextView)findViewById(R.id.note);
        myApiService = Common.getFCMService();
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Light.ttf");
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Generating Token,Please Wait");
        progressDialog.show();
        Random rand = new Random();
        String randomString = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
        data = hashCal("SHA-256", randomString).substring(0, 20);
       id.setText(data);
        random.setTypeface(typeface);
        id.setTypeface(typeface);
        note.setTypeface(typeface);
        RandomID.id=data;

        sendNotification("","7000569010");


    }


    private void sendNotification(final String s, String mPhone) {
        if (mPhone != null) {
            progressDialog.dismiss();
            DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
            final Query data = tokens.orderByKey().equalTo(mPhone);
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        Token serverToken = postsnapshot.getValue(Token.class);
                        Notification notification = new Notification(Phone.getPhone(), "RandomID "+RandomID.id+"");
                        if (serverToken.getToken() != null) {
                            Sender content = new Sender(serverToken.getToken(), notification);
                            myApiService.sendNotification(content).enqueue(new Callback<MyResponse>() {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                    if (response.body().success == 1) {
                                        //  Toast.makeText(getApplicationContext(), "Your Trip is Schedule", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<MyResponse> call, Throwable t) {

                                }
                            });

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }



    public String hashCal(String type, String str) {
        byte[] hashSequence = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashSequence);
            byte messageDigest[] = algorithm.digest();

            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1)
                    hexString.append("0");
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException NSAE) {
        }
        return hexString.toString();
    }

}
