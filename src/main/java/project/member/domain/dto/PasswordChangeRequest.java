package project.member.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record PasswordChangeRequest (@NotEmpty String currentPassword,
                                     @NotEmpty String newPassword
){
}
