package pe.egcc.edutec.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.edutec.service.NegocioService;


@WebServlet({"/ProgramarCursoView","/ProgramarCursoProc","/AnularCursoView","/AnularCursoProc",
	"/AsignarProfesorView",	"/AsignarProfesorProc"
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
	    	ProgramarCursoView(request,response);
	      break;
	    case "/ProgramarCursoProc":
	    	ProgramarCursoProc(request,response);
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
	    
	    }  
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
			      Utilcontroller.forward(request, response, Pagina);
			}else	
			{
				
				
				oNegocioService.AsignarProfesor(IdCursoProg, IdProfesor);
			      String mnesa="Se Realizo el cambio exitosamente ";
			      
			      request.setAttribute("mensaje", mnesa);
			      Utilcontroller.forward(request, response, Pagina);
				
			}
			
			
			
		
		} catch (Exception e) {
				String mnesa="ERROR NO GRABO";
		      
		      request.setAttribute("error", mnesa);
			
			Utilcontroller.forward(request, response, Pagina);
		}
		
		
	}

	private void ProgramarCursoProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String Pagina="ProgramarCurso.jsp";
		try {
			 String ciclo = request.getParameter("ciclo");
		      String curso = request.getParameter("curso");
		      String profesor = request.getParameter("profesor");
		      String horario = request.getParameter("horario");
		      int vacantes =Integer.parseInt(request.getParameter("vacantes"));
		      
			NegocioService oNegocioService=new NegocioService();
		int Codigo=	oNegocioService.programarCurso(ciclo, curso, profesor, horario, vacantes);
		      String mnesa="Grabo exitosamente Codigo="+Codigo;
		      
		      request.setAttribute("mensaje", mnesa);
		      Utilcontroller.forward(request, response, Pagina);
		} catch (Exception e) {
				String mnesa="ERROR NO GRABO";
		      
		      request.setAttribute("error", mnesa);
			
			Utilcontroller.forward(request, response, Pagina);
		}
	}

	private void ProgramarCursoView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		
		String Pagina="/ProgramarCurso.jsp";
		Utilcontroller.forward(request,response,Pagina);
		
	}

	
	private void AnularCursoView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Pagina="/AnularCurso.jsp";
		Utilcontroller.forward(request,response,Pagina);
		
		
	}
	private void AsignarProfesorView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Pagina="/AsignarProfesor.jsp";
		Utilcontroller.forward(request,response,Pagina);
		
		
	}
}
