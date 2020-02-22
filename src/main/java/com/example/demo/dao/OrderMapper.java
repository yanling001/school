package com.example.demo.dao;

import com.example.demo.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKeyWithBLOBs(Order record);

    int updateByPrimaryKey(Order record);

    int selectOrderid(Order order);

    List<Order> selectOrderbyshopid(Integer shopid);
}