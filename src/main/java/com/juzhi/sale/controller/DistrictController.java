package com.juzhi.sale.controller;

import com.juzhi.sale.dao.DistrictDao;
import com.juzhi.sale.entity.District;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class DistrictController {
    @Autowired
    private DistrictDao districtDao;

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

    @RequestMapping(value="/market/add/district/done", method = RequestMethod.POST)
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

    @RequestMapping(value = "/market/",method = RequestMethod.GET)
    public ModelAndView findDistrict(Model model){
        List<District> districtList = districtDao.findDistrict();
        model.addAttribute("districtList",districtList);
        return new ModelAndView("market");
    }
}
