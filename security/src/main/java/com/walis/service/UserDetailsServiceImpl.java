package com.walis.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walis.entity.Member;
import com.walis.entity.Role;
import com.walis.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository memRepo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member mem = memRepo.findByMemId(username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : mem.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        	
        return new org.springframework.security.core.userdetails.User(mem.getMemId(), mem.getPassword(), grantedAuthorities);
	}

}
