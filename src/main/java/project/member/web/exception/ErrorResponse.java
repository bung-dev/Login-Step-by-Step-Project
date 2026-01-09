package project.member.web.exception;

public record ErrorResponse(
        String errorCode,
        String massage
) {
}
