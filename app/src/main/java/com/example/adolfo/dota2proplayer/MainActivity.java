package com.example.adolfo.dota2proplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public static final String PLAYER_QUERY = "PLAYER_QUERY";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private List<Player> mPlayerList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private DotaPlayerRecyclerViewAdapter mPlayerRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activateToolbar();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        mPlayerRecyclerViewAdapter = new DotaPlayerRecyclerViewAdapter(
                MainActivity.this,
                new ArrayList<Player>()
        );

        mRecyclerView.setAdapter( mPlayerRecyclerViewAdapter );

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                mRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(
                                MainActivity.this,
                                ViewPlayerDetailsActivity.class
                        );

                        intent.putExtra(
                                PLAYER_TRANSFER,
                                mPlayerRecyclerViewAdapter.getPlayer(position)
                        );
                        startActivity( intent );
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "Long Tap", Toast.LENGTH_SHORT).show();
                    }
                }
        ));
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                        getApplicationContext()
                );
        String query = getSavedPreferenceData(PLAYER_QUERY);
        if ( query.length() > 0) {
            ProcessPlayer processPlayer =
                    new ProcessPlayer();
            processPlayer.execute();
        }
    }

    private String getSavedPreferenceData(String championQuery) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(
                        getApplicationContext()
                );

        return sharedPreferences.getString(championQuery, "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if( id == R.id.menu_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProcessPlayer extends GetDotaJsonData {

        public ProcessPlayer() {
            super();
        }

        @Override
        public void execute() {
            super.execute();
            ProcessData processData = new ProcessData();
            processData.execute();

        }

        public class ProcessData extends DownloadJsonData {

            @Override
            protected void onPostExecute(String webData) {
                super.onPostExecute(webData);
                mPlayerRecyclerViewAdapter.loadNewData(getPlayers());
            }
        }
    }
}
