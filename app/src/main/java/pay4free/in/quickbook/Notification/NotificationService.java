package pay4free.in.quickbook.Notification;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import pay4free.in.quickbook.Common.Common;
import pay4free.in.quickbook.model.Phone;
import pay4free.in.quickbook.model.Token;

/**
 * Created by AAKASH on 07-03-2018.
 */

public class NotificationService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String tokenRefreshed= FirebaseInstanceId.getInstance().getToken();
        if(Phone.getPhone()!=null)
           updateTokenToFirebase(tokenRefreshed);

    }

    private void updateTokenToFirebase(String tokenRefreshed) {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference tokens=db.getReference("Tokens");
        Token token=new Token(tokenRefreshed,false);
        tokens.child(Phone.getPhone()).setValue(token);
    }
}
