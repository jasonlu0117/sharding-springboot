package com.jl.ss.order.controller;

import com.jl.ss.common.model.R;
import com.jl.ss.common.util.SnowflakeIdWorker;
import com.jl.ss.sharding.entity.OrderEntity;
import com.jl.ss.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("add")
    public R addOrder(@RequestParam Long userId) {
        long orderId = SnowflakeIdWorker.getId();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setUserId(userId);
        orderEntity.setOrderName("order: " + orderId);
        orderService.addOrder(orderEntity);
        return R.ok();
    }

    @GetMapping("list")
    public R findByUserId(@RequestParam Long userId) {
        return R.ok().data(orderService.findByUserId(userId));
    }

    @GetMapping("item")
    public R findByOrderId(@RequestParam Long orderId) {
        return R.ok().data(orderService.findByOrderId(orderId));
    }

}