package me.drakeet.meizhi;

import me.drakeet.meizhi.data.MeizhiData;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by drakeet on 8/9/15.
 */
public interface Drakeet {

    @GET("/data/福利/" + DrakeetFactory.pageSize + "/{page}")
    Observable<MeizhiData> getMeizhiData(@Path("page") int page);
}
