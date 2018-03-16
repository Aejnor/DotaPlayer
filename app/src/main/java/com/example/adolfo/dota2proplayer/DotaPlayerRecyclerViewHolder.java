package com.example.adolfo.dota2proplayer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by adolfo on 16/03/18.
 */

public class DotaPlayerRecyclerViewHolder extends RecyclerView.ViewHolder {

    private ImageView mThumbnail;
    public TextView mName;
    public TextView mSteamID;

    public DotaPlayerRecyclerViewHolder(View itemView) {
        super(itemView);
        mThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
        mName = itemView.findViewById(R.id.textViewTitle);
        mSteamID = itemView.findViewById(R.id.textViewSteamID);
    }

    public ImageView getThumbnail() {
        return mThumbnail;
    }
}
