package me.drakeet.meizhi.data;

import java.util.List;

import me.drakeet.meizhi.model.GankModel;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankData extends BaseData {

    public Result results;
    public List<String> category;

    public class Result {
        public List<GankModel> Android;
        public List<GankModel> 休息视频;
        public List<GankModel> iOS;
        public List<GankModel> 福利;
        public List<GankModel> 拓展资源;
        public List<GankModel> 瞎推荐;
    }
}
