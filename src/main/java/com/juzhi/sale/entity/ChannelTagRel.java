package com.juzhi.sale.entity;

/**
 * Created by xjwan on 4/30/14.
 */
public class ChannelTagRel {
    private int id;
    private int cid;
    private int tid;

    public ChannelTagRel(){}

    public ChannelTagRel(int cid, int tid) {
        this.cid = cid;
        this.tid = tid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
