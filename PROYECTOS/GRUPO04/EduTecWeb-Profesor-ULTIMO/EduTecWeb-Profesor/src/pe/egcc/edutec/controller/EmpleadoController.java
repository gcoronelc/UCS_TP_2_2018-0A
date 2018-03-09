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
import pe.egcc.edutec.dto.EmpleadoDTO;
import pe.egcc.edutec.service.AlumnoService;
import pe.egcc.edutec.service.EmpleadoService;



/**
 * Servlet implementation class ProfesorController
 */
@WebServlet({ "/EmpleadoListar", "/EmpleadoListarProc", "/EmpleadoNuevo", "/EmpleadoNuevoProc",
	"/EmpleadoModificar", "/EmpleadoEliminar" })
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String path = request.getServletPath(); 
		switch(path){		  
		  case "/EmpleadoListar":
			  EmpleadoListar(request,response);
		    break;
		  case "/EmpleadoListarProc":
			  EmpleadoListarProc(request,response);
		    break;
		  case "/EmpleadoNuevo":
			  EmpleadoNuevo(request,response);
		    break;
		  case "/EmpleadoNuevoProc":
			  EmpleadoNuevoProc(request,response);
		    break;
		  case "/EmpleadoEliminar":
			  EmpleadoEliminar(request,response);
		    break;
		  case "/EmpleadoModificar":
			  EmpleadoModificar(request,response);
		    break;
	
		    
		}

	}

	private void EmpleadoModificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String	IdEmpleado = request.getParameter("IdEmpleado");
		try {
		
			EmpleadoService negocioService = new EmpleadoService();
			EmpleadoDTO   oEmpleadoDTO =  negocioService.GetEmpleado(IdEmpleado);
			   request.setAttribute("IdEmpleado", oEmpleadoDTO.getCodigo());
				request.setAttribute("Clave", oEmpleadoDTO.getClave());
				request.setAttribute("ApeEmpleado", oEmpleadoDTO.getApellido());
				request.setAttribute("NomEmpleado", oEmpleadoDTO.getNombre());
			   request.setAttribute("Cargo", oEmpleadoDTO.getCargo());
			request.setAttribute("DirEmpleado", oEmpleadoDTO.getDireccion());
			request.setAttribute("TelEmpleado", oEmpleadoDTO.getTelefono());
			request.setAttribute("EmailEmpleado", oEmpleadoDTO.getEmail());
		
			request.setAttribute("accion", "MODIFICAR");
			
		} catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		 UtilController.forward(request, response, "NuevoEmpleado.jsp");


		
	}

	private void EmpleadoEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String	IdEmpleado= request.getParameter("IdEmpleado");	
		try {

			EmpleadoService negocioService = new EmpleadoService();
				
			   negocioService.EliminarEmpleado(IdEmpleado);
			   

		} catch (Exception e) {
		}
	    UtilController.forward(request, response, "ListadoEmpleado.jsp");

	}

	private void EmpleadoNuevoProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
String	IdEmpleado="";
String	Clave="";
String ApeEmpleado="";
String	NomEmpleado="";
String	Cargo="";
String	DirEmpleado="";
String	TelEmpleado="";
String	EmailEmpleado="";

			    
		    try {

		      // Datos
		    	IdEmpleado = request.getParameter("IdEmpleado");
		    	Clave = request.getParameter("Clave");
		    	ApeEmpleado = request.getParameter("ApeEmpleado");
		    	NomEmpleado = request.getParameter("NomEmpleado");
		    	Cargo = request.getParameter("Cargo");
		    	DirEmpleado = request.getParameter("DirEmpleado");
		    	TelEmpleado = request.getParameter("TelEmpleado");
		    	EmailEmpleado = request.getParameter("EmailEmpleado");
		    	
		      // Proceso
		    	String S=request.getParameter("accion");
		    	EmpleadoService negocioService = new EmpleadoService();
		    if (S.equals("MODIFICAR")) {
		    	
		    	negocioService.ModificarEmpleado(IdEmpleado, Clave, ApeEmpleado, NomEmpleado, Cargo, DirEmpleado, TelEmpleado, EmailEmpleado);
				
		    }
		    else {
		    	
		    	negocioService.RegistrarEmpleado(IdEmpleado, Clave, ApeEmpleado, NomEmpleado, Cargo, DirEmpleado, TelEmpleado, EmailEmpleado);
				
			}
		      
		      // Mensaje
		      String mensaje = "Proceso ok. ID = " + IdEmpleado + ".";
		      request.setAttribute("mensaje", mensaje);

		    } catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }
		    UtilController.forward(request, response, "ListadoEmpleado.jsp");



	}

	private void EmpleadoNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  request.setAttribute("accion", "NUEVO");
		 UtilController.forward(request, response, "NuevoEmpleado.jsp");

	}

	private void EmpleadoListarProc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String DNI = request.getParameter("DNI");
	    
	    // Listas de combos
		EmpleadoService service = new EmpleadoService();
	    List<Map<String, Object>> cursos = service.ListaEmpleado("");
	    
	    // Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(cursos);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);

	}

	private void EmpleadoListar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    UtilController.forward(request, response, "ListadoEmpleado.jsp");

	}

}
