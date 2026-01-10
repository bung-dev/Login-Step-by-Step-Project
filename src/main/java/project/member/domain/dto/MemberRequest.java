package project.member.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import project.member.domain.Member;

public record MemberRequest(@NotBlank(message = "아이디는 필수입니다")
                            @Size(min = 8, max = 16, message = "아이디는 8~16자여야 합니다")
                            String loginId,
                            @NotBlank(message = "비밀번호는 필수입니다")
                            @Pattern(
                                    regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                                    message = "비밀번호는 영문, 숫자, 특수기호를 포함한 8자 이상이어야 합니다"
                            )
                            String password,
                            @NotBlank(message = "닉네임은 필수입니다")
                            @Size(min = 2, message = "닉네임은 2자 이상이어야 합니다")
                            String name) {

    public static MemberRequest from(Member member){ //Entity -> DTO
        return new MemberRequest(member.getLoginId(),
                member.getPassword(),
                member.getName());
    }
}
