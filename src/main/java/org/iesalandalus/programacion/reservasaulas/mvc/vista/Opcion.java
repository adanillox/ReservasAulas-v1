package org.iesalandalus.programacion.reservasaulas.mvc.vista;

public enum Opcion {
	
	INSERTAR_AULA("Insertar Aula") {
		@Override
		public void ejecutar() {
			vista.insertarAula();
			// TODO Auto-generated method stub
			
		}
	},
	BUSCAR_AULA("Buscar Aula") {
		@Override
		public void ejecutar() {
			vista.buscarAula();
			// TODO Auto-generated method stub
			
		}
	},
	BORRAR_AULA("Borrar Aula") {
		@Override
		public void ejecutar() {
			vista.borrarAula();
			// TODO Auto-generated method stub
			
		}
	},
	LISTAR_AULAS("Listar Aulas") {
		@Override
		public void ejecutar() {
			vista.listarAulas();
			// TODO Auto-generated method stub
			
		}
	},
	INSERTAR_PROFESOR("Insertar Profesores") {
		@Override
		public void ejecutar() {
			vista.insertarProfesor();
			// TODO Auto-generated method stub
			
		}
	},
	BORRAR_PROFESOR("Borrar Profesores") {
		@Override
		public void ejecutar() {
			vista.borrarProfesor();
			// TODO Auto-generated method stub
			
		}
	},
	BUSCAR_PROFESOR("Buscar Profesor") {
		@Override
		public void ejecutar() {
			vista.buscarProfesor();
			// TODO Auto-generated method stub
			
		}
	},
	LISTAR_PROFESORES("Listar Profesores") {
		@Override
		public void ejecutar() {
			vista.listarProfesores();
			// TODO Auto-generated method stub
			
		}
	},
	INSERTAR_RESERVA("Insertar Reserva") {
		@Override
		public void ejecutar() {
			vista.realizarReserva();
			// TODO Auto-generated method stub
			
		}
	},
	BORRAR_RESERVA("Borrar Reserva") {
		@Override
		public void ejecutar() {
			vista.anularReserva();
			// TODO Auto-generated method stub
			
		}
	},
	LISTAR_RESERVAS("Listar Reservas") {
		@Override
		public void ejecutar() {
			vista.listarReservas();
			// TODO Auto-generated method stub
			
		}
	},
	LISTAR_RESERVAS_AULA("Listar Reservas Aula") {
		@Override
		public void ejecutar() {
			vista.listarReservasAula();
			// TODO Auto-generated method stub
			
		}
	},

	LISTAR_RESERVAS_PROFESOR("Listar Reservas Profesor") {
		@Override
		public void ejecutar() {
			vista.listarReservasProfesor();
			// TODO Auto-generated method stub
			
		}
	},
	LISTAR_RESERVAS_PERMANENCIA("Listar Reservas Permanencia") {
		@Override
		public void ejecutar() {
			vista.listarReservasPermanencia();
			// TODO Auto-generated method stub
			
		}
	},
	CONSULTAR_DISPONIBILIDAD("Consultar Disponibilidad") {
		@Override
		public void ejecutar() {
			vista.consultarDisponibilidad();
			// TODO Auto-generated method stub
			
		}
	},
		SALIR("Salir") {
			@Override
			public void ejecutar() {
				vista.salir();
				// TODO Auto-generated method stub
				
			}
			
	};
	

private String mensajeAMostrar;
private static Vista vista;

private Opcion( String mensajeAMostrar) {
	this.mensajeAMostrar=mensajeAMostrar;
	}

public String getMensaje() {
	return mensajeAMostrar;
}
public abstract void ejecutar(); 
		
	
protected static void setVista(Vista vista) {
	if (vista == null) {
		throw new NullPointerException("ERROR: La vista no pueda ser nula.");
	}
	Opcion.vista=vista;
}

public String String() {
	return String.format("%d.- %s", ordinal(), mensajeAMostrar);
}
public static Opcion getOpcionSegunOrdinal(int opcion) {
	if(esOrdinalValido(opcion)) {
		return values()[opcion];
	}
		else {
			throw new IllegalArgumentException("Ordinal de la opción no valido");
		}
	
}
public static boolean esOrdinalValido(int valido) {
	return(valido >= 0 && valido <= values().length - 1);
	
}
}
