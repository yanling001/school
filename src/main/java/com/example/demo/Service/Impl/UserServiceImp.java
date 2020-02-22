package com.example.demo.Service.Impl;

import com.example.demo.Service.UserService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.InvitationMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.dao.WeChatMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.WeChat;
import com.example.demo.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    InvitationMapper invitationMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    WeChatMapper weChatMapper;
    @Override
    public ServiceResponse<UserVo> getUserinfor(Integer userId) {
        User user=userMapper.selectByPrimaryKey(userId);
       UserVo userVo = makeUserVo(user);
        return ServiceResponse.createBysuccessMessage("ok",userVo);
    }

    private   UserVo makeUserVo(User user) {
        UserVo userVo=new UserVo();
        userVo.setUserId(user.getUserId());
        userVo.setEmail(user.getEmail());
        userVo.setPhone(user.getPhone());
        userVo.setUpdateTime(user.getUpdateTime());
        userVo.setCreateTime(user.getCreateTime());
        userVo.setCity(user.getCity());
        userVo.setAvatarurl(user.getAvatarurl());
        userVo.setCountry(user.getCountry());
        userVo.setProvince(user.getProvince());
        userVo.setNickname(user.getNickname());
        userVo.setAccept(invitationMapper.selectUseraccept(user.getUserId()));
        userVo.setPublish(invitationMapper.selectUserpublish(user.getUserId()));
        return  userVo;
    }
}
