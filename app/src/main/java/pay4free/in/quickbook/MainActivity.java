package pay4free.in.quickbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import io.paperdb.Paper;
import pay4free.in.quickbook.Common.Common;
import pay4free.in.quickbook.Notification.Notify;
import pay4free.in.quickbook.model.PersonalData;
import pay4free.in.quickbook.model.Phone;

public class MainActivity extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText name,password;
    ProgressDialog progress;
    CheckBox checkBox;
    String user="";
    String pass="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.signup);
        Button login = (Button) findViewById(R.id.login);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("PersonalDetails");
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        progress = new ProgressDialog(this);
        progress.setMessage("Please Wait.......");
        checkBox=(CheckBox)findViewById(R.id.rememberme);
        Paper.init(this);
        user=Paper.book().read(Common.User_key);
        pass=Paper.book().read(Common.password);
        Phone.setPhone(user);
        final Handler thread=new Handler();
        thread.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user!=null&&pass!=null)
                {
                    if(!user.isEmpty()&&!pass.isEmpty())
                        progress.show();
name.setText(user);password.setText(pass);
                    Phone.setPhone(user);
                    login(user,pass);

                }
            }
        },1200);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setMessage("Registering");
                progress.show();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Phone.setPhone(user);
                        startActivity(new Intent(MainActivity.this, SignUp.class));
                    }
                },1000);
                progress.dismiss();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(name.getText().toString())&&!TextUtils.isEmpty(password.getText().toString())) {
                    progress.setMessage("Please Wait.......");
                    progress.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            signInuser(name.getText().toString(), password.getText().toString());

                        }
                    }, 1000);
                    Phone.setPhone(name.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter Details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(String user, String pass) {
        final String localphone=user;
        final String localpassword=pass;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localphone).exists()) {

                    PersonalData user = dataSnapshot.child(localphone).getValue(PersonalData.class);

                    if (user.getPasssword().equals(localpassword)) {
                        progress.dismiss();
                        Toast.makeText(MainActivity.this, "Succesfully login", Toast.LENGTH_SHORT).show();
                        Common.user=user;
                        Phone.setPhone(localphone);
                        Common.nbooked=user.getNbooked();
                        startActivity(new Intent(MainActivity.this,Main2Activity.class));
                        finish();
                    } else {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    }

                }

                else

                {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(), "User not exist in Database", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void signInuser(String phone, String password) {
        progress.show();
        final String localphone=phone;
        final String localpassword=password;
        if(checkBox.isChecked())
        {
            Paper.book().write(Common.User_key,phone);
            Paper.book().write(Common.password,password);


        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localphone).exists()) {
                    PersonalData user = dataSnapshot.child(localphone).getValue(PersonalData.class);

                        if (user.getPasssword().equals(localpassword)) { progress.dismiss();
                            Toast.makeText(MainActivity.this, "Succesfully login", Toast.LENGTH_SHORT).show();
                            Phone.setPhone(localphone);

                            Common.user=user;
                            Common.nbooked=user.getNbooked();
                            startActivity(new Intent(MainActivity.this,Main2Activity.class));
                            finish();
                        } else {
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                        }

                }

                else

                {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(), "User not exist in Database", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

