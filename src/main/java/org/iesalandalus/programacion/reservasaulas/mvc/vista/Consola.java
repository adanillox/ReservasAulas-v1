package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println(" ____________________________________________________________");
		System.out.println("|               		   Menu 	                         ");
		System.out.println("| 0-Inserta aula.					                         ");
		System.out.println("| 1-Buscar aula.                                             ");
		System.out.println("| 2-Borrar aula.					    				     ");
		System.out.println("| 3-Listar aulas.			 		                         ");
		System.out.println("| 4-Insertar profesor.							             ");
		System.out.println("| 5-Borrar profesor.										 ");
		System.out.println("| 6-Buscar profesor					                         ");
		System.out.println("| 7-Lista profesores.                                 	     ");
		System.out.println("| 8-Insertar reserva.				    				     ");
		System.out.println("| 9-Borrar reserva.			  		                   		 ");
		System.out.println("| 10-Listar reservas.							             ");
		System.out.println("| 11-Listar reservas aula.			                         ");
		System.out.println("| 12-Listar reservas profesor.                               ");
		System.out.println("| 13-Listar reservas permanencia.						     ");
		System.out.println("| 14-Consultar disponibilidad.		                         ");
		System.out.println("|_15- Salir._________________________________________________");
	}

	public static void mostrarCabecera(String mostrar) {
		System.out.printf("%n%s%n", mostrar);
		String cadena = "%0" + mostrar.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}

	public static Aula leerAula() {
		return new Aula(leerNombreAula());
	}

	public static String leerNombreAula() {
		System.out.println("Introduce el nombre del aula");
		String nombreAula = Entrada.cadena();
		return nombreAula;
	}

	public static String leerNombreProfesor() {
		System.out.println("Introduce nombre del Profesor:");
		String nombreProfesor = Entrada.cadena();
		return nombreProfesor;
	}

	public static Profesor leerProfesor() {
		System.out.println("Introduce nombre del Profesor:");
		String nombreProfesor = Entrada.cadena();
		System.out.println("Introduce Correo del Profesor:");
		String correoProfesor = Entrada.cadena();
		System.out.println("Telefono del Profesor (Si no tiene pulsar enter)");
		String telefonoProfesor = Entrada.cadena();
		if (telefonoProfesor == null || telefonoProfesor.trim().isEmpty()) {
			return new Profesor(nombreProfesor, correoProfesor);
		} else {
		return new Profesor(nombreProfesor, correoProfesor, telefonoProfesor);	
		}
	}

	public static Tramo leerTramo() {
		System.out.println("Que tramo diario: \n1-Mañana(1) \n2-Tarde(2)");
		int indice = Entrada.entero();
		switch (indice) {
		case 1:
			return Tramo.MANANA;

		case 2:
			return Tramo.TARDE;

		default:
			return null;
		}
	}

	public static LocalDate leerDia() {
		System.out.println("Introduce la fecha del dia");
		System.out.println("DIA:");
		int dia = Entrada.entero();
		System.out.println("MES:");
		int mes = Entrada.entero();
		System.out.println("ANIO:");
		int anio = Entrada.entero();

		return LocalDate.of(dia, mes, anio);

	}
}
