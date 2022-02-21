package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista {

	private static final String ERROR = "ERROR";
	private static final String NOMBRE_VALIDO = "NOMBRE_VALIDO";
	private static final String CORREO_VALIDO = "CORREO_VALIDO";
	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		if (controlador == null)
			throw new IllegalArgumentException("Error el controlador no puede ser nulo");
		this.controlador = controlador;
	}

	public void comenzar() {
		Consola.mostrarCabecera("Programa para la reserva de aula");
		int ordinalOpcion;
		do {
			;
			Consola.mostrarMenu();
			ordinalOpcion = (int) Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {
		System.out.println("Adios");

	}

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		try {
			Aula aula = Consola.leerAula();
			controlador.insertarAula(aula);
			System.out.println("Aula insertado correctamente" + ", " + NOMBRE_VALIDO);
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula = Consola.leerAula();
			controlador.borrarAula(aula);
			System.out.println("Aula borrado correctamente.");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}

	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = Consola.leerAula();
			aula = controlador.buscarAula(aula);
			if (aula != null) {
				System.out.println("El aula buscado es: " + aula);
			} else {
				System.out.println("No existe ningún aula con ese nombre");
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarAulas() {
		Consola.mostrarCabecera("Listar aulas");
		try {
			List<String> aulas = controlador.representarAulas();

			for (String aula : aulas) {
				System.out.println(aula);
			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.insertarProfesor(profesor);
			System.out.println("Profesor insertado correctamente" + NOMBRE_VALIDO);
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.borrarProfesor(profesor);
			System.out.println("Profesor borrado correctamente.");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}

	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
		try {
			profesor = Consola.leerProfesor();
			profesor = controlador.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El profesor buscado es: " + profesor);
			} else {
				System.out.println("No existe ningún profesor con ese nombre");
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarProfesores() {
		try {
			Consola.mostrarCabecera("Listar profesores");
			List<String> profesores = controlador.representarProfesores();
			if (profesores.size() != 0) {
				for (String profesor : profesores) {
					System.out.println(profesor);
				}
			} else {
				System.out.println("No hay profesores que listar.");
			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar Reserva");
		try {
			Profesor profesor = Consola.leerProfesor();
			Reserva reserva = leerReserva(profesor);
			controlador.realizarReserva(reserva);
			System.out.println("Reserva realizada correctamente");
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void anularReserva() {
		Consola.mostrarCabecera("Anular Reserva");
		try {
			Profesor profesor = Consola.leerProfesor();
			Reserva reserva = leerReserva(profesor);
			controlador.anularReserva(reserva);
			System.out.println("Reserva anulada correctamente.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}

	public void listarReservas() {
		Consola.mostrarCabecera("Listar Reservas");
		try {
			List<String> reservas = controlador.representarRerservas();
			if (reservas.size() != 0) {
				for (String reserva : reservas) {
					System.out.println(reserva);
				}
			} else {
				System.out.println("No hay reservas que listar.");
			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listar Reservas Profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			List<Reserva> reservasProfesor = controlador.getReservasProfesor(profesor);
			if (reservasProfesor.size() != 0) {
				for (Reserva reservaProfesor : reservasProfesor) {
					System.out.println(reservaProfesor);
				}
			} else {
				System.out.println(profesor.getNombre() + " no tiene ninguna reserva a su nombre.");
			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar Reservas Permanencia");
		try {
			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			List<Reserva> reservasPermanencia = controlador.getReservasPermanencia(permanencia);
			if (reservasPermanencia.size() != 0) {
				for (Reserva reservaPermanencia : reservasPermanencia) {
					System.out.println(reservaPermanencia);
				}
			} else {
				System.out.println(permanencia.getDia() + ": no hay ninguna reserva.");
			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar Disponibilidad");
		Aula aula = Consola.leerAula();
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		if (controlador.consultarDisponibilidad(aula, permanencia)) {
			System.out.println(aula + " se encuentra disponible el dia " + permanencia.getDia() + ".");
		} else {
			System.out.println(aula + " no se encuentra disponible el dia " + permanencia.getDia() + ".");
		}

	}

	private Reserva leerReserva(Profesor profesor) {
		Consola.mostrarCabecera("Leer Reserva");
		Aula aula = Consola.leerAula();
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		return new Reserva(profesor, aula, permanencia);
	}

	public void listarReservasAula() {

		Consola.mostrarCabecera("Listar Reservas Aula");
		try {
			Aula aula = Consola.leerAula();
			List<Reserva> reservasAulas = controlador.getReservasAula(aula);
			if (reservasAulas.size() != 0) {
				for (Reserva reservaAula : reservasAulas) {
					System.out.println(reservaAula);
				}
			} else {
				System.out.println(aula.getNombre() + " no tiene ninguna reserva a su nombre.");
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}
}
