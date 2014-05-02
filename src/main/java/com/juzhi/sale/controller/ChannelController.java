package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelDao;
import com.juzhi.sale.dao.DistrictDao;
import com.juzhi.sale.entity.Channel;
import com.juzhi.sale.entity.District;
import com.juzhi.sale.entity.Tag;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
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

    @RequestMapping(value = "/bin/add/channel", method = RequestMethod.GET)
    public ModelAndView selectDistrict(Model model) {
        List<District> list = districtDao.findDistrict();
        model.addAttribute("districtList",list);
        return new ModelAndView("selectdistrict");
    }

    @RequestMapping(value = "/bin/add/channel/{districtName}", method = RequestMethod.GET)
    public String redirectToAddChannelPage(@PathVariable String districtName,Model model) {
//        Tag tag = new Tag();
//        tag.setTname("百度推广1000");
//        tag.setDescription("baidu nice");
//        tag.setLink("http://www.baidu.com");
//        channelTagDao.saveTag(tag, "abc");

        return "addchannel";
    }

    @RequestMapping(value="/bin/add/channel/new", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView save(@RequestBody String jsonString,Model model) {

        class DistrictChannelWrapper{
            private String channelName;
            private String districtName;

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            DistrictChannelWrapper wrapper = mapper.readValue(jsonString, DistrictChannelWrapper.class);



        } catch (IOException e) {
            e.printStackTrace();
        }


//        JSONArray array = JSONArray.fromObject(jsonString);
//        Object[] obj = new Object[array.size()];
//        for (int i=0; i<array.size(); i++){
//            JSONObject jsonObject = array.getJSONObject(i);
//           String districtName =  jsonObject.getString("districtName");
//            String channelName =  jsonObject.getString("channelName");
//            Channel channel = new Channel();
//            channel.setCname(jsonObject.getString("channelName"));
//            channelDao.saveChannel(channel, jsonObject.getString("channelName"));
//        }
        return new ModelAndView("success");
    }

}
