package me.drakeet.meizhi;

import me.drakeet.meizhi.data.GankData;
import me.drakeet.meizhi.data.MeizhiData;
import me.drakeet.meizhi.data.休息视频Data;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by drakeet on 8/9/15.
 */
public interface Drakeet {

    @GET("/data/福利/" + DrakeetFactory.meizhiSize + "/{page}") Observable<MeizhiData> getMeizhiData(
        @Path("page") int page);

    @GET("/day/{year}/{month}/{day}") Observable<GankData> getGankData(@Path("year") int year,
        @Path("month") int month, @Path("day") int day);

    @GET("/data/休息视频/" + DrakeetFactory.meizhiSize + "/{page}") Observable<休息视频Data> get休息视频Data(
        @Path("page") int page);
}
