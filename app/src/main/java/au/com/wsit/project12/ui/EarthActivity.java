package au.com.wsit.project12.ui;


import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import au.com.wsit.project12.R;
import au.com.wsit.project12.api.EarthApi;
import au.com.wsit.project12.model.Earth;
import au.com.wsit.project12.ui.fragments.ShowImage;
import au.com.wsit.project12.utils.Constants;

public class EarthActivity extends AppCompatActivity implements OnMapReadyCallback
{

    private static final String TAG = EarthActivity.class.getSimpleName();
    private GoogleMap googleMap;
    private Button zoomIn;
    private Button zoomOut;
    private ProgressBar progressBar;
    private DatePicker datePicker;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);

        // Zoom control
        zoomIn = (Button) findViewById(R.id.zoomIn);
        zoomOut = (Button) findViewById(R.id.zoomOut);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_earth);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        zoomIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animate(v);
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        zoomOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animate(v);
                googleMap.animateCamera(CameraUpdateFactory.zoomOut());

            }
        });

        // showcase the features
        showcase();

    }

    @Override
    public void onMapReady(final GoogleMap googleMap)
    {
        this.googleMap = googleMap;


        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {

                // Add the markers
                MarkerOptions options = new MarkerOptions();
                options.title(latLng.toString()).position(latLng);
                googleMap.addMarker(options);

                showImage(latLng);
            }
        });
    }

    // Query the API for the image
    private void showImage(LatLng latLng)
    {
        // Show loading
        progressBar.setVisibility(View.VISIBLE);

        // Setup the Earth Api
        EarthApi earthApi = new EarthApi();
        earthApi.getImages(latLng.latitude, latLng.longitude, getDate(), new EarthApi.EarthCallback()
        {
            @Override
            public void onResult(Earth earth)
            {
                // Hide the progress bar on result
                progressBar.setVisibility(View.INVISIBLE);

                // Show image fragment
                Log.i(TAG, "image: " + earth.getImageUrl());
                FragmentManager fragmentManager = getFragmentManager();
                ShowImage showImage = new ShowImage();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.IMAGE_URL, earth.getImageUrl());
                showImage.setArguments(bundle);
                showImage.show(fragmentManager, "showImage");

            }

            @Override
            public void onFail(String errorMessage)
            {
                // Hide the progress bar on result
                progressBar.setVisibility(View.INVISIBLE);
                Snackbar.make(relativeLayout, "No imagery for specified date", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    // Get the date into a string format
    private String getDate()
    {
        String day = String.valueOf(datePicker.getDayOfMonth());
        String month = String.valueOf(datePicker.getMonth());
        String year = String.valueOf(datePicker.getYear());

        String date = year + "-" + month + "-" + day;
        Log.i(TAG, "showing date: " + date);

        return date;
    }

    private void showcase()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES_FILE, Context.MODE_PRIVATE);
        boolean shouldShowcase = sharedPreferences.getBoolean(Constants.SHOWCASE_EARTH, true);

        if(shouldShowcase)
        {
            new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(R.id.datePicker, this))
                    .setContentTitle("Choose a date")
                    .setContentText("Zoom around and selection a position on the map to see the image")
                    .hideOnTouchOutside()
                    .build();

            sharedPreferences.edit().putBoolean(Constants.SHOWCASE_EARTH, false).apply();
        }
    }

    // Animate the ImageView button clicks
    private void animate(View v)
    {
        v.setScaleY(0);
        v.setScaleX(0);
        v.animate().scaleX(1).scaleY(1).start();
    }
}
