package pe.egcc.edutec.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.egcc.edutec.dto.ComboDTO;
import pe.egcc.edutec.service.ComboService;
import pe.egcc.edutec.service.NegocioService;



@WebServlet({"/ProgramarCursoView","/ProgramarCursoProc",
	"/GenerarCicloView", "/GenerarCicloProc",
	"/AnularCursoView","/AnularCursoProc",
	"/AsignarProfesorView",	"/AsignarProfesorProc",
	"/EliminarMatriculaView",	"/EliminarMatriculaCursos","/EliminarMatriculaProc"

})
public class NegocioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		 String path = request.getServletPath();
		  switch (path) {
	    case "/ProgramarCursoView":
	    	programarCursoView(request,response);
	      break;
	    case "/ProgramarCursoProc":
	    	programarCursoProc(request,response);
	      break;
	    case "/AnularCursoView":
	    	AnularCursoView(request, response);
	      break;
	    case "/AsignarProfesorView":
	    	AsignarProfesorView(request, response);
	      break;
	    case "/AsignarProfesorProc":
	    	AsignarProfesorProc(request, response);
	      break;
	      
	    case "/GenerarCicloView":
	    	generarCicloView(request,response);
	        break;
	        
	    case "/GenerarCicloProc":
	    	generarCicloProc(request,response);
	        break;  
	        
	    case "/EliminarMatriculaView":
	    	eliminarMatriculaView(request,response);
	      break;
	        
	    case "/EliminarMatriculaCursos":
	       eliminarMatriculaCursos(request, response);
	        break;  
	        
	        
	    case "/EliminarMatriculaProc":
	    	eliminarMatriculaProc(request, response);
	        break;  
	      
	    
	    }  
	}
	
	
	private void eliminarMatriculaProc(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {

		    String pagina = "eliminarMatricula.jsp";

		    String sAlumno = "";
		    String sCurso = "";

		    int vacantes = 20;

		    try {

		      // Datos
		    	sAlumno = request.getParameter("alumno");
		    	sCurso = request.getParameter("curso");
		 

		      // Proceso
		      NegocioService negocioService = new NegocioService();
		      String id = String.valueOf(negocioService.EliminarCurso(sAlumno, sCurso));

		      // Mensaje
		      String mensaje = "Se elimino la programación de curso IdCursoProg = " + sCurso + ".";
		      request.setAttribute("mensaje", mensaje);

		    } catch (Exception e) {

		      request.setAttribute("error", e.getMessage());

		    }

		    request.setAttribute("alumno", sAlumno);
		    request.setAttribute("curso", sCurso);

		    eliminarMatriculaView(request, response);

		  }
	
	
	
	
	
	  private void eliminarMatriculaCursos(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		    
		    // Dato
		    String alumno = request.getParameter("alumno");
		    
		    // Listas de combos
		    ComboService comboService = new ComboService();
		    List<ComboDTO> cursos = comboService.traerCursosAlumno(alumno);
		    
		    // Crear JSON
		    Gson gson = new Gson();
		    String cursosJson = gson.toJson(cursos);
		    
		    // Respuesta    
		  UtilController.reponseJSON(response, cursosJson);
		    
		  }
	
	
	
	


	private void eliminarMatriculaView(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException, IOException {
  	   
		//Lista de combo 
		ComboService comboService = new ComboService();
		List<ComboDTO> alumnos = comboService.traerAlumnos();
		
		//Enviar lista
		request.setAttribute("alumnos",alumnos);		
			
		String pagina = "eliminarMatricula.jsp";
  	    UtilController.forward(request,response,pagina);
		
	}


	private void programarCursoProc(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String pagina = "programarCurso.jsp";

    String ciclo = "";
    String curso = "";
    String profesor = "";
    String horario = "";
    int vacantes = 20;

    try {

      // Datos
      ciclo = request.getParameter("ciclo");
      curso = request.getParameter("curso");
      profesor = request.getParameter("profesor");
      horario = request.getParameter("horario");
      vacantes = Integer.parseInt(request.getParameter("vacantes"));

      // Proceso
      NegocioService negocioService = new NegocioService();
      int id = negocioService.programarCurso(ciclo, curso, profesor, horario, vacantes);

      // Mensaje
      String mensaje = "Proceso ok. ID = " + id + ".";
      request.setAttribute("mensaje", mensaje);

    } catch (Exception e) {

      request.setAttribute("error", e.getMessage());

    }

    request.setAttribute("ciclo", ciclo);
    request.setAttribute("curso", curso);
    request.setAttribute("profesor", profesor);
    request.setAttribute("horario", horario);
    request.setAttribute("vacantes", vacantes);

    programarCursoView(request, response);

  }

    private void programarCursoView(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Listas de combos
    ComboService comboService = new ComboService();
    List<ComboDTO> ciclos = comboService.traerCiclosActuales();
    List<ComboDTO> cursos = comboService.traerCursos();
    List<ComboDTO> profesores = comboService.traerProfesores();
    
    // Habilitar opcion de cada lista
    UtilController.selectedItemCombo(ciclos, request.getAttribute("ciclo"));
    UtilController.selectedItemCombo(cursos, request.getAttribute("curso"));
    UtilController.selectedItemCombo(profesores, request.getAttribute("profesor"));
    
    
    // Enviar listas de combos
    request.setAttribute("ciclos", ciclos);
    request.setAttribute("cursos", cursos);
    request.setAttribute("profesores", profesores);

        
    String pagina = "programarCurso.jsp";
    UtilController.forward(request, response, pagina);

  }

    private void generarCicloProc(HttpServletRequest request, 
  	      HttpServletResponse response) 
  	      throws ServletException, IOException {
  	    
  	    String pagina = "generarNuevoCiclo.jsp";
  	    
  	    try {
  	      
  	      // Datos
  	   //   String cicloNuevo = request.getParameter("cicloNuevo");
  	    //   Date FechaInicio = request.getParameter("FecInicio");
  	    //   Date FechaTermino = request.getParameter("FecTermino");
  	      
  	      Date FechaInicio = null;
  	      Date FechaTermino= null;
  	      
  	      
  	     
  	      
  	      // Proceso
  	      NegocioService negocioService = new NegocioService();
  	      String sId_cicloNuevo = negocioService.generarNuevoCiclo(FechaInicio, FechaTermino);
  	      
  	      // Mensaje
  	      String mensaje = "Proceso ok. ID = " + sId_cicloNuevo + ".";
  	      request.setAttribute("mensaje", mensaje);
  	      
  	    } catch (Exception e) {
  	      
  	      request.setAttribute("error", e.getMessage());
  	      
  	    }
  	    
  	    UtilController.forward(request, response, pagina);
  	        
  	  }

    private void generarCicloView(HttpServletRequest request, 
  	      HttpServletResponse response) 
  	      throws ServletException, IOException {
  	    
  	    String pagina = "generarNuevoCiclo.jsp";
  	    UtilController.forward(request,response,pagina);
  	    
  	  }
  		
  	
	private void AsignarProfesorProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Pagina="AsignarProfesor.jsp";
		try {
			 String IdCursoProg = request.getParameter("IdCursoProg");		
		      String IdProfesor = request.getParameter("IdProfesor");
			NegocioService oNegocioService=new NegocioService();
			
			int Validar=oNegocioService.Validar(IdCursoProg, IdProfesor);
			
			if	(Validar>=3){
				  String mnesa="El Profesor se encuentra en 3 cursos o mas en este Siglo";
				  request.setAttribute("mensaje", mnesa);
			      UtilController.forward(request, response, Pagina);
			}else	
			{
				
				
				oNegocioService.AsignarProfesor(IdCursoProg, IdProfesor);
			      String mnesa="Se Realizo el cambio exitosamente ";
			      
			      request.setAttribute("mensaje", mnesa);
			      UtilController.forward(request, response, Pagina);
				
			}
			
			
			
		
		} catch (Exception e) {
				String mnesa="ERROR NO GRABO";
		      
		      request.setAttribute("error", mnesa);
			
			UtilController.forward(request, response, Pagina);
		}
		
		
	}
	private void AnularCursoView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Pagina="/AnularCurso.jsp";
		UtilController.forward(request,response,Pagina);
		
		
	}
	private void AsignarProfesorView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Pagina="/AsignarProfesor.jsp";
		UtilController.forward(request,response,Pagina);
		
		
	}
}
