package com.walis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walis.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	Member findByMemId(String memId);
}
