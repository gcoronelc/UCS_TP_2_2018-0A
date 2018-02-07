package pe.egcc.edutec.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.edutec.service.NegocioService;


@WebServlet({ 
  "/ProgramarCursoView", "/ProgramarCursoProc", 
  "/GenerarCicloView", "/GenerarCicloProc",
  "/AaaaaView", "/AaaaaProc"})
public class NegocioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  String path = request.getServletPath();
	  
	  switch (path) {
	  
    case "/ProgramarCursoView":
      programarCursoView(request,response);
      break;
      
    case "/ProgramarCursoProc":
      programarCursoProc(request,response);
      break;      


    }
	  
	  
	}

  private void programarCursoProc(HttpServletRequest request, 
      HttpServletResponse response) 
      throws ServletException, IOException {
    
    String pagina = "programarCurso.jsp";
    
    try {
      
      // Datos
      String ciclo = request.getParameter("ciclo");
      String curso = request.getParameter("curso");
      String profesor = request.getParameter("profesor");
      String horario = request.getParameter("horario");
      int vacantes = Integer.parseInt(request.getParameter("vacantes"));
      
      // Proceso
      NegocioService negocioService = new NegocioService();
      int id = negocioService.programarCurso(ciclo, curso, profesor, horario, vacantes);
      
      // Mensaje
      String mensaje = "Proceso ok. ID = " + id + ".";
      request.setAttribute("mensaje", mensaje);
      
    } catch (Exception e) {
      
      request.setAttribute("error", e.getMessage());
      
    }
    
    UtilController.forward(request, response, pagina);
        
  }

  private void programarCursoView(HttpServletRequest request, 
      HttpServletResponse response) 
      throws ServletException, IOException {
    
    String pagina = "programarCurso.jsp";
    UtilController.forward(request,response,pagina);
    
  }
	
	
	
	

}
