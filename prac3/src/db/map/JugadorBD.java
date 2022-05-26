package db.map;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Jugador;

public class JugadorBD {
	/**
	 * Obtiene de la base de datos todos los jugador, 
	 *    devolviendo una lista de objetos del tipo model.Jugador
	 * @return
	 */
	public static List<Jugador> getAll() {
		String sqlQuery = "SELECT * FROM jugador;";
		try {
			PreparedStatement st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			List<Jugador> listaJugador = new java.util.ArrayList<Jugador>();
			while (rs.next()) {
				listaJugador.add(new Jugador(rs.getString(1), rs.getString(2), 
				rs.getString(3), rs.getString(4),rs.getDate(5).toLocalDate()));
			}
			st.close();
			rs.close();
			return listaJugador;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Obtiene de la base de datos el jugador con nif igual al par�metro nifJugador, 
	 *    creando un objeto del tipo model.Jugador
	 * @param nifJugador
	 * @return
	 */
	public static Jugador getById(String nifJugador) {
		String sqlQuery = "SELECT * FROM jugador WHERE nif = '" + nifJugador + "';";
		try {
		PreparedStatement st = db.AdministradorConexion.prepareStatement(sqlQuery);
		st.execute();
		ResultSet rs = st.getResultSet();
		Jugador JugadorBD = new Jugador(rs.getString(1), rs.getString(2), 
			rs.getString(3), rs.getString(4),rs.getDate(5).toLocalDate());
		st.close();
		rs.close();
		return JugadorBD;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
