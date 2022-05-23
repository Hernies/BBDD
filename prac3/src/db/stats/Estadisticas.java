package db.stats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Equipo;
import model.Jugador;

public class Estadisticas {
	/**
	 * M�todo que debe devolver el listado de los jugadores que no han estado en ning�n equipo 
	 * en el a�o recibido como par�metro
	 * @param anio
	 * @return
	 */
	public static List<Jugador> getJugadoresNoHanEstadoEnEquipo(int anio){
		String sqlQuery = "SELECT * FROM jugador WHERE nif NOT IN (SELECT nif FROM jugador_equipo WHERE anio = " + anio + ");";
		try {
			PreparedStatement st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			List<Jugador> jugadores = new ArrayList<Jugador>();
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate());
				jugadores.add(jugador);
			}
			return jugadores;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * M�todo que devuelve el n�mero de equipos del mismo club m�ximo en los que alg�n jugador ha estado
	 * @return
	 */
	public static int getNumeroMaximoEquiposDelMismoClubHaEstadoUnJugador(){
		String sqlQuery = "SELECT MAX(COUNT(DISTINCT(e.id))) FROM equipo e, jugador_equipo j WHERE e.id = j.id_equipo;";
		try {
			PreparedStatement st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			rs.next();
			return rs.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * M�todo que debe devolver el listado de los jugadores que han estado en el mayor n�mero de equipos
	 * del mismo club 
	 * @return
	 */
	public static List<Jugador> getJugadoresMasEquiposMismoClub(){
		String sqlQuery = "SELECT * FROM jugador j WHERE j.nif IN (MAX(SELECT nif FROM jugador_equipo WHERE id_equipo = e.id));";
		try {
			PreparedStatement st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			List<Jugador> jugadores = new ArrayList<Jugador>();
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate());
				jugadores.add(jugador);
			}
			return jugadores;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * M�todo que debe devolver el listado de los jugadores que han estado en el equipo recibido como
	 * par�metro el a�o (anio)
	 * @param equipo
	 * @param anio
	 * @return
	 */
	public static List<Jugador> getJugadoresEquipoAnio(Equipo equipo, int anio) {
		String sqlQuery = "SELECT * FROM jugador j WHERE j.nif IN (SELECT nif FROM jugador_equipo WHERE licencia_equipo = " + equipo.getLicencia() + " AND anio = " + anio + ");";
		try {
			PreparedStatement st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			List<Jugador> jugadores = new ArrayList<Jugador>();
			while (rs.next()) {
				Jugador jugador = new Jugador(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate());
				jugadores.add(jugador);
			}
			return jugadores;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
