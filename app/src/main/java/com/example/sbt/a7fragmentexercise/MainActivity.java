package com.example.sbt.a7fragmentexercise;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.example.sbt.a7fragmentexercise.PageFragment.ARGUMENT_PAGE_NUMBER;

public class MainActivity extends FragmentActivity {

    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 10;
    private List<Shelves> myList;
    ViewPager pager;
    PagerAdapter pagerAdapter;

    DatabaseReference myRef;

Context context;
    AboveShelves aboveShelves;
    String value;

    @Override
    protected void onStart() {
        super.onStart();
        ValueEventListener postListener = new ValueEventListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (aboveShelves!=null) {
                    aboveShelves.deleteShelves();
                }

                Map<String, AboveShelves> map = (Map<String, AboveShelves>) dataSnapshot.getValue();
                for(Map.Entry entry : map.entrySet()){
                    if(entry.getKey().equals("shelves"))
                    {
                        aboveShelves =AboveShelves.get();
                        aboveShelves.setShelves((Map<String, Shelves>) entry.getValue());

                        Log.d(TAG,"onStart Weight: " + aboveShelves.getShelve("id2").getWeight());
                        Log.d(TAG,"onStart Redline: " + aboveShelves.getShelve("id2").getRedline());
                    }
                    if(entry.getKey().equals("URL-TAG"))
                        value =(String) entry.getValue();

                    }
                myList =  aboveShelves.getShelves();

                pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()){

                    @Override
                    public int getCount() {
                        return myList.size();
                        //return 3;
                    }

                    @Override
                    public Fragment getItem(int position) {
                        Shelves shelves = myList.get(position);
                        Bundle arguments = new Bundle();
                        arguments.putString(ARGUMENT_PAGE_NUMBER, shelves.getId());
                        PageFragment pageFragment = new PageFragment();
                        pageFragment.setArguments(arguments);
                        return pageFragment;
                                //PageFragment.newInstance(shelves.getId());
                    }
                };
                pager.invalidate();
                pager.setAdapter(pagerAdapter);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
//                // Get Post object and use the values to update the UI
//                final String value = dataSnapshot.getValue(String.class);
//                Log.d("TAG", "Value is: " + value);
//                //Glide.with(context).load(value).into(image);
            };


        myRef.addValueEventListener(postListener);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        pager = (ViewPager) findViewById(R.id.pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return "Полка  " + position+", позиция "+position;
//        }
//
//        public MyFragmentPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return PageFragment.newInstance(position);
//        }
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//    }

}
//    /**
//     * The number of pages (wizard steps) to show in this demo.
//     */
//    private static final int NUM_PAGES = 5;
//
//    /**
//     * The pager widget, which handles animation and allows swiping horizontally to access previous
//     * and next wizard steps.
//     */
//    private ViewPager mPager;
//
//    /**
//     * The pager adapter, which provides the pages to the view pager widget.
//     */
//    private PagerAdapter mPagerAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_screen_slide);
//        // Instantiate a ViewPager and a PagerAdapter.
//        mPager = (ViewPager) findViewById(R.id.pager);
//        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
//        mPager.setAdapter(mPagerAdapter);
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
//    }
//
//    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
//        public ScreenSlidePagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return new ScreenSlidePageFragment();
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//    }
//}
