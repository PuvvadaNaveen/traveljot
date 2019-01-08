package com.example.s530714.team05_travel_jot;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.text.DateFormat;

public class Location extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button button;
    EditText textView;
    EditText personalnotes;
    private static final int MY_PERMISSION_REQUEST_LOCATION=1;
    android.location.Location location;
    double Latitude;
    double Longitude;
String Datevalue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.travel);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_location);
        Button but = (Button) findViewById(R.id.button3);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
        button=(Button)findViewById(R.id.button8);
        textView=(EditText) findViewById(R.id.editText);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(Location.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.shouldShowRequestPermissionRationale(Location.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)){
                        ActivityCompat.requestPermissions(Location.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                    }
                    else{
                        ActivityCompat.requestPermissions(Location.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSION_REQUEST_LOCATION);


                    }



                }
                else{
                    LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    android.location.Location location=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
//                    Location location=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                    try {
                        textView.setText(hereLocation(location.getLatitude(), location.getLongitude()));
                        Latitude= location.getLatitude();
                        Longitude =location.getLongitude();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(Location.this,"not found",Toast.LENGTH_SHORT).show();

                    }
                }
            }

        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(Location.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                        android.location.Location location=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

                        try{
                            textView.setText(hereLocation(location.getLatitude(),location.getLongitude()));
                            Latitude= location.getLatitude();
                            Longitude =location.getLongitude();

                        }
                        catch(Exception e){

                            Toast.makeText(Location.this,"not found",Toast.LENGTH_SHORT).show();

                        }
                    }


                }
                else{

                    Toast.makeText(Location.this,"No permission granted",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public String hereLocation(double lat, double lon){
        String curcity="";
        Geocoder geocoder=new Geocoder(Location.this, Locale.getDefault());
        List<Address> addressList;
        try{
            addressList=geocoder.getFromLocation(lat,lon,1);
            if(addressList.size()>0){
                curcity=addressList.get(0).getFeatureName()+","+addressList.get(0).getThoroughfare()+","+addressList.get(0).getLocality();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return curcity;
    }

    public void Tag(View v) {
        personalnotes=findViewById(R.id.Edit);
        EditText Date = (EditText) findViewById(R.id.editText5);
        personalnotes.setMovementMethod(new ScrollingMovementMethod());
        String notes = personalnotes.getText().toString();
        Datevalue = Date.getText().toString();
        if (notes.length() > 0) {
            Intent i =getIntent();
            String emailID = i.getStringExtra("emailloca");
            String nameID = i.getStringExtra("name");
            String Radiobutton = i.getStringExtra("radiobutton");
            Intent init = new Intent(this, ActivityFriendsList.class);
            init.putExtra("writenotes",notes);
            init.putExtra("redirectemail",emailID);
            init.putExtra("redirectname",nameID);
            init.putExtra("Location",textView.getText().toString());
            init.putExtra("checkin",Radiobutton);
            init.putExtra("Longititude", Longitude);
            init.putExtra("Latititude",Latitude);
            init.putExtra("Date",Datevalue);
            startActivity(init);

        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter Personal notes to submit", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void Cancel(View v){
        Intent init = new Intent(this,Home.class);
        startActivity(init);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currdate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        EditText ed = (EditText) findViewById(R.id.editText5);
        ed.setText(currdate);

    }
}
