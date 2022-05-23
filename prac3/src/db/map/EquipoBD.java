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
		try {
		PreparedStatement st = db.AdministradorConexion.prepareStatement(sqlQuery);
		st.execute();
		ResultSet rs = st.getResultSet();
		Equipo EquipoBD = new Equipo(rs.getString(1), rs.getString(2), rs.getInt(3), 
			rs.getString(4),rs.getInt(5),rs.getInt(6));
		st.close();
		rs.close();
		return EquipoBD;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
