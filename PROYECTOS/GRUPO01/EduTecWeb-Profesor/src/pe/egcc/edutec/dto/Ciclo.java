package pe.egcc.edutec.dto;

import java.sql.Date;

public class Ciclo {

  private String ciclo;
  private Date fechaini;
  private Date fechafin;

  public Ciclo() 
  {
  }

  public String getCiclo() {
    return ciclo;
  }

  public void setCiclo(String ciclo) {
    this.ciclo = ciclo;
  }

  public Date getFechaIni()
  {
	  return fechaini;
  }
  
  public void setFechaIni(Date fechaini) 
  {
	    this.fechaini = fechaini;
  }

  public Date getFechaFin()
  {
	  return fechafin;
  }
  public void setFechaFin(Date fechafin) 
  {
	    this.fechafin = fechafin;
  }
}
