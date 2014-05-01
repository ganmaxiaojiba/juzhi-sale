package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelDao;
import com.juzhi.sale.dao.ChannelTagDao;
import com.juzhi.sale.entity.Channel;
import com.juzhi.sale.entity.Tag;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class ChannelTagController {
    @Autowired
    private ChannelTagDao channelTagDao;

    @Autowired
    private ChannelDao channelDao;

    @RequestMapping(value = "/bin/add/tag", method = RequestMethod.GET)
    public ModelAndView selectChannel(Model model) {
        List<Channel> list = channelDao.findChannel();
        model.addAttribute("channelList",list);
        return new ModelAndView("selectchannel");
    }

    @RequestMapping(value = "/bin/add/tag/{channelName}", method = RequestMethod.GET)
    public String redirectToAddTagPage(@PathVariable String channelName,Model model) {
//        Tag tag = new Tag();
//        tag.setTname("百度推广1000");
//        tag.setDescription("baidu nice");
//        tag.setLink("http://www.baidu.com");
//        channelTagDao.saveTag(tag, "abc");

        return "addtag";
    }

    @RequestMapping(value="/bin/add/tag/new", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView save(@RequestBody String jsonString,Model model) {
        JSONArray array = JSONArray.fromObject(jsonString);
        Object[] obj = new Object[array.size()];
        for (int i=0; i<array.size(); i++){
            JSONObject jsonObject = array.getJSONObject(i);
            Tag tag = new Tag();
            tag.setTname(jsonObject.getString("tagName"));
            tag.setLink(jsonObject.getString("tagLink"));
            tag.setDescription(jsonObject.getString("tagDesc"));
            channelTagDao.saveTag(tag, jsonObject.getString("channelName"));
        }

        return new ModelAndView("success");
    }

    @RequestMapping(value = "/bin/channeltag/search",method = RequestMethod.GET)
    public ModelAndView searchChannelTag(Model model){
        String name = "百度推广";
        List list = channelTagDao.findTagByName(name);
        model.addAttribute("channeltagList",list);
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


        String id =  Integer.toString(channelTagDao.findIdByChannelTagName(tagName));

        return id;
    }
}
