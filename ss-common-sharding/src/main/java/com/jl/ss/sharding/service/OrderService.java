package com.jl.ss.sharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jl.ss.sharding.entity.OrderEntity;

import java.util.List;

public interface OrderService extends IService<OrderEntity> {

    void addOrder(OrderEntity OrderEntity);

    List<OrderEntity> findByUserId(Long userId);

    OrderEntity findByOrderId(Long orderId);
}

