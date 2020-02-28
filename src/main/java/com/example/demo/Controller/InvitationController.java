package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.InvitationService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.Invitation;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.InvitationVo;
import com.example.demo.pojo.vo.UserVo;
import com.example.demo.util.DateTimeUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(description = "跑腿相关的api")
@Controller
@RequestMapping("/errands")
public class InvitationController {
    @Autowired
    InvitationService invitationService;
    //获取页面信息
    @RequestMapping(value = "inf",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResponse<List<InvitationVo>> getIndex(){
      //认证授权校验
        return  invitationService.getindex();
    }
    //添加跑腿帖子
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse addinvitation(@RequestBody String invitation){
        JSONObject dataObj = JSONObject.parseObject(invitation);
        Invitation invitations=new Invitation();
        invitations.setUesrId((Integer) dataObj.get("p"));
        invitations.setCreateTime(DateTimeUtil.strToDate((String) dataObj.get("time"),DateTimeUtil.STANDARD_FORMAT));
        invitations.setContent(dataObj.getString("content"));
        invitations.setPrice(dataObj.getBigDecimal("price"));
        return  invitationService.addinvitation(invitations);
    }
    //接受帖子

       @ResponseBody
       @RequestMapping(value = "accept",method = RequestMethod.GET)
     public ServiceResponse acceptinvitation(@RequestParam(value = "id") Integer invitation_id,
                                             @RequestParam(value = "user_id") Integer user_id) {
        return  invitationService.acceptinvitation(invitation_id,user_id);
     }
    //收藏
    @ResponseBody
    @RequestMapping(value = "collect",method = RequestMethod.GET)
    public ServiceResponse collect(@RequestParam(value = "id") Integer invitation_id,
                                            @RequestParam(value = "user_id") Integer user_id) {
        return  invitationService.collect(invitation_id,user_id);
    }
    @RequestMapping("/getuserinfo")
    @ResponseBody
    public ServiceResponse<UserVo> getUserinfo(Integer user_id){
        return  invitationService.getUserinfor(user_id);
    }

}
