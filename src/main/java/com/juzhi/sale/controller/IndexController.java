package com.juzhi.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xjwan on 4/29/14.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "market";
    }


}
