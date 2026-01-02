package project.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepository();


    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    void save(){
        //given
        MemberRequest memberRequest = new MemberRequest("test1","test!","test1");
        //when
        Member member = memberRepository.save(memberRequest);
        //then
        Member findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isEqualTo(member);
    }
}
