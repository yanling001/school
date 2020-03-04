package com.example.demo.Service;

import com.example.demo.common.ServiceResponse;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.UserVo;

public interface UserService {
  ServiceResponse<UserVo> getUserinfor(Integer userId);

  ServiceResponse updateUserinfo(User user);
}
