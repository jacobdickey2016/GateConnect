package com.example.android.gateconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jacob on 3/10/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    //name of the database
    private static final String DB_NAME = "COORDINATES";
    //version of the database
    private static final int DB_VERSION = 1;
    //name of columns
    public static final String _ID = "_id";
    public static final String AIRPORT_NAME = "airport_name";
    public static final String GATE_LETTER = "gate_letter";
    public static final String GATE_NUMBER = "gate_number";
    public static final String X_COORD = "x_coord";
    public static final String Y_COORD = "y_coord";

    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    DatabaseHelper (Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //gets called when database is first created
    @Override
    public void onCreate (SQLiteDatabase db) {

        //CREATE the database called "COORDINATES"
        db.execSQL("CREATE TABLE " + DB_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AIRPORT_NAME + " TEXT, "
                + GATE_LETTER + " TEXT, "
                + GATE_NUMBER + " INTEGER, "
                + X_COORD + " INTEGER, "
                + Y_COORD + " INTEGER );");

        //INSERT automated values into database "COORDINATES" using insertCoordinate method

     // //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

        final int HALLWAY_WIDTH = 15;
        final int HALLWAY_HEIGHT = 20;                              //

        // ATL T //  (8)                                            //
        int ATL_TX = 195;
        int ATL_TY = 365;                                           //

        for(int i = 1; i < 9; i++)                                  //
        {
            if (i > 1)                                              //
            {
                ATL_TY = ATL_TY - 15;                               //
                insertCoordinate(db, "ATL", "T", i, ATL_TX, ATL_TY);
            }                                                       //

        }                                                           //

        // ATL A // (34)                                            //
        int ATL_AX = 243;
        int ATL_AY = 365;                                           //

        //bottom half                                               //
        for(int i = 1; i < 19; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_AX = ATL_AX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_AY = ATL_AY - 18;                               //
                ATL_AX = ATL_AX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);//
        }
                                                                    //
        ATL_AX = ATL_AX + HALLWAY_WIDTH;
        ATL_AY = ATL_AY - HALLWAY_HEIGHT;                           //

        //top half                                                  //
        for(int i = 19; i < 35; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_AX = ATL_AX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_AY = ATL_AY - 18;                               //
                ATL_AX = ATL_AX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);//
        }

        // ATL B //  (36)                                           //
        int ATL_BX = 293;
        int ATL_BY = 365;                                           //

        //bottom half                                               //
        for(int i = 1; i < 19; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_BX = ATL_BX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_BY = ATL_BY - 18;                               //
                ATL_BX = ATL_BX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);//
        }
                                                                    //
        ATL_BX = ATL_BX + HALLWAY_WIDTH;
        ATL_BY = ATL_BY - HALLWAY_HEIGHT;                           //

        //top half                                                  //
        for(int i = 19; i < 37; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_BX = ATL_BX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_BY = ATL_BY - 16;                               //
                ATL_BX = ATL_BX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);//
        }                                                           //

        // ATL C //  (57)                                           //
        int ATL_CX = 345;
        int ATL_CY = 365;                                           //

        //bottom half                                               //
        for(int i = 1; i < 30; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_CX = ATL_CX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_CY = ATL_CY - 11;                               //
                ATL_CX = ATL_CX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);//
        }
                                                                    //
        ATL_CX = ATL_CX + HALLWAY_WIDTH;
        ATL_CY = ATL_CY - HALLWAY_HEIGHT;                           //

        //top half                                                  //
        for(int i = 30; i < 58; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_CX = ATL_CX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_CY = ATL_CY - 10;                               //
                ATL_CX = ATL_CX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);//
        }                                                           //

        // ATL D //  (46)                                               //
        int ATL_DX = 350;
        int ATL_DY = 365;                                           //

        //bottom half                                               //
        for(int i = 1; i < 22; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_DX = ATL_DX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_DY = ATL_DY - 15;                               //
                ATL_DX = ATL_DX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);//
        }
        //
        ATL_DX = ATL_DX + HALLWAY_WIDTH;
        ATL_DY = ATL_DY - HALLWAY_HEIGHT;                           //

        //top half                                                  //
        for(int i = 22; i < 47; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_DX = ATL_DX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_DY = ATL_DY - 11;                               //
                ATL_DX = ATL_DX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);//
        }                                                           //

        // ATL E // (37)                                            //
        int ATL_EX = 400;
        int ATL_EY = 365;                                           //

        //bottom half                                               //
        for(int i = 1; i < 14; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_EX = ATL_EX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_EY = ATL_EY - 23;                               //
                ATL_EX = ATL_EX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);//
        }

        insertCoordinate(db, "ATL", "E", 14, 454, 176);
        insertCoordinate(db, "ATL", "E", 15, 454, 200);
        insertCoordinate(db, "ATL", "E", 16, 458, 176);
        insertCoordinate(db, "ATL", "E", 17, 458, 200);
        insertCoordinate(db, "ATL", "E", 18, 462, 176);

                                                                    //
        ATL_EX = ATL_EX + HALLWAY_WIDTH;
        ATL_EY = ATL_EY - HALLWAY_HEIGHT;                           //

        //top half                                                  //
        for(int i = 19; i < 38; i++)
        {                                                           //
            //evens
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_EX = ATL_EX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
            }                                                       //
            //odds
            else if (i > 1)                                         //
            {
                ATL_EY = ATL_EY - 14;                               //
                ATL_EX = ATL_EX + HALLWAY_WIDTH;
                insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);//
            }
            //first one                                             //
            else
                insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);//
        }                                                           //

        // ATL F //  (10)                                           //
        int ATL_FX = 450;
        int ATL_FY = 365;                                           //

        for(int i = 1; i < 11; i++)                                 //
        {
            //evens
            if (i > 1 && i%2 != 0)                                  //
            {
                ATL_FX = ATL_FX - HALLWAY_WIDTH;                    //
                insertCoordinate(db, "ATL", "F", i, ATL_FX, ATL_FY);
            }                                                       //

            //odds                                                  //
            else if (i > 1)
            {                                                       //
                ATL_FX = ATL_FX + HALLWAY_WIDTH;
                ATL_FY = ATL_FY - 21;                               //
                insertCoordinate(db, "ATL", "F", i, ATL_FX, ATL_FY);
            }                                                       //
            //the first one
            else                                                    //
                insertCoordinate(db, "ATL", "F", i, ATL_FX, ATL_FY);
        }                                                           //

        insertCoordinate(db, "ATL", "F", 11, 497, 175);             //
        insertCoordinate(db, "ATL", "F", 12, 497, 175);
        insertCoordinate(db, "ATL", "F", 13, 497, 175);             //
        insertCoordinate(db, "ATL", "F", 14, 497, 175);

    //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

        // IND A //                                                         //
        int IND_AX = 650;
        int IND_AY = 300;                                                   //

        for(int i = 1; i < 26; i++)                                         //
        {
            if (i > 1 && i%2 != 0)                                          //
            {
                IND_AY = IND_AY - 5;                                        //
                insertCoordinate(db, "IND", "A", i, IND_AX, IND_AY);
            }                                                               //
            else
                insertCoordinate(db, "IND", "A", i, IND_AX, IND_AY);
        }                                                                   //

        // IND B //                                                         //
        int IND_BX = 650;
        int IND_BY = 500;                                                   //

        for(int i = 1; i < 26; i++)                                         //
        {
            if (i > 1 && i%2 != 0)                                          //
            {
                IND_BY = IND_BY - 5;                                        //
                insertCoordinate(db, "IND", "B", i, IND_BX, IND_BY);
            }                                                               //
            else
                insertCoordinate(db, "IND", "B", i, IND_BX, IND_BY);
        }                                                                   //

    //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

        // DTW A //                                                                 //
        int DTW_AX = 1000;
        int DTW_AY = 450;                                                           //

        for(int i = 1; i < 79; i++)                                                 //
        {
            if (i > 1 && i%2 != 0)                                                  //
            {
                DTW_AX = DTW_AX - 5;                                                //
                insertCoordinate(db, "DTW", "A", i, DTW_AX, DTW_AY);
            }                                                                       //
            else
                insertCoordinate(db, "DTW", "A", i, DTW_AX, DTW_AY);
        }                                                                           //

        // DTW B //                                                                 //
        int DTW_BX = 800;
        int DTW_BY = 250;                                                           //

        for(int i = 1; i < 22; i++)                                                 //
        {
            if (i > 1 && i%2 != 0)                                                  //
            {
                DTW_BX = DTW_BX - 5;                                                //
                insertCoordinate(db, "DTW", "B", i, DTW_BX, DTW_BY);
            }                                                                       //
            else
                insertCoordinate(db, "DTW", "B", i, DTW_BX, DTW_BY);
        }                                                                           //

        // DTW C //                                                                 //
        int DTW_CX = 500;
        int DTW_CY = 250;                                                           //

        for(int i = 1; i < 44; i++)                                                 //
        {
            if (i > 1 && i%2 != 0)                                                  //
            {
                DTW_CY = DTW_CY - 5;                                                //
                insertCoordinate(db, "DTW", "C", i, DTW_CX, DTW_CY);
            }                                                                       //
            else
                insertCoordinate(db, "DTW", "C", i, DTW_CX, DTW_CY);
        }                                                                           //

    //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

        /*if (db.isOpen())
            db.close();*/

    }

    //gets called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertCoordinate(SQLiteDatabase db, String airport_name, String gate_letter, int gate_number, int x_coord, int y_coord) {

        //create ContentValues object
        ContentValues map_coordinates = new ContentValues();

        //populate table with data
        map_coordinates.put("airport_name", airport_name);
        map_coordinates.put("gate_letter", gate_letter);
        map_coordinates.put("gate_number", gate_number);
        map_coordinates.put("x_coord", x_coord);
        map_coordinates.put("y_coord", y_coord);

        //insert values into table
        db.insert("COORDINATES", null, map_coordinates);
    }

}
