package com.example.android.gateconnect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class checkdatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdatabase);

        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        //SQLDatabase Helper
        try {
            SQLiteOpenHelper dbhelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor cursor = db.query("COORDINATES",
                    new String[] {"airport_name", "gate_letter", "gate_number", "x-coord", "y-coord"},
                    null, null, null, null, null);

            //intialize string builder outside so that it may be referenced by output
            StringBuilder results = new StringBuilder();

            //Code to do something with the cursor
            do {

                //Initialize strings to hold values
                String result1 = cursor.getString(1);
                String result2 = cursor.getString(2);
                Integer result3 = cursor.getInt(3);
                Integer result4 = cursor.getInt(4);
                Integer result5 = cursor.getInt(5);

                //add values together to one string so it can be printed
                results.append(result1);
                results.append(result2);
                results.append(result3);
                results.append(result4);
                results.append(result5);

            } while (cursor.moveToNext());

            TextView output = (TextView) findViewById(R.id.output);
            output.setText(results);

            cursor.close();
            db.close();

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
