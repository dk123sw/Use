package me.drakeet.meizhi.model;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Unique;
import java.io.Serializable;

/**
 * Created by drakeet(http://drakeet.me)
 * Date: 8/18/15 13:55
 */
public class Soul implements Serializable {

    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT) @Column("_id") protected long id;
    @NotNull @Unique @Column("objectId") public String objectId;
}
