package com.example.filipe.piggybank.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.filipe.piggybank.Model.Coin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Filipe on 23/04/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "/mnt/sdcard/piggybankdatabase.db";
    public static final String TABLE_RECORDS = "records";
    public static final String TABLE_PIGGY_BANK = "piggyBank";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME_1 = "DOIS_E";
    public static final String COLUMN_NAME_2 = "UM_E";
    public static final String COLUMN_NAME_3 = "CINQ_C";
    public static final String COLUMN_NAME_4 = "VINTE_C";
    public static final String COLUMN_NAME_5 = "DEZ_C";
    public static final String COLUMN_NAME_6 = "CINC_C";
    public static final String COLUMN_NAME_7 = "DOIS_C";
    public static final String COLUMN_NAME_8 = "UM_C";

    List<String> mListOfColumnNames = Arrays.asList(COLUMN_NAME_1,COLUMN_NAME_2,COLUMN_NAME_3,COLUMN_NAME_4,COLUMN_NAME_5,
            COLUMN_NAME_6,COLUMN_NAME_7,COLUMN_NAME_8);


    public DatabaseHandler (Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE <TABLE_NAME> (<COLUMN_NAME> <TYPE> , <COLUMN_NAME> <TYPE>, ..... ) ;
        String query = " CREATE TABLE " + TABLE_RECORDS + "("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + "," +
                COLUMN_NAME_1 + " INTEGER " + "," +
                COLUMN_NAME_2 + " INTEGER " + "," +
                COLUMN_NAME_3 + " INTEGER " + "," +
                COLUMN_NAME_4 + " INTEGER " + "," +
                COLUMN_NAME_5 + " INTEGER " + "," +
                COLUMN_NAME_6 + " INTEGER " + "," +
                COLUMN_NAME_7 + " INTEGER " + "," +
                COLUMN_NAME_8 + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IFEXISTS" + TABLE_RECORDS);
        //create the database
        onCreate(db);
    }

    public void addRecordToDatabase(List<Integer> listOfValues){
        ContentValues cv = new ContentValues();

        for(int i = 0; i < listOfValues.size() ; i++) {
            cv.put(mListOfColumnNames.get(i), listOfValues.get(i));
        }
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECORDS, null, cv);
        db.close();


    }

    public void addValues(String table, int value){

            ContentValues values = new ContentValues();
            values.put(table,value);
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_RECORDS,null,values);
            db.close();

    }

    public void addCoin(Coin coin)
    {



    }

    public void deleteCoin(String coinName)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM" + TABLE_RECORDS + " WHERE " + COLUMN_NAME_1 + "=\"" + coinName + "\";");
    }
}
