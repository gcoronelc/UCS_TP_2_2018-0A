package pe.egcc.edutec.dto;

public class TarifaDTO {

	
	String	IdTarifa;
	String	PrecioVenta;
	String	Descripcion;
	String Horas;
	String PagoHora;
	public String getIdTarifa() {
		return IdTarifa;
	}
	public void setIdTarifa(String idTarifa) {
		IdTarifa = idTarifa;
	}
	public String getPrecioVenta() {
		return PrecioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		PrecioVenta = precioVenta;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getHoras() {
		return Horas;
	}
	public void setHoras(String horas) {
		Horas = horas;
	}
	public String getPagoHora() {
		return PagoHora;
	}
	public void setPagoHora(String pagoHora) {
		PagoHora = pagoHora;
	}
	

}
