package db.map;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.CategoriaCompeticion;

public class CategoriaCompeticionBD {
	/**
	 * 
	 * @param categoriaCompeticion
	 * @return Obtiene de la base de datos la categor�a de competici�n con id igual al par�metro categoriaCompeticion, 
	 *    creando un objeto del tipo model.CategoriaCompeticion
	 * @throws SQLException
	 */
	public static CategoriaCompeticion getById(int categoriaCompeticion) {
		//abrir conexión a base de datos usando jdbc
		//obtener una categoría de competición de la base de datos
		//cerrar conexión a base de datos
		//devolver categoría de competición
		String sqlQuery = "SELECT * FROM categoria_competicion WHERE id = " + categoriaCompeticion + ";";
		try {
		PreparedStatement st = db.AdministradorConexion.prepareStatement(sqlQuery);
		st.execute();
		ResultSet rs = st.getResultSet();
		CategoriaCompeticion categoriaCompeticionBD = new CategoriaCompeticion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		st.close();
		rs.close();
		return categoriaCompeticionBD;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
