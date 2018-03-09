package pe.egcc.edutec.controller;

import java.io.IOException;
import java.text.DecimalFormat;
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
import pe.egcc.edutec.service.NegocioService;

@WebServlet({
  "/ConAlumnosCursoProgForm", "/ComboAlumnosCursoProg", "/ComboCurso" , "/ingresarNota", "/mantenimientoEmpleadoView","/actualizarEmpleado",
  "/ConAlumnosCursoProgProc", "/CCC", "/ConCursosProfForm", "/registrarNotas" , "/listarEmpleado","/registrarEmpleado","/eliminarEmpleado",
  "/ConCursosProfFormProc", "/ConCicloxPeriodoForm", "/ConMejorAlumno" , "/consultaFinancieraCiclo" , "/consultaFinancieraCicloForm"
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
	  case "/ComboCurso":
		  comboCurso(request,response);
	      break;
      
	  case "/ConAlumnosCursoProgProc":
	    conAlumnosCursoProgProc(request,response);
      break;  
      
	  case "/ConCursosProfForm":
		  conCursosProfForm (request,response);
	  break;
	  
	  case "/ConCicloxPeriodoForm":
		  conCicloxPeriodoForm (request,response);
	  break;
	  
	  case "/ConMejorAlumno":
		  conMejorAlumno (request,response);
	  break;
	  case "/consultaFinancieraCicloForm":
		  consultaFinancieraCicloForm (request,response);
	  break;
	  case "/consultaFinancieraCiclo":
		  consultaFinancieraCiclo (request,response);
	  break;
	  case "/registrarNotas":
		  registrarNotas (request,response);
	  break;
	  case "/ingresarNota":
		  ingresarNota(request,response);
	  break;
	  
	  case "/mantenimientoEmpleadoView":
		  mantenimientoEmpleadoView(request,response);
	  break;
	  case "/listarEmpleado":
		  listarEmpleado(request,response);
	  break;
	  case "/registrarEmpleado":
		  registrarEmpleado(request,response);
	  break;
	  
	  case "/actualizarEmpleado":
		  actualizarEmpleado(request,response);
	  break;
	  
	  case "/eliminarEmpleado":
		  eliminarEmpleado(request,response);
	  break;
	  } 	  
	}
	

	  private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		    
		    // Dato
		    String codigo = request.getParameter("codigo");
		    
		    // Listas de combos
			  ConsultaService service = new ConsultaService();
			  String mensaje = service.eliminarEmpleado(codigo);
			  codigo ="";
			   List<Map<String, Object>> emp = service.consultaEmpleados(codigo);
				// Crear JSON
				    Gson gson = new Gson();
				    String cursosJson = gson.toJson(emp);
				    
				    if(mensaje != "0"){
		 				   request.setAttribute("mensaje", mensaje);
					   }
				    
				    // Respuesta    
		  UtilController.reponseJSON(response, cursosJson);
		  }
	
	
	  private void registrarEmpleado(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		    
		    // Dato
		    String codigo = request.getParameter("codigo");
		    String clave = request.getParameter("clave");
		    String nombre = request.getParameter("nombre");
		    String apellido = request.getParameter("apellido");
		    String cargo = request.getParameter("cargo");
		    String direccion = request.getParameter("direccion");
		    String telefono = request.getParameter("telefono");
		    String email = request.getParameter("email");
		    
		    // Listas de combos
			  ConsultaService service = new ConsultaService();
			  String mensaje = service.registrarEmpleado(codigo,clave,nombre,apellido,cargo,direccion,telefono,email  );
			  
			   List<Map<String, Object>> emp = service.consultaEmpleados(codigo);
				// Crear JSON
				    Gson gson = new Gson();
				    String cursosJson = gson.toJson(emp);
				    
				    if(mensaje != "0"){
		 				   request.setAttribute("mensaje", mensaje);
					   }
				    
				    // Respuesta    
		  UtilController.reponseJSON(response, cursosJson);
		  }
	

	  private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		    
		    // Dato
		    String codigo = request.getParameter("codigo");
 		    String clave = request.getParameter("clave");
		    String nombre = request.getParameter("nombre");
		    String apellido = request.getParameter("apellido");
		    String cargo = request.getParameter("cargo");
		    String direccion = request.getParameter("direccion");
		    String telefono = request.getParameter("telefono");
		    String email = request.getParameter("email");
		    
		    // Listas de combos
			  ConsultaService service = new ConsultaService();
			  String mensaje = service.actualizarEmpleado(codigo,clave,nombre,apellido,cargo,direccion,telefono,email  );
			   if(mensaje != "0"){
				   
			   }
			   List<Map<String, Object>> emp = service.consultaEmpleados(codigo);
				// Crear JSON
				    Gson gson = new Gson();
				    String cursosJson = gson.toJson(emp);
				    
				    // Respuesta    
		  UtilController.reponseJSON(response, cursosJson);
		  }
	

	  private void ingresarNota(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
	    
	    // Dato
	    String alumno = request.getParameter("alumno");
	    int curso =  Integer.parseInt(request.getParameter("curso"));
	    int parcial = Integer.parseInt(request.getParameter("parcial"));
	    int finall = Integer.parseInt(request.getParameter("final"));
	    int sub = Integer.parseInt(request.getParameter("subsanacion"));
	    
     
	    double parcial2 = Integer.parseInt(request.getParameter("parcial"));
	    double finall2 = Integer.parseInt(request.getParameter("final"));
	    double sub2 = Integer.parseInt(request.getParameter("subsanacion"));
	    int promedio = parcial + finall + sub;
	    int s = promedio/3;
	    double pr = s;
	  //  curso = 8241;
	   // curso+= curso++;
	    
	    
	    // Listas de combos
		  ConsultaService service = new ConsultaService();
		  String mensaje = service.insertarNota(alumno , curso , parcial2 , finall2 ,sub2 ,pr );
		   if(mensaje != "0"){
			   
		   }
	    // Crear JSON
	    Gson gson = new Gson();
	   // String cursosJson = gson.toJson(cursos);
	    
	    // Respuesta    
	   //s UtilController.reponseJSON(response, cursosJson);
	    
	  }
	
	 
	  private void comboCurso(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
	    
	    // Dato
	    String alumno = request.getParameter("alumno");
	    
	    // Listas de combos
	    ComboService comboService = new ComboService();
	    List<ComboDTO> cursos = comboService.consultaCurso(alumno);
	    
	    // Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(cursos);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);
	    
	  }
	

	  private void consultaFinancieraCiclo(HttpServletRequest request, 
	      HttpServletResponse response) throws IOException {
	    // Dato
		  String alumno = request.getParameter("alumno");
		    
		    // Listas de los financieros
		  ConsultaService service = new ConsultaService();
		   List<Map<String, Object>> cursos = service.consultaFinanciero(alumno);
	    
	    // Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(cursos);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);
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
    String alumno = request.getParameter("alumno");
    
    // Listas de combos
    ConsultaService service = new ConsultaService();
    List<Map<String, Object>> cursos = service.consultaFinanciero(alumno);
    
    // Crear JSON
    Gson gson = new Gson();
    String cursosJson = gson.toJson(cursos);
    
    // Respuesta    
    UtilController.reponseJSON(response, cursosJson);
  }
  

  private void comboCurso2(HttpServletRequest request, 
      HttpServletResponse response) throws IOException {

	    // Dato
	    String alumno = request.getParameter("alumno");
	    
	    // Listas de combos
	    ComboService comboService = new ComboService();
	    List<ComboDTO> cursos = comboService.consultaCurso(alumno);
	    
	    // Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(cursos);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);
  }
  

  private void registrarNotas(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String pagina = "registrarNotas.jsp";

    String curso = "";
    String alumno = "";
    int idalumno = 0;
    try {

      // Datos
//       curso = request.getParameter("curso");
       alumno =  request.getParameter("alumno");
    } catch (Exception e) {

      request.setAttribute("error", e.getMessage());

    }

    request.setAttribute("alumno", alumno);
//    request.setAttribute("curso", curso);
 
    programarCursoView(request, response);

  }

  private void programarCursoView(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Listas de combos
    ComboService comboService = new ComboService();
//   String idalumno =  request.getParameter("alumno");
    
    List<ComboDTO> alumno = comboService.consultaAlumno();
//    List<ComboDTO> curso = comboService.consultaCurso(idalumno);
    
    // Habilitar opcion de cada lista
    UtilController.selectedItemCombo(alumno, request.getAttribute("alumno"));
//    UtilController.selectedItemCombo(curso, request.getAttribute("curso"));
    
    // Enviar listas de combos
    request.setAttribute("alumno", alumno);
//    request.setAttribute("curso", curso);
//    request.setAttribute("cursos", cursos);
//    request.setAttribute("profesores", profesores);

    String pagina = "registrarNotas.jsp";
    UtilController.forward(request, response, pagina);

  }
  
//  private void registrarNotas(HttpServletRequest request, 
//	      HttpServletResponse response) throws IOException, ServletException {
//	  
//	    
//	    // Listas de combos
//	    ComboService service = new ComboService();
//	    List<ComboDTO> listaAlumno = service.consultaAlumno();
//	    
//	    // Crear JSON
//	    Gson gson = new Gson();
//	    String cursosJson = gson.toJson(listaAlumno);
//	    
//	    // Respuesta    
//	    UtilController.reponseJSON(response, cursosJson);
//	    UtilController.forward(request, response, "registrarNotas.jsp");
//	  }


  private void conAlumnosCursoProgForm(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {
    
    UtilController.forward(request, response, "conAlumnosCursoProg.jsp");
    
  }
  

  private void listarEmpleado(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

	 String codigo =  request.getParameter("codigo");
	  
	  ConsultaService service = new ConsultaService();
	   List<Map<String, Object>> emp = service.consultaEmpleados(codigo);
	// Crear JSON
	    Gson gson = new Gson();
	    String cursosJson = gson.toJson(emp);
	    
	    // Respuesta    
	    UtilController.reponseJSON(response, cursosJson);

  }
  
  private void mantenimientoEmpleadoView(HttpServletRequest request, 
	      HttpServletResponse response) throws ServletException, IOException {
	    
	    UtilController.forward(request, response, "mantenimientoEmpleadoView.jsp");
	    
	  }
  
  
  private void conCursosProfForm(HttpServletRequest request, 
	      HttpServletResponse response) throws ServletException, IOException {
	    
	    UtilController.forward(request, response, "conCursosProfForm.jsp");
	    
	  }
  
  private void consultaFinancieraCicloForm(HttpServletRequest request, 
	      HttpServletResponse response) throws ServletException, IOException {
	    
	    UtilController.forward(request, response, "consultaFinancieraCicloForm.jsp");
  }
  
  private void conCicloxPeriodoForm(HttpServletRequest request, 
	      HttpServletResponse response) throws ServletException, IOException {
	    
	    UtilController.forward(request, response, "conCicloxPeriodoForm.jsp");
  }
  
  private void conMejorAlumno(HttpServletRequest request, 
	      HttpServletResponse response) throws ServletException, IOException {
	    
	    UtilController.forward(request, response, "conMejorAlumno.jsp");
	    
}
}
