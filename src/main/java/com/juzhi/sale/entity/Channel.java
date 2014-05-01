package com.juzhi.sale.entity;

/**
 * Created by xjwan on 4/30/14.
 */
public class Channel {
    private int cid;
    private String cname;

    public Channel() {

    }

    public Channel(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
