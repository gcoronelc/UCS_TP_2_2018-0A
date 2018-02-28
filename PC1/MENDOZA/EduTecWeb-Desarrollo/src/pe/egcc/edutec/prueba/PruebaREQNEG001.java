package pe.egcc.edutec.prueba;

import java.util.Calendar;
import java.util.Date;
import pe.egcc.edutec.service.NegocioService;
import java.util.Date;

public class PruebaREQNEG001 {
  
  public static void main(String[] args) {
    
	  
	 // El proceso genera el siguiente ciclo en funci�n al �ltimo que existe en la tabla CICLO.
	//  La fecha de inicio es el primer d�a del mes y la fecha de t�rmino es el �ltimo d�a del mes.

	    try {
	        
	        // Datos
	    	Date FechaInicio = null;
	    	Date FechaTermino = null;
	      //Calendar Fecha1 = Calendar.getInstance();
	     	        
	        // Proceso
	        NegocioService negocioService = new NegocioService();
	        String id = negocioService.generarNuevoCiclo(FechaInicio,FechaTermino);
	        
	        // Reporte
	        System.out.println("ID: " + id);
	        
	      } catch (Exception e) {
	        
	        System.err.println(e.getMessage());	
	        
	      }
        
  }

}
