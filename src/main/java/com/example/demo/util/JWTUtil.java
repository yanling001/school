package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.pojo.User;
import io.jsonwebtoken.*;
import org.codehaus.xfire.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    //token 密钥
    private static final String TOKEN_SECRET = "27f56a1ca0a347618ff39c7fdf9ab684";
    //15分钟超时时间
    private static final long OUT_TIME = 150 * 60 * 1000;

    private static Logger log = LoggerFactory.getLogger(JWTUtil.class);


    /** 加密
     * @return
     */
    public static String sign(User user) {
        String openid=null;
        if (user.getOpenidAnr()==null){
            openid=user.getOpenidWeb();
        }else  openid=user.getOpenidAnr();
        try {
            Date expiration_time = new Date(System.currentTimeMillis() + OUT_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> headerMap = new HashMap<>(2);
            headerMap.put("type", "JWT");
            headerMap.put("alg", "HS256");
            return JWT.create().withHeader(headerMap).withClaim("nickname", user.getNickname()).withClaim("openid",openid).withClaim("role",user.getRole()).withExpiresAt(expiration_time).sign(algorithm);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /** 解密
     * @param token
     * @return
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT decodedJWT=null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            decodedJWT = verifier.verify(token);
            log.info("超时时间:"+decodedJWT.getExpiresAt());
            log.info("载体信息:"+decodedJWT.getClaim("userId").asString());
            log.info("算法:"+decodedJWT.getAlgorithm());
        }catch (Exception e){
            //解码异常则抛出异常
            log.error(e.getMessage());
            return null;
        }
        return decodedJWT.getClaims();
    }
}
























































