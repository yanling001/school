package com.example.demo.dao;

import com.example.demo.pojo.OrderToProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderToProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderToProduct record);

    int insertSelective(OrderToProduct record);

    OrderToProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderToProduct record);

    int updateByPrimaryKey(OrderToProduct record);

    List<Integer> selectproductbyshopid(Integer orderId);
}