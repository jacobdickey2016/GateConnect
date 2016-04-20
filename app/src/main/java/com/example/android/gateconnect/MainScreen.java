package com.example.android.gateconnect;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

    }

    /**
     * Called when the user clicks the Connect button
     */
    public void onClick(View view) {
        //Spinner airport_select
        Spinner spinner1 = (Spinner) findViewById(R.id.airport_select);

        //get selected value here
        String value = spinner1.getSelectedItem().toString();

        //put selected value and start new activity
        Intent intent = new Intent(this, MapScreen.class);
        intent.putExtra("data", value);
        startActivity(intent);
    }

}