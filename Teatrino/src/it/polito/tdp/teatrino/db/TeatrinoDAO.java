package it.polito.tdp.teatrino.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import it.polito.tdp.teatrino.model.Evento;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.ClienteHidden;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Dipendente;
import it.polito.tdp.teatrino.model.Lezione;

public class TeatrinoDAO {

public List<Cliente> getClienti() {
		
		final String sql = "SELECT * FROM clienti";
		List<Cliente> clienti = new ArrayList<Cliente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				clienti.add(new Cliente(rs.getInt("ID"), rs.getString("Nominativo"), rs.getString("IDcorso"), rs.getString("contatti"), 
						rs.getDouble("pagato"), rs.getDouble("dovuto"))); 
						
			}
			
			conn.close();
			return clienti;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

public List<Corso> getCorsi(Map<String, Corso> corsiMap) {
	// TODO Auto-generated method stub
	final String sql = "SELECT * FROM corsi";
	List<Corso> corsi = new ArrayList<Corso>();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			corsi.add(new Corso(rs.getString("ID"), rs.getString("nome"), rs.getInt("costo"), rs.getString("dipendente1"), 
					rs.getString("dipendente2"), rs.getString("giorno"),rs.getTime("oraInizio").toLocalTime().minusHours(1),rs.getTime("oraFine").toLocalTime().minusHours(1),rs.getString("tipo"))); 
			if(!corsiMap.containsKey(rs.getString("ID")))
				corsiMap.put(rs.getString("ID"), new Corso(rs.getString("ID"), rs.getString("nome"), rs.getInt("costo"), rs.getString("dipendente1"), 
					rs.getString("dipendente2"), rs.getString("giorno"),rs.getTime("oraInizio").toLocalTime().minusHours(1),rs.getTime("oraFine").toLocalTime().minusHours(1),rs.getString("tipo")));
		}
		
		conn.close();
		return corsi;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<ClienteHidden> cercaCliente(String string,Map<String, Corso> map) {

	// TODO Auto-generated method stub
		final String sql = "SELECT c.Nominativo AS n, c.IDcorso AS id, c.contatti AS cont FROM clienti c WHERE c.Nominativo LIKE ?";
		List<ClienteHidden> cl = new ArrayList<ClienteHidden>();
		
		try {
			String val = "%"+string+"%";
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, val);
			System.out.println(val);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				cl.add(new ClienteHidden(rs.getString("n"),map.get(rs.getString("id")).getNome(),rs.getString("cont"))); 
						
			}
			
			conn.close();
			return cl;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
}

public Cliente getCliente(String nome, Map<Integer, Cliente> map) {
		// TODO Auto-generated method stub
	final String sql = "SELECT ID FROM clienti WHERE Nominativo = ?";
	Cliente client = null;
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, nome);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			
			client = map.get(rs.getInt("ID"));
			
		}
		
		conn.close();
		return client;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	
}

public void aggiunta(Cliente cliente) {
	// TODO Auto-generated method stub
	final String sql = "INSERT INTO clienti VALUES (?,?,?,?,0,0);";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, cliente.getId());
		st.setString(2, cliente.getNome());
		st.setString(3, cliente.getIdCorso());
		st.setString(4, cliente.getContatti());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}


public void iscrivi(Cliente cliente, String idCorso) {
	// TODO Auto-generated method stub
	final String sql = "INSERT INTO iscrizioni VALUES (?,?);";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, idCorso);
		st.setInt(2, cliente.getId());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void setPagato(double insert, Cliente cliente) {
	// TODO Auto-generated method stub
final String sql = "UPDATE clienti SET pagato = ? WHERE ID = ?";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		double tot = cliente.getPagato() + insert;
		st.setDouble(1, tot);
		st.setInt(2, cliente.getId());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void setDovuto(double insert, Cliente cliente) {
	// TODO Auto-generated method stub
final String sql = "UPDATE clienti SET dovuto = ? WHERE ID = ?";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		double tot = cliente.getDovuto() + insert;
		st.setDouble(1, tot);
		st.setInt(2, cliente.getId());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void elimina(Cliente cliente) {
	// TODO Auto-generated method stub
final String sql = "DELETE FROM clienti WHERE ID = ?";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, cliente.getId());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public double calcolaRicavi(Corso corso) {
	// TODO Auto-generated method stub
	final String sql = "SELECT presenze as p FROM lezioni l WHERE l.idcorso = ?";
	double tot = 0.0;
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, corso.getId());
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			tot += rs.getInt("p")*corso.getCosto();
			System.out.println(tot);
			
		}
		
		conn.close();
		return tot;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<Month> getMonth() {
	final String sql = "SELECT DATA d FROM lezioni l ORDER BY MONTH(l.`data`) ";
	List<Month> mesi = new ArrayList<Month>();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			if(!mesi.contains(rs.getDate("d").toLocalDate().getMonth()))
				mesi.add(rs.getDate("d").toLocalDate().getMonth());
			
		}
		
		conn.close();
		return mesi;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public Double getRicavi(Month month, Map<String, Corso> map) {
	// TODO Auto-generated method stub
	final String sql = "SELECT presenze p,l.idcorso c FROM lezioni l WHERE MONTH(l.DATA) = ?";
	double tot = 0.0;
	Corso corso;
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, month.getValue());
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			corso = map.get(rs.getString("c"));
			tot += rs.getInt("p")*corso.getCosto();
			System.out.println(tot);
			
		}
		
		conn.close();
		return tot;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<Evento> getEventi() {
	// TODO Auto-generated method stub
	final String sql = "SELECT e.Nome n, e.`Data` d, e.Entrata e FROM eventi e";
	List<Evento> result = new ArrayList<Evento>();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			result.add(new Evento(rs.getString("n"), rs.getDate("d").toLocalDate(), rs.getDouble("e")));
			
		}
		
		conn.close();
		return result;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void aggiungiEvento(String text, LocalDate localDate, double entrata) {
	// TODO Auto-generated method stub
	final String sql = "INSERT INTO eventi VALUES (?,?,?);";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, text);
		st.setString(2, localDate.toString());
		st.setDouble(3, entrata);
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	
}

public List<Month> getMonthEventi(int i) {
	// TODO Auto-generated method stub
	final String sql = "SELECT DATA d FROM eventi e WHERE year(e.`Data`) = ?";
	List<Month> mesi = new ArrayList<Month>();
	System.out.println("DAO\n");
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, i);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			System.out.println(rs.getDate("d").toLocalDate().getMonth());
			if(!mesi.contains(rs.getDate("d").toLocalDate().getMonth()))
				mesi.add(rs.getDate("d").toLocalDate().getMonth());
			
		}
		
		conn.close();
		return mesi;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public Double getRicaviEventi(int year,Month month, List<Evento> eventi) {
	// TODO Auto-generated method stub
	final String sql = "SELECT e.Entrata e FROM eventi e WHERE year(e.Data) = ? AND month(e.`Data`) = ?";
	double tot = 0.0;
	System.out.println("DAO\n");
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, year);
		st.setInt(2, month.getValue());
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			tot += rs.getDouble("e");
			
		}
		
		conn.close();
		return tot;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public double calcolaRicavi(Month month, Corso corso) {
	// TODO Auto-generated method stub
	final String sql = "SELECT presenze as p FROM lezioni l WHERE l.idcorso = ? AND MONTH(l.Data) = ?";
	double tot = 0.0;
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, corso.getId());
		st.setInt(2, month.getValue());
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			tot += rs.getInt("p")*corso.getCosto();
			
		}
		
		conn.close();
		return tot;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<Dipendente> getDipendenti(Map<String, Dipendente> map) {
	// TODO Auto-generated method stub
	final String sql = "SELECT * FROM dipendenti";
	List<Dipendente> clienti = new ArrayList<Dipendente>();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente(rs.getString("id"), rs.getString("nominativo"), rs.getInt("compensoH"), rs.getString("giornoLibero"));
			clienti.add(dipendente); 
			map.put(dipendente.getId(), dipendente);
			System.out.println(dipendente.toString());
		}
		
		conn.close();
		return clienti;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void eliminaDipendente(Dipendente cliente) {
	// TODO Auto-generated method stub
	final String sql = "DELETE FROM dipendenti WHERE id = ?";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, cliente.getId());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void aggiuntaDipendente(Dipendente cliente) {
	// TODO Auto-generated method stub
	final String sql = "INSERT INTO dipendenti VALUES (?,?,?,?);";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, cliente.getId());
		st.setString(2, cliente.getNome());
		st.setDouble(3, cliente.getCompenso());
		st.setString(4, cliente.getGiorno());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<Dipendente> getDipendenti() {
	// TODO Auto-generated method stub
	final String sql = "SELECT * FROM dipendenti";
	List<Dipendente> clienti = new ArrayList<Dipendente>();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Dipendente dipendente = new Dipendente(rs.getString("id"), rs.getString("nominativo"), rs.getInt("compensoH"), rs.getString("giornoLibero"));
			clienti.add(dipendente); 
			System.out.println(dipendente.toString());
		}
		
		conn.close();
		return clienti;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public double calcolaPagamenti(Dipendente d) {
	// TODO Auto-generated method stub
	final String sql = "SELECT d.dipendente1 AS d1, d.dipendente2 AS d2 FROM corsi d, lezioni l WHERE d.ID = l.idcorso";
	double tot = 0.0;
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			if(rs.getString("d1").equals(d.getId()) || rs.getString("d2").equals(d.getId()))
				tot += d.getCompenso();
			System.out.println(tot);
		}
		
		conn.close();
		return tot;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void aggiungiCorso(Corso corso) {
	// TODO Auto-generated method stub
final String sql = "INSERT INTO corsi VALUES (?,?,?,?,?,?,?,?,?);";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, corso.getId());
		st.setString(2, corso.getNome());
		st.setDouble(3, corso.getCosto());
		st.setString(4, corso.getDip1());
		st.setString(5, corso.getDip2());
		st.setString(6, corso.getGiorno());
		st.setString(7, corso.getOraInizio().toString());
		st.setString(8, corso.getOraFine().toString());
		st.setString(9, corso.getTipo());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void eliminaCorso(Corso corso) {
	// TODO Auto-generated method stub
final String sql = "DELETE FROM corsi WHERE ID = ?";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, corso.getId());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<Lezione> getLezioni(Map<String, Lezione> lezioniMap) {
	// TODO Auto-generated method stub
	final String sql = "SELECT * FROM lezioni";
	List<Lezione> lezioni = new ArrayList<Lezione>();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			lezioni.add(new Lezione(rs.getString("idlez"), rs.getString("idcorso"), rs.getDate("data").toLocalDate(), rs.getInt("presenze"))); 
			Lezione lezione = new Lezione(rs.getString("idlez"), rs.getString("idcorso"), rs.getDate("data").toLocalDate(), rs.getInt("presenze"));
			lezioniMap.put(lezione.getId(), lezione);
		}
		
		conn.close();
		return lezioni;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<Cliente> getIscritti(Corso value) {
	// TODO Auto-generated method stub
	final String sql = "SELECT c.ID as id,c.Nominativo as n,c.IDcorso as c,c.contatti as con,c.pagato as pag,c.dovuto as dov FROM clienti c, iscrizioni co WHERE co.idcorso = ? AND c.ID = co.idcliente";
	List<Cliente> cliente = new ArrayList<Cliente>();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, value.getId());
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			cliente.add(new Cliente(rs.getInt("id"), rs.getString("n"), rs.getString("c"), rs.getString("con"), rs.getDouble("pag"), rs.getDouble("dov"))); 
		}
		
		conn.close();
		return cliente;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void eliminaIscrizione(Cliente cliente) {
	// TODO Auto-generated method stub
final String sql = "DELETE FROM iscrizioni WHERE idcliente = ?";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, cliente.getId());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public void aggiungiLezione(Lezione lezione) {
	// TODO Auto-generated method stub
	final String sql = "INSERT INTO lezioni VALUES (?,?,?,?);";
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, lezione.getId());
		st.setString(2, lezione.getIdCorso());
		st.setString(3, lezione.getData().toString());
		st.setInt(4, lezione.getPresenze());
		
		st.executeUpdate();
		conn.close();

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public double mediaPresenze(Corso c) {
	// TODO Auto-generated method stub
	final String sql = "SELECT AVG(presenze) as avg FROM lezioni l WHERE l.idcorso = ?";
	double result = 0.0;
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, c.getId());
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			
			result = rs.getDouble("avg");
			
		}
		
		conn.close();
		return result;

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}


}
