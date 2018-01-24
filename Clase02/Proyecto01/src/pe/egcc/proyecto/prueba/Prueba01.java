package pe.egcc.proyecto.prueba;

import pe.egcc.proyecto.service.ProyectoService;

public class Prueba01 {
  
  public static void main(String[] args) {
    // Datos
    int n1 = 14;
    int n2 = 17;
    // Proceso
    ProyectoService proyectoService = new ProyectoService();
    int suma = proyectoService.calcSuma(n1, n2);
    int producto = proyectoService.calcProducto(n1, n2);
    // Reporte
    System.out.println("DATOS");
    System.out.println("Número 1: " + n1);
    System.out.println("Número 2: " + n2);
    System.out.println("RESULTADO");
    System.out.println("Suma: " + suma);
    System.out.println("Producto: " + producto);
  }

}
