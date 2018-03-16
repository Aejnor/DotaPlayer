package com.example.adolfo.dota2proplayer;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adolfo on 16/03/18.
 */

public class GetDotaJsonData extends GetRawData {

    private static final String LOG_TAG = GetDotaJsonData.class.getSimpleName();

    private List<Player> mPlayers;
    private Uri mDestinationUri;

    public GetDotaJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createUri(searchCriteria, matchAll);
        mPlayers = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return mPlayers;
    }

    private boolean createUri(String searchCriteria, boolean matchAll) {
        final String DOTA_BASE_API_URL = "https://api.opendota.com/api/proPlayers";

        mDestinationUri = Uri.parse(DOTA_BASE_API_URL).buildUpon().build();

        return mDestinationUri != null;
    }

    private void processResult() {
        final String PLAYER_STEAM_ID = "steamid";
        final String PLAYER_AVATAR_MEDIUM = "avatarmedium";
        final String PLAYER_AVATAR_FULL = "avatarfull";
        final String PLAYER_PERSONA_NAME = "personaname";
        final String PLAYER_NAME = "name";
        final String PLAYER_TEAM_NAME = "team_name";
        final String PLAYER_TEAM_TAG = "team_tag";
        final String PLAYER_COUNTRY = "loccountrycode";

        if (getDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "No se ha descargado el JSON");
            return;
        }

        try {
            JSONArray itemsArray = new JSONArray(getData());

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jsonPlayer = itemsArray.getJSONObject(i);
                String steam_id = jsonPlayer.getString(PLAYER_STEAM_ID);
                String avatar_medium = jsonPlayer.getString(PLAYER_AVATAR_MEDIUM);
                String avatar_full = jsonPlayer.getString(PLAYER_AVATAR_FULL);
                String persona_name = jsonPlayer.getString(PLAYER_PERSONA_NAME);
                String name = jsonPlayer.getString(PLAYER_NAME);
                String team_name = jsonPlayer.getString(PLAYER_TEAM_NAME);
                String team_tag = jsonPlayer.getString(PLAYER_TEAM_TAG);
                String country = jsonPlayer.getString(PLAYER_COUNTRY);


                Player player = new Player(steam_id, avatar_medium, avatar_full, persona_name, name, team_name, team_tag, country);
                mPlayers.add(player);
            }

            for (Player player: mPlayers) {
                Log.d(LOG_TAG, "Player: " + player.toString());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "No se puede crear el objeto JSON");
            e.printStackTrace();
        }

    }

    public void execute() {
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Build Uri: " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public class DownloadJsonData extends DownloadRawData {

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        @Override
        protected String doInBackground(String... params) {
            String[] par = {mDestinationUri.toString()};

            return super.doInBackground(par);
        }

    }
}
