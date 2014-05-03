package com.juzhi.sale.dao;

import com.juzhi.sale.entity.Tag;

import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */

public interface ChannelTagDao {
    public void insertTag(Tag tag, String channelName);

    public void saveTag( Tag tag, String cname);

    public List<Tag> findTagByName(String tname);


    public int findIdByChannelTagName(String channelTagName);

    public List<Tag> findTagsByChannelId(int cid);

    public void deleteTagbyTId(int tid);

    public List<Tag> findTag();
}
