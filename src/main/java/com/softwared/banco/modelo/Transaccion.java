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
	private Integer idTransaccion;

	@Column
	private Long numeroCuenta;

	@Column
	@Enumerated(EnumType.STRING)
	private TipoTransacion tipoTransacion;
	@Column
	private BigDecimal valor;
	private LocalDateTime fecha;
	@Column
	@Enumerated(EnumType.STRING)
	private EstadoTransacion estadoTransacion;

	public Transaccion() {
		super();
	}

	public Transaccion(Long numeroCuenta, BigDecimal valor, TipoTransacion tipoTransacion, LocalDateTime localDateTime,
			EstadoTransacion estadoTransacion) {
		super();

		this.numeroCuenta = numeroCuenta;
		this.tipoTransacion = tipoTransacion;
		this.valor = valor;
		this.fecha = localDateTime;
		this.estadoTransacion = estadoTransacion;
	}

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public TipoTransacion getTipoTransacion() {
		return tipoTransacion;
	}

	public void setTipoTransacion(TipoTransacion tipoTransacion) {
		this.tipoTransacion = tipoTransacion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public EstadoTransacion getEstadoTransacion() {
		return estadoTransacion;
	}

	public void setEstadoTransacion(EstadoTransacion estadoTransacion) {
		this.estadoTransacion = estadoTransacion;
	}

}
