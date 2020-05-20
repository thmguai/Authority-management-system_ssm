package com.it.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.it.ssm.domain.Permission;
import com.it.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll?page=1&size=4";
    }
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-page-list");
        return mv;
    }
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String id){
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }
}
