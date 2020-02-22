package com.example.demo.dao;

import com.example.demo.pojo.Invitation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationMapper {
    int deleteByPrimaryKey(Integer invitationId);

    int insert(Invitation record);

    int insertSelective(Invitation record);

    Invitation selectByPrimaryKey(Integer invitationId);

    int updateByPrimaryKeySelective(Invitation record);

    int updateByPrimaryKeyWithBLOBs(Invitation record);

    int updateByPrimaryKey(Invitation record);

    List<Invitation> selectAll();

    List<Invitation> selectUseraccept(Integer userId);

    List<Invitation> selectUserpublish(Integer userId);
}