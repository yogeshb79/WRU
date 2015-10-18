package com.kce.androlatlon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

/**
 * Created by Dell on 7/19/2015.
 */
public class HomeActivity extends ActionBarActivity {



    private Button btnLogout,btnShowLocation;
    TextView name;

    private GoogleMap map;

    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        name=(TextView)findViewById(R.id.nameHome);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);






        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(HomeActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

                    Marker davao = map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Home").snippet("Test Snippet"));

                    // zoom in the camera to Davao city
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(latitude, longitude)), 15));

                    // animate the zoom process
                    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);



                // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {


        // Launching the login activity
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
