package it.polito.tdp.teatrino.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

public class Giorno {

	String nome;
	LocalTime oraI;
	LocalTime oraF;
	long disponibilita;
	List<String> corsi = new ArrayList<String>();
	
	public Giorno(String nome, LocalTime oraI, LocalTime oraF) {
		super();
		this.nome = nome;
		this.oraI = oraI;
		this.oraF = oraF;
		disponibilita = Duration.between(oraI, oraF).toMinutes();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalTime getOraI() {
		return oraI;
	}
	public void setOraI(TemporalAmount oraI) {
		this.oraI = this.oraI.plus(oraI);
	}
	public LocalTime getOraF() {
		return oraF;
	}
	public void setOraF(LocalTime oraF) {
		this.oraF = oraF;
	}
	
	public void disponibilita(long amount) {
		
		this.disponibilita = this.disponibilita - amount;
		
	}
	public long getDisponibilita() {
		return disponibilita;
	}
	@Override
	public String toString() {
		return "Giorno [nome=" + nome + ", disponibilita=" + disponibilita + "oraInizio: "+oraI+ "]";
	}
	public void addCorsi(String corsi) {
		if(!this.corsi.contains(corsi))
			this.corsi.add(corsi);
	}

}
