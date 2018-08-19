package com.walis.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "member")
public class Member {

	
	@Column(name = "mem_id")
	private String memId;
	
	@Column(name = "password")
	private String password;

	@Column(name = "password_confirm")
	private String passwordConfirm;
	
	@Column(name = "mem_name")
	private String memName;
	
	@Column(name = "birth")
	private String birth;
	
	@Column(name = "gender")
	private Integer gender;

	private Set<Role> roles;
	
	@Id
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@ManyToMany
	@JoinTable(name = "member_roles", joinColumns = @JoinColumn(name = "member_memId"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Member [memId=" + memId + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", memName=" + memName + ", birth=" + birth + ", gender=" + gender + ", roles=" + roles + "]";
	}

}
