package ui;

import java.sql.SQLException;
import java.util.List;

import db.map.CategoriaCompeticionBD;
import db.map.CategoriaEdadBD;
import model.CategoriaEdad;


public class Main {
	// Prueba la creaci�n, carga y borrado de Categor�as de Edad
	public static void pruebaModificacionCategorias() {
		List<CategoriaEdad> categorias = CategoriaEdadBD.getAll();
		CategoriaEdad ce = new CategoriaEdad("Prueba", "Prueba de creaci�n", 99, 110);
		categorias.add(ce);
		categorias.get(0).setEdadMinima(-2);
		CategoriaEdadBD.saveAll(categorias);
		System.out.println(categorias);
		
		CategoriaEdadBD.deleteCategoria(ce);
		categorias.remove(categorias.size()-1);
		categorias.get(0).setEdadMinima(4);
		CategoriaEdadBD.saveAll(categorias);
		System.out.println(categorias);
		
		
	}
	
	// Pruebas b�sicas
	// NO MODIFICAR LAS CABECERAS DE NINGUN METODO
	public static void main(String[] args) throws SQLException {
		
		System.out.println("Pruebas de la clase CategoriaCompeticiónBD");
		CategoriaCompeticionBD.getById(1);
		CategoriaCompeticionBD.getById(2);
		

		// pruebaModificacionCategorias();
		
		// List<Jugador> jugadores = JugadorBD.getAll();
		// System.out.println(jugadores.size());
		// Equipo e = EquipoBD.getById("104517534");
		// System.out.println(e);
		
		// List<Jugador> resJugAnioEq = Estadisticas.getJugadoresEquipoAnio(e, 2021);
		// System.out.println(resJugAnioEq);
		
		// List<Jugador> resNoJugAnio = Estadisticas.getJugadoresNoHanEstadoEnEquipo(2021);
		// System.out.println(resNoJugAnio.size());
		
		// System.out.println(Estadisticas.getNumeroMaximoEquiposDelMismoClubHaEstadoUnJugador());
		
		// List<Jugador> resJugMasAnios = Estadisticas.getJugadoresMasEquiposMismoClub();
		// System.out.println(resJugMasAnios.size());
		
	
	}
}
