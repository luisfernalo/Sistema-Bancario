package com.softwared.banco.modelo;




import com.softwared.banco.util.enums.TipoParametro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "parametros")
public class Parametro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idParametro;
	
	@Column
	private String clave;
	@Column
	@Enumerated(EnumType.STRING)
	private TipoParametro tipoParametro;
	@Column
	private String valor;

	
	
	public Parametro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parametro(String clave, TipoParametro tipoParametro, String valor) {
		super();
		this.clave = clave;
		this.tipoParametro = tipoParametro;
		this.valor = valor;
	}

	public Integer getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public TipoParametro getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametro tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
