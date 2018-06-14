package com.simpleradis.service;

import com.simpleradis.model.Member;

import java.util.Optional;

public interface SimpleradisService {

    Member memberSave(Member member);

    Optional<Member> memberFindById(String id);

    Iterable<?> memberFindAll();

    void memberDeleteById(String id);

    Boolean memberExistsById(String id);



}
