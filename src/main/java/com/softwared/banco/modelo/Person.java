package com.softwared.banco.modelo;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements UserDetails {

	private static final long serialVersionUID = -7193472952545946856L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
	private Integer idPerson;

	@Column
	private String holderName;
	private String holderLastName;
	private String holderEmail;
	private String holderPassword;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Person_Authority", joinColumns = { @JoinColumn(name = "id_person") }, inverseJoinColumns = {
			@JoinColumn(name = " id_authority") })
	private Set<Authority> authorities;

	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	private Account account;

	public Person() {
		super();
	}

	public Person(String holderName, String holderLastName, String holderEmail, String holderPassword,
			Set<Authority> authorities) {
		super();
		this.holderName = holderName;
		this.holderLastName = holderLastName;
		this.holderEmail = holderEmail;
		this.holderPassword = holderPassword;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return getHolderPassword();
	}

	@Override
	public String getUsername() {
		return getHolderEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderLastName() {
		return holderLastName;
	}

	public void setHolderLastName(String holderLastName) {
		this.holderLastName = holderLastName;
	}

	public String getHolderEmail() {
		return holderEmail;
	}

	public void setHolderEmail(String holderEmail) {
		this.holderEmail = holderEmail;
	}

	public String getHolderPassword() {
		return holderPassword;
	}

	public void setHolderpassword(String holderpassword) {
		this.holderPassword = holderpassword;
	}
}
