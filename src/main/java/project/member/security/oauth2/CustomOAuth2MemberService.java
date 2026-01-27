package project.member.security.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import project.member.domain.AuthProvider;
import project.member.domain.Member;
import project.member.repository.MemberRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        AuthProvider provider = AuthProvider.valueOf(
                userRequest.getClientRegistration().getRegistrationId().toUpperCase()
        );

        OAuth2Response oAuth2Response = switch (provider) {
            case GOOGLE -> new GoogleResponse(oAuth2User.getAttributes());
            default -> throw new OAuth2AuthenticationException("소셜 로그인이 아닙니다");
        };

        String name = oAuth2Response.getName();
        String providerId = oAuth2Response.getProviderId();
        String loginId = oAuth2Response.getEmail();
        String password = passwordEncoder.encode(UUID.randomUUID().toString().substring(0, 8));

        Member member = memberRepository.findByLoginIdAndDeletedAtIsNull(loginId).orElse(null);
        if(member == null) {
             member = Member.OAuth2Create(loginId, name, password, provider, providerId);

             log.info("member_saved={}", member);
            memberRepository.save(member);
        }

        return new CustomOauth2MemberDetails(member,oAuth2User.getAttributes());
    }
}
