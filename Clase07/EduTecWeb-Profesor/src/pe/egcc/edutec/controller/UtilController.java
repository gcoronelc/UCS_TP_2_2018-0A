package pe.egcc.edutec.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilController {

  public static void forward(HttpServletRequest request, 
      HttpServletResponse response, String pagina) 
      throws ServletException, IOException {
    
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher(pagina);
    dispatcher.forward(request, response);
    
  }
  
  
  

}
