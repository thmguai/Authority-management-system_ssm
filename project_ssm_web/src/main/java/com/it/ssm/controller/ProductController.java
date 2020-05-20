package com.it.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.it.ssm.domain.Product;
import com.it.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String id){
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-edit");
        return mv;
    }
    @RequestMapping("/updateById")
    public String updateById(Product product,@RequestParam(name = "id") String id){
        System.out.println("***********************");
        productService.updateById(product,id);
        return "redirect:findAll?page=1&size=4";
    }

    @RequestMapping("/findAll")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-page-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll?page=1&size=4";
    }
}
