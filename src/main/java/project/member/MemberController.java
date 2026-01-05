package project.member;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MemberResponse getMemberById(@RequestParam Long id) {
        return memberService.getMemberById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MemberResponse CreateMember(@RequestBody @Valid MemberRequest request){

        return memberService.createMember(request);
    }
}
