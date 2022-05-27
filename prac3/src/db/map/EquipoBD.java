package db.map;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Equipo;

public class EquipoBD {
	/**
	 * Obtiene de la base de datos el equipo con licencia igual al par�metro licenciaEquipo, 
	 *    creando un objeto del tipo model.Equipo
	 * @param licenciaEquipo
	 * @return
	 */
	public static Equipo getById(String licenciaEquipo) {
		String sqlQuery = "SELECT * FROM equipo WHERE licencia = '" + licenciaEquipo + "';";
		PreparedStatement st = null;
		Equipo EquipoBD = null;
		try {
		st = db.AdministradorConexion.prepareStatement(sqlQuery);
		st.execute();
		ResultSet rs = st.getResultSet();
		
		if (rs.next())
		EquipoBD = new Equipo(rs.getString(1), rs.getString(2), rs.getInt(3), 
			rs.getString(4),rs.getInt(5),rs.getInt(6));
		return EquipoBD;
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
