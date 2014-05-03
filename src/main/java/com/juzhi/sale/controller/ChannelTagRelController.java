package com.juzhi.sale.controller;

import com.juzhi.sale.dao.ChannelTagRelDao;
import com.juzhi.sale.entity.Channel;
import com.juzhi.sale.entity.ChannelTagRel;
import com.juzhi.sale.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by xjwan on 4/30/14.
 */
@Controller
public class ChannelTagRelController {
    @Autowired
    private ChannelTagRelDao channelTagRelDao;

    @RequestMapping(value = "/bin/tagrelation/save", method = RequestMethod.GET)
    public String saveTagRel() {
        ChannelTagRel channelTagRel = new ChannelTagRel();
        channelTagRel.setCid(1);
        channelTagRel.setTid(1);
        channelTagRelDao.insertTagRel(channelTagRel);
        return "";
    }

    @RequestMapping(value = "/bin/tagrelation/search", method = RequestMethod.GET)
    public ModelAndView searchTagRel(Model model) {
        int cid = 1;
        List list = channelTagRelDao.findTagIdByChannelId(cid);
        model.addAttribute("tagrelationList", list);
        return new ModelAndView("success");
    }
}
