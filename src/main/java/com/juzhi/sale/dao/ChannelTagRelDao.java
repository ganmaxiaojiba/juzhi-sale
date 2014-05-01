package com.juzhi.sale.dao;

import com.juzhi.sale.entity.*;

import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */

public interface ChannelTagRelDao {

    public void insertTagRel(ChannelTagRel channelTagRel);

    public List<ChannelTagRel> findTagIdByChannelId(int cid);
}
