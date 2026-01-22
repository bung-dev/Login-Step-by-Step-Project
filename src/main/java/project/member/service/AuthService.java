package project.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import project.member.domain.Role;
import project.member.domain.dto.LoginRequest;
import project.member.domain.dto.TokenResponse;
import project.member.security.jwt.JWTUtil;
import project.member.web.exception.ErrorCode;

import static project.member.CommonToken.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public TokenResponse login(LoginRequest request) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.loginId(), request.password()));
            String loginId = auth.getName();

            String role = auth.getAuthorities().stream()
                    .findFirst()
                    .orElseThrow(ErrorCode.FORBIDDEN::exception)
                    .getAuthority();

            String accessToken = jwtUtil.createToken(loginId, role, JWT_ACCESS_TOKEN_NAME , JWT_ACCESS_TOKEN_EXPIRED_TIME);

            return TokenResponse.from(accessToken, JWT_ACCESS_TOKEN_NAME);

        } catch (BadCredentialsException e) {
            throw ErrorCode.INVALID_CREDENTIALS.exception();
        }
    }
}
