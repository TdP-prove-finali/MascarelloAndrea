package it.polito.tdp.teatrino.model;

public class Cliente {

	private int id;
	private String nome;
	private String idCorso;
	private String contatti;
	private double pagato;
	private double dovuto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(String idCorso) {
		this.idCorso = idCorso;
	}
	public String getContatti() {
		return contatti;
	}
	public void setContatti(String contatti) {
		this.contatti = contatti;
	}
	public double getPagato() {
		return pagato;
	}
	public void setPagato(double d) {
		this.pagato = d;
	}
	public double getDovuto() {
		return dovuto;
	}
	public void setDovuto(double d) {
		this.dovuto = d;
	}
	
	public Cliente(int id, String nome, String idCorso, String contatti, double pagato, double dovuto) {
		super();
		this.id = id;
		this.nome = nome;
		this.idCorso = idCorso;
		this.contatti = contatti;
		this.pagato = pagato;
		this.dovuto = dovuto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
}
