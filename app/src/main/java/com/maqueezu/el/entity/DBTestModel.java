package com.maqueezu.el.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by admin on 2019/5/8 0008.
 */

@Entity
public class DBTestModel {

    @Id
    private String id;
    @Property
    private String var;

    @Generated(hash = 64820419)
    public DBTestModel(String id, String var) {
        this.id = id;
        this.var = var;
    }

    @Generated(hash = 1453884299)
    public DBTestModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "DBTestModel{" + "id='" + id + '\'' + ", var='" + var + '\'' + '}';
    }
}
