package pay4free.in.quickbook;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
TextView text,appslogan;
    ImageView logo;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        text=(TextView)findViewById(R.id.pro_txt);
        Typeface tr=Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Light.ttf");
        text.setTypeface(tr);

        logo = (ImageView) findViewById(R.id.logo_img);
appslogan=(TextView)findViewById(R.id.pro_tag);
        appslogan.setTypeface(tr);
        if (savedInstanceState == null) {
            flyIn();
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                endSplash();
            }
        }, 3500);
    }

    private void flyIn() {
        animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        logo.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this,
                R.anim.app_name_animation);
       text.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation);
        appslogan.startAnimation(animation);
    }

    private void endSplash() {
        animation = AnimationUtils.loadAnimation(this,
                R.anim.logo_animation_back);
        logo.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this,
                R.anim.app_name_animation_back);
        text.startAnimation(animation);

       animation = AnimationUtils.loadAnimation(this,
             R.anim.pro_animation_back);
        appslogan.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {

               Intent intent = new Intent(Splash.this,
                        PagerActivity.class);
                startActivity(intent);
              //  Toast.makeText(getApplicationContext(),"Starting Your App",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

}

