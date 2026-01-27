package project.member.security.oauth2;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import project.member.domain.dto.TokenResponse;
import project.member.service.IssueTokenService;
import project.member.web.util.CookieUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Log4j2
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final IssueTokenService issueTokenService;
    private final CookieUtil cookieUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("OAUTH SUCCESS auth={}, sessionId={}",
                authentication.getName(),
                request.getSession().getId());

        CustomOauth2MemberDetails principal = (CustomOauth2MemberDetails) authentication.getPrincipal();
        String loginId = principal.getUsername();

        TokenResponse token = issueTokenService.issueToken(loginId);

        response.addHeader(HttpHeaders.SET_COOKIE, cookieUtil.createCookie(token.refreshToken()));

        String encoded = URLEncoder.encode(token.accessToken(), StandardCharsets.UTF_8);
        response.sendRedirect("/oauth2/success?accessToken=" + encoded);

    }
}
