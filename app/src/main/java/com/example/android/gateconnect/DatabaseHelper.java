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

    //name of columns
    public static final String _ID = "_id";
    public static final String AIRPORT_NAME = "airport_name";
    public static final String GATE_LETTER = "gate_letter";
    public static final String GATE_NUMBER = "gate_number";
    public static final String X_COORD = "x_coord";
    public static final String Y_COORD = "y_coord";

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //gets called when database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATE the database called "COORDINATES"
        db.execSQL("CREATE TABLE " + DB_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AIRPORT_NAME + " TEXT, "
                + GATE_LETTER + " TEXT, "
                + GATE_NUMBER + " INTEGER, "
                + X_COORD + " INTEGER, "
                + Y_COORD + " INTEGER );");

        //this is to scale what is in the database it fit the imageView right
        final int SCALE_FACTOR = 4;

        final int ATL_HALLWAY_WIDTH = 75;
        final int ATL_HALLWAY_HEIGHT = 20;

        final int DTW_A_HALLWAY_HEIGHT = 17 * SCALE_FACTOR;
        final int DTW_B_HALLWAY_HEIGHT = 12 * SCALE_FACTOR;

        final int IND_A_HALL_X = 13 * SCALE_FACTOR;
        final int IND_A_HALL_Y = 17 * SCALE_FACTOR;
        final int IND_B_HALL_X = 18 * SCALE_FACTOR;
        final int IND_B_HALL_Y = 17 * SCALE_FACTOR;

        //INSERT automated values into database "COORDINATES" using insertCoordinate method

            // ATL T //  (8)
            int ATL_TX = 195 * SCALE_FACTOR;
            int ATL_TY = 365 * SCALE_FACTOR;

        for (int i = 1; i < 9; i++)
        {
            insertCoordinate(db, "ATL", "T", i, ATL_TX, ATL_TY);
            ATL_TY = ATL_TY - 15 * SCALE_FACTOR;
        }

            // ATL A // (34)
            int ATL_AX = 243 * SCALE_FACTOR;
            int ATL_AY = 365 * SCALE_FACTOR;

            //bottom half
            for (int i = 1; i < 19; i++) {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    ATL_AX = ATL_AX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
                }
                //odds
                else if (i > 1)
                {
                    ATL_AY = ATL_AY - 19 * SCALE_FACTOR;
                    ATL_AX = ATL_AX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
            }

            ATL_AX = ATL_AX + ATL_HALLWAY_WIDTH;
            ATL_AY = ATL_AY - ATL_HALLWAY_HEIGHT;

            //top half
            for (int i = 19; i < 35; i++) {
                //evens
                if (i > 20 && i % 2 == 0)
                {
                    ATL_AX = ATL_AX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
                }
                //odds
                else if (i > 20)
                {
                    ATL_AY = ATL_AY - 19 * SCALE_FACTOR;
                    ATL_AX = ATL_AX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "A", i, ATL_AX, ATL_AY);
            }

            // ATL B //  (36)
            int ATL_BX = 293 * SCALE_FACTOR;
            int ATL_BY = 365 * SCALE_FACTOR;

            //bottom half
            for (int i = 1; i < 19; i++) {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    ATL_BX = ATL_BX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
                }
                //odds
                else if (i > 1)
                {
                    ATL_BY = ATL_BY - 18 * SCALE_FACTOR;
                    ATL_BX = ATL_BX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
            }
            //
            ATL_BX = ATL_BX + ATL_HALLWAY_WIDTH;
            ATL_BY = ATL_BY - ATL_HALLWAY_HEIGHT;

            //top half
            for (int i = 19; i < 37; i++) {
                //evens
                if (i > 20 && i % 2 == 0)
                {
                    ATL_BX = ATL_BX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
                }
                //odds
                else if (i > 20)
                {
                    ATL_BY = ATL_BY - 16 * SCALE_FACTOR;
                    ATL_BX = ATL_BX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "B", i, ATL_BX, ATL_BY);
            }

            // ATL C //  (57)
            int ATL_CX = 345 * SCALE_FACTOR;
            int ATL_CY = 365 * SCALE_FACTOR;

            //bottom half
            for (int i = 1; i < 30; i++) {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    ATL_CX = ATL_CX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
                }
                //odds
                else if (i > 1)
                {
                    ATL_CY = ATL_CY - 11 * SCALE_FACTOR;
                    ATL_CX = ATL_CX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
            }
            //
            ATL_CX = ATL_CX + ATL_HALLWAY_WIDTH;
            ATL_CY = ATL_CY - ATL_HALLWAY_HEIGHT;

            //top half
            for (int i = 30; i < 58; i++) {
                //evens
                if (i > 31 && i % 2 == 0)
                {
                    ATL_CX = ATL_CX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
                }
                //odds
                else if (i > 31)
                {
                    ATL_CY = ATL_CY - 10 * SCALE_FACTOR;
                    ATL_CX = ATL_CX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "C", i, ATL_CX, ATL_CY);
            }

            // ATL D //  (46)
            int ATL_DX = 400 * SCALE_FACTOR;
            int ATL_DY = 365 * SCALE_FACTOR;

            //bottom half
            for (int i = 1; i < 22; i++) {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    ATL_DX = ATL_DX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
                }
                //odds
                else if (i > 1)
                {
                    ATL_DY = ATL_DY - 15 * SCALE_FACTOR;
                    ATL_DX = ATL_DX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
            }
            //
            ATL_DX = ATL_DX + ATL_HALLWAY_WIDTH;
            ATL_DY = ATL_DY - ATL_HALLWAY_HEIGHT;

            //top half
            for (int i = 22; i < 47; i++) {
                //evens
                if (i > 23 && i % 2 == 0)
                {
                    ATL_DX = ATL_DX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
                }
                //odds
                else if (i > 23)
                {
                    ATL_DY = ATL_DY - 12 * SCALE_FACTOR;
                    ATL_DX = ATL_DX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "D", i, ATL_DX, ATL_DY);
            }

            // ATL E // (37)
            int ATL_EX = 450 * SCALE_FACTOR;
            int ATL_EY = 365 * SCALE_FACTOR;

            //bottom half
            for (int i = 1; i < 14; i++) {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    ATL_EX = ATL_EX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
                }
                //odds
                else if (i > 1)
                {
                    ATL_EY = ATL_EY - 23 * SCALE_FACTOR;
                    ATL_EX = ATL_EX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
            }

            insertCoordinate(db, "ATL", "E", 14, 454 * SCALE_FACTOR, 176 * SCALE_FACTOR);
            insertCoordinate(db, "ATL", "E", 15, 454 * SCALE_FACTOR, 200 * SCALE_FACTOR);
            insertCoordinate(db, "ATL", "E", 16, 458 * SCALE_FACTOR, 176 * SCALE_FACTOR);
            insertCoordinate(db, "ATL", "E", 17, 458 * SCALE_FACTOR, 200 * SCALE_FACTOR);
            insertCoordinate(db, "ATL", "E", 18, 462 * SCALE_FACTOR, 176 * SCALE_FACTOR);

            ATL_EY = ATL_EY - ATL_HALLWAY_HEIGHT;

            //top half
            for (int i = 19; i < 38; i++) {
                //evens
                if (i > 20 && i % 2 == 0)
                {
                    ATL_EX = ATL_EX + ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
                }
                //odds
                else if (i > 20)
                {
                    ATL_EY = ATL_EY - 14 * SCALE_FACTOR;
                    ATL_EX = ATL_EX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
                }
                //first one
                else
                    insertCoordinate(db, "ATL", "E", i, ATL_EX, ATL_EY);
            }

            // ATL F //  (10)
            int ATL_FX = 500 * SCALE_FACTOR;
            int ATL_FY = 365 * SCALE_FACTOR;

            for (int i = 1; i < 11; i++)
            {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    ATL_FX = ATL_FX - ATL_HALLWAY_WIDTH;
                    insertCoordinate(db, "ATL", "F", i, ATL_FX, ATL_FY);
                }

                //odds
                else if (i > 1) {
                    ATL_FX = ATL_FX + ATL_HALLWAY_WIDTH;
                    ATL_FY = ATL_FY - 21 * SCALE_FACTOR;
                    insertCoordinate(db, "ATL", "F", i, ATL_FX, ATL_FY);
                }
                //the first one
                else
                    insertCoordinate(db, "ATL", "F", i, ATL_FX, ATL_FY);
            }

            insertCoordinate(db, "ATL", "F", 11, 497 * SCALE_FACTOR, 175 * SCALE_FACTOR);
            insertCoordinate(db, "ATL", "F", 12, 497 * SCALE_FACTOR, 175 * SCALE_FACTOR);
            insertCoordinate(db, "ATL", "F", 13, 497 * SCALE_FACTOR, 175 * SCALE_FACTOR);
            insertCoordinate(db, "ATL", "F", 14, 497 * SCALE_FACTOR, 175 * SCALE_FACTOR);

            //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

            // IND A //
            int IND_AX = 241 * SCALE_FACTOR;
            int IND_AY = 360 * SCALE_FACTOR;

            //gates 3-5
            insertCoordinate(db, "IND", "A", 3, 212 * SCALE_FACTOR, 417 * SCALE_FACTOR);
            insertCoordinate(db, "IND", "A", 4, 212 * SCALE_FACTOR, 405 * SCALE_FACTOR);
            insertCoordinate(db, "IND", "A", 5, 220 * SCALE_FACTOR, 378 * SCALE_FACTOR);

            for (int i = 6; i < 26; i++)
            {
                //odds
                if (i > 6 && i % 2 != 0)
                {
                    //move across the hall
                    IND_AX = IND_AX - IND_A_HALL_X;
                    IND_AY = IND_AY - IND_A_HALL_Y;
                    insertCoordinate(db, "IND", "A", i, IND_AX, IND_AY);
                }
                //evens
                else if (i > 6) {
                    //move to the next spot down the concourse
                    IND_AX = IND_AX + 13 * SCALE_FACTOR;
                    IND_AY = IND_AY - 11 * SCALE_FACTOR;

                    //move back across the hall
                    IND_AX = IND_AX + IND_A_HALL_X;
                    IND_AY = IND_AY + IND_A_HALL_Y;
                    insertCoordinate(db, "IND", "A", i, IND_AX, IND_AY);
                }
                //the 6th one
                else
                    insertCoordinate(db, "IND", "A", i, IND_AX, IND_AY);
            }

            // IND B //  (25)
            int IND_BX = 104 * SCALE_FACTOR;
            int IND_BY = 220 * SCALE_FACTOR;

            //gates 3-5
            insertCoordinate(db, "IND", "B", 3, 36 * SCALE_FACTOR, 238 * SCALE_FACTOR);
            insertCoordinate(db, "IND", "B", 4, 57 * SCALE_FACTOR, 232 * SCALE_FACTOR);
            insertCoordinate(db, "IND", "B", 5, 75 * SCALE_FACTOR, 220 * SCALE_FACTOR);

            for (int i = 6; i < 26; i++)
            {
                //odds
                if (i > 6 && i % 2 != 0)
                {
                    //move across the hall
                    IND_BX = IND_BX - IND_B_HALL_X;
                    IND_BY = IND_BY - IND_B_HALL_Y;
                    insertCoordinate(db, "IND", "B", i, IND_BX, IND_BY);
                }
                //evens
                else if (i > 6) {
                    //move to the next spot down the concourse
                    IND_BX = IND_BX + 8 * SCALE_FACTOR;
                    IND_BY = IND_BY - 13 * SCALE_FACTOR;

                    //move back across the hall
                    IND_BX = IND_BX + IND_B_HALL_X;
                    IND_BY = IND_BY + IND_B_HALL_Y;
                    insertCoordinate(db, "IND", "B", i, IND_BX, IND_BY);
                }
                //the 6th one
                else
                    insertCoordinate(db, "IND", "B", i, IND_BX, IND_BY);
            }

            //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

            // DTW A //  (78)
            int DTW_AX = 44 * SCALE_FACTOR;
            int DTW_AY = 160 * SCALE_FACTOR;

            for (int i = 1; i < 79; i++)
            {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    DTW_AY = DTW_AY - DTW_A_HALLWAY_HEIGHT;
                    insertCoordinate(db, "DTW", "A", i, DTW_AX, DTW_AY);
                }
                //odds
                else if (i > 1)
                {
                    DTW_AX = DTW_AX + 13 * SCALE_FACTOR;
                    DTW_AY = DTW_AY + DTW_A_HALLWAY_HEIGHT;
                    insertCoordinate(db, "DTW", "A", i, DTW_AX, DTW_AY);
                }
                //the first one
                else
                    insertCoordinate(db, "DTW", "A", i, DTW_AX, DTW_AY);
            }

            // DTW B // (21)
            int DTW_BX = 276 * SCALE_FACTOR;
            int DTW_BY = 63 * SCALE_FACTOR;

            for (int i = 1; i < 22; i++)
            {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    DTW_BY = DTW_BY - DTW_B_HALLWAY_HEIGHT;
                    insertCoordinate(db, "DTW", "B", i, DTW_BX, DTW_BY);
                }
                //odds
                else if (i > 1)
                {
                    DTW_BX = DTW_BX - 9 * SCALE_FACTOR;
                    DTW_BY = DTW_BY + DTW_B_HALLWAY_HEIGHT;
                    insertCoordinate(db, "DTW", "B", i, DTW_BX, DTW_BY);
                }
                //the first one
                else
                    insertCoordinate(db, "DTW", "B", i, DTW_BX, DTW_BY);
            }

            // DTW C // (43)
            int DTW_CX = 284 * SCALE_FACTOR;
            int DTW_CY = 67 * SCALE_FACTOR;

            for (int i = 1; i < 44; i++)
            {
                //evens
                if (i > 1 && i % 2 == 0)
                {
                    DTW_CY = DTW_CY - DTW_B_HALLWAY_HEIGHT;
                    insertCoordinate(db, "DTW", "C", i, DTW_CX, DTW_CY);
                }
                //odds
                else if (i > 1)
                {
                    DTW_CX = DTW_CX + 5 * SCALE_FACTOR;
                    DTW_CY = DTW_CY + DTW_B_HALLWAY_HEIGHT;
                    insertCoordinate(db, "DTW", "C", i, DTW_CX, DTW_CY);
                }
                //the first one
                else
                    insertCoordinate(db, "DTW", "C", i, DTW_CX, DTW_CY);
            }

            //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

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
