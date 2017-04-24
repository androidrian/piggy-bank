package com.example.filipe.piggybank.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Filipe on 23/04/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Records.db";
    public static final String TABLE_COINS = "coins";
    public static final String COLUM_ID = "id";
    public static final String COLUM_NAME_1 = "coin_2";
    public static final String COLUM_NAME_2 = "coin_1";
    public static final String COLUM_NAME_3 = "coin_050";
    public static final String COLUM_NAME_4 = "coin_020";
    public static final String COLUM_NAME_5 = "coin_010";
    public static final String COLUM_NAME_6 = "coin_005";
    public static final String COLUM_NAME_7 = "coin_002";
    public static final String COLUM_NAME_8 = "coin_001";





    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_COINS + "("+
                COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                COLUM_NAME_1 + " INTEGER " +
                COLUM_NAME_2 + " INTEGER " +
                COLUM_NAME_3 + " INTEGER " +
                COLUM_NAME_4 + " INTEGER " +
                COLUM_NAME_5 + " INTEGER " +
                COLUM_NAME_6 + " INTEGER " +
                COLUM_NAME_7 + " INTEGER " +
                COLUM_NAME_8 + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IFEXISTS" + TABLE_COINS);
        onCreate(db);
    }

    public void addCoin(Coin coin)
    {
        ContentValues values = new ContentValues();
        values.put(COLUM_NAME_1, coin.getM_coinName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_COINS,null,values);
        db.close();
    }

    public void deleteCoin(String coinName)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM" + TABLE_COINS + " WHERE " + COLUM_NAME_1  + "=\"" + coinName + "\";");
    }
}
