package com.example.demo.Service.Impl;

import com.example.demo.Service.InvitationService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.InvitationCollectMapper;
import com.example.demo.dao.InvitationMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.Invitation;
import com.example.demo.pojo.InvitationCollect;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.InvitationVo;
import com.example.demo.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvitationServiceImp implements InvitationService {
    @Autowired
    InvitationMapper invitationMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    InvitationCollectMapper invitationCollectMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public ServiceResponse<List<InvitationVo>> getindex() {
       List<Invitation> list=  invitationMapper.selectAll();
        List<InvitationVo> invitationVos = makevo(list);
        return  ServiceResponse.createBysuccessMessage("ok",invitationVos);
    }

    @Override
    public ServiceResponse addinvitation(Invitation invitation) {
        invitation.setInvitationStatus(0);
        invitation.setCreateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date())));
        invitation.setUpdateTime(DateTimeUtil.strToDate(DateTimeUtil.dateToStr(new Date())));
        int temp=invitationMapper.insertSelective(invitation);
        if(temp<0){
            return  ServiceResponse.createByErrorMessage("数据库操作错误");
        }
        return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse acceptinvitation(Integer invitation_id, Integer user_id) {
       //先判断invitation是否已被接受收
        Invitation invitation=invitationMapper.selectByPrimaryKey(invitation_id);
        if(invitation==null) return ServiceResponse.createByErrorMessage("贴子信息错误");
        Integer id=invitation.getAcceptUserId();
        if(id!=null){
            return ServiceResponse.createByErrorMessage("任务已经被接受");
        }
        //根据invitationid 将状态改为以接受 并补充接收着
        invitation.setInvitationStatus(1);
        invitation.setAcceptUserId(user_id);
        invitationMapper.updateByPrimaryKey(invitation);
       return ServiceResponse.createBysuccessMessage("ok");
    }

    @Override
    public ServiceResponse collect(Integer invitation_id, Integer user_id) {
        InvitationCollect invitationCollect=new InvitationCollect();
        invitationCollect.setInvitationId(invitation_id);
        invitationCollect.setUserId(user_id);
        invitationCollectMapper.insert(invitationCollect);
        return ServiceResponse.createBysuccessMessage("ok");
    }

    private List<InvitationVo> makevo(List<Invitation> all) {
        List<InvitationVo> invitationVos=new ArrayList<>();
        for(Invitation temp: all){
           InvitationVo invitationVo=new InvitationVo();
           invitationVo.setContent(temp.getContent());
           invitationVo.setPrice(temp.getPrice());
           invitationVo.setTime(temp.getCreateTime());
           invitationVo.setId(temp.getInvitationId());
           invitationVo.setInvitationStatus(temp.getInvitationStatus());
           User user=userMapper.selectByPrimaryKey(temp.getUesrId());
           invitationVo.setPerson(user.getNickname());
           invitationVo.setHead(user.getAvatarurl());
           invitationVo.setUesrId(temp.getUesrId());
           invitationVos.add(invitationVo);
       }
       return invitationVos;
    }
}
