package com.softwared.banco.modelo;




import com.softwared.banco.util.enums.TypeParameterEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "parameter")
public class Parameter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idParameter;
	
	@Column
	private String keyParameter;
	@Column
	@Enumerated(EnumType.STRING)
	private TypeParameterEnum typeParameter;
	@Column
	private String valueParameter;

	
	
	public Parameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parameter(String keyParameter, TypeParameterEnum typeParameter, String valueParameter) {
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

	public TypeParameterEnum getTypeParameter() {
		return typeParameter;
	}

	public void setTypeParameter(TypeParameterEnum typeParameter) {
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

	public TypeParameterEnum getTipoParametro() {
		return typeParameter;
	}

	public void setTipoParametro(TypeParameterEnum tipoParametro) {
		this.typeParameter = tipoParametro;
	}

	public String getValueParameter() {
		return valueParameter;
	}

	public void setValueParameter(String valor) {
		this.valueParameter = valor;
	}

}
