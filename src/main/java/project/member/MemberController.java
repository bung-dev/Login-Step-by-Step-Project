package project.member;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MemberResponse getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MemberResponse createMember(@RequestBody @Valid MemberRequest request){

        return memberService.createMember(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<MemberResponse> getAllMembers(){
        return memberService.getAllMembers();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMembers(@PathVariable Long id){
        memberService.deleteMember(id);
    }
}
