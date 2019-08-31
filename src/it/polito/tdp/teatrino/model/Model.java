package it.polito.tdp.teatrino.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.teatrino.model.Evento;
import it.polito.tdp.teatrino.db.TeatrinoDAO;

public class Model {

	private Map<Integer, Cliente> clientiMap;
	private Map<String, Corso> corsiMap;
	private Map<String, Dipendente> dipendentiMap;
	public List<ClienteHidden> clientiHidden;
	private List<Evento> eventi;
	private Map<String, Lezione> lezioniMap;
	private TeatrinoDAO dao;
	private ArrayList<Corso> best;
	private List<Giorno> settimana = new ArrayList<Giorno>();
	private double parcheggioMercato = 2.50;
	private double parcheggio = 1.00;
	private double enel = 0.2;
	private double affitto = 400;
	
	public Model() {
		
		dao = new TeatrinoDAO();
		clientiMap = new HashMap<Integer, Cliente>();
		corsiMap = new HashMap<String, Corso>();
		clientiHidden = new ArrayList<ClienteHidden>();
		eventi = new ArrayList<Evento>(dao.getEventi());
		dipendentiMap = new HashMap<String, Dipendente>();
		lezioniMap = new HashMap<String, Lezione>();
		dao.getLezioni(lezioniMap);
		dao.getDipendenti(dipendentiMap);
		dao.getCorsi(corsiMap);
	}
	
	public Map<Integer, Cliente> getClientiMap() {
		return clientiMap;
	}
	
	public Map<String, Corso> getCorsiMap() {
		return corsiMap;
	}

	public List<Evento> getEventi(LocalDate localDate) {
		
		List<Evento> result = new ArrayList<Evento>();
		
		for(Evento evento : eventi) {
			
			if(evento.getData().equals(localDate))
				result.add(evento);
			
		}
		
		return result;
	}
	
	public List<Evento> getEventi() {
		
		eventi = dao.getEventi();
	
		return eventi;
	}

	public Map<String, Dipendente> getDipendentiMap() {
		return dipendentiMap;
	}

	public Map<String, Lezione> getLezioniMap() {
		return lezioniMap;
	}

	public List<Corso> trovaSequenza(LocalTime lunediI, LocalTime lunediF, LocalTime martediI, LocalTime martediF,
			LocalTime mercolediI, LocalTime mercolediF, LocalTime giovediI, LocalTime giovediF, LocalTime venerdiI,
			LocalTime venerdiF, LocalTime sabatoI, LocalTime sabatoF, LocalTime domenicaI, LocalTime domenicaF) {
		// TODO Auto-generated method stub
		best = new ArrayList<Corso>();
		List<Corso> partial = new ArrayList<Corso>();
		
		settimana.add(new Giorno("Lunedì", lunediI, lunediF));
		settimana.add(new Giorno("Martedì", martediI, martediF));
		settimana.add(new Giorno("Mercoledì", mercolediI, mercolediF));
		settimana.add(new Giorno("Giovedì", giovediI, giovediF));
		settimana.add(new Giorno("Venerdì", venerdiI, venerdiF));
		settimana.add(new Giorno("Sabato", sabatoI, sabatoF));
		settimana.add(new Giorno("Domenica", domenicaI, domenicaF));
		
		recursion(partial,0,settimana);
		
		return best;
	}

	private void recursion(List<Corso> partial,int L ,List<Giorno> sett) {
		// TODO Auto-generated method stub
		System.out.println("PARTITA RICORSIONE");
		
		if (L == corsiMap.values().size() && getProfitto(partial) > getProfitto(best)) {
			
			System.out.println("NEW BEST");
			best = new ArrayList<Corso>(partial);
			System.out.println(getProfitto(best));
			System.out.println("ESCO LIVELLO MAX");
			return;
		}
		
		if (L == corsiMap.values().size()) {
			
			System.out.println("ESCO");
			return;
		}
		
		for (Corso corso : corsiMap.values()) {
			
			if(!partial.contains(corso)) {
			Giorno gg = cercaGiorno(corso,sett);
			if(gg != null) {
			Corso c = new Corso(corso.getId(), corso.getNome(), corso.getCosto(), corso.getDip1(), 
					corso.getDip2(), gg.nome, gg.oraI, gg.oraI.plus(Duration.between(corso.getOraInizio(), corso.getOraFine())), 
					corso.getTipo());
			partial.add(c);
			c.setGg(gg);
			gg.addCorsi(c.getId());
			System.out.println("AGGIUNGO : "+new Corso(corso.getId(), corso.getNome(), corso.getCosto(), corso.getDip1(), 
					corso.getDip2(), gg.nome, gg.oraI, gg.oraI.plus(Duration.between(corso.getOraInizio(), corso.getOraFine())), 
					corso.getTipo()).toStringRicorsiva());
			if(gg.getOraI().plus(Duration.between(corso.getOraInizio(), corso.getOraFine())).compareTo(gg.oraF) <= 0 ) {
				gg.setOraI(Duration.between(corso.getOraInizio(), corso.getOraFine()));
			}
			gg.disponibilita(Duration.between(corso.getOraInizio(), corso.getOraFine()).toMinutes());
			for (Corso corso2 : partial) {
				System.out.println(corso2.toStringRicorsiva());
			}
			
			recursion(partial,L+1,sett);
			System.out.println("RICORRO AL LIVELLO : "+(L+1));
			System.out.println("RIMUOVO : "+partial.get(partial.size()-1));
			partial.get(partial.size()-1).getGg().setOraI(Duration.between(partial.get(partial.size()-1).getOraFine(), partial.get(partial.size()-1).getOraInizio()));
			partial.remove(partial.size()-1);
			}
			}
		}
	}

	private Giorno cercaGiorno(Corso corso, List<Giorno> sett) {
		// TODO Auto-generated method stub
		for(Giorno giorno : sett) {

			if(!giorno.corsi.contains(corso.getId())) {
			if(giorno.getOraI().plus(Duration.between(corso.getOraInizio(), corso.getOraFine())).compareTo(giorno.oraF) <= 0  && 
					!dipendentiMap.get(corso.getDip1()).getGiorno().equals(giorno.nome) && giorno.getOraI().compareTo(LocalTime.of(7, 00)) >= 0 &&
					giorno.getOraI().compareTo(LocalTime.of(23, 00)) <= 0) {
				if(dipendentiMap.get(corso.getDip2()) != null) {
					if(!dipendentiMap.get(corso.getDip2()).getGiorno().equals(giorno.nome)) {
						return giorno;
				}
				} else {
					return giorno;
				}
			}
			
		}
		}
		return null;
	}

	public double getProfitto(List<Corso> partial) {
		// TODO Auto-generated method stub
		double entrate = 0.0;
		double costi = 0.0;
		costi += affitto/4;
		
		for(Corso corso : partial) {
			
			entrate += corso.getCosto() * dao.mediaPresenze(corso);
			if(!corso.getDip1().equals("Giovanna Candi"))
				costi += dipendentiMap.get(corso.getDip1()).getCompenso()*(Duration.between(corso.getOraInizio(), corso.getOraFine()).toHours());
			if(dipendentiMap.get(corso.getDip2()) != null) {
				costi += dipendentiMap.get(corso.getDip2()).getCompenso()*(Duration.between(corso.getOraInizio(), corso.getOraFine()).toHours());
			}
			if(corso.getGiorno().equals("Martedì") || corso.getGiorno().equals("Giovedì") || corso.getGiorno().equals("Sabato")) {
					costi += parcheggioMercato * Duration.between(corso.getOraInizio(), corso.getOraFine()).toHours();
			} else {
				
					costi += parcheggio  * Duration.between(corso.getOraInizio(), corso.getOraFine()).toHours();
			}
			if(corso.getOraInizio().compareTo(LocalTime.of(17, 00)) >= 0 && (LocalDate.now().getMonth().equals(Month.DECEMBER) || 
					LocalDate.now().getMonth().equals(Month.JANUARY)|| LocalDate.now().getMonth().equals(Month.FEBRUARY))) {
				
				costi += enel * Duration.between(corso.getOraInizio(), corso.getOraFine()).toHours();
				
			} else if(corso.getOraInizio().compareTo(LocalTime.of(19, 00)) >= 0 && (LocalDate.now().getMonth().equals(Month.MARCH) || 
					LocalDate.now().getMonth().equals(Month.APRIL)|| LocalDate.now().getMonth().equals(Month.MAY))) {
				
				costi += enel * Duration.between(corso.getOraInizio(), corso.getOraFine()).toHours();
				
			} else if(corso.getOraInizio().compareTo(LocalTime.of(21, 30)) >= 0 && (LocalDate.now().getMonth().equals(Month.JUNE) || 
					LocalDate.now().getMonth().equals(Month.JULY)|| LocalDate.now().getMonth().equals(Month.AUGUST))){
				
				costi += enel * Duration.between(corso.getOraInizio(), corso.getOraFine()).toHours();
				
			} else if(corso.getOraInizio().compareTo(LocalTime.of(19, 00)) >= 0){
				
				costi += enel * Duration.between(corso.getOraInizio(), corso.getOraFine()).toHours();
			}
		}
		
		System.out.println(entrate-costi);
		return entrate - costi;
	}

}
