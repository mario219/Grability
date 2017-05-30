package com.mario219.grability.restApi;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mario219.grability.Main;
import com.mario219.grability.R;
import com.mario219.grability.app.AlreadyDatabase;
import com.mario219.grability.persistence.EntrySql;
import com.mario219.grability.response.Child;
import com.mario219.grability.response.Data;
import com.mario219.grability.response.Data_;
import com.mario219.grability.response.ResponseObject;
import com.mario219.grability.viewFactory.FeedAdapter;
import com.mario219.grability.viewFactory.FeedFactory;
import com.mario219.grability.viewFactory.SingleFeedModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marioalejndro on 25/01/17.
 */

public class OnRestCompleted implements OnCallCompleted {

    private static final String TAG = OnRestCompleted.class.getSimpleName();
    private Context context;
    private Main activity;

    @Override
    public void onCallCompleted(ResponseObject responseObject, Main activity, Context context) {

        this.activity = activity;
        this.context = context;
        List<Child> childrenList = new ArrayList<>();
        List<Data_> dataList = new ArrayList<>();

        Data data = responseObject.getData();
        childrenList = data.getChildren();

        Iterator childrenIterator = childrenList.iterator();
        while(childrenIterator.hasNext()){
            Child object = (Child) childrenIterator.next();
            Data_ data_ = object.getData();
            dataList.add(data_);
        }
        childrenIterator.remove();
        saveEntrys(dataList);
    }

    private void saveEntrys(List<Data_> dataList){

        EntrySql instance = EntrySql.getInstance(context);
        instance.deleteDataBase();
        Iterator dataIterator = dataList.iterator();

        while(dataIterator.hasNext()){
            Data_ obj = (Data_) dataIterator.next();
            String displayName, iconImg, title, bannerImg, publicDescription;
            try {
                displayName = obj.getDisplayName().toString();
            } catch (Exception e) {
                Log.i(TAG, " element NULL");
                displayName = "null";
            }

            try {
                iconImg = obj.getIconImg().toString();
            } catch (Exception e) {
                Log.i(TAG, " element NULL");
                iconImg = "null";
            }

            try {
                title = obj.getTitle().toString();
            } catch (Exception e) {
                Log.i(TAG, " element NULL");
                title = "null";
            }

            try {
                bannerImg = obj.getBannerImg();
            } catch (Exception e) {
                Log.i(TAG, " element NULL");
                bannerImg = "null";
            }

            try {
                publicDescription = obj.getPublicDescriptionHtml().toString();
            } catch (Exception e) {
                Log.i(TAG, " element NULL");
                publicDescription = "null";
            }

            instance.saveFeed(displayName, iconImg, title, bannerImg, publicDescription);
        }
        instance.close();
        AlreadyDatabase.DbAlready(context, true);
        loadView();
    }

    private void loadView (){
        List<SingleFeedModel> feedList = new ArrayList<>();
        feedList = new FeedFactory(context).getFeedList();
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FeedAdapter feedAdapter = new FeedAdapter(feedList);
        recyclerView.setAdapter(feedAdapter);

    }

}


