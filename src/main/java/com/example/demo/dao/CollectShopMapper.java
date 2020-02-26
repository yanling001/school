package com.example.demo.dao;

import com.example.demo.pojo.CollectShop;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectShopMapper {
    int deleteByPrimaryKey(Integer collectshopId);

    int insert(CollectShop record);

    int insertSelective(CollectShop record);

    CollectShop selectByPrimaryKey(Integer collectshopId);

    int updateByPrimaryKeySelective(CollectShop record);

    int updateByPrimaryKey(CollectShop record);
}