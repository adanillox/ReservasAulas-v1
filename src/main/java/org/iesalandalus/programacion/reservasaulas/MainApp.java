package org.iesalandalus.programacion.reservasaulas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class MainApp {

	public static void main(String[] args) throws OperationNotSupportedException {
		
		System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	
	}

}
