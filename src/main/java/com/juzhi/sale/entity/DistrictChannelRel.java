package com.juzhi.sale.entity;

/**
 * Created by xjwan on 4/30/14.
 */
public class DistrictChannelRel {
    private int id;
    private int did;
    private int cid;

    public DistrictChannelRel() {
    }

    public DistrictChannelRel(int id, int did, int cid) {
        this.id = id;
        this.did = did;
        this.cid = cid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
