package au.com.wsit.project12.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import au.com.wsit.project12.R;

public class MainActivity extends Activity
{
    private ImageView earth;
    private ImageView rover;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earth = (ImageView) findViewById(R.id.earthImageView);
        rover = (ImageView) findViewById(R.id.roverImageView);

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

    }

    // Animate the ImageView button clicks
    private void animate(View v)
    {
        v.setScaleY(0);
        v.setScaleX(0);
        v.animate().scaleX(1).scaleY(1).start();
    }
}
