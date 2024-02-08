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
	private String userName;
	private String passwordTitular;
	private BigDecimal saldoInicial;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_Authority", joinColumns = { @JoinColumn(name = "id_cuenta") }, inverseJoinColumns = {
			@JoinColumn(name = " id_authority") })
	private Set<Authority> authorities;

	public Cuenta() {
		super();
		this.authorities = new HashSet<Authority>();
	}

	public Cuenta(Long numeroCuenta, String nombreTitular, String passwordTitular, BigDecimal saldoInicial) {
		super();
		this.numberAccount = numeroCuenta;
		this.userName = nombreTitular;
		this.passwordTitular = passwordTitular;
		this.saldoInicial = saldoInicial;
	}

	public Cuenta(Long numeroCuenta, String nombreTitular, String passwordTitular, BigDecimal saldoInicial,
			Set<Authority> authorities) {
		super();
		this.numberAccount = numeroCuenta;
		this.userName = nombreTitular;
		this.passwordTitular = passwordTitular;
		this.saldoInicial = saldoInicial;
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public String getPassword() {
		return this.passwordTitular;
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

	public void setIdAccount(Integer idCuenta) {
		this.idAccount = idCuenta;
	}

	public Long getNumeroCuenta() {
		return numberAccount;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numberAccount = numeroCuenta;
	}

	public String getNombreTitular() {
		return userName;
	}

	public void setNombreTitular(String nombreTitular) {
		this.userName = nombreTitular;
	}

	public String getPasswordTitular() {
		return passwordTitular;
	}

	public void setPasswordTitular(String passwordTitular) {
		this.passwordTitular = passwordTitular;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

}
