package com.it.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.ssm.domain.Role;
import com.it.ssm.domain.UserInfo;
import com.it.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;


    @RequestMapping("/disableById")
    public String disableById(@RequestParam(name = "id") String id){
        userService.disableById(id);
        return "redirect:findAll?page=1&size=4";
    }
    @RequestMapping("/ableById")
    public String ableById(@RequestParam(name = "id") String id){
        userService.ableById(id);
        return "redirect:findAll?page=1&size=4";
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId") String userId,@RequestParam(name = "ids") String[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll?page=1&size=4";
    }
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo= userService.findById(id);
        List<Role> roles = userService.findOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo= userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page") Integer page,@RequestParam(name = "size") Integer size){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-page-list");
        return mv;
    }
    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect: findAll?page=1&size=4";
    }
}
