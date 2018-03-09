package pe.egcc.edutec.prueba;

import java.util.List;
import java.util.Map;

import pe.egcc.edutec.service.ConsultaService;

public class PruebaREQCON001B {
  
  public static void main(String[] args) {
   
    ConsultaService service = new ConsultaService();
    
    List<Map<String,Object>> lista = service.conAlumnosCursoPrg(7890);
    
    for (Map<String,Object> dto : lista) {
      System.out.println(dto.get("alumno").toString() + " | " 
            + dto.get("promedio").toString());
    }
    
    
  }

}
