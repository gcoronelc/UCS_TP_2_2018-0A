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
import pe.egcc.edutec.service.ComboService;
import pe.egcc.edutec.service.ConsultaService;

@WebServlet({
  "/ConAlumnosCursoProgForm", "/ComboAlumnosCursoProg",
  "/ConAlumnosCursoProgProc", "/CCC"
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
	    
	  case "/ComboAlumnosCursoProg":
	    comboAlumnosCursoProg(request,response);
      break;	 
      
	  case "/ConAlumnosCursoProgProc":
	    conAlumnosCursoProgProc(request,response);
      break;  
	  
	  }
	  
	  
	 
	}

  private void conAlumnosCursoProgProc(HttpServletRequest request, 
      HttpServletResponse response) throws IOException {
    // Dato
    int cursoprog = Integer.parseInt(request.getParameter("curso"));
    
    // Listas de combos
    ConsultaService service = new ConsultaService();
    List<Map<String, Object>> cursos = service.conAlumnosCursoPrg(cursoprog);
    
    // Crear JSON
    Gson gson = new Gson();
    String cursosJson = gson.toJson(cursos);
    
    // Respuesta    
    UtilController.reponseJSON(response, cursosJson);
  }

  private void comboAlumnosCursoProg(HttpServletRequest request, 
      HttpServletResponse response) throws IOException {
    // Dato
    String ciclo = request.getParameter("ciclo");
    
    // Listas de combos
    ComboService comboService = new ComboService();
    List<ComboDTO> cursos = comboService.traerCursosPorCiclo(ciclo);
    
    // Crear JSON
    Gson gson = new Gson();
    String cursosJson = gson.toJson(cursos);
    
    // Respuesta    
    UtilController.reponseJSON(response, cursosJson);
  }

  private void conAlumnosCursoProgForm(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {
    
    UtilController.forward(request, response, "conAlumnosCursoProg.jsp");
    
  }

}
