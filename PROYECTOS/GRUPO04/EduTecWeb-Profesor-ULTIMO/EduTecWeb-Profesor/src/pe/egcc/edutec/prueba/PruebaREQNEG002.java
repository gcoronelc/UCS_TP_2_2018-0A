package pe.egcc.edutec.prueba;

import pe.egcc.edutec.service.NegocioService;

public class PruebaREQNEG002 {
  
  public static void main(String[] args) {
    
    try {
      
      // Datos
      String ciclo = "2018-02";
      String curso = "C011";
      String profesor = "";
      String horario = "SAB y DOM de 8 a 12 horas";
      int vacantes = 20;
      
      // Proceso
      NegocioService negocioService = new NegocioService();
      int id = negocioService.programarCurso(ciclo, curso, profesor, horario, vacantes);
      
      // Reporte
      System.out.println("ID: " + id);
      
    } catch (Exception e) {
      
      System.err.println(e.getMessage());
      
    }
    

    
  }

}
