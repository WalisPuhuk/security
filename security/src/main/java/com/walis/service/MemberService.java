package com.walis.service;

import com.walis.entity.Member;

public interface MemberService {

	void save(Member member);
	
	Member findByMemId(String memId);
}
