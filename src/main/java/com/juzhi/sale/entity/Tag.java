package com.juzhi.sale.entity;

/**
 * Created by xjwan on 4/30/14.
 */
public class Tag {
    private int tid;
    private String tname;
    private String description;
    private String link;
    private int click_rate;

    public Tag() {
    }


    public Tag(int tid, String tname, String description, String link, int click_rate) {
        this.click_rate = click_rate;
        this.tid = tid;
        this.tname = tname;
        this.description = description;
        this.link = link;
    }

    public int gettid() {
        return tid;
    }

    public void settid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getClick_rate() {
        return click_rate;
    }

    public void setClick_rate(int click_rate) {
        this.click_rate = click_rate;
    }
}
