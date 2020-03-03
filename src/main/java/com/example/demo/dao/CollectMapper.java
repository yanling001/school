package com.example.demo.dao;

import com.example.demo.pojo.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    int selectCount(@Param("userId") Integer userId,@Param("productId") Integer productId);

    Collect selectcollet(@Param("userId") Integer userId,@Param("productId") Integer productId);
}