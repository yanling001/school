package com.example.demo.dao;

import com.example.demo.pojo.ProductComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(ProductComment record);

    int insertSelective(ProductComment record);

    ProductComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(ProductComment record);

    int updateByPrimaryKeyWithBLOBs(ProductComment record);

    int updateByPrimaryKey(ProductComment record);

    List<ProductComment> selectByproductid(Integer productId);

    int getcommentid(ProductComment productComment);
}