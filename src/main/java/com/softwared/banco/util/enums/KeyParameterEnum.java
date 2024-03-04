package com.softwared.banco.util.enums;

public enum KeyParameterEnum {
	VALOR_MIN_TR("VALOR_MINIMO_TRANSACCION");
	
	private String clave;

	private KeyParameterEnum(String clave) {
		this.clave = clave;
	}

	public String getkeyParameterEnum() {
		return this.clave;
	}

}
