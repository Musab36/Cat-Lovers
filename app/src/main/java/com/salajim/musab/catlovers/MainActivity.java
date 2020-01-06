package com.salajim.musab.catlovers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.salajim.musab.catlovers.adapters.CatsAdapter;
import com.salajim.musab.catlovers.api.Service;
import com.salajim.musab.catlovers.models.Cats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    public List<Cats> catsList;
    private CatsAdapter mAdapter;

    ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        toolbar.setTitle("Cats Images");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        /*
        Intent intent = getIntent();
        String cats = intent.getStringExtra("cats");
        */


        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ....");
        progressDialog.show();

        final Service service = new Service();
        service.getCats(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                progressDialog.dismiss();
                catsList = service.prossesResults(response);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                        catsList = new ArrayList<>();
                        mAdapter = new CatsAdapter(getApplicationContext(), catsList);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }
                });
            }
        });

    }
}

