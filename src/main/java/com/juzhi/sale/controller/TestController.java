package com.juzhi.sale.controller;

/**
 * Created by xjwan on 4/28/14.
 */

import com.juzhi.sale.dao.HelloDaoImpl;
import com.juzhi.sale.entity.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TestController {
    @Autowired
    private HelloDaoImpl helloDaoImpl;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    // @ResponseBody 直接返回string
    public String testHello(ModelMap model) {
        model.addAttribute("message", "helloworld");
        return "hello";
    }

    @RequestMapping(value = "/vt", method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map map = new HashMap();
        List wordList = new ArrayList();

        wordList.add("hello");
        wordList.add("world");

        map.put("wordList", wordList);

        return new ModelAndView("index", map);
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveHello() {
        Hello hello = new Hello();
        hello.setId(1);
        hello.setName("hello");
        helloDaoImpl.insert(hello);

        return "success";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView selectByName(String name, Model model) {
        name = "hello";
        List list = helloDaoImpl.findByName(name);
        model.addAttribute("test", list);
        return new ModelAndView("success");
    }

    @RequestMapping("index.vm")
    public ModelAndView test1() {
        return new ModelAndView("pages/widgets");
    }
}