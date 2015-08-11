package me.drakeet.meizhi.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.drakeet.meizhi.model.GankModel;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankData extends BaseData {

    public Result results;
    public List<String> category;

    public class Result {
        @SerializedName("Android")
        public List<GankModel> androidList;
        @SerializedName("休息视频")
        public List<GankModel> 休息视频List;
        @SerializedName("iOS")
        public List<GankModel> iOSList;
        @SerializedName("福利")
        public List<GankModel> 福利List;
        @SerializedName("拓展资源")
        public List<GankModel> 拓展资源List;
        @SerializedName("瞎推荐")
        public List<GankModel> 瞎推荐List;
    }

}
