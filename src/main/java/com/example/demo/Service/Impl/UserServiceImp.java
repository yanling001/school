package com.example.demo.Service.Impl;

import com.example.demo.Service.UserService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.InvitationMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.dao.WeChatMapper;
import com.example.demo.pojo.User;
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
        if (user==null) return ServiceResponse.createByError();
        UserVo userVo = makeUserVo(user);

        return ServiceResponse.createBysuccessMessage("ok",userVo);
    }

    @Override
    public ServiceResponse updateUserinfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return ServiceResponse.createBysuccessMessage("0k");
    }

    private UserVo makeUserVo(User user) {
        UserVo userVo =new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setGender(user.getGender());
        userVo.setCity(user.getProvince()+"-"+user.getCity());
        userVo.setPhone(user.getPhone());
        userVo.setEmail(user.getEmail());
        userVo.setAvatarurl(user.getAvatarurl());
        return userVo;
    }


}
