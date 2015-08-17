package me.drakeet.meizhi;

import com.activeandroid.Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;
import me.drakeet.meizhi.other.ActiveAndroidStrategy;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * 2015-08-07T03:57:47.229Z
 * Created by drakeet on 8/9/15.
 */
public class DrakeetRetrofit {

    final Drakeet service;

    final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .setExclusionStrategies(new ActiveAndroidStrategy(null, Model.class))
        .serializeNulls()
        .create();

    DrakeetRetrofit() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(12, TimeUnit.SECONDS);

        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(client))
            .setEndpoint("http://gank.avosapps.com/api")
            .setConverter(new GsonConverter(gson))
            .build();
        service = restAdapter.create(Drakeet.class);
    }

    public Drakeet getService() {
        return service;
    }
}
