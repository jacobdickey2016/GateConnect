package com.example.android.gateconnect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.PixelFormat;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
//import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


@SuppressWarnings("deprecation")
public class MapScreen extends AppCompatActivity {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Initialize //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //create paint
    Paint paint = new Paint();

    //initialize paths
    Path path_a;
    Path path_b;
    Path path_c;

    //create strings
    String Spinner_A1_Choice = "A";
    String Spinner_A2_Choice = "3";
    String Spinner_D1_Choice = "A";
    String Spinner_D2_Choice = "3";

    //create bitmaps
    Bitmap tempBitmap = null;
    BitmapDrawable mapDrawable;

    //create canvas
    Canvas tempCanvas;

    //create onDraw counter
    int counter = 0;

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

        // im not sure what picasso does, make bitmap smaller?
        //...error was with other bitmap, not this one...
        //keeping this line because it doesn't break anything
        //Picasso.with(this).load(R.drawable.atl_map).into(myMap);

        //updates the map with the correct paths
        findViewById(R.id.activity_map_screen).invalidate();

        //Change the Title to whichever airport was selected
        TextView tTextView = (TextView) findViewById(R.id.title_text);
        tTextView.setText(value);

        //Change the Map to whichever airport was selected
        switch (value) {
            case "ATL":
                Picasso.with(this).load(R.drawable.map_atl).into(myMap);
                break;
            case "DTW":
                Picasso.with(this).load(R.drawable.map_dtw).into(myMap);
                break;
            default:
                Picasso.with(this).load(R.drawable.map_ind).into(myMap);
                break;
        }

        //Creates all spinners (A1, A2, D1, D2)
        // A2 and D2 options are dependent on A1 and D1 selections respectively
        Create_Spinner_A1();
        Create_Spinner_D1();

        //creates the bitmap over the image to be drawn on
        //createBitmap();

        //myMap.invalidateDrawable(bpd);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // createBitmap //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // THIS ALLOWS THE MAP IMAGE TO BE DRAWN ON AND IT NOT AFFECT THE OTHER VIEWS ON THE SCREEN //
    public void createBitmap() {

        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        //Initialize Map
        ImageView myMap = (ImageView) findViewById(R.id.map);

        findViewById(R.id.activity_map_screen).invalidate();

        //set paint that the line will be drawn with
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(18f);
        paint.setColor(Color.RED);

        Drawable myDrawable;

        switch (value) {
            case "ATL":
                myDrawable = getResources().getDrawable(R.drawable.map_atl);
                break;
            case "DTW":
                myDrawable = getResources().getDrawable(R.drawable.map_dtw);
                break;
            default: // case "IND"
                myDrawable = getResources().getDrawable(R.drawable.map_ind);
                break;
        }

        assert (myDrawable) != null;


        //Create a new image bitmap and attach a brand new canvas to it
        tempBitmap = Bitmap.createBitmap(myDrawable.getIntrinsicWidth(),
                myDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        tempBitmap.setHasAlpha(true);

        Bitmap mapBitmap = ((BitmapDrawable) myDrawable).getBitmap();

        //Draw the image bitmap into the canvas
        tempCanvas = new Canvas(tempBitmap);

        //create everything else you want into the canvas (the path)
        tempCanvas.drawColor(Color.TRANSPARENT);
        tempCanvas.drawPath(path_a, paint);
        tempCanvas.drawPath(path_b, paint);
        tempCanvas.drawPath(path_c, paint);

        //Attach the canvas to the ImageView
        mapDrawable = new BitmapDrawable(getResources(), tempBitmap);
        myMap.setImageDrawable(mapDrawable);

        //draw it
        tempCanvas.drawBitmap(mapBitmap, 0, 0, null);

        mapDrawable.draw(tempCanvas);

        myMap.invalidate();

        myMap.invalidateDrawable(mapDrawable);

        findViewById(R.id.activity_map_screen).invalidate();

        counter++;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Create Spinner Listeners //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // The listener for Spinner A1 that sends whatever choice made to
    // the method that populates the second spinner.
    public class Spinner_A1_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> Spinner_A1_Adapter_View,
                                   View v, int position, long row) {
            //gets selected item
            Spinner_A1_Choice = Spinner_A1_Adapter_View
                    .getItemAtPosition(position).toString();
            findViewById(R.id.map).invalidate();
            Create_A2_Spinner(Spinner_A1_Choice);
            onDraw(tempCanvas);
            findViewById(R.id.map).invalidate();
            findViewById(R.id.activity_map_screen).invalidate();

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


    // The listener for Spinner A2 that records
    // whatever is currently selected.
    public class Spinner_A2_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> Spinner_A2_Adapter_View,
                                   View v, int position, long row) {
            Spinner_A2_Choice = Spinner_A2_Adapter_View
                    .getItemAtPosition(position).toString();
            findViewById(R.id.map).invalidate();
            onDraw(tempCanvas);
            findViewById(R.id.map).invalidate();
            findViewById(R.id.activity_map_screen).invalidate();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    // The listener for Spinner D1 that sends whatever choice made to
    // the method that populates the second spinner.
    public class Spinner_D1_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> Spinner_D1_Adapter_View,
                                   View v, int position, long row) {

            Spinner_D1_Choice = Spinner_D1_Adapter_View
                    .getItemAtPosition(position).toString();
            findViewById(R.id.map).invalidate();
            Create_D2_Spinner(Spinner_D1_Choice);
            onDraw(tempCanvas);
            findViewById(R.id.map).invalidate();
            findViewById(R.id.activity_map_screen).invalidate();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    // The listener for Spinner D2 that records
    // whatever is currently selected.
    public class Spinner_D2_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> Spinner_D2_Adapter_View,
                                   View v, int position, long row) {
            Spinner_D2_Choice = Spinner_D2_Adapter_View
                    .getItemAtPosition(position).toString();
            findViewById(R.id.map).invalidate();
            onDraw(tempCanvas);
            findViewById(R.id.map).invalidate();
            findViewById(R.id.activity_map_screen).invalidate();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Create Spinners A1 & A2 //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Creates Spinner A1 and sends the choice made to
    // a method for populating Spinner A2
    public Spinner Create_Spinner_A1() {

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
        return spinner_a1;
    }


    // Creates the second spinner for the Arriving Gate section
    public Spinner Create_A2_Spinner(String Spinner_A1_Choice) {

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
                        break;
                    case "B":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_b_numbers);
                        break;
                    case "C":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_c_numbers);
                        break;
                    case "D":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_d_numbers);
                        break;
                    case "E":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_e_numbers);
                        break;
                    case "F":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_f_numbers);
                        break;
                    default:
                        // " T "
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_t_numbers);
                        break;
                }
                break;
            case "DTW":
                switch (Spinner_A1_Choice) {
                    case "A":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_a_numbers);
                        break;
                    case "B":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_b_numbers);
                        break;
                    default:
                        // " C "
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_c_numbers);
                        break;
                }
                break;
            default:    // "IND" //
                Spinner_A2_Array = getResources().getStringArray(R.array.ind_gate_numbers);
                break;
        }


        SpinnerAdapter Spinner_A2_Adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                Spinner_A2_Array);
        Spinner_A2.setAdapter(Spinner_A2_Adapter);
        Spinner_A2.setOnItemSelectedListener(new Spinner_A2_Listener());
        return Spinner_A2;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Create Spinners D1 & D2 //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Creates Spinner D1 and sends the choice made to
    // a method for populating Spinner D2
    public Spinner Create_Spinner_D1() {

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
        return spinner_d1;
    }

    // Creates the second spinner for the Departing Gate section
    public Spinner Create_D2_Spinner(String Spinner_D1_Choice) {

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
                        //myMap.invalidateDrawable(bpd);
                        break;
                    case "B":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_b_numbers);
                        break;
                    case "C":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_c_numbers);
                        break;
                    case "D":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_d_numbers);
                        break;
                    case "E":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_e_numbers);
                        break;
                    case "F":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_f_numbers);
                        break;
                    default:
                        // " T "
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_t_numbers);
                        break;
                }
                break;
            case "DTW":
                switch (Spinner_D1_Choice) {
                    case "A":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_a_numbers);
                        break;
                    case "B":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_b_numbers);
                        break;
                    default:
                        // " C "
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_c_numbers);
                        break;
                }
                break;
            default:    // "IND" //
                Spinner_D2_Array = getResources().getStringArray(R.array.ind_gate_numbers);
                break;
        }

        SpinnerAdapter Spinner_D2_Adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                Spinner_D2_Array);
        Spinner_D2.setAdapter(Spinner_D2_Adapter);
        Spinner_D2.setOnItemSelectedListener(new Spinner_D2_Listener());
        return Spinner_D2;

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Set Paths //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setPaths(String a1_choice, String a2_choice, String d1_choice, String d2_choice) {
        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        try {
            //create a database helper so that cursors can navigate the database
            SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = DatabaseHelper.getReadableDatabase(); // open database

            float a_x = 0;
            float a_y = 0;
            float d_x = 0;
            float d_y = 0;

            float atl_hallway = 190 * 4;
            float dtw_hallway_x = (284 * 4) + 40;
            //float dtw_hallway_y = 295 * 2;

            float point_ax = 93  * 4;
            float point_ay = 222 * 4;
            float point_bx = 230 * 4;
            float point_by = 357 * 4;

            Cursor cursor_a = db.query("COORDINATES", // open cursor
                    new String[]{"x_coord", "y_coord"},
                    "airport_name = ? AND gate_letter = ? AND gate_number = ?",
                    new String[]{value, a1_choice, a2_choice},
                    null, null, null);

            try {
                if (cursor_a.moveToFirst()) {
                    a_x = cursor_a.getFloat(0);
                    a_y = cursor_a.getFloat(1);
                }
            } catch (Exception e) {
                // exception handling
            } finally {
                if (cursor_a != null) {
                    cursor_a.close(); // close cursor
                }
            }

            Cursor cursor_d = db.query("COORDINATES",
                    new String[]{"x_coord", "y_coord"},
                    "airport_name = ? AND gate_letter = ? AND gate_number = ?",
                    new String[]{value, d1_choice, d2_choice},
                    null, null, null);
            try {
                if (cursor_d.moveToFirst()) {
                    d_x = cursor_d.getFloat(0);
                    d_y = cursor_d.getFloat(1);
                }

            } catch (Exception e) {
                // exception handling
            } finally {
                if (cursor_d != null) {
                    cursor_d.close();
                }
            }

            //create paths
            path_a = new Path();
            path_b = new Path();
            path_c = new Path();

            //if the selected gates are in the same terminal
            if (value.equals("ATL") && a1_choice.equals(d1_choice)) {
                //move through the terminal from first gate to gate
//                path_a.moveTo(0, 0);
//                path_a.lineTo(2400, 1600);
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(d_x, d_y);
                path_a.close();
                path_b.close();
                path_c.close();
            } else if (value.equals("ATL")) {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(a_x, atl_hallway);
                path_a.close();

                //move through the hallway to next terminal
                path_b.moveTo(a_x, atl_hallway);
                path_b.lineTo(d_x, atl_hallway);
                path_b.close();

                //move through the terminal to the second gate
                path_c.moveTo(d_x, atl_hallway);
                path_c.lineTo(d_x, d_y);
                path_c.close();
            } //if the selected gates are in the same terminal
            else if (value.equals("DTW") && a1_choice.equals(d1_choice) ||
                     value.equals("DTW") && a1_choice.equals("C") && d1_choice.equals("B") ||
                     value.equals("DTW") && a1_choice.equals("B") && d1_choice.equals("C")) {
                //move through the terminal from first gate to gate
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(d_x, d_y);
                path_a.close();
                path_b.close();
                path_c.close();
            } else if (value.equals("DTW")) {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(dtw_hallway_x, a_y);
                path_a.close();

                //move through the hallway to next terminal
                path_b.moveTo(dtw_hallway_x, a_y);
                path_b.lineTo(dtw_hallway_x, d_y);
                path_b.close();

                //move through the terminal to the second gate
                path_c.moveTo(dtw_hallway_x, d_y);
                path_c.lineTo(d_x, d_y);
                path_c.close();
            } //if the selected gates are in the same terminal
            else if (value.equals("IND") && a1_choice.equals(d1_choice)) {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(d_x, d_y);
                path_a.close();
                path_b.close();
                path_c.close();
            } else if (value.equals("IND") && ((a_x + a_y) < (d_x + d_y))) {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(point_ax, point_ay);
                path_a.close();

                //move through the hallway to next terminal
                path_b.moveTo(point_ax, point_ay);
                path_b.lineTo(point_bx, point_by);
                path_b.close();

                //move through the terminal to the second gate
                path_c.moveTo(point_bx, point_by);
                path_c.lineTo(d_x, d_y);
                path_c.close();
            } else //(value.equals("IND") && ((a_x + a_y) > (d_x + d_y)))
            {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(point_bx, point_by);
                path_a.close();

                //move through the hallway to next terminal
                path_b.moveTo(point_bx, point_by);
                path_b.lineTo(point_ax, point_ay);
                path_b.close();

                //move through the terminal to the second gate
                path_c.moveTo(point_ax, point_ay);
                path_c.lineTo(d_x, d_y);
                path_c.close();
            }
            if (db.isOpen())
                db.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // On Draw //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void onDraw(Canvas canvas) {

        //use local variables as parameters to set paths
        setPaths(Spinner_A1_Choice, Spinner_A2_Choice, Spinner_D1_Choice, Spinner_D2_Choice);

        createBitmap();

        findViewById(R.id.map).invalidate();
        findViewById(R.id.activity_map_screen).invalidate();

    }

}

//    protected void onResume(ImageView myMap) {
//        super.onResume();
//        setContentView(R.layout.activity_map_screen);
//
//        myMap.invalidate();
//
//    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Create Listener for all Spinners //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*public class All_Spinner_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> Spinner_Adapter_View,
                                   View v, int position, long row) {

            //get id to specify for different spinners
            int id = v.getId();

            if (id == R.id.spinner_a1)
            {
                Spinner_A1_Choice = Spinner_Adapter_View
                        .getItemAtPosition(position).toString();
                findViewById(R.id.main).invalidate();
                Create_A2_Spinner(Spinner_A1_Choice);
            }
            else if (id == R.id.spinner_a2)
            {
                Spinner_A2_Choice = Spinner_Adapter_View
                        .getItemAtPosition(position).toString();
                findViewById(R.id.main).invalidate();
            }
            else if (id == R.id.spinner_d1)
            {
                Spinner_D1_Choice = Spinner_Adapter_View
                        .getItemAtPosition(position).toString();
                findViewById(R.id.main).invalidate();
                Create_D2_Spinner(Spinner_D1_Choice);
            }
            else if (id == R.id.spinner_d2)
            {
                Spinner_D2_Choice = Spinner_Adapter_View
                        .getItemAtPosition(position).toString();
                findViewById(R.id.main).invalidate();
            }

            //finally draw using the spinner choices
            setPaths(Spinner_A1_Choice, Spinner_A2_Choice, Spinner_D1_Choice, Spinner_D2_Choice);
            onDraw(tempCanvas);
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }

    }*/