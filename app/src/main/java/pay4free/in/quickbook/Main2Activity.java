package pay4free.in.quickbook;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.icu.util.Calendar;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;
import pay4free.in.quickbook.Common.Common;
import pay4free.in.quickbook.Dialog.DatePickerFragment;
import pay4free.in.quickbook.Dialog.DatePickerFragmentDrop;
import pay4free.in.quickbook.Interface.ItemClickListener;
import pay4free.in.quickbook.Viewholder.MenuViewHolder;
import pay4free.in.quickbook.model.Cars;
import pay4free.in.quickbook.model.Offers;
import pay4free.in.quickbook.model.PersonalData;
import pay4free.in.quickbook.model.Phone;
import pay4free.in.quickbook.model.Token;


public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String city[]={"Manali","Shimla","Bhopal","Ladakh"};
   static String travelcity="";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    DrawerLayout drawer;

    private static Context context;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Paper.init(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner s=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,city);
        s.setAdapter(adapter);
        s.setSelection(2);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3)
            {
            if(arg2==3)
            {
                travelcity="Ladakh";
            }
                if(arg2==0)
                {
                    travelcity="Manali";
                }
                if(arg2==2)
                {
                    travelcity="Bhopal";
                }
                if(arg2==1)
                {
                    travelcity="Shimla";
                }

            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
context=Main2Activity.this;
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //showDialog();
            }
        });

    }



    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.logout)
        {
            Paper.book().destroy();
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Main2Activity.this,MainActivity.class));

                    finish();

                }
            },3000);
            ProgressDialog dialog=new ProgressDialog(Main2Activity.this);
            dialog.setMessage("Login out");
            dialog.show();


        }
        if(id==R.id.nav_Policy)
        {
            startActivity(new Intent(Main2Activity.this,Policy.class));
        }


        if (id == R.id.nav_help) {

            Toast.makeText(getApplicationContext(),"Call us On 7000569010",Toast.LENGTH_SHORT).show();
            // Handle the camera action
        }
/*
        if (id == R.id.nav_menu) {
            // Handle the camera action
        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_logout) {
        }
*/



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

     /*   //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        static Calendar datetime;
        static Button find;
        View rootView;
        static int year,day,month;
       int Dialog_id=1;
        int i=1;
        static DatePickerDialog.OnDateSetListener mDateSetListener;
        static EditText dest;
        TextView time1,time2;
        EditText dateedit;
        FirebaseStorage storage;
        StorageReference storageReference;
        static FirebaseDatabase database;
        static DatabaseReference categories;
        static RecyclerView recyclerView;
        static LinearLayoutManager linearLayoutManager;
        static FirebaseRecyclerAdapter<Offers,MenuViewHolder> adapter;
        static FirebaseRecyclerAdapter<Cars,MenuViewHolder> adapter1;
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String TAG = "Sample";

        private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";

        private static final String STATE_TEXTVIEW = "STATE_TEXTVIEW";
        private SwitchDateTimeDialogFragment dateTimeFragment;


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);


            return fragment;
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            database=FirebaseDatabase.getInstance();
            categories=database.getReference("Offers");

            String s=getActivity().getIntent().getStringExtra("venue");
if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {
    rootView = inflater.inflate(R.layout.fragment_main2, container, false);
  updateToken(FirebaseInstanceId.getInstance().getToken());
    recyclerView=(RecyclerView)rootView.findViewById(R.id.offers);
    find=(Button)rootView.findViewById(R.id.findvehicle);

    dest=(EditText) rootView.findViewById(R.id.edtdest);
    time1=(TextView)rootView.findViewById(R.id.editdate);
    time2=(TextView) rootView.findViewById(R.id.editdrop);
    Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Light.ttf");
    dest.setTypeface(typeface);
    dest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dest.setText("");
            Intent intent=new Intent(context,Location.class);
            intent.putExtra("city",travelcity);
            startActivity(intent);
        }
    });
   // dest.setTextSize(4f);
    dest.setText(s);
    Paper.init(getActivity());
    time1.setTypeface(typeface);
    time2.setTypeface(typeface);
    find.setTypeface(typeface);
    recyclerView.setHasFixedSize(true);
    linearLayoutManager=new LinearLayoutManager(context);
    recyclerView.setLayoutManager(linearLayoutManager);

    loadoffers();


    time1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(), "timePicker");


               // time1.setText((String) Paper.book().read(DateTime.datetime));
               // Paper.book().delete(DateTime.datetime);
        }


    });


    time2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment newFragment = new DatePickerFragmentDrop();
            newFragment.show(getFragmentManager(), "timePicker");


        }
    });

    find.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
if(!TextUtils.isEmpty(dest.getText())&&!TextUtils.isEmpty(time1.getText())&&!TextUtils.isEmpty(time2.getText())) {


    Intent i = new Intent(context, VehicleList.class);
    i.putExtra("city", travelcity);
    i.putExtra("pickup",time1.getText().toString());
    i.putExtra("drop",time2.getText().toString());
    startActivity(i);
    getActivity().finish();

}
else
{
    Toast.makeText(getContext(),"Please enter Details",Toast.LENGTH_SHORT).show();
}

        }
    });
}
else if(getArguments().getInt(ARG_SECTION_NUMBER)==2)
{

    rootView = inflater.inflate(R.layout.fragment_main1, container, false);
    TextView lastrip = (TextView) rootView.findViewById(R.id.lasttrip);
    final TextView name = (TextView) rootView.findViewById(R.id.name);
    final TextView phonetxt = (TextView) rootView.findViewById(R.id.phone);
    final TextView phone = (TextView) rootView.findViewById(R.id.phoneno);
    final TextView bookedtext = (TextView) rootView.findViewById(R.id.bookedtext);
    final TextView booking = (TextView) rootView.findViewById(R.id.nobooking);
    final TextView earning = (TextView) rootView.findViewById(R.id.earning);
    Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Light.ttf");
    lastrip.setTypeface(typeface);
    name.setTypeface(typeface);
   phonetxt.setTypeface(typeface);
    phone.setTypeface(typeface);
    bookedtext.setTypeface(typeface);
   earning.setTypeface(typeface);
    Query q = FirebaseDatabase.getInstance().getReference().child("PersonalDetails").child(Phone.getPhone());
    q.addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // for example: if you're expecting your user's data as an object of the "User" class.
                   PersonalData user = dataSnapshot.getValue(PersonalData.class);
                    name.setText(user.getName());
                    phone.setText(user.getMobile());
                    booking.setText(Common.nbooked+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // read query is cancelled.
                }
            });

    recyclerView=(RecyclerView)rootView.findViewById(R.id.booked);
    recyclerView.setHasFixedSize(true);
    linearLayoutManager=new LinearLayoutManager(context);
    recyclerView.setLayoutManager(linearLayoutManager);
loadbooked();

}
else if(getArguments().getInt(ARG_SECTION_NUMBER)==3)
        rootView = inflater.inflate(R.layout.fragment_main3, container, false);
           //dateedit = (EditText) rootView.findViewById(R.id.editdate);
           // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        private void updateToken(String tokken) {
            FirebaseDatabase db=FirebaseDatabase.getInstance();
            DatabaseReference tokens=db.getReference("Tokens");
            Token token=new Token(tokken,false);
            tokens.child(Phone.getPhone()).setValue(token);
        }


        private static void loadoffers() {
            

                adapter=new FirebaseRecyclerAdapter<Offers, MenuViewHolder>(Offers.class,R.layout.offer_item,MenuViewHolder.class,categories) {
                    @Override
                    protected void populateViewHolder(MenuViewHolder viewHolder,Offers model, int position) {
                        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Light.ttf");
                        viewHolder.textMenuName.setText(model.getName());
                        viewHolder.textMenuName.setTypeface(typeface);
                        Picasso.with(context).load(model.getImage()).into(viewHolder.imageView);
                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {

                            }
                        });

                    }
                };
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }
        private static void loadbooked() {
           final DatabaseReference booked=database.getReference("PersonalDetails").child(Phone.getPhone()).child("Booked");
            final Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Light.ttf");
            adapter1=new FirebaseRecyclerAdapter<Cars, MenuViewHolder>(Cars.class,R.layout.booked,MenuViewHolder.class,booked) {
                @Override
                protected void populateViewHolder(final MenuViewHolder viewHolder, Cars model, int position) {

                    viewHolder.textMenuName.setText(model.getName());
                    viewHolder.textMenuName.setTypeface(typeface);
                    Picasso.with(context).load(model.getImage()).into(viewHolder.imageView);
                    viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {

                        }
                    });


                    if (model.isBooked()) {
                        viewHolder.book.setText("");
                        viewHolder.book.setVisibility(View.INVISIBLE);
                    }
                    /*
                    viewHolder.book.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseDatabase.getInstance().getReference().child("Vechicle").orderByChild("booked").equalTo(true).child("booked").setValue(false);
                            Toast.makeText(context,"Trip Ended",Toast.LENGTH_SHORT).show();
                            booked.child(childSnapshot.getKey()).child("booked").setValue(false);
                        }
                    });



               /*     booked.addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // for example: if you're expecting your user's data as an object of the "User" class.

                                    for (final DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                        Cars user = childSnapshot.getValue(Cars.class);
                                        if (user.isBooked()) {
                                            viewHolder.book.setVisibility(View.VISIBLE);
                                            viewHolder.book.setText("End Trip");
                                            viewHolder.book.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    FirebaseDatabase.getInstance().getReference().child("Vechicle").child(childSnapshot.getKey()).child("booked").setValue(false);
                                                    Toast.makeText(context,"Trip Ended",Toast.LENGTH_SHORT).show();
                                                    booked.child(childSnapshot.getKey()).child("booked").setValue(false);
                                                }
                                            });


                                        }
                                        else
                                        {
                                            viewHolder.book.setVisibility(View.INVISIBLE);
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // read query is cancelled.
                                }
                            });
*/
                }

            };



            adapter1.notifyDataSetChanged();
            recyclerView.setAdapter(adapter1);

        }

        }


}

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
   class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return Main2Activity.PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Hire";
                case 1:
                    return "Profile";
                case 2:
                    return "Buy";
            }
            return null;
        }
    }

