package com.juzhi.sale.entity;

/**
 * Created by xjwan on 4/30/14.
 */
public class District {
    private int d_id;
    private String dname;
    private String description;

    public District(){}

    public District(String dname, String description) {
        this.dname = dname;
        this.description = description;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
