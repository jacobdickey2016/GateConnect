package com.example.android.gateconnect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MapScreen extends AppCompatActivity {

    ////////////////
    // INITIALIZE //
    ////////////////

    //create paint
    Paint paint = new Paint();

    //create paths
    Path path_a = new Path();
    Path path_b = new Path();
    Path path_c = new Path();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        // gets value from spinner in previous activity
        String value = getIntent().getStringExtra("data");

        //Initialize Map
        ImageView myMap = (ImageView) findViewById(R.id.map);

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
        Spinner spinner_a1 = Create_Spinner_A1();
        Spinner spinner_d1 = Create_Spinner_D1();

    }

        ////////////////////
        // Arriving Gates //
        ////////////////////

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
        //Spinner_A2.setOnItemSelectedListener(new Spinner_A2_Listener());
        return Spinner_A2;
    }

        /////////////////////
        // Departing Gates //
        /////////////////////

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

    // The listener for Spinner D1 that sends whatever choice made to
    // the method that populates the second spinner.
    public class Spinner_D1_Listener implements
            AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> Spinner_D1_Adapter_View,
                                   View v, int position, long row) {
            String Spinner_D1_Choice = Spinner_D1_Adapter_View
                    .getItemAtPosition(position).toString();
            Create_D2_Spinner(Spinner_D1_Choice);
        }

        public void onNothingSelected(AdapterView<?> arg0) {


        }

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
            //Spinner_D2.setOnItemSelectedListener(new Spinner_D2_Listener());
            return Spinner_D2;
        }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // On Draw //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    protected void onDraw(Canvas canvas) {

        //Map
        ImageView myMap = (ImageView) findViewById(R.id.map);

        //Bitmap
        //Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.id.map);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.id.map, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;

        //Create a new image bitmap and attach a brand new canvas to it
        Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),
                myBitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);

        //Attach the canvas to the ImageView
        BitmapDrawable bpd = new BitmapDrawable(getResources(), tempBitmap);

        //set paint
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(18f);
        paint.setColor(Color.WHITE);

        //set paths
        path_a.moveTo(125f, 1000f);
        path_a.lineTo(125f, 1500f);
        path_a.close();

        path_b.moveTo(116f, 1500f);
        path_b.lineTo(559f, 1500f);
        path_b.close();

        path_c.moveTo(550f, 1500f);
        path_c.lineTo(550f, 1200f);
        path_c.close();

        // THIS ALLOWS THE MAP IMAGE TO BE DRAWN ON AND IT NOT AFFECT THE OTHER VIEWS ON THE SCREEN //

        //Create a new image bitmap and attach a brand new canvas to it
        // ^ in INITIALIZE section ^ //

        //Draw the image bitmap into the canvas
        tempCanvas.drawBitmap(myBitmap, 0, 0, null);

        //Draw everything else you want into the canvas (the path)
        tempCanvas.drawPath(path_a, paint);
        tempCanvas.drawPath(path_b, paint);
        tempCanvas.drawPath(path_c, paint);

        //Attach the canvas to the ImageView
        // bpd in ^ INITIALIZE section ^ //

        myMap.setImageDrawable(bpd);
    }

}


