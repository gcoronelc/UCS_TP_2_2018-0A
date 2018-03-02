package pe.egcc.generador.bean;

public class Grupo implements Comparable<Grupo>{

  private String nombre;
  private int orden;

  public Grupo() {
  }

  public Grupo(String nombre) {
    this.nombre = nombre;
    this.orden = 0;
  }
  
  public Grupo(String nombre, int orden) {
    this.nombre = nombre;
    this.orden = orden;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getOrden() {
    return orden;
  }

  public void setOrden(int orden) {
    this.orden = orden;
  }

  @Override
  public int compareTo(Grupo g) {
    return ((this.orden > g.getOrden())?1:-1);
  }
  
  
}
