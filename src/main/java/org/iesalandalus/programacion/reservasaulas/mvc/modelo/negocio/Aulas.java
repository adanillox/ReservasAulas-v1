package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.*;

import javax.naming.OperationNotSupportedException;


import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	private  List<Aula> coleccionAulas; 
	
	public Aulas() {
		 this.coleccionAulas= new ArrayList<>();
	}
	
	
	public Aulas(Aulas aulas) {
		if(aulas==null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}
		else{
			setAulas(aulas);
		}

	}
	private void setAulas(Aulas aulas) {
		if(aulas==null) {
			throw new NullPointerException("ERROR: Las de aulas no pueden ser null.");
		}
		coleccionAulas=copiaProfundaAulas(aulas.coleccionAulas);
	}
	
	
	public List<Aula> getAulas(){
		return  copiaProfundaAulas(coleccionAulas) ;
	}
	
	
	public int getNumAulas() {
		return coleccionAulas.size();
	}
	
	private List<Aula> copiaProfundaAulas(List<Aula> aula) {
		List<Aula> copiaAula= new ArrayList<>();
		Iterator<Aula> a=aula.iterator();
		
		while(a.hasNext()) {
			Aula c =a.next();
			copiaAula.add(new Aula(c));
		}
		return copiaAula;
	}
	




	public void insertar(Aula aula) throws OperationNotSupportedException {

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}if(!coleccionAulas.contains(aula)){	 
					coleccionAulas.add(aula);
				
			}else {
				throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
			}
	}

	public Aula buscar(Aula aula)  { 
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		if(coleccionAulas.contains(aula)) {
			int i=coleccionAulas.indexOf(aula);
			aula=coleccionAulas.get(i);
			return new Aula(aula);

		}else {
			return null;
		}
			}
	
	
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		if(coleccionAulas.contains(aula)) {
			coleccionAulas.remove(aula);
		}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
			}
		
	}	
	public List<String> representar(){
		System.out.println("Listado de aulas");
		List<String> representa = new ArrayList<>();
		for(Aula a : coleccionAulas) {
			representa.add(a.toString());
		 
		}if (!representa.isEmpty()) {
			
			return representa ;
			}
		throw new NullPointerException("ERROR: Inserte primero un aula.");
		
	}
}
