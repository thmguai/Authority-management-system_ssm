package com.it.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.it.ssm.domain.Syslog;
import com.it.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {


    @Autowired
    private ISyslogService syslogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Syslog> logs = syslogService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(logs);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-page-list");
        return mv;
    }
}
