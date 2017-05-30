package com.mario219.grability;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mario219.grability.app.AlreadyDatabase;
import com.mario219.grability.app.ConnectivityBehavior;
import com.mario219.grability.restApi.CallWork;
import com.mario219.grability.restApi.OnRestCompleted;
import com.mario219.grability.viewFactory.FeedAdapter;
import com.mario219.grability.viewFactory.FeedFactory;
import com.mario219.grability.viewFactory.SingleFeedModel;

import java.util.ArrayList;
import java.util.List;


public class Main extends AppCompatActivity {

    private static final String TAG = Main.class.getSimpleName();
    private List<SingleFeedModel> feedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadData();
    }

    private void loadData(){
        if(ConnectivityBehavior.isOnline(this)){
                if(AlreadyDatabase.getDbAlready(this) != true) {
                    new CallWork(new OnRestCompleted()).work(Main.this, this);
                }else{
                    loadRecycler();
                }
        }else{
            loadRecycler();
        }
    }

    private void loadRecycler(){
        feedList = new FeedFactory(getApplicationContext()).getFeedList();
        if(feedList == null){
            Toast.makeText(getApplication(), "You don't have an internet connection and the Timeline could not be loaded",
                    Toast.LENGTH_LONG).show();
            return;
        }
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FeedAdapter feedAdapter = new FeedAdapter(feedList);
        recyclerView.setAdapter(feedAdapter);
    }

}
