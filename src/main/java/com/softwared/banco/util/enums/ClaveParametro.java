package com.softwared.banco.util.enums;

public enum ClaveParametro {
	VALOR_MIN_TR("VALOR_MINIMO_TRANSACCION");
	
	private String clave;

	private ClaveParametro(String clave) {
		this.clave = clave;
	}

	public String getClaveParametro() {
		return this.clave;
	}

}
