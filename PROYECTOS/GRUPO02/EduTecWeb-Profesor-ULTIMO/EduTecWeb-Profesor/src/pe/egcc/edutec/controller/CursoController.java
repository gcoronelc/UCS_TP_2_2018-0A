package pe.egcc.edutec.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.egcc.edutec.dto.ComboDTO;
import pe.egcc.edutec.dto.CursoDTO;
import pe.egcc.edutec.dto.TarifaDTO;
import pe.egcc.edutec.service.ComboService;
import pe.egcc.edutec.service.ConsultaService;

/**
 * Servlet implementation class CursoController
 */
@WebServlet({ "/CursoController", "/CursoListar", "/CursoListarProc", "/CursoNuevo",
	"/CursoNuevoProc", "/CursoEliminar", "/CursoModificar","/comboTarifas" })
public class CursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath(); 
		switch(path){		  
		  case "/CursoListar":
			  CursoListar(request,response);
		    break;
		  case "/CursoListarProc":
			  CursoListarProc(request,response);
		    break;
		  case "/CursoNuevo":
			  CursoNuevo(request,response);
		    break;
		  case "/CursoNuevoProc":
			  CursoNuevoProc(request,response);
		    break;
		  case "/CursoEliminar":
			  CursoEliminar(request,response);
		    break;
		  case "/CursoModificar":
			  CursoModificar(request,response);
		    break;
		  case "/comboTarifas":
			  comboTarifas(request,response);
		    break; 
		    
		}
	}

	private void CursoModificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String	IdCurso = request.getParameter("IdCurso");
		try {
			ComboService comboService = new ComboService();
		    List<ComboDTO> Tarifas = comboService.traerTarifas("");
		    request.setAttribute("Tarifas", Tarifas);
		
			   ConsultaService negocioService = new ConsultaService();
			   CursoDTO   oCursoDTO=  negocioService.GetCurso(IdCurso);
			   request.setAttribute("IdCurso", oCursoDTO.getIdCurso());
			   request.setAttribute("IdTarifa", oCursoDTO.getIdTarifa());
			request.setAttribute("NomCurso", oCursoDTO.getNomCurso());

			request.setAttribute("accion", "MODIFICAR");
			
		} catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		   UtilController.forward(request, response, "NuevoCurso.jsp");
			

		
	}

	private void CursoEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String	IdCurso = request.getParameter("IdCurso");	
		try {

			   ConsultaService negocioService = new ConsultaService();
				
			   negocioService.EliminarCurso(IdCurso);
			   

		} catch (Exception e) {
		}
		  UtilController.forward(request, response, "ListadoCursos.jsp");

	}

	  private void comboTarifas(HttpServletRequest request, 
	      HttpServletResponse response) throws IOException {
	    // Dato
	    
	    // Listas de combos
	    ComboService comboService = new ComboService();
	    List<ComboDTO> Tarifas = comboService.traerTarifas("");
	    
	    // Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(Tarifas);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);
	  }


	private void CursoNuevoProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String IdCurso = "";
	    
		  String IdTarifa = "";
		    
		    String NomCurso = "";
	
			    
		    try {

		      // Datos
		    	IdTarifa = request.getParameter("IdTarifa");
		    	
		    	NomCurso = request.getParameter("NomCurso");
		    
		      // Proceso
		    	String S=request.getParameter("accion");
		      ConsultaService negocioService = new ConsultaService();
		    if (S.equals("MODIFICAR")) {
		    	IdCurso = request.getParameter("IdCurso");
		    	
		    	negocioService.ModificarCurso(IdCurso,IdTarifa, NomCurso);
				
		    }
		    else {
		    	negocioService.RegistrarCurso(IdTarifa, NomCurso);
				
		    	//negocioService.RegistrarCurso(IdTarifa, PrecioVenta, Descripcion, Horas, PagoHora);

			}
		      
		      // Mensaje
		      String mensaje = "Proceso ok. ID = " + IdTarifa + ".";
		      request.setAttribute("mensaje", mensaje);

		    } catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		    UtilController.forward(request, response, "ListadoCursos.jsp");


		
	}

	private void CursoNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ComboService comboService = new ComboService();
	    List<ComboDTO> Tarifas = comboService.traerTarifas("");
	    request.setAttribute("Tarifas", Tarifas);
	    
	    request.setAttribute("accion", "NUEVO");

		   UtilController.forward(request, response, "NuevoCurso.jsp");
		
	}

	private void CursoListarProc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String Descripcion = request.getParameter("Descripcion");
	    
	    // Listas de combos
	    ConsultaService service = new ConsultaService();
	    List<Map<String, Object>> cursos = service.ListarCursos(Descripcion);
	    
	    // Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(cursos);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);

	}

	private void CursoListar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    UtilController.forward(request, response, "ListadoCursos.jsp");
	}

}
