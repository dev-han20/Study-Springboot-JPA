package org.codingnojam.springbootjpastudy.service;

import lombok.RequiredArgsConstructor;
import org.codingnojam.springbootjpastudy.domain.Member;
import org.codingnojam.springbootjpastudy.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    @Transactional
    public Long join(Member member) {
        validateMemberNameDuplicateMember(member.getName());
        memberRepository.save(member);
        return member.getId();
    }

    private void validateMemberNameDuplicateMember(String name) {
        List<Member> memberList = memberRepository.findByName(name);
        if (!memberList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이름의 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> fineMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        return memberRepository.findOne(id);
    }


}
