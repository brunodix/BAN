package br.udesc.core;

public enum Papeis {
	
	ADMINISTRADOR(1), PADRAO(2);
	
	private int value;
	
	public int getValue() {
		return value;
	}
	
	private Papeis(int value) {
		this.value = value;
	}

}
