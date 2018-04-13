package com.example.filipe.piggybank.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.filipe.piggybank.Model.Coin;

/**
 * Created by Filipe on 23/04/2017.
 */

/**
 * SQLiteOpenHelper Class
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public final String TAG = getClass().getName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "piggyBank.db";
    public static final String TABLE_NAME = "coins";
    public static final  String COLUM_ID = "id";
    public static final String COLUM_NAME_1 = "coin_2";
    public static final String COLUM_NAME_2 = "coin_1";
    public static final String COLUM_NAME_3 = "coin_050";
    public static final String COLUM_NAME_4 = "coin_020";
    public static final String COLUM_NAME_5 = "coin_010";
    public static final String COLUM_NAME_6 = "coin_005";
    public static final String COLUM_NAME_7 = "coin_002";
    public static final String COLUM_NAME_8 = "coin_001";
    

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Constructor
     * @param context the View/Application context
     */
    public DatabaseHelper(Context context){

        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG,"onCreate: " + getClass().toString() + " Called! \n Creating tables...");
        String createTableQuery = "CREATE TABLE "+ TABLE_NAME + "("+
                COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUM_NAME_1 + " INTEGER " +
                COLUM_NAME_2 + " INTEGER " +
                COLUM_NAME_3 + " INTEGER " +
                COLUM_NAME_4 + " INTEGER " +
                COLUM_NAME_5 + " INTEGER " +
                COLUM_NAME_6 + " INTEGER " +
                COLUM_NAME_7 + " INTEGER " +
                COLUM_NAME_8 + " INTEGER " +
                ");";
        db.execSQL(createTableQuery);
        Log.d(TAG,"Create Table QUERY: \n " + createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IFEXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean addCoin(String coinQty)
    {
        Log.d(TAG,getClass().getName() + ": addCoin()");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_NAME_1,coinQty);

        Log.d(TAG,"addCoin: Adding " + coinQty + " to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME,null,values);//returns -1 if error in db.insert()
        db.close();

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //TODO finish this method in order to implement saving to database 1 coin at time or think about other implementation
//    public boolean addCoinRecordToDB(Coin coin, String coinQty){
//
//
//
//        long result = db.insert(TABLE_NAME,null,values);//returns -1 if error in db.insert()
//        db.close();
//
//        if(result == -1){
//            return false;
//        }else{
//            return true;
//        }
//    }

    public void deleteCoin(String coinName)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM" + TABLE_NAME + " WHERE " + COLUM_NAME_1  + "=\"" + coinName + "\";");
    }

    public void getInformation(){

    }

    /**
     * Gets a Cursor from reading the Database
     * @return teh Cursor with all the records in the Database
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "  + TABLE_NAME;
        Cursor data = db.rawQuery(query,null);

        return data;

    }

    /**
     * Deletes all columns in Database
     */
    public void deleteDatabase(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
    }

    public void readDataFromDatabase(Context context){
        Log.d(TAG,"READING FROM DATABSE...");
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor data = databaseHelper.getData();
        while(data.moveToNext()){
            Log.d(TAG,data.getString(1));
        }

    }

}
