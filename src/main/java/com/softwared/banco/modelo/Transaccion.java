package com.softwared.banco.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.softwared.banco.util.enums.EstadoTransacion;
import com.softwared.banco.util.enums.TipoTransacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "Transaccion")
public class Transaccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTransaction;

	@Column
	private Long numberAccount;

	@Column
	@Enumerated(EnumType.STRING)
	private TipoTransacion typeTransaction;
	@Column
	private BigDecimal valueTransaction;
	private LocalDateTime dateTransaction;
	@Column
	@Enumerated(EnumType.STRING)
	private EstadoTransacion stateTransaction;

	public Transaccion() {
		super();
	}

	public Transaccion(Long numberAccount, BigDecimal valueTransaction, TipoTransacion typeTransaction, LocalDateTime localDateTime,
			EstadoTransacion stateTransaction) {
		super();

		this.numberAccount = numberAccount;
		this.typeTransaction = typeTransaction;
		this.valueTransaction = valueTransaction;
		this.dateTransaction = localDateTime;
		this.stateTransaction = stateTransaction;
	}

	public Integer getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Long getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(Long numberAccount) {
		this.numberAccount = numberAccount;
	}

	public TipoTransacion getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(TipoTransacion typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public BigDecimal getValueTransaction() {
		return valueTransaction;
	}

	public void setValueTransaction(BigDecimal valueTransaction) {
		this.valueTransaction = valueTransaction;
	}

	public LocalDateTime getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(LocalDateTime dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public EstadoTransacion getStateTransaction() {
		return stateTransaction;
	}

	public void setStateTransaction(EstadoTransacion stateTransaction) {
		this.stateTransaction = stateTransaction;
	}

}
