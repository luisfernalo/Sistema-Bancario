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
	private Integer idParameter;
	
	@Column
	private String keyParameter;
	@Column
	@Enumerated(EnumType.STRING)
	private TipoParametro typeParameter;
	@Column
	private String valueParameter;

	
	
	public Parametro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parametro(String keyParameter, TipoParametro typeParameter, String valueParameter) {
		super();
		this.keyParameter = keyParameter;
		this.typeParameter = typeParameter;
		this.valueParameter = valueParameter;
	}

	public Integer getIdParameter() {
		return idParameter;
	}

	public void setIdParameter(Integer idParameter) {
		this.idParameter = idParameter;
	}

	public String getKeyParameter() {
		return keyParameter;
	}

	public void setKeyParameter(String keyParameter) {
		this.keyParameter = keyParameter;
	}

	public TipoParametro getTypeParameter() {
		return typeParameter;
	}

	public void setTypeParameter(TipoParametro typeParameter) {
		this.typeParameter = typeParameter;
	}

	public Integer getIdParametro() {
		return idParameter;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParameter = idParametro;
	}

	public String getClave() {
		return keyParameter;
	}

	public void setClave(String clave) {
		this.keyParameter = clave;
	}

	public TipoParametro getTipoParametro() {
		return typeParameter;
	}

	public void setTipoParametro(TipoParametro tipoParametro) {
		this.typeParameter = tipoParametro;
	}

	public String getValueParameter() {
		return valueParameter;
	}

	public void setValueParameter(String valor) {
		this.valueParameter = valor;
	}

}
