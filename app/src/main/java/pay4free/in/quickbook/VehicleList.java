package pay4free.in.quickbook;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import pay4free.in.quickbook.Interface.ItemClickListener;
import pay4free.in.quickbook.Viewholder.MenuViewHolder;
import pay4free.in.quickbook.model.Cars;

public class VehicleList extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static Context context;
    TextView pick,drop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pick=(TextView)findViewById(R.id.pick);
        drop=(TextView)findViewById(R.id.drop);
        Intent intent=getIntent();
        pick.setText(intent.getStringExtra("pickup"));
        drop.setText(intent.getStringExtra("drop"));
        //pick.setText(intent.getStringExtra("pickup"));
        //drop.setText(intent.getStringExtra("drop"));
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        context=VehicleList.this;

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        View rootView;
        FirebaseRecyclerAdapter<Cars,MenuViewHolder> adapter;
        FirebaseRecyclerAdapter<Cars,MenuViewHolder> adapter1;
        Query query;
        LinearLayoutManager linearLayoutManager;
        RecyclerView recyclerView;
       String city="";

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

           FirebaseDatabase database= FirebaseDatabase.getInstance();
            DatabaseReference categories = database.getReference("Vechicle");
            Intent intent=getActivity().getIntent();
            city= intent.getStringExtra("city");

            if(getArguments().getInt(ARG_SECTION_NUMBER)==2)
            {
                rootView=inflater.inflate(R.layout.activity_list_vehicle, container, false);
                recyclerView=(RecyclerView)rootView.findViewById(R.id.cars);
                query = categories.orderByChild("cid").equalTo(city+""+0);
                recyclerView.setHasFixedSize(true);
                linearLayoutManager=new LinearLayoutManager(context);
                recyclerView.setLayoutManager(linearLayoutManager);
                loadvehicle();


            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
            {
                rootView=inflater.inflate(R.layout.activity_list_vehicle, container, false);
                recyclerView=(RecyclerView)rootView.findViewById(R.id.cars);
                query = categories.orderByChild("cid").equalTo(city+""+2);
                recyclerView.setHasFixedSize(true);
                linearLayoutManager=new LinearLayoutManager(context);
                recyclerView.setLayoutManager(linearLayoutManager);
                loadvehicle();
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==3)
            {

                rootView=inflater.inflate(R.layout.activity_list_vehicle, container, false);
                recyclerView=(RecyclerView)rootView.findViewById(R.id.cars);
                query = categories.orderByChild("cid").equalTo(city+""+1);
                recyclerView.setHasFixedSize(true);
                linearLayoutManager=new LinearLayoutManager(context);
                recyclerView.setLayoutManager(linearLayoutManager);
                loadvehicle();
            }

            return rootView;
        }



        private void loadvehicle() {
            adapter = new FirebaseRecyclerAdapter<Cars,MenuViewHolder>(Cars.class, R.layout.offer_item, MenuViewHolder.class,query) {
                @Override
                protected void populateViewHolder(MenuViewHolder viewHolder, Cars model,final int position) {

                    if (model.isBooked()) {

                        viewHolder.book.setVisibility(View.INVISIBLE);


                    }
                    else {
                        viewHolder.book.setVisibility(View.VISIBLE);
                    }
                    viewHolder.textMenuName.setText(model.getName());
                    Picasso.with(context).load(model.getImage()).into(viewHolder.imageView);
                    viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {


                        }
                    });
                    Cars local = model;
                    viewHolder.book.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent showdetail = new Intent(getActivity(), AutoMobileDetail.class);
                            String s=adapter.getRef(position).getKey();
                            showdetail.putExtra("carId", s);
                            startActivity(showdetail);
                        }
                    });
                }

            };
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);


        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
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
                    return "SuperBike";
                case 1:
                    return "Bike";
                case 2:
                    return "Car";
            }
            return null;
        }
    }
}
