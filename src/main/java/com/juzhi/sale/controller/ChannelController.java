package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelDao;
import com.juzhi.sale.dao.DistrictDao;
import com.juzhi.sale.entity.Channel;
import com.juzhi.sale.entity.District;
import com.juzhi.sale.entity.DistrictChannelWrapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class ChannelController {

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private DistrictDao districtDao;


//    @RequestMapping(value = "/bin/channel/save", method = RequestMethod.GET)
//    public String saveChannel() {
//        Channel channel = new Channel();
//        channel.setCname("baidu");
//        channelDao.saveChannel(channel);
//        return "channel";
//    }

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

    /*
    add channel ,step 1:select district.
     */
    @RequestMapping(value = "/market/add/channel", method = RequestMethod.GET)
    public ModelAndView selectDistrict(Model model) {
        List<District> list = districtDao.findDistrict();
        model.addAttribute("districtList",list);
        return new ModelAndView("selectdistrict");
    }

    /*
    add channel ,step 2:redirect addchannel page.
     */
    @RequestMapping(value = "/market/add/channel/{districtName}", method = RequestMethod.GET)
    public String redirectToAddChannelPage(@PathVariable String districtName,Model model) {
//        Tag tag = new Tag();
//        tag.setTname("百度推广1000");
//        tag.setDescription("baidu nice");
//        tag.setLink("http://www.baidu.com");
//        channelTagDao.saveTag(tag, "abc");

        return "addchannel";
    }

    /*
    add channel, step3: save channel to database table db_channel and db_d_c_relation;
     */
    @RequestMapping(value="/market/add/channel/done", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody String jsonString) {

        StringBuilder msg = new StringBuilder();

        ObjectMapper mapper = new ObjectMapper();

        try {
            DistrictChannelWrapper wrapper = mapper.readValue(jsonString, DistrictChannelWrapper.class);
            Channel channel = new Channel();
            channel.setCname(wrapper.getChannelName());
            channelDao.saveChannel(channel,wrapper.getDistrictName());

        } catch (IOException e) {
            e.printStackTrace();

            msg.append("Add channel fail!");
            return msg.toString();
        }

        msg.append("Add channel success!");

        return msg.toString();
    }

    @RequestMapping("/bin/view/district/channels")
    @ResponseBody
    public String findAllChannelsByDId(HttpServletResponse response){
        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Channel> channelList = channelDao.findChannelsByDistrictId(3);

        StringBuilder channels = new StringBuilder();

        for( Channel channel : channelList) {
            channels.append(channel.getCname());
            System.out.println(channels.toString());
        }
        return channels.toString();
    }

    /*
删除第一步，查出所有的channel
 */
    @RequestMapping(value = "/market/delete/channel",method = RequestMethod.GET)
    public ModelAndView selectChannel(Model model){
        List<Channel> channelList = channelDao.findChannel();
        model.addAttribute("channelList",channelList);
        return new ModelAndView("deletechannel");
    }

    /*
    删除第二步，删除是否成功
     */
    @RequestMapping(value = "/market/delete/channel/{channelName}",method = RequestMethod.GET)
    public ModelAndView deleteTag(@PathVariable String channelName, Model model){
        int cid = channelDao.findIdByChannelName(channelName);
        StringBuilder msg = new StringBuilder();
        if (cid != 0){
            channelDao.deleteChannelByChannelId(cid);
            msg.append("delete channel success!");
        }else {
            msg.append("delete channel fail！");
        }
        model.addAttribute("msg",msg);
        return new ModelAndView("success");
    }
}
