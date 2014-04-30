package com.juzhi.sale.controller;

import com.juzhi.sale.dao.DistrictDao;
import com.juzhi.sale.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class DistrictController {
    @Autowired
    private DistrictDao districtDao;

    @RequestMapping(value = "/bin/district/save",method = RequestMethod.GET)
    public String saveDistrict(){
        District district = new District();
        district.setDname("上海");
        district.setDescription("上海是一个很好的平台");
        districtDao.saveDistrict(district);
        return "";
    }
}
