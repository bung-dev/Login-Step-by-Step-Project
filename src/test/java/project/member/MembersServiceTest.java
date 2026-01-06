package project.member;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MembersServiceTest {

    MemberRepository memberRepository = new MemberRepository();

    MemberService memberService = new MemberService(memberRepository);

    @Test
    void createMemberAndGetMemberById(){
        // given
        MemberRequest memberRequest = new MemberRequest("test1", "test!", "test1");
        // when
        MemberResponse memberResponse = memberService.createMember(memberRequest);
        MemberResponse getMemberByIdResponse = memberService.getMemberById(1L);
        // then
        assertThat(memberResponse.name()).isEqualTo(getMemberByIdResponse.name());

    }

    @Test
    void getAllMembers(){
        //given
        MemberRequest memberRequest1 = new MemberRequest("test1", "test!", "test1");
        MemberRequest memberRequest2 = new MemberRequest("test2", "test!", "test2");

        MemberResponse member1 = memberService.createMember(memberRequest1);
        MemberResponse member2 = memberService.createMember(memberRequest2);

        //when
        List<MemberResponse> allMembers = memberService.getAllMembers();

        //then
        assertThat(allMembers.size()).isEqualTo(2);
        assertThat(allMembers.get(0).name()).isEqualTo(member1.name());
        assertThat(allMembers.get(1).name()).isEqualTo(member2.name());
    }

    @Test
    void deleteByMember(){
        //given
        MemberRequest memberRequest = new MemberRequest("test1", "test!", "test1");

        memberService.createMember(memberRequest);
        //when
        memberService.deleteMember(1L);
        //then
        assertThat(memberService.getMemberById(1L)).isNull();
    }
}
