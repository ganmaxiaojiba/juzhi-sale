package com.juzhi.sale.controller;

import com.juzhi.sale.dao.DistrictChannelRelDao;
import com.juzhi.sale.entity.*;
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
public class DistrictChannelRelController {
    @Autowired
    private DistrictChannelRelDao districtChannelRelDao;

    @Autowired
    private District district;

    @Autowired
    private Channel channel;

    @RequestMapping(value = "/bin/districtchannelrel/save", method = RequestMethod.GET)
    public String saveDistrictChannelRel() {
        DistrictChannelRel districtChannelRel = new DistrictChannelRel();

        districtChannelRel.setDid(2);
        districtChannelRel.setCid(2);
        districtChannelRelDao.insertRel(districtChannelRel);
        return "";
    }

    @RequestMapping(value = "/bin/districtchannelrel/search", method = RequestMethod.GET)
    public ModelAndView searchDistrictChannelRel(Model model) {
        int did = 1;
        List list = districtChannelRelDao.findChannelIdByDistrictId(did);
        model.addAttribute("districtchannelrelList", list);
        return new ModelAndView("success");
    }
}
