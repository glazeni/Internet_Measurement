package internetmeasurement.android.main;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import internetmeasurement.android.R;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    public ViewPager viewPager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        //Disable Orientation
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        //Remove title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Constructor needs to be called after request window!
        super.onCreate(savedInstanceState);

        //Set view
        setContentView(R.layout.activity_main);

        //TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        //Initializing View Pager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Creating PagerAdapter
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), 4);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Wiring tabLayout with Viewpager
        tabLayout.setupWithViewPager(viewPager);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);


        //Customization tabs within tabLayout
        setupTabViews();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //viewPager.setCurrentItem(tab.getPosition());
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private void setupTabViews() {
        //Create typeface to individual Textviews
        //Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Eurosti.ttf");
        //Tab1 Custom View Text & Icon & Font
        TextView tabText1  = (TextView) findViewById(R.id.tab1_text);
        tabText1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_network_check_white_24px, 0, 0);
        //tabText1.setTypeface(typeFace);
        //Tab2 Custom View Text & Icon & Font
        TextView tabText2 = (TextView) findViewById(R.id.tab2_text);
        tabText2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_assessment_white_24px, 0, 0);
        //tabText2.setTypeface(typeFace);
        //Tab3 Custom View Text & Icon & Font
        TextView tabText3  = (TextView) findViewById(R.id.tab3_text);
        tabText3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_settings_white_24px, 0, 0);
        //tabText3.setTypeface(typeFace);
        //Tab4 Custom View Text & Icon & Font
        TextView tabText4 = (TextView) findViewById(R.id.tab4_text);
        tabText4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_info_outline_white_24px, 0, 0);
        //tabText4.setTypeface(typeFace);

        //Apply Custom Views to tabs
            tabLayout.getTabAt(0).setCustomView(tabText1);
            tabLayout.getTabAt(1).setCustomView(tabText2);
            tabLayout.getTabAt(2).setCustomView(tabText3);
            tabLayout.getTabAt(3).setCustomView(tabText4);

    }
    @Override
    public void onBackPressed() {

        //Quit Alert
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                        //int pid = getTaskId();
                        //android.os.Process.killProcess(pid);
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }).create().show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}


