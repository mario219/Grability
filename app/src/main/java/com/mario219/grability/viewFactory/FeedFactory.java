package com.mario219.grability.viewFactory;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.mario219.grability.persistence.EntrySql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario219 on 26/01/17.
 */

public class FeedFactory {

    private static final String TAG = FeedFactory.class.getSimpleName();
    private Context context;
    private List<SingleFeedModel> feedList = new ArrayList<>();

    public FeedFactory (Context context){
        this.context = context;
    }

    public List<SingleFeedModel> getFeedList(){
        EntrySql instance = EntrySql.getInstance(context);
        Cursor cursor = instance.getAllEntrys();
        if(cursor.getCount() == 0){
            Log.i(TAG, "Cursor is empty");
            instance.close();
            return null;
        }

        while(cursor.moveToNext()){
            SingleFeedModel object = new SingleFeedModel();
            object.setId(cursor.getInt(0));
            object.setDisplayName(cursor.getString(1));
            object.setTitle(cursor.getString(3));
            object.setDescription(cursor.getString(5));
            feedList.add(object);
        }
        instance.close();
        return feedList;
    }
}
