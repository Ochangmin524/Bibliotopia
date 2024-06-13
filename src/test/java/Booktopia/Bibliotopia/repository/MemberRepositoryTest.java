package Booktopia.Bibliotopia.repository;

import Booktopia.Bibliotopia.domain.Book;
import Booktopia.Bibliotopia.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 멤버_생성_저장_조회_테스트() {
        Member member = new Member();
        member.setName("test");
        Member savedMember = memberRepository.save(member);
        Member findMember =
                memberRepository.findById(savedMember.getId()).get();
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }
    @Test
    public void 멤버_수정_테스트() {
        //given

        Member member = new Member();
        member.setName("testMemberBeforeEdit");
        Member savedMember = memberRepository.save(member);
        //when
        Member findMember = memberRepository.findById(member.getId()).get();
        findMember.setName("testMemberAfterEdit");

        //then
        assertThat(memberRepository.findById(member.getId()).get().getName()).isEqualTo("testMemberAfterEdit");


    }

    @Test
    public void 멤버_삭제_테스트() {
        //given

        Member member = new Member();
        member.setName("testMemberBeforeEdit");
        Member savedMember = memberRepository.save(member);
        assertThat(memberRepository.count()).isEqualTo(1);

        //when
        memberRepository.delete(member);
        //then
        assertThat(memberRepository.count()).isEqualTo(0);
    }
}