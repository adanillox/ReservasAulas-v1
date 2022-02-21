package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Profesor {
	private static final String ER_TELEFONO = "^[69][0-9]{8}$";
	private static final String ER_CORREO = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	public Profesor(String nombre, String correo, String telefono) {
		if (telefono == null) {
			setNombre(nombre);
			setCorreo(correo);
		} else {
			setNombre(nombre);
			setCorreo(correo);
			setTelefono(telefono);
		}

	}

	public Profesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		} else {
			if (profesor.getTelefono() == null) {
				setNombre(profesor.getNombre());
				setCorreo(profesor.getCorreo());
			} else {
				setNombre(profesor.getNombre());
				setCorreo(profesor.getCorreo());
				setTelefono(profesor.getTelefono());
			}

		}
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}
		if (nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		} else {
			this.nombre = formateaNombre(nombre);
		}

	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}

		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		} else {
			this.correo = correo;
		}

	}

	public void setTelefono(String telefono) {
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		} else {
			this.telefono = telefono;
		}

	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	private String formateaNombre(String nombre) {

		nombre = nombre.trim().toLowerCase();
		nombre = nombre.replaceAll("\\s+", " ");
		String correccion = nombre.substring(0, 1).toUpperCase();
		for (int i = 1; i < nombre.length(); i++) {
			if (nombre.charAt(i - 1) == ' ') {
				correccion += nombre.substring(i, i + 1).toUpperCase();

			} else {
				correccion += nombre.substring(i, i + 1);
			}

		}
		return correccion;

	}

	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		if (telefono == null) {
			return "nombre=" + nombre + ", correo=" + correo;
		} else {
			return "nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono;
		}

	}

}
