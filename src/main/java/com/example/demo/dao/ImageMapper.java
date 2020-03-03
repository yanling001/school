package com.example.demo.dao;

import com.example.demo.pojo.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    List<String> selectImageByproductid(Integer productId);

    List<String> selectImageBycommentid(Integer commentId);

    List<String> selectImageByshopid(Integer shopId);
}