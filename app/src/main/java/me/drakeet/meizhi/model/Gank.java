package me.drakeet.meizhi.model;
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Table;
import java.util.Date;

/**
 * Created by drakeet on 8/11/15.
 */
@Table("ganks") public class Gank extends Soul {

    @Column("url") public String url;
    @Column("type") public String type;
    @Column("desc") public String desc;
    @Column("who") public String who;
    @Column("used") public boolean used;
    @Column("createdAt") public Date createdAt;
    @Column("updatedAt") public Date updatedAt;
}
