package com.mario219.grability;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mario219.grability.app.AlreadyDatabase;
import com.mario219.grability.app.ConnectivityBehavior;
import com.mario219.grability.restApi.CallWork;
import com.mario219.grability.restApi.OnRestCompleted;
import com.mario219.grability.viewFactory.DetailFactory;

public class Detail extends AppCompatActivity {

    private static final String TAG = Detail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        checkConection();
        int idFeed = getIntent().getExtras().getInt("id");
        new DetailFactory(Detail.this, this).execute(idFeed);
    }

    private void checkConection(){
        if(!ConnectivityBehavior.isOnline(this)){
            Toast.makeText(this, "You don´t have an internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}
