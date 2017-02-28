package au.com.wsit.project12.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TableLayout;

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
}
