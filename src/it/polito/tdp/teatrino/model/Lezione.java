package it.polito.tdp.teatrino.model;

import java.time.LocalDate;

public class Lezione {

	private String id;
	private String idCorso;
	private LocalDate data;
	int presenze;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdCorso() {
		return idCorso;
	}
	public void setIdCorso(String idCorso) {
		this.idCorso = idCorso;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getPresenze() {
		return presenze;
	}
	public void setPresenze(int presenze) {
		this.presenze = presenze;
	}
	
	public Lezione(String id, String idCorso, LocalDate data, int presenze) {
		super();
		this.id = id;
		this.idCorso = idCorso;
		this.data = data;
		this.presenze = presenze;
	}
	
	@Override
	public String toString() {
		return id + ", " + idCorso + ", " + data + ", " + presenze ;
	}
	
}
