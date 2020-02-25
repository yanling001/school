package com.example.demo.Filter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.util.JWTUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
  @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        //token验证
        if (!StringUtils.isEmpty(token)) {
            Map<String, Claim> claimMap = JWTUtil.verifyToken(token);
            if (claimMap != null) {
                //账户操作...
                //校验token
                String nickname = claimMap.get("nickname").asString();
                String openid = claimMap.get("openid").asString();
                Integer role = claimMap.get("role").asInt();
                System.out.println(nickname+"  "+openid+"  "+role);
                User user = userMapper.checkUser(nickname, openid);
                if (user != null) {
                    request.setAttribute("claim",claimMap);
                    // 存入redis
                    return true;
                }
            }
        }
//        PrintWriter writer = response.getWriter();
//        response.setHeader("Content-type","application/json");
//
//        writer.write(JSON.toJSONString(ServiceResponse.createByErrorMessage("error")));
//        writer.flush();
//        writer.close();
        return true;
    }
}