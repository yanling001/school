package com.example.demo.dao;

import com.example.demo.pojo.WeChat;
import org.springframework.stereotype.Repository;

@Repository
public interface WeChatMapper {
    int deleteByPrimaryKey(Integer wechatId);

    int insert(WeChat record);

    int insertSelective(WeChat record);

    WeChat selectByPrimaryKey(Integer wechatId);

    int updateByPrimaryKeySelective(WeChat record);

    int updateByPrimaryKey(WeChat record);
    //暂时无用
    WeChat selectBynickname(String nickname);

    WeChat selectByUnionid(String unionid);
}