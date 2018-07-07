package pay4free.in.quickbook.Pager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pay4free.in.quickbook.MainActivity;
import pay4free.in.quickbook.R;
import pay4free.in.quickbook.Splash;

/**
 * Created by AAKASH on 25-12-2017.
 */

public class Slider extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public Slider(Context context) {
        this.context = context;
    }

    //List of attributes
    public  int[] listimages={R.drawable.bicycle,R.drawable.motor,R.drawable.carrr,R.drawable.mine};
    public String listtitle[]={"Bicycle","MotorBike","Car","QuickBook"};
    public String description[]={"Share your Bicycle","Share your MotorBike","Share your Car","TRAVEL MADE EASY"};
    public int colour[]={Color.rgb(55,55,55),Color.rgb(239,85,85),Color.rgb(110,49,89),Color.rgb(1,188,212)};

    @Override
    public int getCount() {
        return listtitle.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(LinearLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View view=layoutInflater.inflate(R.layout.slider,container,false);
        LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.sliderlinear);
        ImageView imageView=(ImageView)view.findViewById(R.id.slideimg);
        TextView textView=(TextView)view.findViewById(R.id.title);
        TextView textView1=(TextView)view.findViewById(R.id.description);
        linearLayout.setBackgroundColor(colour[position]);
        imageView.setImageResource(listimages[position]);
        textView.setText(listtitle[position]);
         Button button=null;
        textView1.setText(description[position]);
        if(position==getCount()-1)
        {
            button=(Button)view.findViewById(R.id.btnn);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent readMore = new Intent(context,MainActivity.class);
                    context.startActivity(readMore);
                    ((Activity) context).finish();

                }
            });
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView((LinearLayout)object);
    }

    public Context getContext() {
        return context;
    }



}
