package pay4free.in.quickbook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import info.hoang8f.widget.FButton;
import pay4free.in.quickbook.Dialog.DateTime;
import pay4free.in.quickbook.model.Cars;
import pay4free.in.quickbook.model.Offers;

public class AutoMobileDetail extends AppCompatActivity {
    String autoid="";
    FirebaseDatabase database;
    DatabaseReference vehicle;
    EditText editText;int c;
    Cars element;
    int hour,rem;
    Typeface typeface;FButton click;String s2;
    Button pay,latter,agent;String s,s1;
    Context context;
    TextView carname,rupees,time,basicfare,basecharges,refundable,amount,payable,finalamount;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView autoimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_mobile_detail);
        database=FirebaseDatabase.getInstance();
        vehicle=database.getReference("Vechicle");
        pay=(Button)findViewById(R.id.pay);
        latter=(Button)findViewById(R.id.paylatter);
        agent=(Button)findViewById(R.id.payagent);
      //  Toast.makeText(getApplicationContext(),"We highly recommend you to choose pay latter or Pay Via Agent",Toast.LENGTH_SHORT).show();
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        autoimage=(ImageView)findViewById(R.id.autoimage);
        carname=(TextView)findViewById(R.id.carname);
        rupees=(TextView)findViewById(R.id.rupees);
        time=(TextView)findViewById(R.id.time);
        basicfare=(TextView)findViewById(R.id.fare);
        basecharges=(TextView)findViewById(R.id.rupees1);
       refundable=(TextView)findViewById(R.id.fare1);
        click=(FButton)findViewById(R.id.apply);
        amount=(TextView)findViewById(R.id.refundable);
        payable=(TextView)findViewById(R.id.payable) ;
        editText=(EditText)findViewById(R.id.offer);
        finalamount=(TextView)findViewById(R.id.paymoney);
        pay.setVisibility(View.INVISIBLE);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Light.ttf");
        carname.setTypeface(typeface);
        rupees.setTypeface(typeface);
        time.setTypeface(typeface);
        basicfare.setTypeface(typeface);
        basecharges.setTypeface(typeface);
        refundable.setTypeface(typeface);
        amount.setTypeface(typeface);
        payable.setTypeface(typeface);

        int a=Integer.parseInt(basecharges.getText().toString());
        int b=Integer.parseInt(amount.getText().toString());
        c=a+b;
/*
        long minutes=Math.max(Time.drop,Time.pick)-Math.min(Time.pick,Time.drop);
        hour=(int)minutes/60;
        rem=(int)minutes%60;

        */
        String dateStart = DateTime.pickup;
        String dateStop = DateTime.drop;

        //Custom date format
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Get msec from each, and subtract.
        long diff = d2.getTime() - d1.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
      //  System.out.println("Time in seconds: " + diffSeconds + " seconds.");
       // System.out.println("Time in minutes: " + diffMinutes + " minutes.");
        //System.out.println("Time in hours: " + diffHours + " hours.");




        if(diffMinutes%60<15)
        {
            time.setText("For"+" "+diffHours+" "+"hours");
        }
        else
        {
            time.setText("For"+" "+(diffHours+1)+" "+"hours");
        }







        Query q = FirebaseDatabase.getInstance().getReference().child("Offers").child("01");
        q.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // for example: if you're expecting your user's data as an object of the "User" class.
                        Offers offers = dataSnapshot.getValue(Offers.class);
                        s1=offers.getOffer();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // read query is cancelled.
                    }
                });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s2=editText.getText().toString();
                if(s2.equals(s1))
                {
                    c=c-50;

                    s=String.valueOf(c);
                    finalamount.setText(s);
                    Toast.makeText(getApplicationContext(),"Congrats,you received 50off",Toast.LENGTH_SHORT).show();
                }
            }
        });
latter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(AutoMobileDetail.this,PayLater.class));
    }
});

        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AutoMobileDetail.this,Agent.class));
            }
        });



        s=String.valueOf(c);
        finalamount.setText(s);
        finalamount.setTypeface(typeface);

        if(getIntent()!=null)
        {
            autoid=getIntent().getStringExtra("carId");
        }
        if(!autoid.isEmpty())
        {
           getdetail(autoid);
        }


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutoMobileDetail.this,Payment.class);
                intent.putExtra("carId",autoid);
                intent.putExtra("amount",s); startActivity(intent);
            }
        });
    }

    private void getdetail(String autoid) {
        vehicle.child(autoid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                element=dataSnapshot.getValue(Cars.class);
                if(element!=null) {
                    Picasso.with(getBaseContext()).load(element.getImage()).into(autoimage);
                    carname.setText(element.getName());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
}
