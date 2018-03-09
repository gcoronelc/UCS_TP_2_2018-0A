package pe.egcc.edutec.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.egcc.edutec.dto.TarifaDTO;
import pe.egcc.edutec.service.ConsultaService;
import pe.egcc.edutec.service.NegocioService;

/**
 * Servlet implementation class TarifaController
 */
@WebServlet({ "/TarifaController", "/TarifaListar", "/TarifaListarProc","/TarifaNuevo","/TarifaNuevoProc",
	"/TarifaEliminar",	"/TarifaModificar",	"/TarifaModificarProc" })
public class TarifaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		  
		   
		switch(path){
		  
		  case "/TarifaListar":
			  TarifaListar(request,response);
		    break;
		    
		  case "/TarifaListarProc":
			  TarifaListarProc(request,response);
	      break;	 
	      
		  case "/TarifaNuevo":
			  TarifaNuevo(request,response);
	      break;	
		  
		  case "/TarifaNuevoProc":
			  TarifaNuevoProc(request,response);
	      break;	
		  case "/TarifaModificar":
			  TarifaModificar(request,response);
	      break;
		  case "/TarifaModificarProc":
			  TarifaModificarProc(request,response);
	      break;
		  }
		  
		  
	
	}

	private void TarifaModificarProc(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub


	}

	private void TarifaModificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String	IdTarifa = request.getParameter("IdTarifa");
		try {
			
			   ConsultaService negocioService = new ConsultaService();
			   TarifaDTO   oTarifaDTO=  negocioService.GetIdTarifa(IdTarifa);
			   request.setAttribute("IdTarifa", oTarifaDTO.getIdTarifa());
			   request.setAttribute("PrecioVenta", oTarifaDTO.getPrecioVenta());
			request.setAttribute("Descripcion", oTarifaDTO.getDescripcion());
			request.setAttribute("Horas", oTarifaDTO.getHoras());
			request.setAttribute("PagoHora", oTarifaDTO.getPagoHora());
			request.setAttribute("accion", "MODIFICAR");
			
		} catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		 UtilController.forward(request, response, "NuevoTarifa.jsp");

	}

	private void TarifaEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String	IdTarifa = request.getParameter("IdTarifa");	
		try {

			   ConsultaService negocioService = new ConsultaService();
				request.setAttribute("accion", "ELIMINAR");
			   negocioService.Eliminar(IdTarifa);
			   

		} catch (Exception e) {
		}
		 UtilController.forward(request, response, "ListadoTarifas.jsp");

	}

	private void TarifaNuevoProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   // String pagina = "programarCurso.jsp";

	    String IdTarifa = "";
	    Double PrecioVenta = (double) 0;
	    String Descripcion = "";
	    Integer Horas = 0;
	    Double PagoHora = (double) 0;
		    
	    try {

	      // Datos
	    	IdTarifa = request.getParameter("IdTarifa");
	    	PrecioVenta =Double.parseDouble(request.getParameter("PrecioVenta"));
	    	Descripcion = request.getParameter("Descripcion");
	    	Horas =Integer.parseInt( request.getParameter("Horas"));
	    	PagoHora = Double.parseDouble(request.getParameter("PagoHora"));

	      // Proceso
	    	String S=request.getParameter("accion");
	      ConsultaService negocioService = new ConsultaService();
	    if (S.equals("MODIFICAR")) {
	    	negocioService.ModificarTarifa(IdTarifa, PrecioVenta, Descripcion, Horas, PagoHora);
			
	    }
	    else {
	    	negocioService.Registrar(IdTarifa, PrecioVenta, Descripcion, Horas, PagoHora);

		}
	      
	      // Mensaje
	      String mensaje = "Proceso ok. ID = " + IdTarifa + ".";
	      request.setAttribute("mensaje", mensaje);

	    } catch (Exception e) {

	      request.setAttribute("error", e.getMessage());

	    }
	    UtilController.forward(request, response, "ListadoTarifas.jsp");


	}

	private void TarifaNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	    RequestDispatcher rd = request.getRequestDispatcher("NuevoTarifa.jsp");
	    request.setAttribute("accion", "NUEVO");
	    rd.forward(request, response);
	}

	private void TarifaListarProc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		 String Descripcion = request.getParameter("Descripcion");
		    
		    // Listas de combos
		    ConsultaService service = new ConsultaService();
		    List<Map<String, Object>> cursos = service.ListarTarifas(Descripcion);
		    
		    // Crear JSON
		    Gson gson = new Gson();
		    String cursosJson = gson.toJson(cursos);
		    
		    // Respuesta    
		    UtilController.reponseJSON(response, cursosJson);
		
	}

	private void TarifaListar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    UtilController.forward(request, response, "ListadoTarifas.jsp");
	}

}
