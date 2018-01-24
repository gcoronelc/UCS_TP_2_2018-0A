package pe.egcc.proyecto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.proyecto.service.ProyectoService;

@WebServlet("/ProyectoController")
public class ProyectoController extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // Datos
	  int n1 = Integer.parseInt(request.getParameter("num1"));
	  int n2 = Integer.parseInt(request.getParameter("num2"));
	  
    // Proceso
    ProyectoService proyectoService = new ProyectoService();
    int suma = proyectoService.calcSuma(n1, n2);
    int producto = proyectoService.calcProducto(n1, n2);
	  
	  // Reporte
    request.setAttribute("n1", n1);
    request.setAttribute("n2", n2);
    request.setAttribute("suma", suma);
    request.setAttribute("producto", producto);
    
    // Forward
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("index.jsp");
    dispatcher.forward(request, response);
    
	}

}
