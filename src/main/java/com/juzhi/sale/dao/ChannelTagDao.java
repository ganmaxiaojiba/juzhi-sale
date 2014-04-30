package com.juzhi.sale.dao;

import com.juzhi.sale.entity.*;

import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */

public interface ChannelTagDao {
    public void insertTag(Tag tag);
    public List<Tag> findTagByChannel(String cname);
}
