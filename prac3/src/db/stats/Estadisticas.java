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
		String sqlQuery = "SELECT * FROM jugador WHERE nif IN (SELECT nif_jugador FROM jugador_milita_equipo WHERE fecha_inicio > CAST('"+ anio +"-12-31' AS date) OR fecha_fin < CAST('"+anio+"-01-01' AS date));";
		PreparedStatement st = null;
		try {
			st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			List<Jugador> listaJugador = new ArrayList<Jugador>();
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
	 * M�todo que devuelve el n�mero de equipos del mismo club m�ximo en los que alg�n jugador ha estado
	 * @return
	 */
	public static int getNumeroMaximoEquiposDelMismoClubHaEstadoUnJugador(){
		String sqlQuery = "SELECT MAX(COUNT(DISTINCT(e.id))) FROM equipo e, jugador_milita_equipo j WHERE e.licencia = j.licencia_equipo;"; // NO FUNCIONA Error Code: 1111. Invalid use of group function
		PreparedStatement st = null;
		try {
			st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
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
	 * M�todo que debe devolver el listado de los jugadores que han estado en el mayor n�mero de equipos
	 * del mismo club 
	 * @return
	 */
	public static List<Jugador> getJugadoresMasEquiposMismoClub(){
		//TODO arreglar query
		String sqlQuery = "ELECT MAX(Total) FROM (SELECT COUNT(*) AS Total FROM (club INNER JOIN equipo ON club.nombre = equipo.nombre_club INNER JOIN jugador_milita_equipo ON equipo.licencia = jugador_milita_equipo.licencia_equipo) GROUP BY jugador_milita_equipo.nif_jugador) AS Results;";
		PreparedStatement st = null;
		try {
			st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			List<Jugador> listaJugador = new ArrayList<Jugador>();
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
	 * M�todo que debe devolver el listado de los jugadores que han estado en el equipo recibido como
	 * par�metro el a�o (anio)
	 * @param equipo
	 * @param anio
	 * @return
	 */
	public static List<Jugador> getJugadoresEquipoAnio(Equipo equipo, int anio) {
		String sqlQuery = "SELECT * FROM jugador j WHERE j.nif IN (SELECT nif_jugador FROM jugador_milita_equipo WHERE licencia_equipo = " + equipo.getLicencia() + " AND fecha_inicio > CAST('"+ anio +"-12-31' AS date) OR fecha_fin < CAST('"+anio+"-01-01' AS date));";
		PreparedStatement st = null;
		try {
			st = db.AdministradorConexion.prepareStatement(sqlQuery);
			st.execute();
			ResultSet rs = st.getResultSet();
			List<Jugador> listaJugador = new ArrayList<Jugador>();
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
}
