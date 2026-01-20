package project.member.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record TokenResponse(@NotEmpty String token) {
    public static TokenResponse from(String token) {
        return new TokenResponse("Bearer " + token);
    }
}
