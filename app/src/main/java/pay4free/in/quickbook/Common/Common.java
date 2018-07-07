package pay4free.in.quickbook.Common;

import pay4free.in.quickbook.Remote.MyApiService;
import pay4free.in.quickbook.Remote.RetrofitClient;
import pay4free.in.quickbook.model.PersonalData;

/**
 * Created by AAKASH on 19-01-2018.
 */

public class Common  {
    public static int nbooked;
    public static PersonalData user;
    public static final String User_key="User";
    public static final String password="Pwd";
    private static final String BASE_URL="https://fcm.googleapis.com/";
    public static MyApiService getFCMService()
    {
        return RetrofitClient.getClient(BASE_URL).create(MyApiService.class);
    }
}
