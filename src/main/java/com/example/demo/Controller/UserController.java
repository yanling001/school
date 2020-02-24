package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.UserService;
import com.example.demo.common.ServiceResponse;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.vo.UserVo;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.JWTUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
 //   private String appid= "wxba447fb7b8387ff2";
    //private String appsecret="e420d0eb7fdc85016d2a885a7ee3f162";
    private String appid= "wx6ed324df0267565f";
    private String appsecret="069b310fb741669422d3995cfaf54030";
    private String openid;
    private String sessionid;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    //登录接口
    @RequestMapping("/login")
    @ResponseBody
    private ServiceResponse<Map<String,Object>> login(@RequestBody String jsonData) {
        System.out.println("infoData" + jsonData);
       JSONObject dataObj = JSONObject.parseObject(jsonData);
        String  code =  (String) dataObj.get("code");
        String  encryptedData =  (String) dataObj.get("encryptedData");
        String  iv =  (String) dataObj.get("iv");
        String sessionkey = getSessionKey(code);
        JSONObject userInfo = this.getUserInfo(encryptedData, sessionkey, iv);//JSONObject.parseObject(jsonData);
        System.out.println(userInfo.toJSONString());
        User user=  JSON.parseObject(userInfo.toJSONString(),User.class);
        user.setOpenidWeb((String) userInfo.get("openId"));//
        System.out.println(user.getNickname()+"   "+user.getOpenidWeb());
         User temp=userMapper.checkUser(user.getNickname(),user.getOpenidWeb());
         Map<String,Object> map=new HashMap();

         if (temp==null){
             userMapper.insert(user);
             User checkUser=userMapper.checkUser(user.getNickname(),user.getOpenidWeb());
             map.put("userid",checkUser.getUserId());
             String token = JWTUtil.sign(user);
             map.put("token",token);
             return ServiceResponse.createBysuccessMessage("ok",map);
         }
        String token = JWTUtil.sign(temp);
        map.put("userid",temp.getUserId());
        map.put("token",token);
        return ServiceResponse.createBysuccessMessage("ok",map);
    }
    private JSONObject getUserInfo(String encryptedData, String sessionkey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {               // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                System.out.println("result:  "+ result);
                return JSONObject.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;


    }

    private String getSessionKey(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session"+"?appid="+appid+"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code";
        Map<String, String> map = new HashMap();
        map.put("appid", "wxba447fb7b8387ff2");
        map.put("appsecret", "e420d0eb7fdc85016d2a885a7ee3f162");
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        // 解析json
        JSONObject jsonObject = HttpUtil.GET(url);
        System.out.println(jsonObject.toJSONString());
        System.out.println((String) jsonObject.get("session_key"));
        return (String) jsonObject.get("session_key");

    }
    @RequestMapping("/get")
    @ResponseBody
    public ServiceResponse<UserVo> getUserinfo(Integer user_id){
         return  userService.getUserinfor(user_id);
    }
}
