package it.polito.tdp.teatrino.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Corso {

	private String id;
	private String nome;
	private int costo;
	private String dip1;
	private String dip2;
	private String giorno;
	private LocalTime oraInizio;
	private LocalTime oraFine;
	private String tipo;
	private Giorno gg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public String getDip1() {
		return dip1;
	}
	public void setDip1(String dip1) {
		this.dip1 = dip1;
	}
	public String getDip2() {
		return dip2;
	}
	public void setDip2(String dip2) {
		this.dip2 = dip2;
	}
	public String getGiorno() {
		return giorno;
	}
	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}
	public LocalTime getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(LocalTime oraInizio) {
		this.oraInizio = oraInizio;
	}
	public LocalTime getOraFine() {
		return oraFine;
	}
	public void setOraFine(LocalTime oraFine) {
		this.oraFine = oraFine;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Corso other = (Corso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Corso(String id, String nome, int costo, String dip1, String dip2, String giorno, LocalTime oraInizio,
			LocalTime oraFine, String tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.costo = costo;
		this.dip1 = dip1;
		this.dip2 = dip2;
		this.giorno = giorno;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return nome;
	}
	
	public String toStringRicorsiva() {
		
		return nome + ", dipendenti : "+ dip1+"/"+dip2 + ", giorno : "+giorno+", dalle : "+oraInizio+" alle : "+oraFine;
	}
	public Giorno getGg() {
		return gg;
	}
	public void setGg(Giorno gg) {
		this.gg = gg;
	}
}
