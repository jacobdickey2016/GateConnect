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
//import android.graphics.drawable.BitmapDrawable;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

//import java.io.File;

//todo:: the path still does not display

public class MapScreen extends AppCompatActivity {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Initialize //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //create paint
    Paint paint = new Paint();

    //create paths
    Path path_a = new Path();
    Path path_b = new Path();
    Path path_c = new Path();

    //create strings
    String Spinner_A1_Choice = "A";
    String Spinner_A2_Choice = "1";
    String Spinner_D1_Choice = "A";
    String Spinner_D2_Choice = "15";

    //create drawable bitmap
    //BitmapDrawable bpd;

    //create canvas
    Canvas tempCanvas;

    //create bitmap
    //Bitmap myBitmap;

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
        //keeping this line because it doesnt cause issues
        Picasso.with(this).load(R.drawable.atl_map).into(myMap);

        //updates the map with the correct paths
        findViewById(R.id.main).invalidate();

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
        Create_Spinner_A1(myMap);
        Create_Spinner_D1(myMap);

        //creates the bitmap over the image to be drawn on
        createBitmap();

        //myMap.invalidate();
        //myMap.invalidateDrawable(bpd);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // createBitmap //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // THIS ALLOWS THE MAP IMAGE TO BE DRAWN ON AND IT NOT AFFECT THE OTHER VIEWS ON THE SCREEN //
    public void createBitmap()
    {
        //Map
        ImageView myMap = (ImageView) findViewById(R.id.map);

        BitmapFactory.Options options = new BitmapFactory.Options();

        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        //String imageType = options.outMimeType;
        options.inJustDecodeBounds = true;
        options.inMutable = true;

        //Create bitmap based on whichever image was selected
        //Bitmap tempBitmap = BitmapFactory.decodeResource(getResources(), R.id.map, options);

        //Create a new image bitmap and attach a brand new canvas to it
        Bitmap tempBitmap = Bitmap.createBitmap(imageWidth + 1,
                imageHeight + 1, Bitmap.Config.RGB_565);

        //Draw the image bitmap into the canvas
        tempCanvas = new Canvas(tempBitmap);
//        tempCanvas.drawBitmap(myMap.getDrawingCache(), 0, 0, null);
        tempCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.atl_map), 0, 0, null);

        //Draw everything else you want into the canvas (the path)
        tempCanvas.drawPath(path_a, paint);
        tempCanvas.drawPath(path_b, paint);
        tempCanvas.drawPath(path_c, paint);

        //Attach the canvas to the ImageView
        myMap.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

        //onDraw(tempCanvas);

        myMap.invalidate();

        //Attach the canvas to the ImageView
        //BitmapDrawable bpd = new BitmapDrawable(getResources(), tempBitmap);

        //myMap.setImageDrawable(bpd);

        //myMap.invalidateDrawable(bpd);
    }

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Create Spinner Listeners //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // The listener for Spinner A1 that sends whatever choice made to
    // the method that populates the second spinner.
    public class Spinner_A1_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?>Spinner_A1_Adapter_View,
                                   View v, int position, long row) {
            //gets selected item
            Spinner_A1_Choice = Spinner_A1_Adapter_View
                    .getItemAtPosition(position).toString();
            findViewById(R.id.main).invalidate();

            Create_A2_Spinner(Spinner_A1_Choice);
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
            findViewById(R.id.main).invalidate();
            //onDraw(tempCanvas);
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
            findViewById(R.id.main).invalidate();
            Create_D2_Spinner(Spinner_D1_Choice);

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
            findViewById(R.id.main).invalidate();
            onDraw(tempCanvas);
        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
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
                        //myMap.invalidate();
                        break;
                    case "B":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_b_numbers);
                        //myMap.invalidate();
                        break;
                    case "C":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_c_numbers);
                        //myMap.invalidate();
                        break;
                    case "D":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_d_numbers);
                        //myMap.invalidate();
                        break;
                    case "E":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_e_numbers);
                        //myMap.invalidate();
                        break;
                    case "F":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_f_numbers);
                        //myMap.invalidate();
                        break;
                    default:
                        // " T "
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.atl_gate_t_numbers);
                        //myMap.invalidate();
                        break;
                }
                break;
            case "DTW":
                switch (Spinner_A1_Choice) {
                    case "A":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_a_numbers);
                        //myMap.invalidate();
                        break;
                    case "B":
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_b_numbers);
                        //myMap.invalidate();
                        break;
                    default:
                        // " C "
                        Spinner_A2_Array = getResources().getStringArray(
                                R.array.dtw_gate_c_numbers);
                        //myMap.invalidate();
                        break;
                }
                break;
            default:    // "IND" //
                Spinner_A2_Array = getResources().getStringArray(R.array.ind_gate_numbers);
                //myMap.invalidate();
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
                        //myMap.invalidate();
                        //myMap.invalidateDrawable(bpd);
                        break;
                    case "B":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_b_numbers);
                        //myMap.invalidate();
                        break;
                    case "C":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_c_numbers);
                        //myMap.invalidate();
                        break;
                    case "D":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_d_numbers);
                        //myMap.invalidate();
                        break;
                    case "E":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_e_numbers);
                        //myMap.invalidate();
                        break;
                    case "F":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_f_numbers);
                        //myMap.invalidate();
                        break;
                    default:
                        // " T "
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.atl_gate_t_numbers);
                        //myMap.invalidate();
                        break;
                }
                break;
            case "DTW":
                switch (Spinner_D1_Choice) {
                    case "A":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_a_numbers);
                        //myMap.invalidate();
                        break;
                    case "B":
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_b_numbers);
                        //myMap.invalidate();
                        break;
                    default:
                        // " C "
                        Spinner_D2_Array = getResources().getStringArray(
                                R.array.dtw_gate_c_numbers);
                        //myMap.invalidate();
                        break;
                }
                break;
            default:    // "IND" //
                Spinner_D2_Array = getResources().getStringArray(R.array.ind_gate_numbers);
                //myMap.invalidate();
                break;
        }

        SpinnerAdapter Spinner_D2_Adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                Spinner_D2_Array);
        Spinner_D2.setAdapter(Spinner_D2_Adapter);
        Spinner_D2.setOnItemSelectedListener(new Spinner_D2_Listener());
        //myMap.invalidate();
        return Spinner_D2;

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
            SQLiteDatabase db = DatabaseHelper.getReadableDatabase(); // open database

            float atl_hallway = 190;
            float dtw_hallway = 295;
            float a_x = 100;
            float a_y = 100;
            float d_x = 300;
            float d_y = 300;

           /* //move through the terminal from first gate to hallway
            path_a.moveTo(1, 1);
            path_a.lineTo(100, 100);
            path_a.close();

            //move through the hallway to next terminal
            path_b.moveTo(100, 100);
            path_b.lineTo(200, 150);
            path_b.close();

            //move through the terminal to the second gate
            path_c.moveTo(200, 150);
            path_c.lineTo(2, 2);
            path_c.close();*/

            Cursor cursor_a = db.query("COORDINATES", // open cursor
                    new String[] {"x_coord", "y_coord"},
                    "airport_name = ? AND gate_letter = ? AND gate_number = ?",
                    new String[] {value, a1_choice, a2_choice},
                    null, null, null);

            try {
                if(cursor_a.moveToFirst())
                {
                    d_x = cursor_a.getFloat(0);
                    d_y = cursor_a.getFloat(1);
                }
            } catch (Exception e) {
                // exception handling
            } finally {
                if(cursor_a != null){
                    cursor_a.close(); // close cursor
                }
            }

            Cursor cursor_d = db.query("COORDINATES",
                    new String[] {"x_coord", "y_coord"},
                    "airport_name = ? AND gate_letter = ? AND gate_number = ?",
                    new String[] {value, d1_choice, d2_choice},
                    null, null, null);
            try {
                if(cursor_d.moveToFirst())
                {
                    d_x = cursor_d.getFloat(0);
                    d_y = cursor_d.getFloat(1);
                }

            } catch (Exception e) {
                // exception handling
            } finally {
                if(cursor_d != null){
                    cursor_d.close();
                }
            }


            if (value.equals("ATL") && a1_choice.equals(d1_choice))
            {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(d_x, d_y);
                path_a.close();
            }
            else if (value.equals("ATL"))
            {
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
            }
            else if (value.equals("DTW") && a1_choice.equals(d1_choice))
            {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(d_x, d_y);
                path_a.close();
            }
            else if (value.equals("DTW"))
            {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(a_x, dtw_hallway);
                path_a.close();

                //move through the hallway to next terminal
                path_b.moveTo(a_x, dtw_hallway);
                path_b.lineTo(d_x, dtw_hallway);
                path_b.close();

                //move through the terminal to the second gate
                path_c.moveTo(d_x, dtw_hallway);
                path_c.lineTo(d_x, d_y);
                path_c.close();
            }
            else if (value.equals("IND") && a1_choice.equals(d1_choice))
            {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(d_x, d_y);
                path_a.close();
            }
            else if (value.equals("IND") && ((a_x + a_y) < (d_x + d_y)))
            {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(93,222);
                path_a.close();

                //move through the hallway to next terminal
                path_b.moveTo(93,222);
                path_b.lineTo(230,357);
                path_b.close();

                //move through the terminal to the second gate
                path_c.moveTo(230,357);
                path_c.lineTo(d_x, d_y);
                path_c.close();
            }
            else //(value.equals("IND") && ((a_x + a_y) > (d_x + d_y)))
            {
                //move through the terminal from first gate to hallway
                path_a.moveTo(a_x, a_y);
                path_a.lineTo(230,357);
                path_a.close();

                //move through the hallway to next terminal
                path_b.moveTo(230,357);
                path_b.lineTo(93,222);
                path_b.close();

                //move through the terminal to the second gate
                path_c.moveTo(93,222);
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

        //Initialize Map
        ImageView myMap = (ImageView) findViewById(R.id.map);

        //set paint that the line will be drawn with
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(18f);
        paint.setColor(Color.RED);
/*
        Paint transparentPaint = new Paint();
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        transparentPaint.setAntiAlias(true);*/

        //use local variables as parameters to set paths
        setPaths(Spinner_A1_Choice, Spinner_A2_Choice, Spinner_D1_Choice, Spinner_D2_Choice);

        //set values of the selected items in each spinner to local variables
//        String ax_test = "A";
//        String ay_test = "1";
//        String dx_test = "B";
//        String dy_test = "2";
//        setPaths(ax_test, ay_test, dx_test, dy_test);

        //Draw the path onto the canvas using paint
//        canvas.drawPath(path_a, paint);
//        canvas.drawPath(path_b, paint);
//        canvas.drawPath(path_c, paint);

        //Create a new image bitmap and attach a brand new canvas to it
        Bitmap tempBitmap = Bitmap.createBitmap(myMap.getWidth(),
                myMap.getHeight(), Bitmap.Config.ARGB_8888);
        tempBitmap.setHasAlpha(true);

        //Draw the image bitmap into the canvas
        tempCanvas = new Canvas(tempBitmap);

//        tempCanvas.drawBitmap(myMap.getDrawingCache(), 0, 0, null);
//        tempCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.atl_map), 0, 0, null);


        Drawable myDrawable = getResources().getDrawable(R.drawable.atl_map);
        Bitmap atlMap = ((BitmapDrawable) myDrawable).getBitmap();


        //create everything else you want into the canvas (the path)
        tempCanvas.drawColor(Color.TRANSPARENT);
        tempCanvas.drawPath(path_a, paint);
        tempCanvas.drawPath(path_b, paint);
        tempCanvas.drawPath(path_c, paint);

        //draw it
        tempCanvas.drawBitmap(atlMap, 0, 0, null);

        //Attach the canvas to the ImageView
        myMap.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

        myMap.invalidate();

    }


//    protected void onResume(ImageView myMap) {
//        super.onResume();
//        setContentView(R.layout.activity_map_screen);
//
//        myMap.invalidate();
//
//    }

}


