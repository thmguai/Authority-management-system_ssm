package com.it.ssm.service;

import com.it.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    public List<Orders> findAll(int page, int size);

    Orders findById(String orderId);
}
