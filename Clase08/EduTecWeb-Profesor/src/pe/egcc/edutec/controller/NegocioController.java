package pe.egcc.edutec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.edutec.dto.ComboDTO;
import pe.egcc.edutec.service.ComboService;
import pe.egcc.edutec.service.NegocioService;

@WebServlet({ "/ProgramarCursoView", "/ProgramarCursoProc", "/GenerarCicloView", "/GenerarCicloProc", "/AaaaaView",
    "/AaaaaProc" })
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

    }

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

}
