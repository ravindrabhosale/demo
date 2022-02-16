package com.team.persistence.repository;

import com.team.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member, String> {
    List<Member> findByRole(String role);

    List<Member> findByLocation(String location);

    List<Member> findByAge(int age);
}