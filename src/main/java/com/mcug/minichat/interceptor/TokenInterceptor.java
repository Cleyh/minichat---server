package com.mcug.minichat.interceptor;

import com.mcug.minichat.utils.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.UUID;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        Map<String, Object> result = TokenService.isValidToken(token);
        if (token != null && result != null) {
//            String userId = TokenService.getIdFromToken(token);
            String userId = result.get("user_id").toString();
            request.setAttribute("userId", userId);
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
