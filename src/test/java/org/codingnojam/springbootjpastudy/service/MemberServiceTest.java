package org.codingnojam.springbootjpastudy.service;

import org.assertj.core.api.Assertions;
import org.codingnojam.springbootjpastudy.domain.Member;
import org.codingnojam.springbootjpastudy.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void memberJoinTest(){

        Member member = new Member();
        member.setName("codingNOjam");

        Long joinId = memberService.join(member);
        Member joinMember = memberService.findMember(joinId);

        em.flush();
        Assertions.assertThat(member).isEqualTo(joinMember);
    }

    @Test
    public void duplicateJoinTest(){

        Member member1 = new Member();
        member1.setName("Han");

        Member member2 = new Member();
        member2.setName("Han");

        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }


        Assertions.fail("중복가입 발생");



    }

}