package pe.egcc.edutec.dto;

public class FinancieraDTO {

  private String nombreAlumno;
  private String nombreCurso;
  private int ingresos;
  private int gastos;
  private int ganancias;
  
  public FinancieraDTO() {
  }
  
  public FinancieraDTO(String nombreAlumno, String nombreCurso, int ingresos , int gastos , int ganancias) {
	    this.nombreAlumno = nombreAlumno;
	    this.nombreCurso = nombreCurso;
	    this.gastos = gastos;
	    this.ingresos = ingresos;
	    
	    this.ganancias = ganancias;
	  }

  
public String getNombreAlumno() {
	return nombreAlumno;
}

public void setNombreAlumno(String nombreAlumno) {
	this.nombreAlumno = nombreAlumno;
}

public String getNombreCurso() {
	return nombreCurso;
}

public void setNombreCurso(String nombreCurso) {
	this.nombreCurso = nombreCurso;
}

public int getIngresos() {
	return ingresos;
}

public void setIngresos(int ingresos) {
	this.ingresos = ingresos;
}

public int getGastos() {
	return gastos;
}

public void setGastos(int gastos) {
	this.gastos = gastos;
}

public int getGanancias() {
	return ganancias;
}

public void setGanancias(int ganancias) {
	this.ganancias = ganancias;
}

   
}
