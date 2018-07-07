package pay4free.in.quickbook;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import pay4free.in.quickbook.Common.Common;
import pay4free.in.quickbook.Dialog.DateTime;
import pay4free.in.quickbook.Notification.Notification;
import pay4free.in.quickbook.Remote.MyApiService;
import pay4free.in.quickbook.model.Cars;
import pay4free.in.quickbook.model.MyResponse;
import pay4free.in.quickbook.model.Phone;
import pay4free.in.quickbook.model.Sender;
import pay4free.in.quickbook.model.Token;
import pay4free.in.quickbook.model.Transaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class AfterBooked extends AppCompatActivity {
    MyApiService myApiService;
    FirebaseDatabase database;
    int id;
    TextView amount,transact,idd,paid;
    double money;
    String carname;
    String name, mobile, txnid, vehicleid, driverphone;
    Boolean status, isFromOrder;
    Transaction transaction;
    DatabaseReference reference;
    Button click;
    Query q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_booked);
        click = (Button) findViewById(R.id.next);
        transact=(TextView)findViewById(R.id.tranaction);
        amount=(TextView)findViewById(R.id.amount);

        myApiService = Common.getFCMService();

      //  id = 0;
       // status = true;
      //  name = "aakash";
      //  txnid = "12345";
      //  mobile = "7000569143";
     //   vehicleid = "21";
        if(getIntent()!=null) {
            money=getIntent().getDoubleExtra("amount",1.00);
            id = getIntent().getIntExtra("id", 0);
            status = getIntent().getBooleanExtra("status", true);
            name = getIntent().getStringExtra("mFirstName");
            mobile = Phone.getPhone();
            txnid = getIntent().getStringExtra("transactionid");
            vehicleid = getIntent().getStringExtra("vechicleid");
            isFromOrder = getIntent().getBooleanExtra("isFromOrder", true);
        }
        amount.setText(money+"");
        transact.setText(txnid);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Light.ttf");
        amount.setTypeface(typeface);
        transact.setTypeface(typeface);
        q = FirebaseDatabase.getInstance().getReference("Vechicle").child(vehicleid);
        transaction = new Transaction(status,mobile,txnid,id,isFromOrder,name);
        sending();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        datatoFirebase();
                    }
                }, 2500);
            }
        });




    }

    private void sending() {
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                      Cars cars=dataSnapshot.getValue(Cars.class);
                        driverphone=cars.getPhoneno();
                        carname=cars.getName();
                        sendNotification(carname,driverphone);



                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        datatoFirebase();
    }

    private void datatoFirebase() {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
       final DatabaseReference reference=database.getReference();
        reference.child("PersonalDetails").child(Phone.getPhone()).child("Success").child(Common.nbooked + 1 + "").setValue(transaction);
        reference.child("Transaction").child("Success").child(Phone.getPhone()).child(Common.nbooked + 1 + "").setValue(transaction);
        reference.child("Vechicle").child(vehicleid).child("booked").setValue(true);
        Query q =  FirebaseDatabase.getInstance().getReference("Vechicle").child(vehicleid);
        q.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // for example: if you're expecting your user's data as an object of the "User" class.
                        Cars user = dataSnapshot.getValue(Cars.class);
                        reference.child("PersonalDetails").child(Phone.getPhone()).child("Booked").child(Common.nbooked + 1 + "").setValue(user);
                        reference.child("PersonalDetails").child(Phone.getPhone()).child("nbooked").setValue(++Common.nbooked);
                        reference.child("Booked").child(Phone.getPhone()).child(Common.nbooked + 1 + "").child(vehicleid).setValue(user);
                        reference.child("Booked").child(Phone.getPhone()).child(Common.nbooked + 1 + "").child(vehicleid).child("PickupTime").setValue(DateTime.pickup);
                        reference.child("Booked").child(Phone.getPhone()).child(Common.nbooked + 1 + "").child(vehicleid).child("DropTime").setValue(DateTime.drop);

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // read query is cancelled.
                    }
                });

    }

    private void sendNotification(final String s, String mPhone) {
        if (mPhone != null) {
            DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
            Query data = tokens.orderByKey().equalTo(mPhone);
            data.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        Token serverToken = postsnapshot.getValue(Token.class);
                        Notification notification = new Notification(s, "NewBooking,"+mobile);
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
}
