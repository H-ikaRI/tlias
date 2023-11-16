package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testJWT(){
        Map<String,Object> Claims = new HashMap<>();
        Claims.put("id",1);
        Claims.put("name","tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")//签名算法
                .setClaims(Claims)//自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))//jwt的有效期为一小时
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void testParseJwt(){
        Claims i = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY5OTE4Mzc5OX0.Nfh5ZLi1OcjEQpOUOy8lT5a09tiOMPzYM5-p-7ZXk-g")
                .getBody();
        System.out.println(i);
    }

}
