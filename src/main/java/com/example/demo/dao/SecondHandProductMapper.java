package com.example.demo.dao;

import com.example.demo.pojo.SecondHandProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SecondHandProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(SecondHandProduct record);

    int insertSelective(SecondHandProduct record);

    SecondHandProduct selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(SecondHandProduct record);

    int updateByPrimaryKeyWithBLOBs(SecondHandProduct record);

    int updateByPrimaryKey(SecondHandProduct record);

    int selectid(SecondHandProduct product);

    List<SecondHandProduct> selectAll();
}