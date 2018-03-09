package pe.egcc.edutec.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.egcc.edutec.dto.ComboDTO;
import pe.egcc.edutec.service.ComboService;
import pe.egcc.edutec.service.NegocioService;

@WebServlet({ 
  "/ProgramarCursoView", "/ProgramarCursoProc", 
  //"/GenerarCicloView", "/GenerarCicloProc",
  "/GenerarCicloView","/GenerarCicloProc",
  "/EliminarMatriculaView", "/EliminarMatriculaCursos", "/EliminarMatriculaProc" })
public class NegocioController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String path = request.getServletPath();

    switch (path) {

    case "/ProgramarCursoView":
      programarCursoView(request, response);
      break;

    case "/ProgramarCursoProc":
      programarCursoProc(request, response);
      break;

    case "/EliminarMatriculaView":
      eliminarMatriculaView(request, response);
      break;
      
    case "/EliminarMatriculaCursos":
      eliminarMatriculaCursos(request, response);
      break;
    
    case "/GenerarCicloView":
        GenerarCicloView(request, response);
        break;
    case "/GenerarCicloProc":
        GenerarCicloProc(request, response);
        break;
   /* case "/RegistrarNotaProc":
    	RegistrarNotaProc(request, response);
        break;
    case "/RegistrarNotaView":
    	RegistrarNotaView(request, response);
        break;*/
    }
  }

  private void eliminarMatriculaCursos(HttpServletRequest request, HttpServletResponse response) 
      throws IOException 
  { 
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
      HttpServletResponse response) throws ServletException, IOException {
    
    // Listas de combos
    ComboService comboService = new ComboService();
    List<ComboDTO> alumnos = comboService.traerAlumnos();
    
    // Enviar listas
    request.setAttribute("alumnos", alumnos);
    
    String pagina = "eliminarMatricula.jsp";
    UtilController.forward(request, response, pagina);
  }

  

  private void GenerarCicloProc(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

	    String pagina = "registrarCiclo.jsp";
	    String ciclo = "";
	    String fechaini = null;
	    String fechafin = null;
	    try {

	      // Datos
	      ciclo = request.getParameter("ciclo");
	      String inicio = request.getParameter("fechaini");
	      String fin = request.getParameter("fechafin");
	      
	      
	      // Proceso
	      NegocioService negocioService = new NegocioService();
	      String id = negocioService.GenerarCiclo(ciclo,inicio, fin);

	      // Mensaje
	      String mensaje = "Proceso ok. ID = " + id + ".";
	      request.setAttribute("mensaje", mensaje);

	    } catch (Exception e) {

	      request.setAttribute("error", e.getMessage());

	    }

	    request.setAttribute("ciclo", ciclo);
		request.setAttribute("fechainicio", fechaini);
	    request.setAttribute("fechafin", fechafin);

	    programarCursoView(request, response);

	  }
/*
  private void RegistrarNotaProc(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

	    String pagina = "registrarNota.jsp";
	    int cursoprog = 0;
	    String alumno = "";
	    String fechamatricula = "";
	    Double examenparcial = null;
	    Double examenfinal = null;
	    Double promedio = null;
	    Double subsanacion = null;
	    Double exsubsanacion = null;

	    try {

	      // Datos
	    	cursoprog = Integer.parseInt(request.getParameter("ciclo"));
	    	alumno = request.getParameter("alumno");
	    	fechamatricula = request.getParameter("fechamatricula");
	    	examenparcial = Double.parseDouble(request.getParameter("examenparcial")); 
	    	examenfinal = Double.parseDouble(request.getParameter("examenfinal")); 
	    	promedio = Double.parseDouble(request.getParameter("promedio")); 
	    	subsanacion = Double.parseDouble(request.getParameter("subsanacion"));
	    	exsubsanacion = Double.parseDouble(request.getParameter("exsubsanacion"));
	    	
	      // Proceso
	      NegocioService negocioService = new NegocioService();
	      int id = negocioService.registrarNota(ciclo, curso, profesor, horario, vacantes);

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
  */
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

  private void GenerarCicloView(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException 
	  {
	
	    String pagina = "registrarCiclo.jsp";
	    UtilController.forward(request, response, pagina);

	  }
  
  private void programarCursoView(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException 
  {
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

}
