package project.member.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import project.member.domain.Member;

public record MemberResponse(@NotEmpty String loginId,
                             @NotEmpty String name) {

    public static MemberResponse from(Member member){ //Entity -> DTO
        return new MemberResponse(member.getLoginId(),
                member.getName());
    }
}
