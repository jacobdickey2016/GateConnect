package com.example.android.gateconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jacob on 3/10/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //name of the database
    private static final String DB_NAME = "COORDINATES";
    //version of the database
    private static final int DB_VERSION = 1;

    public static final String _ID = "_id";
    public static final String AIRPORT_NAME = "airport_name";
    public static final String GATE_LETTER = "gate_letter";
    public static final String GATE_NUMBER = "gate_number";
    public static final String X_COORD = "x_coord";
    public static final String Y_COORD = "y_coord";

    DatabaseHelper (Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //gets called when database is first created
    @Override
    public void onCreate (SQLiteDatabase db) {

        //CREATE the database called "COORDINATES"
        db.execSQL("CREATE TABLE " + DB_NAME + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AIRPORT_NAME + " TEXT, "
                + GATE_LETTER + " TEXT, "
                + GATE_NUMBER + " INTEGER, "
                + X_COORD + " INTEGER, "
                + Y_COORD + " INTEGER)");

        //INSERT automated values into database "COORDINATES" using insertCoordinate method

     // //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

        // ATL T //                                                 //
        int ATL_TX = 150;
        int ATL_TY = 300;                                           //

        for(int i = 1; i < 56; i++)                                 //
        {
            if (i > 1 && i%2 != 0)                                  //
            {
                ATL_TY = ATL_TY - 5;                                //
                insertCoordinate(db, "ATL", "T", i, ATL_TX, ATL_TY);
            }                                                       //
            else
                insertCoordinate(db, "ATL", "T", i, ATL_TX, ATL_TY);
        }                                                           //

        // ATL A //                                                 //
        int ATL_AX = 200;
        int ATL_AY = 300;                                           //

        for(int i = 1; i < 35; i++)                                 //
        {
            if (i > 1 && i%2 == 0)                                  //
            {
                ATL_AY = ATL_AY - 5;                                //
                insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
            }                                                       //
            else
                insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
        }                                                           //

        // ATL B //                                                 //
        int ATL_BX = 250;
        int ATL_BY = 300;                                           //

        for(int i = 1; i < 37; i++)                                 //
        {
            if (i > 1 && i%2 != 0)                                  //
            {
                ATL_BY = ATL_BY - 5;                                //
                insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
            }                                                       //
            else
                insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
        }                                                           //

        // ATL C //                                                 //
        int ATL_CX = 300;
        int ATL_CY = 300;                                           //

        for(int i = 1; i < 56; i++)                                 //
        {
            if (i > 1 && i%2 != 0)                                  //
            {
                ATL_CY = ATL_CY - 5;                                //
                insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
            }                                                       //
            else
                insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
        }                                                           //

        // ATL D //                                                 //
        int ATL_DX = 350;
        int ATL_DY = 300;                                           //

        for(int i = 1; i < 47; i++)                                 //
        {
            if (i > 1 && i%2 != 0)                                  //
            {
                ATL_DY = ATL_DY - 5;                                //
                insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
            }                                                       //
            else
                insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
        }                                                           //

        // ATL E //                                                 //
        int ATL_EX = 400;
        int ATL_EY = 300;                                           //

        for(int i = 1; i < 38; i++)                                 //
        {
            if (i > 1 && i%2 != 0)                                  //
            {
                ATL_EY = ATL_EY - 5;                                //
                insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
            }                                                       //
            else
                insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
        }                                                           //

        // ATL F //                                                 //
        int ATL_FX = 450;
        int ATL_FY = 300;                                           //

        for(int i = 1; i < 11; i++)                                 //
        {
            if (i > 1 && i%2 != 0)                                  //
            {
                ATL_FY = ATL_FY - 5;                                //
                insertCoordinate(db, "ATL", "F", i, ATL_FX, ATL_FY);
            }                                                       //
            else
                insertCoordinate(db, "ATL", "F", i, ATL_FX, ATL_FY);
        }                                                           //

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
