package pe.egcc.generador.prueba;

import java.util.List;
import pe.egcc.generador.bean.Grupo;
import pe.egcc.generador.service.GeneradorService;

public class Prueba01 {

  public static void main(String[] args) {
    GeneradorService service = new GeneradorService();
    List<Grupo> grupos = service.generar();

    
    for (Grupo g : grupos) {
      System.out.println(g.getNombre() + " - " + g.getOrden());
    }
  }
  
}
