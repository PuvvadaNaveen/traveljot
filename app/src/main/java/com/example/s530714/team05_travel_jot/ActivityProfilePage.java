package com.example.s530714.team05_travel_jot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityProfilePage extends AppCompatActivity {
    RadioGroup rg;
    RadioButton radioButton;
    RadioButton rb1;
    RadioButton rb2;
    EditText email;
    EditText name;
    EditText named;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.travel);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        // boolean checked = ((RadioButton) view).isChecked();


//        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        email = findViewById(R.id.editText3);
        named = findViewById(R.id.editText2);
        Intent init = getIntent();
        String EmailID = init.getStringExtra("emailprof");
        String namesed = init.getStringExtra("names");
        email.setText(EmailID);
        named.setText(namesed);

        final String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("saveemail", "Traveljot@gmail.com");
        Log.d("my saved email", "" + restoredText);
        email.setText(restoredText);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void checkin(View v) {

        name = findViewById(R.id.editText2);
        String emailID = email.getText().toString();
        String nameID = name.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+com+";

        if (emailID.matches(emailPattern) && emailID.length() > 0 && nameID.length() > 0 && (rb1.isChecked()==true || rb2.isChecked() == true) ) {
            Intent i = new Intent(this, Location.class);
            i.putExtra("emailloca", email.getText().toString());
            i.putExtra("name", nameID);
            i.putExtra("radiobutton",radioButton.getText().toString());
            startActivity(i);
        } else {
            Toast.makeText(getBaseContext(), "Please enter Valid Information ", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View v) {
        Intent in = new Intent(this, Home.class);
        startActivity(in);
    }

    public void rbClick(View v) {

        int selectedId = rg.getCheckedRadioButtonId();
        if (selectedId != -1) {
            radioButton = (RadioButton) findViewById(selectedId);
            String s =radioButton.getText().toString() ;
        }
        else {

            Toast.makeText(ActivityProfilePage.this,
                    "Please select either public or private", Toast.LENGTH_SHORT).show();
        }
            if (rb1.isChecked() == true) {
                Toast.makeText(ActivityProfilePage.this, "Personal Notes: Can be viewed by only you", Toast.LENGTH_LONG).show();

            }

            if (rb2.isChecked() == true) {
                Toast.makeText(ActivityProfilePage.this, "Personal Notes: Can be viewed by everyone", Toast.LENGTH_LONG).show();

            }
    }
}
