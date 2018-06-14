package com.simpleradis.service;

import com.simpleradis.model.Member;
import com.simpleradis.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("memberService")
public class SimpleredisServiceImpl implements SimpleradisService {

    @Autowired
    MemberRepository memberRepository;

    public Member memberSave(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> memberFindById(String id) {
        return memberRepository.findById(id);
    }

    public Iterable<?> memberFindAll() {
        return memberRepository.findAll();
    }

    public void memberDeleteById(String id) {
        memberRepository.deleteById(id);
    }

    public Boolean memberExistsById(String id) {
        return memberRepository.existsById(id);
    }
}
