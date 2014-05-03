package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelDao;
import com.juzhi.sale.dao.ChannelTagDao;
import com.juzhi.sale.dao.DistrictDao;
import com.juzhi.sale.entity.Channel;
import com.juzhi.sale.entity.District;
import com.juzhi.sale.entity.Tag;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class DistrictController {
    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private ChannelTagDao channelTagDao;

    @RequestMapping(value = "/bin/district/save", method = RequestMethod.GET)
    public String saveDistrict() {
        District district = new District();
        district.setDname("上海");
        district.setDescription("上海是一个很好的平台");
        districtDao.saveDistrict(district);
        return "";
    }

    @RequestMapping(value = "/market/add/district", method = RequestMethod.GET)
    public ModelAndView addDistrict(Model model) {
        return new ModelAndView("adddistrict");
    }

    @RequestMapping(value = "/market/add/district/done", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody String jsonString) {

        StringBuilder msg = new StringBuilder();

        ObjectMapper mapper = new ObjectMapper();

        try {
            District wrapper = mapper.readValue(jsonString, District.class);
            District district = new District();
            district.setDname(wrapper.getDname());
            district.setDescription(wrapper.getDescription());
            districtDao.saveDistrict(district);

        } catch (IOException e) {
            e.printStackTrace();

            msg.append("Add district fail!");
            return msg.toString();
        }

        msg.append("Add district success!");

        return msg.toString();
    }

    @RequestMapping(value = "/market/", method = RequestMethod.GET)
    public ModelAndView findDistrict(Model model) {
        List<District> districtList = districtDao.findDistrict();
        model.addAttribute("districtList", districtList);
        return new ModelAndView("market");
    }

    @RequestMapping(value = "/market/view/{districtName}", method = RequestMethod.GET)
    public ModelAndView findAll(@PathVariable String districtName, Model model) {
        Map<String, List<Tag>> map = new HashMap<String, List<Tag>>();
        String cname = null;

        int did = districtDao.findIdByDistrictName(districtName);

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

    @RequestMapping(value = "/market/delete/district", method = RequestMethod.GET)
    public ModelAndView selectDistrict(Model model) {
        List<District> list = districtDao.findDistrict();
        model.addAttribute("districtList", list);
        return new ModelAndView("deletedistrict");
    }

    @RequestMapping(value = "/market/delete/district/{districtName}", method = RequestMethod.GET)
    public ModelAndView deleteDistrict(@PathVariable String districtName, Model model) {
        int did = districtDao.findIdByDistrictName(districtName);
        StringBuilder msg = new StringBuilder();
        if (did != 0) {
            districtDao.deleteDistrictByDId(did);
            msg.append("delete district success!");
        }else {
            msg.append("delete district fail!");
        }
        model.addAttribute("msg", msg);
        return new ModelAndView("success");
    }


    @RequestMapping("/market/add/district/{districtName}")
    public ModelAndView addDistrictNav(@PathVariable String districtName, Model model) {

        model.addAttribute("dname", districtName);

        return new ModelAndView("adddistrictnav");
    }
}
