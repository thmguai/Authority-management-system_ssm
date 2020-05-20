package com.it.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.it.ssm.dao.IProductDao;
import com.it.ssm.domain.Product;
import com.it.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductDao iProductDao;
    @Override
    public List<Product> findAll(Integer page, Integer size) {

        PageHelper.startPage(page,size);
        return iProductDao.findAll();

    }

    @Override
    public void save(Product product) {
        iProductDao.save(product);
    }

    @Override
    public Product findById(String id) {
        return iProductDao.findById(id);
    }

    @Override
    public void updateById(Product product, String id) {
        System.out.println("----******************************************");
        System.out.println(product+id);
        iProductDao.updateById(product,id);
    }
}
