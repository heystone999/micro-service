package com.stone.order.service;

import com.stone.order.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Long create(Order order);
}