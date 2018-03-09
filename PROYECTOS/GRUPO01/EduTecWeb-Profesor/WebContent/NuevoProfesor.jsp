<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
	  <link href="css/menu.css" rel="stylesheet" type="text/css"/>
	  <title>Main</title>
  </head>
     <div class="page">
            <div class="header">
                <jsp:include page="header.jsp"/>
            </div>
            <div>
                <jsp:include page="menu.jsp"/>
            </div>
      
      <div class="contenido">
      
        <h1>NUEVO TARIFA</h1>     
               
        <c:if test="${mensaje != null}">
          <div class="msgInfo">${mensaje}</div>
        </c:if>
        
        <c:if test="${error != null}">
          <div class="msgError">${error}</div>
        </c:if>
        <form method="post" action="ProfesorNuevoProc">
        <fieldset>
        <legend>Datos</legend>
       <input type="hidden" name="accion" value="${accion}" />
          <table>
            <tr>
              <td>ID PROFESOR</td>
              <td>
                    	<c:if test="${accion == 'MODIFICAR' }">${IdProfesor}
                    	   <input type="hidden" name="IdProfesor" value="${IdProfesor}" />
                    	</c:if>
					
              </td>
            </tr>
             <tr>
              <td>Apellido </td>
              <td>
         <input id="ApeProfesor" type="text" name="ApeProfesor" value="${ApeProfesor}"/>
              		
      
              </td>
            </tr>
            <tr>
              <td>Nombre </td>
              <td>
        <input id="NomProfesor" type="text" name="NomProfesor" value="${NomProfesor}"/>
              
              </td>
            </tr>
                <tr>
              <td>Dirección</td>
              <td>
        <input id="DirProfesor" type="text" name="DirProfesor" value="${DirProfesor}" />
              
              </td>
            </tr>
                 <tr>
              <td>Telefono</td>
              <td>
        <input id="TelProfesor" type="text" name="TelProfesor" value="${TelProfesor}" />
              
              </td>
            </tr>
                <tr>
              <td>Email</td>
              <td>
        <input id="EmailProfesor" type="text" name="EmailProfesor" value="${EmailProfesor}" />
              
              </td>
            </tr>
               <tr>
              <td>Clave</td>
              <td>
        <input id="Clave" type="text" name="Clave" value="${Clave}" />
              
              </td>
            </tr>
              
            <tr>
              <td colspan="2">
                <input type="submit" value="Procesar" />
              </td>
            </tr>
          </table>
               
        </fieldset>
        </form>
      
      </div>
   </div>
    
  </body>
</html>
