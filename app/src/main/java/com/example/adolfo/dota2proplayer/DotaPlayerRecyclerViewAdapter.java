package com.example.adolfo.dota2proplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by adolfo on 16/03/18.
 */

public class DotaPlayerRecyclerViewAdapter extends RecyclerView.Adapter<DotaPlayerRecyclerViewHolder> {

    private static final String LOG_TAG = DotaPlayerRecyclerViewAdapter.class.getSimpleName();

    private List<Player> mPlayerList;
    private Context mContext;

    public DotaPlayerRecyclerViewAdapter(Context context, List<Player> playerList) {
        mPlayerList = playerList;
        mContext = context;
    }

    @Override
    public DotaPlayerRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.browse, null, false);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(lp);

        DotaPlayerRecyclerViewHolder dotaPlayerRecyclerViewHolder =
                new DotaPlayerRecyclerViewHolder(view);

        return dotaPlayerRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(DotaPlayerRecyclerViewHolder holder, int position) {
        // Obtenemos el elemento que va a estar en la posición pedida
        Player playerItem = mPlayerList.get(position);

        Log.d(LOG_TAG, "Processing: " +playerItem.getName() + " -> " + Integer.toString(position));

        // Pintamos el thumbnail en la pantalla
        Picasso.with(mContext).load(playerItem.getAvatarMedium())
                .error(R.drawable.placeholder)      // En caso de error
                .placeholder(R.drawable.placeholder)// Mientras descarga
                .into(holder.getThumbnail());

        holder.mName.setText(playerItem.getName());
    }

    @Override
    public int getItemCount() {
        return (mPlayerList != null ? mPlayerList.size() : 0 );
    }

    public void loadNewData(List<Player> players){
        mPlayerList = players;

        notifyDataSetChanged();
    }

    public Player getPlayer(int position) {
        return (mPlayerList != null ? mPlayerList.get(position) : null );
    }
}
