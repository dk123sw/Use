package me.drakeet.meizhi.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by drakeet on 6/20/15.
 */
@Table(name = "Meizhis")
public class Meizhi extends Model implements Serializable {

    @Column(name = "url")
    public String url;
    @Column(name = "type")
    public String type;
    @Column(name = "desc")
    public String desc;
    @Column(name = "who")
    public String who;
    @Column(name = "used")
    public boolean used;
    @Column(name = "objectId")
    public String objectId;
    @Column(name = "createdAt")
    public String createdAt;
    @Column(name = "updatedAt")
    public String updatedAt;
    @Column(name = "imageWidth")
    public int imageWidth;
    @Column(name = "imageHeight")
    public int imageHeight;

}
