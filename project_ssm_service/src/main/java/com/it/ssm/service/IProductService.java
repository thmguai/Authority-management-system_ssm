package com.it.ssm.service;

import com.it.ssm.domain.Product;

import java.util.List;


public interface IProductService {

    public List<Product> findAll(Integer page, Integer size);


    void save(Product product);

    Product findById(String id);

    void updateById(Product product, String id);
}
