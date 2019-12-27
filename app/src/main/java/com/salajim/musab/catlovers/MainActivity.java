package com.salajim.musab.catlovers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.salajim.musab.catlovers.adapters.CatsAdapter;
import com.salajim.musab.catlovers.api.Service;
import com.salajim.musab.catlovers.api.Client;
import com.salajim.musab.catlovers.models.Cats;
import com.salajim.musab.catlovers.models.CatsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<Cats> catsList;
    private CatsAdapter mAdapter;

    ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        toolbar.setTitle("Cats");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        catsList = new ArrayList<>();
        mAdapter = new CatsAdapter(this, catsList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ....");
        progressDialog.show();

        try {
            if(BuildConfig.API_KEY.isEmpty()) {
                Toast.makeText(this, "Please obtain your api key from the developers", Toast.LENGTH_SHORT).show();
                return;
            }

            Client client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<CatsResponse> call = apiService.getImages(BuildConfig.API_KEY);

            call.enqueue(new Callback<CatsResponse>() {
                @Override
                public void onResponse(Call<CatsResponse> call, Response<CatsResponse> response) {
                    progressDialog.dismiss();

                    if(response == null) {
                        Toast.makeText(MainActivity.this, "No response from the server", Toast.LENGTH_SHORT).show();
                    }

                    List<Cats> catsList = response.body().getImages();

                    mAdapter = new CatsAdapter(getApplicationContext(), catsList);
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<CatsResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Log.d("Server error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            });

       } catch (Exception e) {
            Log.d("get Data error", e.getMessage());
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

