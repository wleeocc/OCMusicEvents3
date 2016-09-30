package edu.orangecoastcollege.cs273.wlee.ocmusicevents;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EventListActivity extends ListActivity {

   // private ListView eventsListView;
    private Context context = this;
    private ArrayList<MusicEvent> allMusicEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_event_list);

        //eventsListView = (ListView) findViewById(R.id.eventsListView);

        try {
            allMusicEvents = JSONLoader.loadJSONFromAsset(context);
        }
        catch (IOException ex)
        {
            Log.e("OC Music Events", "Error loading JSON data." + ex.getMessage());
        }

        //setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles)); // comment out
        setListAdapter(new MusicEventAdapter(context, R.layout.music_event_list_item_layout, allMusicEvents));
            // pull the data in from JSON


        // setContentView(R.layout.activity_event_list);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {
        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        MusicEvent clickedEvent = allMusicEvents.get(pos);

        String title = clickedEvent.getTitle();
        String date = clickedEvent.getDate();
        String day = clickedEvent.getDay();
        String location = clickedEvent.getLocation();
        String address1 = clickedEvent.getAddress1();
        String address2 = clickedEvent.getAddress2();

        detailsIntent.putExtra("Title", title);
        detailsIntent.putExtra("Date", date);
        detailsIntent.putExtra("Day", day);
        detailsIntent.putExtra("Location", location);
        detailsIntent.putExtra("Address1", address1);
        detailsIntent.putExtra("Address2", address2);

        startActivity(detailsIntent);
    }
/*
    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {
        // 1) Get the positions, get the title, get the details
        // 2) Make a new Intent
        // 3) put the strings into the intent
        // 4) start activity
    }
*/
}
