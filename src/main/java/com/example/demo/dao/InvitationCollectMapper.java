package com.example.demo.dao;

import com.example.demo.pojo.InvitationCollect;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationCollectMapper {
    int deleteByPrimaryKey(Integer invitationCollectId);

    int insert(InvitationCollect record);

    int insertSelective(InvitationCollect record);

    InvitationCollect selectByPrimaryKey(Integer invitationCollectId);

    int updateByPrimaryKeySelective(InvitationCollect record);

    int updateByPrimaryKey(InvitationCollect record);
}