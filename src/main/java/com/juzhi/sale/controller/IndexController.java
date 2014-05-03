package com.juzhi.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xjwan on 4/29/14.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "redirect:market/";
    }


}
