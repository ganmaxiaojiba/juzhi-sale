package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelTagDao;
import com.juzhi.sale.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class ChannelTagController {
    @Autowired
    private ChannelTagDao channelTagDao;

    @RequestMapping(value = "/bin/channeltag/save",method = RequestMethod.GET)
    public String saveChannelTag(){
        Tag tag = new Tag();
        tag.setTname("baidu");
        tag.setDescription("baidu nice");
        tag.setLink("http://www.baidu.com");
        channelTagDao.insertTag(tag);
        return "";
    }
}
