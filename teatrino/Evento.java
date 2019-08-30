package it.polito.tdp.teatrino;

import java.sql.Date;
import java.time.LocalDate;

public class Evento {

	private String nome;
	private LocalDate data;
	private double entrata;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public double getEntrata() {
		return entrata;
	}
	public void setEntrata(double entrata) {
		this.entrata = entrata;
	}
	public Evento(String nome, LocalDate data, double entrata) {
		super();
		this.nome = nome;
		this.data = data;
		this.entrata = entrata;
	}
	
	
}
