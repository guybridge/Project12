package au.com.wsit.project12.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import au.com.wsit.project12.R;
import au.com.wsit.project12.utils.Constants;

public class PostcardActivity extends Activity
{

    private ImageView image;

    private TextView roverNameTextView;
    private TextView cameraNameTextView;
    private TextView photoDateTextView;
    private TextView solTextView;

    private ImageButton sendButton;

    private RelativeLayout postcardLayout;

    private String roverName;
    private String cameraName;
    private String date;
    private String sol;
    private String imageUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postcard);

        image = (ImageView) findViewById(R.id.cameraPicture);

        roverNameTextView = (TextView) findViewById(R.id.roverName);
        cameraNameTextView = (TextView) findViewById(R.id.cameraName);
        photoDateTextView = (TextView) findViewById(R.id.photoDate);
        solTextView = (TextView) findViewById(R.id.solTextView);
        sendButton = (ImageButton) findViewById(R.id.sendButton);

        postcardLayout = (RelativeLayout) findViewById(R.id.activity_postcard);

        // Get the intent data
        Intent intent = getIntent();
        imageUrl= intent.getStringExtra(Constants.IMAGE_URL);
        roverName = intent.getStringExtra(Constants.NAME);
        cameraName = intent.getStringExtra(Constants.CAMERA);
        date = intent.getStringExtra(Constants.EARTH_DATE);
        sol = intent.getStringExtra(Constants.SOL);

        // Load the image
        Picasso.with(this).load(imageUrl).into(image);

        // Set the textViews
        roverNameTextView.setText("FROM: " + roverName);
        cameraNameTextView.setText(cameraName);
        photoDateTextView.setText(date);
        solTextView.setText(sol);

        // Send button click listener
        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animate(v);
                // TODO: Create Intent Chooser
                Bitmap postcard = getBitmap(postcardLayout);
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("image/jpeg");
                sendIntent.putExtra(Intent.EXTRA_STREAM, postcard);
                startActivity(Intent.createChooser(sendIntent, "Send postcard using"));

            }
        });

    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(
                v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public Bitmap getBitmap(RelativeLayout layout)
    {
        layout.setDrawingCacheEnabled(true);
        layout.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(layout.getDrawingCache());
        layout.setDrawingCacheEnabled(false);

        return bmp;

    }

    private void flattenImage(View view)
    {
        Bitmap b = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        view.draw(c);
    }

    // Animate the ImageView button clicks
    private void animate(View v)
    {
        v.setScaleY(0);
        v.setScaleX(0);
        v.animate().scaleX(1).scaleY(1).start();
    }
}
