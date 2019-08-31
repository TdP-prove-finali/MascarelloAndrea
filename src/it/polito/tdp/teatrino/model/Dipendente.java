package it.polito.tdp.teatrino.model;

public class Dipendente {

	String id;
	String nome;
	int compenso;
	String giorno;
	
	public Dipendente(String id,String nome, int compenso, String giorno) {
		super();
		this.id = id;
		this.nome = nome;
		this.compenso = compenso;
		this.giorno = giorno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCompenso() {
		return compenso;
	}

	public void setCompenso(int compenso) {
		this.compenso = compenso;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	@Override
	public String toString() {
		return nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
