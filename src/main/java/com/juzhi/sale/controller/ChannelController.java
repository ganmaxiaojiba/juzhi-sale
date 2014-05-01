package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelDao;
import com.juzhi.sale.entity.Channel;
import com.juzhi.sale.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    public ModelAndView searchChannel(Model model) {
        String name = "上海";

        List list = channelDao.findChannelByDistrict(name);

        model.addAttribute("channelList", list);

        return new ModelAndView("success");
    }

    @RequestMapping("/bin/view/channel/{channelName}")
    @ResponseBody
    public String viewTagsByChannelName(@PathVariable String channelName, Model model) {

//        List<Tag> tagList = new ArrayList<Tag>();
//        tagList.add(new Tag(1, "sina", "hello,sina", "http://www.sina.com", 10));
//
//        tagList.add(new Tag(1, "sina", "hello,sina", "http://www.sina.com", 10));
//
//        tagList.add(new Tag(1, "sina", "hello,sina", "http://www.sina.com", 10));
//
//        tagList.add(new Tag(1, "sina", "hello,sina", "http://www.sina.com", 10));
//
//
//        model.addAttribute("channelName", channelName);
//        model.addAttribute("tagList", tagList);
//
//
//        return new ModelAndView("channel");


        String id =  Integer.toString(channelDao.findIdByChannelName(channelName));

        return id;
    }

}
