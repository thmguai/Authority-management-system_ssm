package com.it.ssm.dao;

import com.it.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IProductDao {

    @Select("select * from product where id=#{id}")
    public Product findById(String id);

    @Select("select * from product")
    public List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);


    @Update("update product set productName=#{product.productName},productNum=#{product.productNum},cityName=#{product.cityName},departureTime=#{product.departureTime},productPrice=#{product.productPrice},productDesc=#{product.productDesc},productStatus=#{product.productStatus} where id=#{id}")
    void updateById(@Param("product") Product product, @Param("id") String id);

}
