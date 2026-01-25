package project.member.web.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import project.member.web.exception.ErrorCode;

import java.util.Arrays;

import static project.member.CommonToken.JWT_COOKIE_REFRESH_TOKEN_EXPIRED_TIME;
import static project.member.CommonToken.JWT_REFRESH_TOKEN_NAME;

@Component
public class CookieUtil {
    public String createCookie(String value) {
        return ResponseCookie.from(JWT_REFRESH_TOKEN_NAME, value)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(JWT_COOKIE_REFRESH_TOKEN_EXPIRED_TIME)
                .build()
                .toString();
    }

    public String readCookie(HttpServletRequest request){
        if (request.getCookies() == null) {
            throw ErrorCode.REFRESH_TOKEN_MISSING.exception();
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> JWT_REFRESH_TOKEN_NAME.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
}
