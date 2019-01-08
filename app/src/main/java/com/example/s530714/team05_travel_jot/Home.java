package com.example.s530714.team05_travel_jot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {
    EditText email;
    String whereClause;
    TextView notes;
    String Emailprofil;
    String Taggedemail;
    String s;
    String nameValue;
    String checkinValue;
    String location;
    String Personal_notes;
    String taggedemail;
    String datevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.travel);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_note_view);
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );

        notes = findViewById(R.id.textView6);
        notes.setMovementMethod(new ScrollingMovementMethod());
        Intent i = getIntent();
        Emailprofil = i.getStringExtra("emailhome");
        Taggedemail= i.getStringExtra("emailhome");
        String whereClause = "emailID = '" + Emailprofil + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause( whereClause );
        Backendless.Data.of( "CheckinTableInfo" ).find( queryBuilder, new AsyncCallback<List<Map>>(){
            @Override
            public void handleResponse( List<Map> foundContacts )
            {

                List<String> temp = new LinkedList<>();
                HashMap<String,String> hashmap1= new HashMap<>();
                String str1 = null;
                String str2=null;
                String str3=null;
                String str4 = null;
                Log.d("den",""+foundContacts);
                for(int i=0; i<foundContacts.size(); i++) {
                    for (Object item : foundContacts.get(i).entrySet()) {
                        s = item.toString();

                        if (s.contains("name")) {
                            nameValue = s.substring(s.indexOf("=") + 1);
                        }

                        if (s.contains("Checkin_Type")) {
                            checkinValue = s.substring(s.indexOf("=") + 1);
                        }

                        if (s.contains("Location")) {
                           location = s.substring(s.indexOf("=") + 1);
                            str1=location;
                        }

                        if (s.contains("PersonalNotes")) {
                            Personal_notes = s.substring(s.indexOf("=") + 1);
                            str2=Personal_notes;
                        }

                        if (s.contains("Tagged_Email_id")) {
                            taggedemail = s.substring(s.indexOf("=") + 1);
                            str3=taggedemail;
                        }

                        if (s.contains("datevalue")) {
                            datevalue = s.substring(s.indexOf("=") + 1);
                            str4=datevalue;
                        }

//                        if (s.contains("name") || s.contains("personalNotes") || s.contains("Location") || s.contains("tagged_Email_id") || s.contains("datevalue")) {
//                            temp.add(item.toString());
//
//                        }

                        hashmap1.put("\nLocation: "+str1,"\nPersonal notes: "+str2+"\nTagged_Email: "+str3+"\nDate: "+str4);
                            hashmap1.remove("Location: null");
                            notes.setText("Name: " + nameValue +"\n"+hashmap1);
                    }

                }
            }

            @Override
            public void handleFault( BackendlessFault fault )
            {
                Log.d("fault", String.valueOf(fault));
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void profil (View v){
        Intent i =getIntent();
        String Emailprofil = i.getStringExtra("emailhome");
        Intent pro = new Intent(this, ActivityProfilePage.class);
        pro.putExtra("emailprof",Emailprofil);
        pro.putExtra("names",nameValue);
        startActivity(pro);
    }
    public void home (View v){
        Intent pro = new Intent(this, MainActivity.class);
        startActivity(pro);
    }

    public void CheckIN(View v)
    {
        Intent i =getIntent();
        String Emailprofil = i.getStringExtra("emailhome");
        Intent in = new Intent(getApplicationContext(), Location.class);
        in.putExtra("emailloca",Emailprofil);
        in.putExtra("name",nameValue);
        in.putExtra("radiobutton",checkinValue);
        startActivity(in);
    }
}
