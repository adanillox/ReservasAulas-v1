package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.junit.Test;

public class ReservasTest {

	private static final String NOMBRE_PROFESOR1 = "José Ramón";
	private static final String NOMBRE_PROFESOR2 = "Andrés";
	private static final String CORREO = "a@b.cc";
	private static final String NOMBRE_AULA1 = "Salón de actos 1";
	private static final String NOMBRE_AULA2 = "Salón de actos 2";
	private static final String NOMBRE_AULA3 = "Salón de actos 3";
	private static final LocalDate DIA1 = LocalDate.of(2022, 12, 1);
	private static final LocalDate DIA2 = LocalDate.of(2022, 12, 31);
	private static final LocalDate DIA3 = LocalDate.of(2022, 12, 22);
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	
	private final Profesor profesor1 = new Profesor(NOMBRE_PROFESOR1, CORREO);
	private final Profesor profesor2 = new Profesor(NOMBRE_PROFESOR2, CORREO);
	private final Aula aula1 = new Aula(NOMBRE_AULA1);
	private final Aula aula2 = new Aula(NOMBRE_AULA2);
	private final Aula aula3 = new Aula(NOMBRE_AULA3);
	private final Permanencia permanencia1 = new Permanencia(DIA1, Tramo.MANANA);
	private final Permanencia permanencia2 = new Permanencia(DIA2, Tramo.MANANA);
	private final Permanencia permanencia3 = new Permanencia(DIA3, Tramo.MANANA);
	private final Reserva reserva1 = new Reserva(profesor1, aula1, permanencia1);
	private final Reserva reserva2 = new Reserva(profesor1, aula1, permanencia2);
	private final Reserva reserva3 = new Reserva(profesor1, aula2, permanencia1);
	private final Reserva reserva4 = new Reserva(profesor1, aula2, permanencia2);
	private final Reserva reserva5 = new Reserva(profesor2, aula1, permanencia1);


	@Test
	public void constructorDefectoTest() {
		Reservas reservas = new Reservas();
		assertEquals(0, reservas.getNumReservas());
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		Reservas reservas1 = new Reservas();
		Reservas reservas2;
		reservas2 = new Reservas(reservas1);
		assertEquals(0, reservas2.getNumReservas());
		assertFalse(reservas1.getReservas() == reservas2.getReservas());
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		Reservas reservas = null;
		Reservas reservas1 = null;
		try {
			reservas1 = new Reservas(reservas);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se pueden copiar reservas nulas.", e.getMessage());
			assertNull(reservas1);
		}
	}
	
	@Test
	public void insertarUnaValidaTest() {
		Reservas reservas = new Reservas();
		try {
			reservas.insertar(reserva1);
			assertEquals(1, reservas.getNumReservas());
			assertEquals(reserva1, reservas.buscar(reserva1));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void insertarNulaTest() {
		Reservas reservas = new Reservas();
		try {
			reservas.insertar(null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede realizar una reserva nula.", e.getMessage());
			assertEquals(0, reservas.getNumReservas());
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void insertarRepetidaTest() {
		Reservas reservas = new Reservas();
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva1);
			fail(ERROR_EXCEPCION);
		} catch (OperationNotSupportedException e) {
			assertEquals("ERROR: La reserva ya existe.", e.getMessage());
			assertEquals(1, reservas.getNumReservas());
		}
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva5);
			fail(ERROR_EXCEPCION);
		} catch (OperationNotSupportedException e) {
			assertEquals("ERROR: La reserva ya existe.", e.getMessage());
			assertEquals(1, reservas.getNumReservas());
		}
	}
	
	@Test
	public void insertarTresValidoTest() {
		Reservas reservas = new Reservas();
		try {
			reservas.insertar(reserva1);
			assertEquals(1, reservas.getNumReservas());
			assertEquals(reserva1, reservas.buscar(reserva1));
			reservas.insertar(reserva2);
			assertEquals(2, reservas.getNumReservas());
			assertEquals(reserva2, reservas.buscar(reserva2));
			reservas.insertar(reserva3);
			assertEquals(3, reservas.getNumReservas());
			assertEquals(reserva3, reservas.buscar(reserva3));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void getReservasTest() {
		Reservas reservas = new Reservas();
		try {
			reservas.insertar(reserva1);
			List<Reserva> reservas1 = reservas.getReservas();
			assertFalse(reservas1 == reservas.getReservas());
			assertFalse(reservas1.get(0) == reservas.getReservas().get(0));
			assertEquals(reservas1.get(0), reservas.getReservas().get(0));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	private Reservas insertarCuatro() {
		Reservas reservas = new Reservas();
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva4);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
		return reservas;
	}
	
	@Test
	public void borrarPrincipioValidoTest() {
		Reservas reservas = insertarCuatro();
		try {
			reservas.borrar(reserva1);
			assertEquals(3, reservas.getNumReservas());
			assertNull(reservas.buscar(reserva1));
			List<Reserva> reservas1 = reservas.getReservas();
			assertEquals(reserva2, reservas1.get(0));
			assertEquals(reserva3, reservas1.get(1));
			assertEquals(reserva4, reservas1.get(2));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarMedioValidoTest() {
		Reservas reservas = insertarCuatro();
		try {
			reservas.borrar(reserva2);
			assertEquals(3, reservas.getNumReservas());
			assertNull(reservas.buscar(reserva2));
			List<Reserva> reservas1 = reservas.getReservas();
			assertEquals(reserva1, reservas1.get(0));
			assertEquals(reserva3, reservas1.get(1));
			assertEquals(reserva4, reservas1.get(2));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarFinalValidoTest() {
		Reservas reservas = insertarCuatro();
		try {
			reservas.borrar(reserva4);
			assertEquals(3, reservas.getNumReservas());
			assertNull(reservas.buscar(reserva4));
			List<Reserva> reservas1 = reservas.getReservas();
			assertEquals(reserva1, reservas1.get(0));
			assertEquals(reserva2, reservas1.get(1));
			assertEquals(reserva3, reservas1.get(2));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarNuloTest() {
		Reservas reservas = insertarCuatro();
		try {
			reservas.borrar(null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede anular una reserva nula.", e.getMessage());
			assertEquals(4, reservas.getNumReservas());
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarNoValidoTest() {
		Reservas reservas = insertarCuatro();
		try {
			Reserva reserva = new Reserva(profesor1, aula3, permanencia1);
			reservas.borrar(reserva);
			fail(ERROR_EXCEPCION);
		} catch (OperationNotSupportedException e) {
			assertEquals("ERROR: La reserva a anular no existe.", e.getMessage());
			assertEquals(4, reservas.getNumReservas());
		}
		try {
			Reserva reserva = new Reserva(profesor1, aula2, permanencia3);
			reservas.borrar(reserva);
			fail(ERROR_EXCEPCION);
		} catch (OperationNotSupportedException e) {
			assertEquals("ERROR: La reserva a anular no existe.", e.getMessage());
			assertEquals(4, reservas.getNumReservas());
		}
	}
	
	@Test
	public void borrarInsertarTest() {
		Reservas reservas = insertarCuatro();
		try {
			reservas.borrar(reserva1);
			assertEquals(3, reservas.getNumReservas());
			assertNull(reservas.buscar(reserva1));
			reservas.insertar(reserva1);
			assertEquals(4, reservas.getNumReservas());
			assertEquals(reserva1, reservas.buscar(reserva1));
			List<Reserva> reservas1 = reservas.getReservas();
			assertEquals(reserva2, reservas1.get(0));
			assertEquals(reserva3, reservas1.get(1));
			assertEquals(reserva4, reservas1.get(2));
			assertEquals(reserva1, reservas1.get(3));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void buscarNuloTest() {
		Reservas reservas = insertarCuatro();
		try {
			reservas.buscar(null);
			fail(ERROR_EXCEPCION);
		} 
		catch (NullPointerException e) 
		{
			assertEquals("ERROR: No se puede buscar un reserva nula.", e.getMessage());
		}
	}
	
	@Test
	public void representarTest() {
		Reservas reservas = insertarCuatro();
		List<String> representacion = reservas.representar();
		assertEquals(reserva1.toString(), representacion.get(0));
		assertEquals(reserva2.toString(), representacion.get(1));
		assertEquals(reserva3.toString(), representacion.get(2));
		assertEquals(reserva4.toString(), representacion.get(3));
	}
	
	@Test
	public void getReservasProfesorTest() {
		Reservas reservas = insertarCuatro();
		List<Reserva> reservasProfesor = reservas.getReservasProfesor(profesor1);
		assertEquals(reserva1, reservasProfesor.get(0));
		assertEquals(reserva2, reservasProfesor.get(1));
		assertEquals(reserva3, reservasProfesor.get(2));
		assertEquals(reserva4, reservasProfesor.get(3));
		assertEquals(4, reservasProfesor.size());
		reservasProfesor = reservas.getReservasProfesor(profesor2);
		assertEquals(0, reservasProfesor.size());
	}
	
	@Test
	public void getReservasAulaTest() {
		Reservas reservas = insertarCuatro();
		List<Reserva> reservasAula = reservas.getReservasAula(aula1);
		assertEquals(reserva1, reservasAula.get(0));
		assertEquals(reserva2, reservasAula.get(1));
		assertEquals(2, reservasAula.size());
		reservasAula = reservas.getReservasAula(aula3);
		assertEquals(0, reservasAula.size());
	}
	
	@Test
	public void getReservasPermanenciaTest() {
		Reservas reservas = insertarCuatro();
		List<Reserva> reservasPermanencia = reservas.getReservasPermanencia(permanencia1);
		assertEquals(reserva1, reservasPermanencia.get(0));
		assertEquals(reserva3, reservasPermanencia.get(1));
		assertEquals(2, reservasPermanencia.size());
		reservasPermanencia = reservas.getReservasPermanencia(permanencia3);
		assertEquals(0, reservasPermanencia.size());
	}
	
	@Test
	public void consultarDisponibilidadValidoTest() {
		Reservas reservas = insertarCuatro();
		assertFalse(reservas.consultarDisponibilidad(aula1, permanencia1));
		Aula aula = new Aula("Aula");
		Permanencia permanencia = new Permanencia(LocalDate.of(2018, 12, 22), Tramo.MANANA);
		assertTrue(reservas.consultarDisponibilidad(aula1, permanencia));
		assertTrue(reservas.consultarDisponibilidad(aula, permanencia1));
		assertTrue(reservas.consultarDisponibilidad(aula, permanencia));
	}
	
	@Test
	public void consultarDisponibilidadNoValidoTest() {
		Reservas reservas = insertarCuatro();
		try {
			reservas.consultarDisponibilidad(null, permanencia1);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede consultar la disponibilidad de un aula nula.", e.getMessage());
		}
		try {
			reservas.consultarDisponibilidad(aula1, null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede consultar la disponibilidad de una permanencia nula.", e.getMessage());
		}
	}

}
