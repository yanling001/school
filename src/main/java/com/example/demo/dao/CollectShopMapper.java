package com.example.demo.dao;

import com.example.demo.pojo.CollectShop;
import com.example.demo.pojo.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectShopMapper {
    int deleteByPrimaryKey(Integer collectshopId);

    int insert(CollectShop record);

    int insertSelective(CollectShop record);

    CollectShop selectByPrimaryKey(Integer collectshopId);

    int updateByPrimaryKeySelective(CollectShop record);

    int updateByPrimaryKey(CollectShop record);

    CollectShop selectcollet(@Param("userId") Integer userid,@Param("shopId") Integer shopid);

    List<Integer> selectUsercollet(Integer userId);
}