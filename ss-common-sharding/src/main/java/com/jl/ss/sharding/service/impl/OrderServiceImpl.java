package com.jl.ss.sharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jl.ss.sharding.entity.OrderEntity;
import com.jl.ss.sharding.mapper.OrderMapper;
import com.jl.ss.sharding.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Override
    public void addOrder(OrderEntity orderEntity) {
        this.baseMapper.insert(orderEntity);
    }

    @Override
    public List<OrderEntity> findByUserId(Long userId) {
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public OrderEntity findByOrderId(Long orderId) {
        return this.baseMapper.selectById(orderId);
    }

}
