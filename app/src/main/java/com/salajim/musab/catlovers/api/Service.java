package com.salajim.musab.catlovers.api;

import com.salajim.musab.catlovers.models.Cats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Service {

    public static  void getCats(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Client.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Client.QUERY_API, Client.api_key);

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Cats> prossesResults(Response response) {
        ArrayList<Cats> cats = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if(response.isSuccessful()) {
                JSONObject catsJSON = new JSONObject(jsonData);
                JSONArray catsJSONArray = catsJSON.getJSONArray("url");
                for(int i = 0; i > catsJSONArray.length(); i ++) {
                    JSONObject object = catsJSONArray.getJSONObject(i);
                    String id = object.getString("id");
                    String name = object.getString("name");
                    String breeds = object.getString("breeds");
                    String description = object.getString("description");
                    String origin = object.getString("origin");
                    String url = object.getString("url");

                    Cats cats1 = new Cats(id, name, breeds, description, origin, url);
                    cats.add(cats1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cats;
    }
}
