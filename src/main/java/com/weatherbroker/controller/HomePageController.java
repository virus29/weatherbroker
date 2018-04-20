package com.weatherbroker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomePageController {
    @RequestMapping(method = GET, path = "/")
    public String showHomePage(){
        return "index";
    }

}
