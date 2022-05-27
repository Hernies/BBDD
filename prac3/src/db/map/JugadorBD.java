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
		PreparedStatement st = null;
		try {
			st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			List<Jugador> listaJugador = new java.util.ArrayList<Jugador>();
			while (rs.next()) {
				listaJugador.add(new Jugador(rs.getString(1), rs.getString(2), 
				rs.getString(3), rs.getString(4),rs.getDate(5).toLocalDate()));
		
			}
			return listaJugador;
		}
		 catch (SQLException e) {
			try{
				if(st != null && !st.isClosed()){
					st.close();
				}
			} catch (SQLException e2){
				e2.printStackTrace();
			}
			throw new RuntimeException("Error al obtener los jugadores de la base de datos");
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
		PreparedStatement st = null;
		try {
			st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			Jugador jugador = null;
			if(rs.next())
			jugador = new Jugador(rs.getString(1), rs.getString(2), 
			rs.getString(3), rs.getString(4),rs.getDate(5).toLocalDate());
			return jugador;
		} catch (SQLException e) {
			try {
				if(st != null && !st.isClosed()){
					st.close();
				}
			} catch (SQLException e2){
				e2.printStackTrace();
			}
			throw new RuntimeException("Error al obtener el jugador de la base de datos");
		}
	}
}


