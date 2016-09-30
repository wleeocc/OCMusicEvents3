package edu.orangecoastcollege.cs273.wlee.ocmusicevents;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    private ImageView eventImageView; // step 1

    private TextView eventTitleTextView;
    private TextView eventDateDayTextView;
    private TextView eventTimeTextView;
    private TextView eventLocationTextView;
    private TextView eventAddress1TextView;
    private TextView eventAddress2TextView;

    // In order to use AssetManager, need to know Context // Step 5-1
    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details); // Step 3

        eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDateDayTextView = (TextView) findViewById(R.id. eventDateDayTextView);
        eventTimeTextView = (TextView) findViewById(R.id. eventTimeTextView);
        eventLocationTextView = (TextView) findViewById(R.id. eventLocationTextView);
        eventAddress1TextView = (TextView) findViewById(R.id. eventAddress1TextView);
        eventAddress2TextView = (TextView) findViewById(R.id. eventAddress2TextView);

        // Get the data from Intent // Step 4
        Intent detailsIntent = getIntent();
        String title = detailsIntent.getStringExtra("Title");
        String date = detailsIntent.getStringExtra("Date");
        String day = detailsIntent.getStringExtra("Day");
        String location = detailsIntent.getStringExtra("Location");
        String address1 = detailsIntent.getStringExtra("Address1");
        String address2 = detailsIntent.getStringExtra("Address2");
        String imageFileName = title.replace(" ", "") + ".jpeg";

        eventImageView = (ImageView) findViewById(R.id.eventImageView);

        // Load the image from the Assets folder using the AssetManager Class // Step 5
        AssetManager am = context.getAssets();

        // Try to load the image file // Step 6
        try{
            InputStream stream = am.open(imageFileName);
            Drawable image = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(image);
        }
        catch (IOException ex)
        {
            Log.e("OC Music Events", "Cannot Load image: " + imageFileName + ex);
        }

        eventTitleTextView.setText(title);
        eventDateDayTextView.setText(date);
        eventTimeTextView.setText(day);
        eventLocationTextView.setText(location);
        eventAddress1TextView.setText(address1);
        eventAddress2TextView.setText(address2);

        //eventImageView = (ImageView) findViewById(R.id.eventImageView); // step 2
    }



}
