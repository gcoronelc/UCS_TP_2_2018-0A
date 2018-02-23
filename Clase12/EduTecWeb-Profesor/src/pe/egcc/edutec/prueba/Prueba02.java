package pe.egcc.edutec.prueba;

import java.util.List;

import pe.egcc.edutec.dto.ComboDTO;
import pe.egcc.edutec.service.ComboService;

public class Prueba02 {

  public static void main(String[] args) {
    
    try {
      
      ComboService comboService = new ComboService();
      List<ComboDTO> lista = comboService.traerCursos();
      
      for(ComboDTO dto: lista){
        System.out.println(dto.getCode() + " - " + dto.getName());
      }
      
    } catch (Exception e) {
      
      e.printStackTrace();
      
    }
    
  }
  
}
