package project.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.member.domain.Member;
import project.member.domain.dto.MemberRequest;
import project.member.domain.dto.MemberResponse;
import project.member.repository.MemberRepository;
import project.member.web.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberResponse join(MemberRequest req){
        if (memberRepository.existsByLoginId(req.loginId())) {
            throw ErrorCode.DUPLICATE_LOGIN_ID.exception();
        }

        String encodePassword = passwordEncoder.encode(req.password());
        Member member = Member.create(req.loginId(), req.name(), encodePassword);
        Member saved = memberRepository.save(member);

        return  MemberResponse.from(saved);
    }

    public MemberResponse get(String loginId){
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(ErrorCode.MEMBER_NOT_FOUND::exception);


        return MemberResponse.from(member);
    }

    public List<MemberResponse> list(){
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    public MemberResponse update(MemberRequest req){
        Member member = memberRepository.findByLoginId(req.loginId())
                .orElseThrow(ErrorCode.MEMBER_NOT_FOUND::exception);

        member.changeName(req.name());
        member.changePassword(req.password());
        Member updated = memberRepository.save(member);

        return MemberResponse.from(updated);
    }

    public void delete(Long id){
        memberRepository.deleteById(id);
    }
}
