package com.paicheya.hammer.newtransapp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 搜索历史entity
 * Created by cly on 17/1/31.
 */

@Entity
public class SearchHistory {
    @Id
    private long id;
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 174600038)
    public SearchHistory(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1905904755)
    public SearchHistory() {
    }
}
