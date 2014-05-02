package com.juzhi.sale.dao;

import com.juzhi.sale.entity.*;

import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */
public interface ChannelDao {
    public void saveChannel(Channel channel,String dname);

    public int findIdByChannelName(String channelName);

    public List<Channel> findChannel();

    public List<Channel> findChannelByDistrict(String dname);

    public List<Channel> findChannelsByDistrictId(int did);
}
