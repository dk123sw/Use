/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.drakeet.meizhi.data.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Unique;
import java.io.Serializable;

/**
 * 序列化：
 *  1.永久性保存对象，保存对象的字节序列到本地文件中；
 *  2.对象在网络中传递
 *  Meizhi、Gank的父类
 */
public class Soul implements Serializable {

//     @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)主键自增长
//     @Column 指定列名
//     @NotNull @Unique 非空约束 唯一约束
    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT) @Column("_id") public long id;
    @NotNull @Unique @Column("objectId") public String objectId;
}
