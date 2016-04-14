package com.example.android.gateconnect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MapScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Initialize //
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //create paint
    Paint paint = new Paint();

    //create paths
    Path path_a = new Path();
    Path path_b = new Path();
    Path path_c = new Path();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // On Create //
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        //Initialize Map
        ImageView myMap = (ImageView) findViewById(R.id.map);

        //updates the map with the correct paths
        myMap.invalidate();

        //Change the Title to whichever airport was selected
        TextView tTextView = (TextView) findViewById(R.id.title_text);
        tTextView.setText(value);

        //Change the Map to whichever airport was selected
        switch (value) {
            case "ATL":
                myMap.setImageResource(R.drawable.atl_map);
                break;
            case "DTW":
                myMap.setImageResource(R.drawable.dtw_map);
                break;
            default:
                myMap.setImageResource(R.drawable.ind_map);
                break;
        }

        //Creates all spinners (A1, A2, D1, D2)
        // A2 and D2 options are dependent on A1 and D1 selections respectively
        Spinner spinner_a1 = Create_Spinner_A1(myMap);
        Spinner spinner_d1 = Create_Spinner_D1(myMap);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Create Spinners A1 & A2 //
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Creates Spinner A1 and sends the choice made to
    // a method for populating Spinner A2
    public Spinner Create_Spinner_A1(ImageView myMap) {
        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        Spinner spinner_a1 = (Spinner) findViewById(R.id.spinner_a1);

        String[] gate_letters;

        //Initialize Spinner A1 based on whichever airport was selected
        switch (value) {
            case "ATL":
                gate_letters = getResources().getStringArray(
                        R.array.atl_gate_letters);
                break;
            case "DTW":
                gate_letters = getResources().getStringArray(
                        R.array.dtw_gate_letters);
                break;
            default:
                gate_letters = getResources().getStringArray(
                        R.array.ind_gate_letters);
                break;
        }

        SpinnerAdapter spinner_a1_adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                gate_letters);
        spinner_a1.setAdapter(spinner_a1_adapter);
        spinner_a1.setOnItemSelectedListener(new Spinner_A1_Listener());
        myMap.invalidate();
        return spinner_a1;
    }

    // The listener for Spinner A1 that sends whatever choice made to
    // the method that populates the second spinner.
    public class Spinner_A1_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?>Spinner_A1_Adapter_View,
                                   View v, int position, long row) {
            String Spinner_A1_Choice = Spinner_A1_Adapter_View
                    .getItemAtPosition(position).toString();
            Create_A2_Spinner(Spinner_A1_Choice);
        }

        public void onNothingSelected(AdapterView<?> arg0) {


        }

    }

    // Creates the second spinner for the Arriving Gate section
    public Spinner Create_A2_Spinner(String Spinner_A1_Choice) {

        //Initialize Map
        ImageView myMap = (ImageView) findViewById(R.id.map);

        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        String[] Spinner_A2_Array;

        Spinner Spinner_A2 = (Spinner) findViewById(R.id.spinner_a2);

        switch (value) {
            case "ATL":
                switch (Spinner_A1_Choice) {
                    case "A":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_a_numbers);
                        myMap.invalidate();
                        break;
                    case "B":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_b_numbers);
                        myMap.invalidate();
                        break;
                    case "C":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_c_numbers);
                        myMap.invalidate();
                        break;
                    case "D":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_d_numbers);
                        myMap.invalidate();
                        break;
                    case "E":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_e_numbers);
                        myMap.invalidate();
                        break;
                    case "F":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_f_numbers);
                        myMap.invalidate();
                        break;
                    default:
                        // " T "
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_t_numbers);
                        myMap.invalidate();
                        break;
                }
                break;
            case "DTW":
                switch (Spinner_A1_Choice) {
                    case "A":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_a_numbers);
                        myMap.invalidate();
                        break;
                    case "B":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_b_numbers);
                        myMap.invalidate();
                        break;
                    default:
                        // " C "
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_c_numbers);
                        myMap.invalidate();
                        break;
                }
                break;
            default:    // "IND" //
                Spinner_A2_Array = getResources().getStringArray(R.array.ind_gate_numbers);
                myMap.invalidate();
                break;
        }


        SpinnerAdapter Spinner_A2_Adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                Spinner_A2_Array);
        Spinner_A2.setAdapter(Spinner_A2_Adapter);
        //Spinner_A2.setOnItemSelectedListener(new Spinner_A2_Listener());
        return Spinner_A2;
    }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Create Spinners D1 & D2 //
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Creates Spinner D1 and sends the choice made to
    // a method for populating Spinner D2
    public Spinner Create_Spinner_D1(ImageView myMap) {
        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        Spinner spinner_d1 = (Spinner) findViewById(R.id.spinner_d1);

        String[] gate_letters;

        //Initialize Spinner A1 based on whichever airport was selected
        switch (value) {
            case "ATL":
                gate_letters = getResources().getStringArray(
                        R.array.atl_gate_letters);
                break;
            case "DTW":
                gate_letters = getResources().getStringArray(
                        R.array.dtw_gate_letters);
                break;
            default:
                gate_letters = getResources().getStringArray(
                        R.array.ind_gate_letters);
                break;
        }
        SpinnerAdapter spinner_d1_adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                gate_letters);
        spinner_d1.setAdapter(spinner_d1_adapter);
        spinner_d1.setOnItemSelectedListener(new Spinner_D1_Listener());
        myMap.invalidate();
        return spinner_d1;
    }

    // The listener for Spinner D1 that sends whatever choice made to
    // the method that populates the second spinner.
    public class Spinner_D1_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> Spinner_D1_Adapter_View,
                                   View v, int position, long row) {
            //Initialize Map
            ImageView myMap = (ImageView) findViewById(R.id.map);

            String Spinner_D1_Choice = Spinner_D1_Adapter_View
                    .getItemAtPosition(position).toString();
            Create_D2_Spinner(Spinner_D1_Choice, myMap);
        }

        public void onNothingSelected(AdapterView<?> arg0) {


        }

    }

        // Creates the second spinner for the Departing Gate section
    public Spinner Create_D2_Spinner(String Spinner_D1_Choice, ImageView myMap) {
        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        String[] Spinner_D2_Array;

        Spinner Spinner_D2 = (Spinner) findViewById(R.id.spinner_d2);

        switch (value) {
            case "ATL":
                switch (Spinner_D1_Choice) {
                    case "A":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_a_numbers);
                        myMap.invalidate();
                        break;
                    case "B":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_b_numbers);
                        myMap.invalidate();
                        break;
                    case "C":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_c_numbers);
                        myMap.invalidate();
                        break;
                    case "D":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_d_numbers);
                        myMap.invalidate();
                        break;
                    case "E":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_e_numbers);
                        myMap.invalidate();
                        break;
                    case "F":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_f_numbers);
                        myMap.invalidate();
                        break;
                    default:
                        // " T "
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_t_numbers);
                        myMap.invalidate();
                        break;
                }
                break;
            case "DTW":
                switch (Spinner_D1_Choice) {
                    case "A":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_a_numbers);
                        myMap.invalidate();
                        break;
                    case "B":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_b_numbers);
                        myMap.invalidate();
                        break;
                    default:
                        // " C "
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_c_numbers);
                        myMap.invalidate();
                        break;
                }
                break;
            default:    // "IND" //
                Spinner_D2_Array = getResources().getStringArray(R.array.ind_gate_numbers);
                myMap.invalidate();
                break;
        }

            SpinnerAdapter Spinner_D2_Adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_dropdown_item,
                    Spinner_D2_Array);
            Spinner_D2.setAdapter(Spinner_D2_Adapter);
            //Spinner_D2.setOnItemSelectedListener(new Spinner_D2_Listener());
            myMap.invalidate();
            return Spinner_D2;

        }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // createBitmap //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // THIS ALLOWS THE MAP IMAGE TO BE DRAWN ON AND IT NOT AFFECT THE OTHER VIEWS ON THE SCREEN //
    public void createBitmap()
    {
        //Map
        //ImageView myMap = (ImageView) findViewById(R.id.map);

        BitmapFactory.Options options = new BitmapFactory.Options();

        //int imageHeight = options.outHeight;
        //int imageWidth = options.outWidth;
        //String imageType = options.outMimeType;

        //code used to free up unused bytes
        options.inJustDecodeBounds = true;

        //Create bitmap based on whichever image was selected
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.id.map, options);

        //Create a new image bitmap and attach a canvas to it
        /*Bitmap tempBitmap = Bitmap.createBitmap(imageWidth,
                imageHeight, Bitmap.Config.RGB_565);*/

        //attach bitmap to canvas
        Canvas canvas = new Canvas(myBitmap);

        //Draw the image bitmap into the canvas
        canvas.drawBitmap(myBitmap, 0, 0, null);

        //Draw the path onto the canvas using paint
        canvas.drawPath(path_a, paint);
        canvas.drawPath(path_b, paint);
        canvas.drawPath(path_c, paint);

        onDraw(canvas);

        //Create a new drawable bitmap
        //BitmapDrawable bpd = new BitmapDrawable(getResources(), myBitmap);

        //Attach drawable to ImageView myMap
        //myMap.setImageDrawable(bpd);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Set Paths //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setPaths(String a1_choice, String a2_choice, String d1_choice, String d2_choice)
    {
        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        try {
            //create a database helper so that cursors can navigate the database
            SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = DatabaseHelper.getReadableDatabase();

            int hallway = 1500;
            int a_x = 1;
            int a_y = 1;
            int d_x = 1;
            int d_y = 1;

            Cursor cursor_a = db.query("COORDINATES",
                    new String[] {"x-coord", "y-coord"},
                    "airport_name = ? AND airport_letter = ? AND airport_number = ?",
                    new String[] {value, a1_choice, a2_choice},
                    null, null, null);

            if(cursor_a.moveToFirst())
            {
                a_x = cursor_a.getInt(0);
                a_y = cursor_a.getInt(1);
            }

            Cursor cursor_d = db.query("COORDINATES",
                    new String[] {"x-coord", "y-coord"},
                    "airport_name = ? AND airport_letter = ? AND airport_number = ?",
                    new String[] {value, d1_choice, d2_choice},
                    null, null, null);

            if(cursor_d.moveToFirst())
            {
                d_x = cursor_d.getInt(0);
                d_y = cursor_d.getInt(1);
            }

            //move through the terminal from first gate to hallway
            path_a.moveTo(a_x, a_y);
            path_a.lineTo(a_x, hallway);
            path_a.close();

            //move through the hallway to next terminal
            path_b.moveTo(a_x, hallway);
            path_b.lineTo(d_x, hallway);
            path_b.close();

            //move through the terminal to the second gate
            path_c.moveTo(d_x, hallway);
            path_c.lineTo(d_x, d_y);
            path_c.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos);
        Log.v("onItemSelected", (String) parent.getItemAtPosition(pos));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // On Draw //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void onDraw(Canvas canvas) {

        //creates the bitmap over the image to be drawn on
        createBitmap();

        //set paint that the line will be drawn with
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(18f);
        paint.setColor(Color.RED);

        //create local spinners
        Spinner Spinner_A1 = (Spinner) findViewById(R.id.spinner_a1);
        Spinner Spinner_A2 = (Spinner) findViewById(R.id.spinner_a2);
        Spinner Spinner_D1 = (Spinner) findViewById(R.id.spinner_d1);
        Spinner Spinner_D2 = (Spinner) findViewById(R.id.spinner_d2);

        //attach listeners to spinners
        Spinner_A1.setOnItemSelectedListener(this);
        Spinner_A2.setOnItemSelectedListener(this);
        Spinner_D1.setOnItemSelectedListener(this);
        Spinner_D2.setOnItemSelectedListener(this);

        //set values of the selected items in each spinner to local variables
        String a_x = Spinner_A1.getSelectedItem().toString();
        String a_y = Spinner_A2.getSelectedItem().toString();
        String d_x = Spinner_D1.getSelectedItem().toString();
        String d_y = Spinner_D2.getSelectedItem().toString();

        //use local variables as parameters to set paths
        setPaths(a_x, a_y, d_x, d_y);

        //Draw the path onto the canvas using paint
        canvas.drawPath(path_a, paint);
        canvas.drawPath(path_b, paint);
        canvas.drawPath(path_c, paint);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_map_screen);

        //Map
        ImageView myMap = (ImageView) findViewById(R.id.map);

        myMap.invalidate();
    }

}


