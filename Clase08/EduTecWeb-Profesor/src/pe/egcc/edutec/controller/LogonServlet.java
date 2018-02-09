package pe.egcc.edutec.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.edutec.dto.EmpleadoDTO;
import pe.egcc.edutec.service.LogonService;

@WebServlet( {"/Ingresar","/Salir"})
public class LogonServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, 
	    HttpServletResponse response) 
	    throws ServletException, IOException {
	  
	  String path = request.getServletPath();
	  switch (path) {
    case "/Ingresar":
      ingresar(request,response);
      break;
    case "/Salir":
      salir(request,response);
      break;
    }  
	  
	}

  private void ingresar(HttpServletRequest request, 
      HttpServletResponse response) 
      throws ServletException, IOException {
    String destino;
    try {
      // Datos
      String codigo = request.getParameter("codigo");
      String clave = request.getParameter("clave");
      // Procesar
      LogonService logonService = new LogonService();
      EmpleadoDTO empleadoDTO;
      empleadoDTO = logonService.validarUsuario(codigo, clave);
      destino = "main.jsp";
    } catch (Exception e) {
      request.setAttribute("error", e.getMessage());
      destino = "index.jsp";
    }
    // Forward
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher(destino);
    dispatcher.forward(request, response);
  }

  private void salir(HttpServletRequest request, HttpServletResponse response) {
    
    
  }

	
	
}
