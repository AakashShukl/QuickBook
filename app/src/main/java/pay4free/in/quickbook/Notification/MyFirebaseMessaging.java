package pay4free.in.quickbook.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import pay4free.in.quickbook.MainActivity;
import pay4free.in.quickbook.R;

/**
 * Created by AAKASH on 07-03-2018.
 */

public class MyFirebaseMessaging extends FirebaseMessagingService{
    @Override

    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);
        sendnotification(remoteMessage);
    }

    private void sendnotification(RemoteMessage remoteMessage)
    {
        RemoteMessage.Notification notification=remoteMessage.getNotification();
        Intent intent =new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this).setSmallIcon(R.drawable.mine).setContentTitle(notification.getTitle()).setContentText(notification.getBody()).setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent);
        NotificationManager notify =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notify.notify(0,builder.build());
    }
}
