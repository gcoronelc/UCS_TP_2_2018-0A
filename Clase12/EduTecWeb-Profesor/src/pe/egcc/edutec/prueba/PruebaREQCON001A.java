package pe.egcc.edutec.prueba;

import java.util.List;

import pe.egcc.edutec.dto.ComboDTO;
import pe.egcc.edutec.service.ComboService;

public class PruebaREQCON001A {
  
  public static void main(String[] args) {
   
    ComboService comboService = new ComboService();
    
    List<ComboDTO> lista = comboService.traerCursosPorCiclo("2018-02");
    
    for (ComboDTO dto : lista) {
      System.out.println(dto.getCode() + " ==> " + dto.getName());
    }
    
    
  }

}
