package project.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepository();


    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    void saveAndFindById(){
        //given
        Member member1 = Member.builder()
                .loginId("test1")
                .password("test!")
                .name("test1")
                .build();
        //when
        Member member = memberRepository.save(member1);
        //then
        Member findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll(){
        //given
        Member member1 = Member.builder()
                .loginId("test1")
                .password("test!")
                .name("test1")
                .build();
        Member member2 = Member.builder()
                .loginId("test2")
                .password("test!")
                .name("test2")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> all = memberRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getName()).isEqualTo(member1.getName());
        assertThat(all.get(1).getName()).isEqualTo(member2.getName());
    }

    @Test
    void deleteByMemberId(){
        //given
        Member member1 = Member.builder()
                .loginId("test1")
                .password("test!")
                .name("test1")
                .build();

        Member member = memberRepository.save(member1);
        //when
        System.out.println(member.toString());
        memberRepository.deleteMemberById(member.getId());

        //then
        assertThat(memberRepository.findById(member.getId())).isNull();
    }
}
