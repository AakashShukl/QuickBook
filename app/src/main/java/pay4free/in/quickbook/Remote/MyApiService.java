package pay4free.in.quickbook.Remote;



import pay4free.in.quickbook.model.MyResponse;
import pay4free.in.quickbook.model.Sender;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by AAKASH on 09-03-2018.
 */

public interface MyApiService {
    @Headers(
            {
                      "Content-Type:application/json",
                      "Authorization:key=AAAAeQiXutE:APA91bHRnXt3tuk8c9wU3X0_wQ2u255jId6V4TebhQsQ4EU1ZYBPfzNAQZG8ImnqfGvGdCw-jUP8T6V_t6-UbmENAKa6QF73nqM_gNPqG1Kzkx-yISVryv4YCoC90M_dXpaLqzgsqxa7"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);

}
