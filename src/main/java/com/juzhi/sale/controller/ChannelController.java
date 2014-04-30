package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelDao;
import com.juzhi.sale.entity.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class ChannelController {

    @Autowired
    private ChannelDao channelDao;


    @RequestMapping(value = "/bin/channel/save", method = RequestMethod.GET)
    public String saveChannel() {
        Channel channel = new Channel();
        channel.setCname("baidu");

        channelDao.saveChannel(channel);


        return "channel";
    }



}
