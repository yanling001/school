package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.WeChatService;
import com.example.demo.common.WechatResponeMS;
import com.example.demo.pojo.WeChat;
import com.example.demo.util.HttpUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
      private String appid= "wxba447fb7b8387ff2";
    private String appsecret="e420d0eb7fdc85016d2a885a7ee3f162";

    private String openid;
    private String session_key;
    @Autowired
    private WeChatService weChatService;
    @RequestMapping("/wxLogin")
    private String login(String code,String userInfo) {

        System.out.println(userInfo);
        WeChat user= JSON.parseObject(userInfo, WeChat.class);
        String msg =  weChatService.wechatlogin(user);
        String url="https://api.weixin.qq.com/sns/jscode2session";
        //?appid="+"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code"
        Map<String,String> map=new HashMap();
        map.put("appid","wxba447fb7b8387ff2");
        map.put("appsecret","e420d0eb7fdc85016d2a885a7ee3f162");
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        // 解析json
        JSONObject jsonObject = HttpUtil.GET(url, map);
        this.session_key = jsonObject.get("session_key")+"";
        this.openid = jsonObject.get("openid")+"";
        System.out.println(jsonObject.toJSONString());
      return "ok";
    }
    @GetMapping ("/Login")
    private String logintest() {
        System.out.println("访问");

        String url=  "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid="+appid+"&"  // 代表你公众号
                + "redirect_uri=http://localhost:8080/school/wechat/test&" // 处理Code的URL地址
                + "response_type=code&"
                + "scope=snsapi_login&" // 要拿到用户的基本微信（需要授权）还是只拿到用户的openid
                + "state=STATE&connect_redirect=1#wechat_redirect"; // state 参数用来验证 防止csrf（跨站请求伪造）攻击

        return url;
    }
    @GetMapping ("/test")
    @ResponseBody
    private WeChat test(String code) {
        System.out.println("code"+"  "+code);
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxec5fbae400e99092&secret=23bf7606a61183a31fbbd196c96fd747&code="+code+"&grant_type=authorization_code";
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        String access_token=(String) jsonObject.get("access_token");
        String opendid=(String) jsonObject.get("opendid");
        System.out.println("access_token"+"  "+jsonObject.get("access_token"));
        System.out.println("opendid"+"  "+jsonObject.get("openid"));
        String u=//"https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+opendid+"&lang=zh_CN";
        "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+opendid+"&lang=zh_CN";

        System.out.println(resultString.toString());

        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(u);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject1 = (JSONObject) JSONObject.parse(resultString);
        System.out.println(jsonObject1.toJSONString());
        WeChat weChat=new WeChat();
        weChat.setNickname((String) jsonObject1.get("nickname"));
        return  weChat;
    }
    @RequestMapping(value = "/wxuserinfo",method = RequestMethod.POST)
    private void login(@RequestBody String jsonData) {
        System.out.println("infoData" + jsonData);
        JSONObject dataObj = JSONObject.parseObject(jsonData);
        String  code =  (String) dataObj.get("code");
        String  encryptedData =  (String) dataObj.get("encryptedData");
        String  iv =  (String) dataObj.get("iv");
        String sessionkey = getSessionKey(code);
        JSONObject userInfo = this.getUserInfo(encryptedData, sessionkey, iv);
        System.out.println(userInfo.toJSONString());

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
}
