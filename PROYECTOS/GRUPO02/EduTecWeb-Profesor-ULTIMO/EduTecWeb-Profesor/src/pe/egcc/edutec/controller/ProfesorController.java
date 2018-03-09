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
import pe.egcc.edutec.dto.ProfesorDTO;
import pe.egcc.edutec.service.ComboService;
import pe.egcc.edutec.service.ConsultaService;
import pe.egcc.edutec.service.ProfesorService;

/**
 * Servlet implementation class ProfesorController
 */
@WebServlet({ "/ProfesorListar", "/ProfesorListarProc", "/ProfesorNuevo", "/ProfesorNuevoProc",
	"/ProfesorModificar", "/ProfesorEliminar" })
public class ProfesorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String path = request.getServletPath(); 
		switch(path){		  
		  case "/ProfesorListar":
			  ProfesorListar(request,response);
		    break;
		  case "/ProfesorListarProc":
			  ProfesorListarProc(request,response);
		    break;
		  case "/ProfesorNuevo":
			  ProfesorNuevo(request,response);
		    break;
		  case "/ProfesorNuevoProc":
			  ProfesorNuevoProc(request,response);
		    break;
		  case "/ProfesorEliminar":
			  ProfesorEliminar(request,response);
		    break;
		  case "/ProfesorModificar":
			  ProfesorModificar(request,response);
		    break;
	
		    
		}

	}

	private void ProfesorModificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String	IdProfesor = request.getParameter("IdProfesor");
		try {
		
			   ProfesorService negocioService = new ProfesorService();
			   ProfesorDTO   oProfesorDTO=  negocioService.GetProfesor(IdProfesor);
			   request.setAttribute("IdProfesor", oProfesorDTO.getIdProfesor());
			   request.setAttribute("ApeProfesor", oProfesorDTO.getApeProfesor());
			request.setAttribute("NomProfesor", oProfesorDTO.getNomProfesor());
			request.setAttribute("DirProfesor", oProfesorDTO.getDirProfesor());
			request.setAttribute("TelProfesor", oProfesorDTO.getTelProfesor());
			request.setAttribute("EmailProfesor", oProfesorDTO.getEmailProfesor());
			request.setAttribute("Clave", oProfesorDTO.getClave());

			request.setAttribute("accion", "MODIFICAR");
			
		} catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		 UtilController.forward(request, response, "NuevoProfesor.jsp");


		
	}

	private void ProfesorEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String	IdProfesor = request.getParameter("IdProfesor");	
		try {

			   ProfesorService negocioService = new ProfesorService();
				
			   negocioService.EliminarProfesor(IdProfesor);
			   

		} catch (Exception e) {
		}
	    UtilController.forward(request, response, "ListadoProfesor.jsp");

	}

	private void ProfesorNuevoProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

String	IdProfesor="";
String ApeProfesor="";
String	NomProfesor="";
String	DirProfesor="";
String	TelProfesor="";
String	EmailProfesor="";
String	Clave="";
	
			    
		    try {

		      // Datos
		    
		    	ApeProfesor = request.getParameter("ApeProfesor");
		    	NomProfesor = request.getParameter("NomProfesor");
		    	DirProfesor = request.getParameter("DirProfesor");
		    	TelProfesor = request.getParameter("TelProfesor");
		    	EmailProfesor = request.getParameter("EmailProfesor");
		    	Clave = request.getParameter("Clave");
		    	
		      // Proceso
		    	String S=request.getParameter("accion");
		     ProfesorService negocioService = new ProfesorService();
		    if (S.equals("MODIFICAR")) {
		    	IdProfesor = request.getParameter("IdProfesor");
		    	
		    	negocioService.ModificarProfesor(IdProfesor, ApeProfesor, NomProfesor, DirProfesor, TelProfesor, EmailProfesor, Clave);
				
		    }
		    else {
		    	
		    	negocioService.RegistrarProfesor( ApeProfesor, NomProfesor, DirProfesor, TelProfesor, EmailProfesor, Clave);
				
			}
		      
		      // Mensaje
		      String mensaje = "Proceso ok. ID = " + IdProfesor + ".";
		      request.setAttribute("mensaje", mensaje);

		    } catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		    UtilController.forward(request, response, "ListadoProfesor.jsp");



	}

	private void ProfesorNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  request.setAttribute("accion", "NUEVO");
		 UtilController.forward(request, response, "NuevoProfesor.jsp");

	}

	private void ProfesorListarProc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String DNI = request.getParameter("DNI");
	    
	    // Listas de combos
	    ProfesorService service = new ProfesorService();
	    List<Map<String, Object>> cursos = service.ListarProfesores(DNI);
	    
	    // Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(cursos);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);

	}

	private void ProfesorListar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    UtilController.forward(request, response, "ListadoProfesor.jsp");

	}

}
