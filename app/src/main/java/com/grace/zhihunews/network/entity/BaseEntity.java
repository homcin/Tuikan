package com.grace.zhihunews.network.entity;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/14.
 */
public class BaseEntity implements Serializable {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    public long idx;

}
