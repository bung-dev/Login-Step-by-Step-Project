package project.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import project.member.domain.Member;
import project.member.repository.InMemoryMemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InMemoryMemberRepositoryTest {

    InMemoryMemberRepository inMemoryMemberRepository = new InMemoryMemberRepository();


    @AfterEach
    void afterEach(){
        inMemoryMemberRepository.clear();
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
        Member member = inMemoryMemberRepository.save(member1);
        //then
        Member findMember = inMemoryMemberRepository.findById(member.getId());
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

        inMemoryMemberRepository.save(member1);
        inMemoryMemberRepository.save(member2);
        //when
        List<Member> all = inMemoryMemberRepository.findAll();

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

        Member member = inMemoryMemberRepository.save(member1);
        //when
        System.out.println(member.toString());
        inMemoryMemberRepository.deleteMemberById(member.getId());

        //then
        assertThat(inMemoryMemberRepository.findById(member.getId())).isNull();
    }
}
