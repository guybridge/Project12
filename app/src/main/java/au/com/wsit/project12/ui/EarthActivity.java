package au.com.wsit.project12.ui;


import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);

        // Zoom control
        zoomIn = (Button) findViewById(R.id.zoomIn);
        zoomOut = (Button) findViewById(R.id.zoomOut);

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
        EarthApi earthApi = new EarthApi();
        earthApi.getImages(latLng.latitude, latLng.longitude, "2015-01-01", new EarthApi.EarthCallback()
        {
            @Override
            public void onResult(Earth earth)
            {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_earth, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_show:
                // show images
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
