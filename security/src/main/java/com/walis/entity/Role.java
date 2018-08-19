package com.walis.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Column(name = "id")
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	private Set<Member> members;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRoleId")
	@SequenceGenerator(name = "seqRoleId", initialValue = 1, allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@ManyToMany(mappedBy = "roles")
	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}
