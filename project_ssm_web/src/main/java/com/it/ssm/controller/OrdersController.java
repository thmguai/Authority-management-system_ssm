package com.it.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.it.ssm.domain.Orders;
import com.it.ssm.domain.Traveller;
import com.it.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;


    //无分页
    /*@RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");
        return mv;
    }*/

    //有分页
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        //PageInfo就是分页bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String orderId){

        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(orderId);
        List<Traveller> list = orders.getTravellers();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
