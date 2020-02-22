package com.example.demo.Controller;

import com.example.demo.Service.InvitationService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.Invitation;
import com.example.demo.pojo.vo.InvitationVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Api(description = "跑腿相关的api")
@Controller
@RequestMapping("/errands")
public class InvitationController {
    @Autowired
    InvitationService invitationService;
    //获取页面信息
    @ApiOperation(value = "载入界面时获取跑腿信息", notes = "查询数据库的全部跑腿帖子")
    @RequestMapping(value = "inf",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResponse<List<InvitationVo>> getIndex(){
      //认证授权校验
        return  invitationService.getindex();
    }
    //添加跑腿帖子
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
      //使用@ApiOperation注解来修饰接口
     @ApiImplicitParams({
                @ApiImplicitParam(name = "user_id", value = "用户id",dataType = "int",required = true),
                @ApiImplicitParam(name = "content", value = "文档", required = true),     //使用ApiImplcitParam修饰接口参数
                @ApiImplicitParam(name = "time", value = "创建时间", required = true )     //使用ApiImplcitParam修饰接口参数
        })
      @ApiOperation(value = "通过用户Id来获取用户信息",notes = "RestFul风格，需要传用户Id" )
    public ServiceResponse addinvitation(Invitation invitation){
        return  invitationService.addinvitation(invitation);
    }
    //接受帖子
      @ApiImplicitParams({
              @ApiImplicitParam(name = "id", value = "帖子id",dataType = "int", required = true),
              @ApiImplicitParam(name = "user_id", value = "用户id",dataType = "int", required = true)
      })
       @ApiOperation(value = "通过用户Id和帖子id来接受帖子",notes = "RestFul风格，需要传用户Id帖子id" )
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


}
