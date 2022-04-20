package com.huaqi.zhanxin.tools;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class GetInformationFromRequest {
    @Autowired
    private int userId;
    public GetInformationFromRequest(HttpServletRequest request){


        String token = request.getHeader("token");
        DecodedJWT verify = JwtConfig.verify(token);
        userId=Integer.valueOf(verify.getClaim("userId").asString());

    }
    public int getUserId(){
        return userId;
    }
}
