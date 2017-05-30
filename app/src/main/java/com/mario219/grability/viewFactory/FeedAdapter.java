package com.mario219.grability.viewFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mario219.grability.Detail;
import com.mario219.grability.R;

import java.util.List;

/**
 * Created by mario219 on 25/01/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedHolder> {

    private static final String TAG = FeedAdapter.class.getSimpleName();
    private List<SingleFeedModel> feedList;

    public FeedAdapter(List<SingleFeedModel> feedList){
        this.feedList = feedList;
    }

    @Override
    public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);

        return new FeedHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedHolder holder, int position) {
        SingleFeedModel feedItem = feedList.get(position);
        holder.title.setText(feedItem.getTitle());
        final int id = feedItem.getId();
        holder.displayName.setText(feedItem.getDisplayName());
        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent launchDetail = new Intent(c, Detail.class);
                launchDetail.putExtra("id", id);
                c.startActivity(launchDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public class FeedHolder extends RecyclerView.ViewHolder {

        public TextView title, displayName, publicDescription, id;
        public Button viewMore;

        public FeedHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvTitle);
            displayName = (TextView) view.findViewById(R.id.tvUser);
            viewMore = (Button) view.findViewById(R.id.button);
        }
    }
}
