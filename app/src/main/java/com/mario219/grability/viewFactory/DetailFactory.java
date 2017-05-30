package com.mario219.grability.viewFactory;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mario219.grability.Detail;
import com.mario219.grability.R;
import com.mario219.grability.persistence.EntrySql;
import com.mario219.grability.response.Data_;
import com.squareup.picasso.Picasso;


/**
 * Created by marioalejndro on 26/01/17.
 */

public class DetailFactory extends AsyncTask<Integer, Void, Data_> {

    private Activity activity;
    private Context context;
    private static final String TAG = DetailFactory.class.getSimpleName();

    public DetailFactory(Activity activity, Context c){
        this.activity = activity;
        this.context = c;
    }

    @Override
    protected Data_ doInBackground(Integer... params) {
        EntrySql instance = EntrySql.getInstance(context);
        Cursor data = instance.searchFeed(params[0]);
        Data_ object = new Data_();
        while(data.moveToNext()) {
            object.setId(data.getString(0));
            object.setDisplayName(data.getString(1));
            object.setIconImg(data.getString(2));
            object.setTitle(data.getString(3));
            object.setBannerImg(data.getString(4));
            object.setPublicDescriptionHtml(data.getString(5));
        }

        return object;
    }

    @Override
    protected void onPostExecute(Data_ singleFeed) {
        TextView tvTitled = (TextView) activity.findViewById(R.id.tvTitleD);
        TextView tvUserd = (TextView) activity.findViewById(R.id.tvUserD);
        TextView tvCaptiond = (TextView) activity.findViewById(R.id.tvCaptionD);
        ImageView ivImage = (ImageView) activity.findViewById(R.id.ivBanner);
        tvTitled.setText(singleFeed.getTitle());
        tvUserd.setText(singleFeed.getDisplayName());
        tvCaptiond.setText(singleFeed.getPublicDescriptionHtml());
        try {
            Picasso
                    .with(context)
                    .load(singleFeed.getBannerImg())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.cloud_error)
                    .into(ivImage);
        }catch(Exception e){
                e.printStackTrace();
        }
    }
}
