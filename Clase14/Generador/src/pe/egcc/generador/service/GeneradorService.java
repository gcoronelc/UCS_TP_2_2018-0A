package pe.egcc.generador.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import pe.egcc.generador.bean.Grupo;

public class GeneradorService {

  private Random random = new Random();
  
  public List<Grupo> generar() {

    List<Grupo> grupos = construyeLista();
    generaOrden(grupos);
    Collections.sort(grupos);

    return grupos;
  }

  private List<Grupo> construyeLista() {
    List<Grupo> grupos = new ArrayList<>();
    grupos.add(new Grupo("Grupo 01"));
    grupos.add(new Grupo("Grupo 02"));
    grupos.add(new Grupo("Grupo 03"));
    grupos.add(new Grupo("Grupo 04"));
    grupos.add(new Grupo("Grupo 05"));
    return grupos;
  }

  private void generaOrden(List<Grupo> grupos) {
    for (Grupo grupo : grupos) {
      int n = 0;
      while(n == 0){
        n = random.nextInt(5) + 1;
        for (Grupo bean : grupos) {
          if(bean.getOrden() == n){
            n = 0;
            break;
          }
        }        
      }
      grupo.setOrden(n);
    }    
  }

  
}
