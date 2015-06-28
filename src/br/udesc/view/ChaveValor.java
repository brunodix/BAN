package br.udesc.view;

public class ChaveValor {
	
	private int chave;
	
	private String valor;
	
	public ChaveValor(int chave, String valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}
	
	@Override
	public String toString() {
		return valor;
	}
	
	

}
