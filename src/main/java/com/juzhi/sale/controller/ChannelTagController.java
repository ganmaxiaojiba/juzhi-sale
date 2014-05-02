package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelDao;
import com.juzhi.sale.dao.ChannelTagDao;
import com.juzhi.sale.dao.DistrictDao;
import com.juzhi.sale.entity.Channel;
import com.juzhi.sale.entity.ChannelTagWrapper;
import com.juzhi.sale.entity.Tag;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class ChannelTagController {
    @Autowired
    private ChannelTagDao channelTagDao;

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private DistrictDao districtDao;

    @RequestMapping(value = "/market/add/tag", method = RequestMethod.GET)
    public ModelAndView selectChannel(Model model) {
        List<Channel> list = channelDao.findChannel();
        model.addAttribute("channelList", list);
        return new ModelAndView("selectchannel");
    }

    @RequestMapping(value = "/market/add/tag/{channelName}", method = RequestMethod.GET)
    public String redirectToAddTagPage(@PathVariable String channelName, Model model) {
//        Tag tag = new Tag();
//        tag.setTname("百度推广1000");
//        tag.setDescription("baidu nice");
//        tag.setLink("http://www.baidu.com");
//        channelTagDao.saveTag(tag, "abc");

        return "addtag";
    }

    @RequestMapping(value = "/market/add/tag/done", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody String jsonString, HttpServletResponse response) {

        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        StringBuilder msg = new StringBuilder();

        ObjectMapper mapper = new ObjectMapper();
        try {
            ChannelTagWrapper channelTagWrapper = mapper.readValue(jsonString, ChannelTagWrapper.class);

            Tag tag = new Tag();
            tag.setTname(channelTagWrapper.getTagName());
            tag.setDescription(channelTagWrapper.getTagDesc());
            tag.setLink(channelTagWrapper.getTagLink());

            channelTagDao.saveTag(tag, channelTagWrapper.getChannelName());
        } catch (IOException e) {
            e.printStackTrace();

            msg.append("Add tag fail!");
            return msg.toString();
        }
        msg.append("Add tag success!");

        return msg.toString();
    }

    @RequestMapping(value = "/bin/channeltag/search", method = RequestMethod.GET)
    public ModelAndView searchChannelTag(Model model) {
        String name = "百度推广";
        List list = channelTagDao.findTagByName(name);
        model.addAttribute("channeltagList", list);

        return new ModelAndView("success");
    }


    @RequestMapping("/bin/view/tag/{tagName}")
    @ResponseBody
    public String viewTagsByChannelName(@PathVariable String tagName, Model model) {

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


        String id = Integer.toString(channelTagDao.findIdByChannelTagName(tagName));

        return id;
    }

    @RequestMapping(value = "/bin/add/success", method = RequestMethod.GET)
    public ModelAndView addSuccess(String msg, Model model) {
        //StringBuilder msg = new StringBuilder();

        model.addAttribute("msg", msg);
        return new ModelAndView("success");
    }

    @RequestMapping("/bin/view/channel/tags")
    @ResponseBody
    public String findAllTagsByCId(Model model) {
        // Map<Channel,Tag> map = new HashMap<>();
        //List<Tag> tagList = new ArrayList<Tag>();
        List<Tag> tagList = channelTagDao.findTagsByChannelId(1);

        StringBuilder tags = new StringBuilder();

        for (Tag tag : tagList) {
            tags.append(tag.getTname());
        }
        return tags.toString();
    }

    @RequestMapping(value = "/market/view/shanghai", method = RequestMethod.GET)
    public ModelAndView findAll(Model model) {
        Map<String, List<Tag>> map = new HashMap<String, List<Tag>>();
        String cname = null;

        int did = districtDao.findIdByDistrictName("上海");
        List<Channel> channelList = channelDao.findChannelsByDistrictId(did);
        for (Channel channel : channelList) {
            cname = channel.getCname();
            int cid = channelDao.findIdByChannelName(cname);
            List<Tag> tagList = channelTagDao.findTagsByChannelId(cid);
            map.put(cname, tagList);
        }
        model.addAttribute("map", map);
        model.addAttribute("channelList", channelList);
        return new ModelAndView("district");
    }
}
