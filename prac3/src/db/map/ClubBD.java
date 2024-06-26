package db.map;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Club;

public class ClubBD {
	/**
	 * Obtiene de la base de datos el club con nombre igual al par�metro nombreClub, 
	 *    creando un objeto del tipo model.Club
	 * @param nombreClub
	 * @return
	 */
	public static Club getById(String nombreClub) {
		String sqlQuery = "SELECT * FROM club WHERE nombre = '" + nombreClub + "';";
		PreparedStatement st = null;
		Club ClubBD = null;
		try {
		st = db.AdministradorConexion.prepareStatement(sqlQuery);
		st.execute();
		ResultSet rs = st.getResultSet();
		if (rs.next())
			ClubBD = new Club(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), 
			rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
		return ClubBD;
		} catch (SQLException e) {
			try{
				if(st != null && !st.isClosed()){
					st.close();
				}
			} catch (SQLException e2){
				e2.printStackTrace();
			}		
			return null;
		}
	}	
}
