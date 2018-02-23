package pe.egcc.edutec.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.edutec.dto.ComboDTO;

public class UtilController {

  public static void forward(HttpServletRequest request, 
      HttpServletResponse response, String pagina) 
      throws ServletException, IOException {
    
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher(pagina);
    dispatcher.forward(request, response);
    
  }
  
  
  public static void reponseJSON(HttpServletResponse response, String json) 
      throws IOException{
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(json);
  }
  
  
  public static void selectedItemCombo(List<ComboDTO> lista, Object code){
    if( code == null){
      return;
    }
    for(ComboDTO dto: lista){
      if(dto.getCode().equals(code.toString())){
        dto.setSelected(1);
      }
    }
    
  }
  

}
