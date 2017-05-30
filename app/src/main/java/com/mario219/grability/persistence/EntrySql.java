package com.mario219.grability.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by marioalejndro on 25/01/17.
 */

public class EntrySql extends SQLiteOpenHelper{

    private static final String TAG = EntrySql.class.getSimpleName();
    private static final String DATABASE_NAME = "redditfeeds.db";
    private static final String FEED_TABLE = "FEED";
    private static final String ID = "ID";
    private static final String DISPLAY_NAME = "DISPLAY_NAME";
    private static final String ICON_IMAGE = "ICON_IMAGE";
    private static final String FEED_TITLE = "FEED_TITLE";
    private static final String BANNER_IMG = "BANNER_IMG";
    private static final String PUBLIC_DESCRIPTION = "PUBLIC_DESCRIPTION";

    private static EntrySql mInstance = null;
    private Context context;

    public static EntrySql getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new EntrySql(context.getApplicationContext());
        }
        return mInstance;
    }

    private EntrySql(Context context) {
        super(context, DATABASE_NAME , null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FEED_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "DISPLAY_NAME TEXT, " +
                "ICON_IMAGE TEXT, " +
                "FEED_TITLE TEXT, " +
                "BANNER_IMG TEXT, " +
                "PUBLIC_DESCRIPTION TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FEED_TABLE);
        onCreate(db);
    }

    public void saveFeed(String displayName, String iconImg, String feedTitle, String bannerImg, String pDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DISPLAY_NAME, displayName);
        contentValues.put(ICON_IMAGE, iconImg);
        contentValues.put(FEED_TITLE, feedTitle);
        contentValues.put(BANNER_IMG, bannerImg);
        contentValues.put(PUBLIC_DESCRIPTION, pDescription);
        long result = db.insert(FEED_TABLE, null, contentValues);
        if(result == -1)
            Log.i(TAG, feedTitle + " something went wrong saving the feed in table.");
        db.close();
    }

    public void deleteDataBase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FEED_TABLE, null, null);
        db.close();
    }


    public Cursor getAllEntrys(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + FEED_TABLE, null);
        return cursor;
    }

    public Cursor searchFeed(int id){
        String query = "select * from " + FEED_TABLE + "where";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + FEED_TABLE + " where " + ID + "='" + id + "'" , null);
        return cursor;
    }
}
