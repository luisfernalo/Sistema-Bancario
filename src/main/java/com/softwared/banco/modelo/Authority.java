package com.softwared.banco.modelo;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "authority")
public class Authority implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_authority")
	private Long idAuthority;

	
	private String name;

	public Authority() {
		super();
	}

	public Authority(String name) {
		this.name = name;
	}

	public Authority(Long idAuthority, String name) {
		this.idAuthority = idAuthority;
		this.name = name;
	}

	@Override
	public String getAuthority() {
		//cambios
		return this.name;
	}

	public Long getIdAuthority() {
		return idAuthority;
	}

	public void setIdAuthority(Long idAuthority) {
		this.idAuthority = idAuthority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
