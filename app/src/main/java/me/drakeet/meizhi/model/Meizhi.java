package me.drakeet.meizhi.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by drakeet on 6/20/15.
 */
@Table(name = "Meizhis") public class Meizhi extends Model {

    @Column(name = "url") public String url;
    @Column(name = "type") public String type;
    @Column(name = "desc") public String desc;
    @Column(name = "who") public String who;
    @Column(name = "used") public boolean used;
    @Column(name = "objectId") public String objectId;
    @Column(name = "createdAt") public Date createdAt;
    @Column(name = "updatedAt") public Date updatedAt;
    @Column(name = "publishedAt") public Date publishedAt;
    @Column(name = "imageWidth") public int imageWidth;
    @Column(name = "imageHeight") public int imageHeight;
}
