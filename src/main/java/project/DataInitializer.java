package project;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import project.member.domain.dto.MemberRequest;
import project.member.service.InMemoryMemberService;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final InMemoryMemberService inMemoryMemberService;

    @Override
    public void run(@NonNull ApplicationArguments args) throws Exception {
        inMemoryMemberService.createMember(new MemberRequest("test1", "test!", "test1"));
        inMemoryMemberService.createMember(new MemberRequest("test2", "test!", "test2"));
    }
}
