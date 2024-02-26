package com.stone.order.web;

import com.stone.order.pojo.Order;
import com.stone.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }

    @GetMapping("/query")
    public String queryOrder() {
        orderService.queryGoods();
        System.out.println("查询订单");
        return "查询订单";
    }

    @GetMapping("/update")
    public String updateOrder() {
        return "更新订单";
    }

    @GetMapping("/save")
    public String saveOrder() {
        orderService.queryGoods();
        System.out.println("新增订单");
        return "新增订单";
    }
}
