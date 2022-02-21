package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;


public class Profesores {
private List<Profesor> coleccionProfesores;


public Profesores() {
	 this.coleccionProfesores= new ArrayList<>();
}


public Profesores(Profesores profesores) {
	if(profesores==null) {
		throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
	}
	else{
		setProfesores(profesores);
	}

}
private void setProfesores(Profesores profesores) {
	if(profesores==null) {
		throw new NullPointerException("ERROR: Las de profesores no pueden ser null.");
	}
	coleccionProfesores=copiaProfundaProfesores(profesores.coleccionProfesores);
}


public List<Profesor> getProfesores(){
	return  copiaProfundaProfesores(coleccionProfesores) ;
}


public int getNumProfesores() {
	return coleccionProfesores.size();
}

private List<Profesor> copiaProfundaProfesores(List<Profesor> profesor) {
	List<Profesor> copiaProfesor= new ArrayList<>();
	Iterator<Profesor> a=profesor.iterator();
	
	while(a.hasNext()) {
		Profesor c =a.next();
		copiaProfesor.add(new Profesor(c));
	}
	return copiaProfesor;
}





public void insertar(Profesor profesor) throws OperationNotSupportedException {

	if (profesor == null) {
		throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
	}if(!coleccionProfesores.contains(profesor)){	 
				coleccionProfesores.add(profesor);
			
		}else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
		}
}

public Profesor buscar(Profesor profesor)  { 
	if (profesor == null) {
		throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
	}
	if(coleccionProfesores.contains(profesor)) {
		int i=coleccionProfesores.indexOf(profesor);
		profesor=coleccionProfesores.get(i);
		return new Profesor(profesor);

	}else {
		return null;
	}
		}


public void borrar(Profesor profesor) throws OperationNotSupportedException {
	if (profesor == null) {
		throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
	}
	if(coleccionProfesores.contains(profesor)) {
		coleccionProfesores.remove(profesor);
	}
		else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
	
}	
public List<String> representar(){
	System.out.println("Listado de profesores");
	List<String> representa = new ArrayList<>();
	for(Profesor a : coleccionProfesores) {
		representa.add(a.toString());
	 
	}
	return representa ;
}
}
