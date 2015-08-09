package me.drakeet.meizhi;

import me.drakeet.meizhi.data.MeizhiList;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by drakeet on 8/9/15.
 */
public interface Drakeet {

    @GET("/data/福利/" + DrakeetFactory.pageSize + "/{page}")
    Observable<MeizhiList> getMeizhiList(@Path("page") int page);
}
