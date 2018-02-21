package pe.egcc.edutec.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({
  "/ConAlumnosCursoProgForm", "/Aaaa",
  "/BBB", "/CCC"
})
public class ConsultaController extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, 
	    HttpServletResponse response) 
	    throws ServletException, IOException {
	  
	  String path = request.getServletPath();
	  
	  switch(path){
	  
	  case "/ConAlumnosCursoProgForm":
	    conAlumnosCursoProgForm(request,response);
	    break;
	    
	  
	  
	  }
	  
	  
	 
	}

  private void conAlumnosCursoProgForm(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {
    
    UtilController.forward(request, response, "conAlumnosCursoProg.jsp");
    
  }

}
