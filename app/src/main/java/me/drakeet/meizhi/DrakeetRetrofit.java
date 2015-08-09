package me.drakeet.meizhi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.OkHttpClient;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by drakeet on 8/9/15.
 */
public class DrakeetRetrofit {

    final Drakeet service;

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateTypeAdapter())
            .create();

    DrakeetRetrofit() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(30, TimeUnit.SECONDS);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint("http://gank.avosapps.com/api")
                .setConverter(new GsonConverter(gson))
                .build();
        service = restAdapter.create(Drakeet.class);
    }

    public Drakeet getService() {
        return service;
    }
}
