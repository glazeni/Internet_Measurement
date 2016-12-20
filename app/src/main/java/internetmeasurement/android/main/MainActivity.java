package internetmeasurement.android.main;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import internetmeasurement.android.R;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_network_check_white_36px,
            R.drawable.ic_assessment_white_36px,
            R.drawable.ic_settings_white_36px,
            R.drawable.ic_info_outline_white_36px
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

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

        //Adding icons to the Tabs and setup tabLayout with viewPager
        setupTabIcons();

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


    private void setupTabIcons() {
            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
            tabLayout.getTabAt(3).setIcon(tabIcons[3]);
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

