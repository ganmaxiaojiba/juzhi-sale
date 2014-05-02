package com.juzhi.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gschen on 5/2/14.
 */
@Controller
public class MarketController {

    @RequestMapping("/market/")
    public String redirectToMarketIndex(){

        return "market";
    }

}
