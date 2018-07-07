package pay4free.in.quickbook.Notification;

import android.app.*;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import pay4free.in.quickbook.R;
import pay4free.in.quickbook.model.Cars;
import pay4free.in.quickbook.model.Phone;

public class Notify extends Service implements ChildEventListener{

    FirebaseDatabase database;
    DatabaseReference notify;

    @Override
    public void onCreate() {
        super.onCreate();
        database=FirebaseDatabase.getInstance();
        notify=database.getReference("PersonalDetails");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notify.addChildEventListener(this);
        return super.onStartCommand(intent, flags, startId);
    }

    public Notify() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        //Trigger Here
        for(DataSnapshot childsnapshot:dataSnapshot.getChildren()) {
            {
if(childsnapshot.getKey().equals("Booked"))
                {
                    for (DataSnapshot dataSnapshot1 : childsnapshot.getChildren()) {

                        Cars cars = dataSnapshot1.getValue(Cars.class);

                                String s1 = dataSnapshot.getKey();
                        notify.removeEventListener(this);
                                showNotification(cars, s1);
                            }
                    }

                }
}
    }



    private void showNotification(Cars cars,String num) {
        if(Phone.getPhone()!=null&&cars.getPhoneno()!=null) {
            if (cars.getPhoneno().equals(Phone.getPhone())) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
                builder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL).setTicker("QwikBook").setContentInfo("New Booking").setContentText(cars.getName() + "," + num).setSmallIcon(R.drawable.mine);

                NotificationManager manager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
                int randomint = new Random().nextInt(9999 - 1) + 1;
                manager.notify(randomint, builder.build());
            }
        }
        }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
