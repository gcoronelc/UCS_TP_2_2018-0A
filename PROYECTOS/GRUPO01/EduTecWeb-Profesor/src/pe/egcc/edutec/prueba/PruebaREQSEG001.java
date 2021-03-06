package pe.egcc.edutec.prueba;

import pe.egcc.edutec.dto.EmpleadoDTO;
import pe.egcc.edutec.service.LogonService;

public class PruebaREQSEG001 {
  
  public static void main(String[] args) {
    
    try {
      
      // Datos
      String codigo = "acampo";
      String clave  = "acampoa";
      
      // Proceso
      LogonService logonService = new LogonService();
      EmpleadoDTO empleadoDTO = logonService.validarUsuario(codigo, clave);
      System.out.println("Hola: " + empleadoDTO.getNombre());
      
    } catch (Exception e) {
      
      System.err.println(e.getMessage());
      
    }
        
  }

}
