package com.juzhi.sale.entity;

/**
 * Created by xjwan on 4/30/14.
 */
public class Tag {
    private int t_id;
    private String tname;
    private String description;
    private String link;
    private int click_rate;

    public Tag(){}

    public Tag(int click_rate, int t_id, String tname, String description, String link) {
        this.click_rate = click_rate;
        this.t_id = t_id;
        this.tname = tname;
        this.description = description;
        this.link = link;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
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
