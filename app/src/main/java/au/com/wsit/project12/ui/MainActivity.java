package au.com.wsit.project12.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import au.com.wsit.project12.R;
import au.com.wsit.project12.utils.Constants;


public class MainActivity extends Activity implements OnShowcaseEventListener
{
    private ImageView earth;
    private ImageView rover;

    // Showcase setup
    private ShowcaseView showcaseView;
    private boolean showRover = true;
    private boolean showEarth = true;
    private boolean shouldShowcase;

    private SharedPreferences sharedPreferences;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earth = (ImageView) findViewById(R.id.earthImageView);
        rover = (ImageView) findViewById(R.id.roverImageView);

        sharedPreferences = getSharedPreferences(Constants.PREFERENCES_FILE, Context.MODE_PRIVATE);
        shouldShowcase = sharedPreferences.getBoolean(Constants.SHOWCASE, true);

        // Click listener for starting eye in the sky
        earth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animate(v);
                Intent earthIntent = new Intent(MainActivity.this, EarthActivity.class);
                startActivity(earthIntent);
            }
        });

        // Click listener for starting the rover postcard maker
        rover.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animate(v);
                Intent roverIntent = new Intent(MainActivity.this, RoverActivity.class);
                startActivity(roverIntent);
            }
        });


        if(shouldShowcase)
        {
            showcase();
        }


    }

    // Animate the ImageView button clicks
    private void animate(View v)
    {
        v.setScaleY(0);
        v.setScaleX(0);
        v.animate().scaleX(1).scaleY(1).start();
    }

    private void showcase()
    {
        showRoverShowcase();
    }

    private void showRoverShowcase()
    {
        if(showRover)
        {
            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(R.id.roverImageView, this))
                    .setContentTitle("Rover postcard maker")
                    .setContentText("Select the rover to make a postcard from one of the mars rovers.")
                    .hideOnTouchOutside()
                    .setShowcaseEventListener(this)
                    .build();

            showRover = false;
        }

    }

    private void showEarthShowCase()
    {
        if(showEarth)
        {
            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(R.id.earthImageView, this))
                    .setContentTitle("Eye-In-The-Sky")
                    .setContentText("Select a position on the map to show the image at that location")
                    .hideOnTouchOutside()
                    .setShowcaseEventListener(this)
                    .build();

            showEarth = false;

            sharedPreferences.edit().putBoolean(Constants.SHOWCASE, false).apply();
        }

    }


    @Override
    public void onShowcaseViewHide(ShowcaseView showcaseView)
    {
        Log.i(TAG, "onShowcaseViewHide called");
        showEarthShowCase();
    }

    @Override
    public void onShowcaseViewDidHide(ShowcaseView showcaseView)
    {
        Log.i(TAG, "onShowcaseViewDidHide Called");
    }

    @Override
    public void onShowcaseViewShow(ShowcaseView showcaseView)
    {
        Log.i(TAG, "onShowcaseViewShow called");
    }

    @Override
    public void onShowcaseViewTouchBlocked(MotionEvent motionEvent)
    {
        Log.i(TAG, "onShowcaseViewTouchBlocked called");
    }
}
