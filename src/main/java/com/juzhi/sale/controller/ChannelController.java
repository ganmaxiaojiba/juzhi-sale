package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelDao;
import com.juzhi.sale.entity.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(value = "/bin/channel/search", method = RequestMethod.GET)
    public ModelAndView searchChannel(Model model){
        String name = "shanghai";
        List list = channelDao.findChannelByDistrict(name);
        model.addAttribute("channel",list);
        return new ModelAndView("success");
    }
}
