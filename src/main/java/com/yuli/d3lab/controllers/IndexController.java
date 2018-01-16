//: com.yuli.d3lab.controllers.IndexController.java


package com.yuli.d3lab.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/home"})
    public String getIndexPage() {
        return "index";
    }

    @GetMapping
    @RequestMapping({"/d3lab/{chapter}/{pageName}"})
    public String getContentPage(@PathVariable String chapter,
                                 @PathVariable String pageName) {
        return chapter + "/" + pageName;
    }

}///:~