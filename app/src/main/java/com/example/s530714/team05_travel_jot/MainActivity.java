package com.example.s530714.team05_travel_jot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.travel);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_home);
    }
    public void Entry (View v){
        email=findViewById(R.id.editText);
        String emailID = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z]+\\.+com+";

        if(emailID.matches(emailPattern) && emailID.length()>0) {
            Intent init = new Intent(this, Home.class);

            final String MY_PREFS_NAME ="MyPrefsFile";
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE).edit();
            editor.putString("saveemail", emailID);
            editor.apply();

            init.putExtra("emailhome",emailID);
            startActivity(init);

        }
        else{
            Toast.makeText(getBaseContext(),"Please enter correct Login credentials",Toast.LENGTH_SHORT).show();
        }
    }
}
