package com.example.s530714.team05_travel_jot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import static com.backendless.servercode.services.codegen.ServiceCodeFormat.as;

public class ActivityFriendsList extends AppCompatActivity {
    EditText email;
    String TaggedemailID;
    String emailID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.travel);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_friends_list);
    }
    public void Back (View v){
        Intent init = new Intent(this,Location.class);
        startActivity(init);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void Submit (View v){
        email=findViewById(R.id.editText4);
        TaggedemailID = email.getText().toString();
        String emailPattern = "^(,?[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)+$";
        if(TaggedemailID.matches(emailPattern) && TaggedemailID.length()>0) {
            CheckinTableInfo checkin = new CheckinTableInfo();
            Intent i =getIntent();
            emailID = i.getStringExtra("redirectemail");
            String nameID = i.getStringExtra("redirectname");
            String notes = i.getStringExtra("writenotes");
            String Location = i.getStringExtra("Location");
            String Radiobutton = i.getStringExtra("checkin");
            String Datevalue =  i.getStringExtra("Date");
            double Lat = i.getDoubleExtra("Latititude",0.0);
            double Long = i.getDoubleExtra("Longititude",0.0);
            checkin.Name=nameID;
            checkin.EmailID=emailID;
            checkin.PersonalNotes=notes;
            checkin.Checkin_Type=Radiobutton;
            checkin.Tagged_Email_id=TaggedemailID;
            checkin.Location=Location;
            checkin.Latitude=Lat;
            checkin.Longitude=Long;
            checkin.Datevalue=Datevalue;
            Backendless.Data.of(CheckinTableInfo.class).save(checkin, new AsyncCallback<CheckinTableInfo>() {

                @Override
                public void handleResponse(CheckinTableInfo response) {
                    Log.d("DB", "Inserted values into table" + response);
                    Intent inti = new Intent(getApplicationContext(), Home.class);
                    inti.putExtra("emailhome",emailID);
                    Toast.makeText(getApplicationContext(),"You have tagged"+" "+TaggedemailID,Toast.LENGTH_SHORT).show();
                    startActivity(inti);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("MYAPP", "Server reported an error " + fault.getMessage());
                }
            });


        }
        else{
            Toast.makeText(getBaseContext(),"Please enter the Email address",Toast.LENGTH_SHORT).show();
        }



    }
}
