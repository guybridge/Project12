package au.com.wsit.project12.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TableLayout;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import au.com.wsit.project12.R;
import au.com.wsit.project12.adapters.SectionsPagerAdapter;
import au.com.wsit.project12.utils.Constants;

public class RoverActivity extends AppCompatActivity
{

    private static final String TAG = RoverActivity.class.getSimpleName();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SectionsPagerAdapter sectionsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rover);
        getSupportActionBar().setElevation(0);

        // UI Setup
        setupUI();

        // Showcase
        showcase();

    }

    // Setup the viewPage and tabLayout
    private void setupUI()
    {
        sectionsPagerAdapter = new SectionsPagerAdapter(RoverActivity.this, getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);

        tabLayout.getTabAt(0).setText(Constants.CURIOUSITY);
        tabLayout.getTabAt(1).setText(Constants.OPPORTUNITY);
        tabLayout.getTabAt(2).setText(Constants.SPIRIT);
    }

    private void showcase()
    {

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES_FILE, Context.MODE_PRIVATE);
        boolean shouldShowcase = sharedPreferences.getBoolean(Constants.SHOWCASE_ROVER, true);

        if(shouldShowcase)
        {
            new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(R.id.tabLayout, this))
                    .setContentTitle("Select a rover image")
                    .setContentText("Scroll through the rover images, select one you like to send it as a postcard.")
                    .hideOnTouchOutside()
                    .build();

            sharedPreferences.edit().putBoolean(Constants.SHOWCASE_ROVER, false).apply();
        }

    }


}
