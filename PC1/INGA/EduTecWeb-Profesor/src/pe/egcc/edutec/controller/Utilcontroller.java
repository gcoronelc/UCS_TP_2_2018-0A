package pe.egcc.edutec.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utilcontroller {

	public static void forward(HttpServletRequest request, HttpServletResponse response,String Pagina) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	RequestDispatcher dispatcher;
	dispatcher=request.getRequestDispatcher(Pagina);
	dispatcher.forward(request, response);
	}
	
	

}
