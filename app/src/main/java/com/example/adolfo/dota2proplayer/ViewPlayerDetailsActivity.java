package com.example.adolfo.dota2proplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by adolfo on 16/03/18.
 */

public class ViewPlayerDetailsActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_details);

        activateToolbarWithBackEnabled();

        Intent intent = getIntent();
        Player player = (Player) intent.getSerializableExtra(PLAYER_TRANSFER);

        TextView playerName = findViewById(R.id.player_name);
        playerName.setText("Nombre: " + player.getName());

        TextView playerSteamId = findViewById(R.id.player_steam_id);
        playerSteamId.setText("SteamID: " + player.getSteamID());

        TextView playerTeamName= findViewById(R.id.player_team_name);
        playerTeamName.setText("Equipo: " + player.getTeamName());

        TextView playerTeamTag = findViewById(R.id.player_team_name);
        playerTeamTag.setText("TAG de equipo: " + player.getTeamTag());

        ImageView photoImage = findViewById(R.id.player_image);
        Picasso.with(this).load(player.getAvatarFull())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(photoImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player_details, menu);

        return true;
    }

}
