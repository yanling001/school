package com.example.demo.Service;

import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.Invitation;
import com.example.demo.pojo.vo.InvitationVo;
import com.example.demo.pojo.vo.UserVo;

import java.util.List;

public interface InvitationService {
    ServiceResponse<List<InvitationVo>> getindex();
    ServiceResponse<UserVo> getUserinfor(Integer userId);
    ServiceResponse addinvitation(Invitation invitation);

    ServiceResponse acceptinvitation(Integer invitation_id, Integer user_id);

    ServiceResponse collect(Integer invitation_id, Integer user_id);
}
