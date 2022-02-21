package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	private List<Reserva> coleccionReservas;

	public Reservas() {
		this.coleccionReservas = new ArrayList<>();
	}

	public Reservas(Reservas reservas) {
		if (reservas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		} else {
			setReservas(reservas);
		}

	}

	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new NullPointerException("ERROR: Las de reservas no pueden ser null.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
	}

	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return coleccionReservas.size();
	}

	private List<Reserva> copiaProfundaReservas(List<Reserva> reserva) {
		List<Reserva> copiaReserva = new ArrayList<>();
		Iterator<Reserva> a = reserva.iterator();

		while (a.hasNext()) {
			Reserva c = a.next();
			copiaReserva.add(new Reserva(c));
		}
		return copiaReserva;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {

		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		}
		if (!coleccionReservas.contains(reserva)) {
			coleccionReservas.add(reserva);

		} else {
			throw new OperationNotSupportedException("ERROR: La reserva ya existe.");
		}
	}

	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		int mRes = reserva.getPermanencia().getDia().getMonthValue();
		int mAct = LocalDate.now().getMonthValue();
		int aRes = reserva.getPermanencia().getDia().getYear();
		int aAct = LocalDate.now().getYear();

		boolean valido = false;

		if (aRes - aAct == 1 && (mRes - mAct == -11 || mRes - mAct == -10)) {
			valido = true;
		}
		if (aRes == aAct && (mRes - mAct == 1 || mRes - mAct == 2)) {
			valido = true;
		}
		return valido;
	}

	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate mesFecha) {

		List<Reserva> reservasMes = new ArrayList<>();

		Month mesReserva = mesFecha.getMonth();

		for (Iterator<Reserva> remes = getReservas().iterator(); remes.hasNext();) {

			Reserva res = remes.next();

			if (res.getProfesor().equals(profesor) && res.getPermanencia().getDia().getMonth().equals(mesReserva)) {

				reservasMes.add(new Reserva(res));
			}

		}

		return reservasMes;

	}

	private Reserva getReservaAulaDia(Aula aula, LocalDate diaFecha) {

		Reserva reserva = null;

		int diaReserva = diaFecha.getDayOfMonth();

		for (Iterator<Reserva> it = getReservas().iterator(); it.hasNext();) {

			reserva = it.next();
		}
		if (reserva.getAula().equals(aula) && reserva.getPermanencia().getDia().getDayOfMonth() == diaReserva) {

			return new Reserva(reserva);

		} else {

			return null;
		}

	}

	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		}
		if (coleccionReservas.contains(reserva)) {
			int i = coleccionReservas.indexOf(reserva);
			reserva = coleccionReservas.get(i);
			return new Reserva(reserva);

		} else {
			return null;
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede anular una reserva nula.");
		}
		if (coleccionReservas.contains(reserva)) {
			coleccionReservas.remove(reserva);
		} else {
			throw new OperationNotSupportedException("ERROR: La reserva a anular no existe.");
		}

	}

	public List<String> representar() {
		System.out.println("Listado de reservas");
		List<String> representa = new ArrayList<>();
		for (Reserva a : coleccionReservas) {
			representa.add(a.toString());

		}
		return representa;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {
		List<Reserva> res=new ArrayList<>();
		for (Iterator<Reserva> r = getReservas().iterator(); r.hasNext();) {
			Reserva reserva = r.next();
			if(reserva.getProfesor().equals(profesor)) {
				res.add(new Reserva(reserva));
			}
		}
		return res;
		}
	

	public List<Reserva> getReservasAula(Aula aula) {
		List<Reserva> res=new ArrayList<>();
		for (Iterator<Reserva> r = getReservas().iterator(); r.hasNext();) {
			Reserva reserva = r.next();
			if(reserva.getAula().equals(aula)) {
				res.add(new Reserva(reserva));
			}
		}
		return res;
		}
	

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		List<Reserva> res=new ArrayList<>();
		for (Iterator<Reserva> r = getReservas().iterator(); r.hasNext();) {
			Reserva reserva = r.next();
			
			if(reserva.getPermanencia().equals(permanencia)) {
				res.add(new Reserva(reserva));
			}
		}
		return res;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");

		}
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (Iterator<Reserva> r = getReservas().iterator(); r.hasNext();) {
			Reserva reserva = r.next();
			if (reserva.getAula().equals(aula) && reserva.getPermanencia().equals(permanencia)) {

				return false;
			}

		}

		return true;

	}
}