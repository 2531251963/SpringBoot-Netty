package org.easyarch.springboot_netty.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description https://blog.csdn.net/Shenpibaipao/article/details/88431445#javajwt_118
 * @Author Liyihe
 * @Date 2019/09/16 下午9:21
 * @Version 1.0
 */
@Configuration
public class JwtUtil {
    private  Algorithm alg = Algorithm.HMAC256("lyh15510828597");
    private final String issuer="EasyArch Lyh";
    private final String audience="EasyArch";
    public String createJWT(String userid){
        Date currentTime = new Date();
        return JWT.create()
                .withIssuer(issuer) // 发行者
                .withSubject(userid) // 用户身份标识
                .withAudience(audience) // 用户单位
                .withIssuedAt(currentTime) // 签发时间
                .withExpiresAt(new Date(currentTime.getTime()+ 604800000)) // 一天有效期
                .sign(alg);
    }
    public String getUserIdFromJWT(String token){
        JWTVerifier verifier = JWT.require(alg)
                .withIssuer(issuer)
                .withAudience(audience)
                .build();
        try{
            verifier.verify(token);
            DecodedJWT originToken = JWT.decode(token);
            return originToken.getSubject();
        } catch (JWTVerificationException e) {
            System.out.println("验证错误");
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println( new JwtUtil(). createJWT("222"));
    }
}
