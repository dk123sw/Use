package me.drakeet.meizhi.model;

import com.activeandroid.annotation.Column;

import java.util.Date;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankModel {

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
    public Date createdAt;
    @Column(name = "updatedAt")
    public Date updatedAt;

}
