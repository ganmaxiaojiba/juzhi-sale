package com.juzhi.sale.entity;

/**
 * Created by xjwan on 4/30/14.
 */
public class Channel {
    private int c_id;
    private String cname;

    public Channel() {

    }

    public Channel(int c_id, String cname) {
        this.c_id = c_id;
        this.cname = cname;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
