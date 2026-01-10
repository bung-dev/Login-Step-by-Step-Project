package project.member.domain.dto;

import jakarta.validation.constraints.NotBlank;
import project.member.domain.Member;

public record MemberRequest(@NotBlank(message = "아이디는 필수입니다")
                            String loginId,
                            @NotBlank(message = "비밀번호는 필수입니다")
                            String password,
                            @NotBlank(message = "닉네임은 필수입니다")
                            String name) {

    public static MemberRequest from(Member member){ //Entity -> DTO
        return new MemberRequest(member.getLoginId(),
                member.getPassword(),
                member.getName());
    }
}
