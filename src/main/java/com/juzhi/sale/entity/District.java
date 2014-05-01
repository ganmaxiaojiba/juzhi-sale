package com.juzhi.sale.entity;

/**
 * Created by xjwan on 4/30/14.
 */
public class District {
    private int did;
    private String dname;
    private String description;

    public District() {
    }

    public District(String dname, String description) {
        this.dname = dname;
        this.description = description;
    }

    public int getdid() {
        return did;
    }

    public void setdid(int did) {
        this.did = did;
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
