package pay4free.in.quickbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import pay4free.in.quickbook.Common.Common;
import pay4free.in.quickbook.Notification.Notification;
import pay4free.in.quickbook.Remote.MyApiService;
import pay4free.in.quickbook.model.MyResponse;
import pay4free.in.quickbook.model.Sender;
import pay4free.in.quickbook.model.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayLater extends AppCompatActivity {
MyApiService myApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_later);
        myApiService= Common.getFCMService();
        sendNotification("aakash","");
    }


    private void sendNotification(String s,String mPhone) {

        DatabaseReference tokens= FirebaseDatabase.getInstance().getReference("Tokens");
        Query data=tokens.orderByKey().equalTo("7000569143");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren()) {
                    Token serverToken = postsnapshot.getValue(Token.class);
                    Notification notification = new Notification("DEV", "you are nice");
                    if (serverToken.getToken() != null) {
                        Sender content = new Sender(serverToken.getToken(), notification);
                        myApiService.sendNotification(content).enqueue(new Callback<MyResponse>() {
                            @Override
                            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                if (response.body().success == 1) {
                                    Toast.makeText(getApplicationContext(), "Booked", Toast.LENGTH_SHORT).show();
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
