package com.walis.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.walis.entity.Member;
import com.walis.repository.MemberRepository;
import com.walis.repository.RoleRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void save(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		member.setRoles(new HashSet<>(roleRepo.findAll()));
		memRepo.save(member);
	}

	@Override
	public Member findByMemId(String memId) {
		return memRepo.findByMemId(memId);
	}

}
