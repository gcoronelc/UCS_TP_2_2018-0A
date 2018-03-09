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

import pe.egcc.edutec.dto.AlumnoDTO;


import pe.egcc.edutec.service.AlumnoService;



/**
 * Servlet implementation class ProfesorController
 */
@WebServlet({ "/AlumnoListar", "/AlumnoListarProc", "/AlumnoNuevo", "/AlumnoNuevoProc",
	"/AlumnoModificar", "/AlumnoEliminar" })
public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String path = request.getServletPath(); 
		switch(path){		  
		  case "/AlumnoListar":
			  AlumnoListar(request,response);
		    break;
		  case "/AlumnoListarProc":
			  AlumnoListarProc(request,response);
		    break;
		  case "/AlumnoNuevo":
			  AlumnoNuevo(request,response);
		    break;
		  case "/AlumnoNuevoProc":
			  AlumnoNuevoProc(request,response);
		    break;
		  case "/AlumnoEliminar":
			  AlumnoEliminar(request,response);
		    break;
		  case "/AlumnoModificar":
			  AlumnoModificar(request,response);
		    break;
	
		    
		}

	}

	private void AlumnoModificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String	IdAlumno = request.getParameter("IdAlumno");
		try {
		
			AlumnoService negocioService = new AlumnoService();
			AlumnoDTO   oAlumnoDTO =  negocioService.GetAlumno(IdAlumno);
			   request.setAttribute("IdAlumno", oAlumnoDTO.getIdAlumno());
			   request.setAttribute("ApeAlumno", oAlumnoDTO.getApeAlumno());
			request.setAttribute("NomAlumno", oAlumnoDTO.getNomAlumno());
			request.setAttribute("DirAlumno", oAlumnoDTO.getDirAlumno());
			request.setAttribute("TelAlumno", oAlumnoDTO.getTelAlumno());
			request.setAttribute("EmailAlumno", oAlumnoDTO.getEmailAlumno());
			request.setAttribute("Clave", oAlumnoDTO.getClave());

			request.setAttribute("accion", "MODIFICAR");
			
		} catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		 UtilController.forward(request, response, "NuevoAlumno.jsp");


		
	}

	private void AlumnoEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String	IdAlumno= request.getParameter("IdAlumno");	
		try {

			AlumnoService negocioService = new AlumnoService();
				
			   negocioService.EliminarAlumno(IdAlumno);
			
			   String mensaje = "Eliminacion correcta";
			      request.setAttribute("mensaje", mensaje);


		} catch (Exception e) {
		}
	    UtilController.forward(request, response, "ListadoAlumno.jsp");

	}

	private void AlumnoNuevoProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

String	IdAlumno="";
String ApeAlumno="";
String	NomAlumno="";
String	DirAlumno="";
String	TelAlumno="";
String	EmailAlumno="";
String	Clave="";
	
			    
		    try {

		      // Datos
		    
		    	ApeAlumno = request.getParameter("ApeAlumno");
		    	NomAlumno = request.getParameter("NomAlumno");
		    	DirAlumno = request.getParameter("DirAlumno");
		    	TelAlumno = request.getParameter("TelAlumno");
		    	EmailAlumno = request.getParameter("EmailAlumno");
		    	Clave = request.getParameter("Clave");
		    	
		      // Proceso
		    	String S=request.getParameter("accion");
		    	AlumnoService negocioService = new AlumnoService();
		    if (S.equals("MODIFICAR")) {
		    	IdAlumno = request.getParameter("IdAlumno");
		    	
		    	negocioService.ModificarAlumno(IdAlumno, ApeAlumno, NomAlumno, DirAlumno, TelAlumno, EmailAlumno, Clave);
				
		    }
		    else {
		    	
		    	negocioService.RegistrarAlumno( ApeAlumno, NomAlumno, DirAlumno, TelAlumno, EmailAlumno, Clave);
				
			}
		      
		      // Mensaje
		      String mensaje = "Proceso ok. Nombre Alumnos = " + NomAlumno + ".";
		      request.setAttribute("mensaje", mensaje);

		    } catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		    UtilController.forward(request, response, "ListadoAlumno.jsp");



	}

	private void AlumnoNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  request.setAttribute("accion", "NUEVO");
		 UtilController.forward(request, response, "NuevoAlumno.jsp");

	}

	private void AlumnoListarProc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String DNI = request.getParameter("DNI");
	    
	    // Listas de combos
		AlumnoService service = new AlumnoService();
	    List<Map<String, Object>> cursos = service.ListarAlumno(DNI);
	    
	    // Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(cursos);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);

	}

	private void AlumnoListar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    UtilController.forward(request, response, "ListadoAlumno.jsp");

	}

}
