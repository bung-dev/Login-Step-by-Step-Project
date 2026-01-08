package project.member.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import project.member.domain.Member;

public record MemberRequest(@NotEmpty String loginId,
                            @NotEmpty String password,
                            @NotEmpty String name) {

    public static MemberRequest from(Member member){ //Entity -> DTO
        return new MemberRequest(member.getLoginId(),
                member.getPassword(),
                member.getName());
    }
}
