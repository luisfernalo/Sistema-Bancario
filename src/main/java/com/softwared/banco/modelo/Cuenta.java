package com.softwared.banco.modelo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuenta")
public class Cuenta implements UserDetails {

	private static final long serialVersionUID = -1963678291864906103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta")
	private Integer idAccount;

	@Column
	private Long numberAccount;
	private String holderName;
	private String holderEmail;
	private String holderpassword;
	private BigDecimal initialBalance;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_Authority", joinColumns = { @JoinColumn(name = "id_account") }, inverseJoinColumns = {
			@JoinColumn(name = " id_authority") })
	private Set<Authority> authorities;

	public Cuenta() {
		super();
		this.authorities = new HashSet<Authority>();
	}

	public Cuenta(Long numberAccount, String holderName, String holderEmail, String holderpassword,
			BigDecimal initialBalance) {
		super();
		this.numberAccount = numberAccount;
		this.holderName = holderName;
		this.holderEmail = holderEmail;
		this.holderpassword = holderpassword;
		this.initialBalance = initialBalance;
	}

	public Cuenta(Long numberAccount, String holderName, String holderEmail, String holderpassword,
			BigDecimal initialBalance, Set<Authority> authorities) {
		super();
		this.numberAccount = numberAccount;
		this.holderName = holderName;
		this.holderEmail = holderEmail;
		this.holderpassword = holderpassword;
		this.initialBalance = initialBalance;
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return this.holderEmail;
	}

	@Override
	public String getPassword() {
		return this.holderpassword;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// posibles errores que salgan
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public Long getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(Long numberAccount) {
		this.numberAccount = numberAccount;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderEmail() {
		return holderEmail;
	}

	public void setHolderEmail(String holderEmail) {
		this.holderEmail = holderEmail;
	}

	public String getHolderpassword() {
		return holderpassword;
	}

	public void setHolderpassword(String holderpassword) {
		this.holderpassword = holderpassword;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

}
