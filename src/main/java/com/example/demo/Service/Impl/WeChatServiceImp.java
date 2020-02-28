package com.example.demo.Service.Impl;

import com.example.demo.Service.WeChatService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.UserMapper;
import com.example.demo.dao.WeChatMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.WeChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeChatServiceImp implements WeChatService {
  @Autowired
    WeChatMapper weChatMapper;
  @Autowired
    UserMapper userMapper;
    @Override
    public String wechatlogin(WeChat weChat) {
        //先判断信息是不是已经存储
        WeChat wechat= weChatMapper.selectBynickname(weChat.getNickname());
        if(wechat == null){
         int temp =  weChatMapper.insert(weChat);
         if(temp<0){
             return "数据库错误";
         }
         return  "用户未绑定信息";
        }
       User user =new User();
        if(user==null) return   "用户未绑定信息";
        return "添加成功";
    }
}
