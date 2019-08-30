package it.polito.tdp.teatrino.model;

public class ClienteHidden {

	private String nome;
	private String corso;
	private String contatti;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCorso() {
		return corso;
	}
	public void setCorso(String corso) {
		this.corso = corso;
	}
	public String getContatti() {
		return contatti;
	}
	public void setContatti(String contatti) {
		this.contatti = contatti;
	}
	
	public ClienteHidden(String nome, String idCorso, String contatti) {
		this.nome = nome;
		this.corso = idCorso;
		this.contatti = contatti;
	}
	
	@Override
	public String toString() {
		return nome + ", " + corso + ", " + contatti ;
	}
	
}

