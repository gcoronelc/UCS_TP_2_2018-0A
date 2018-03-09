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
        <form method="post" action="AlumnoNuevoProc">
        <fieldset>
        <legend>Datos</legend>
       <input type="hidden" name="accion" value="${accion}" />
          <table>
            <tr>
              <td>ID alumno</td>
              <td>
                    	<c:if test="${accion == 'MODIFICAR' }">${IdAlumno}
                    	   <input type="hidden" name="IdAlumno" value="${IdAlumno}" />
                    	</c:if>
					
              </td>
            </tr>
             <tr>
              <td>Apellido </td>
              <td>
         <input id="ApeAlumno" type="text" name="ApeAlumno" value="${ApeAlumno}"/>
              		
      
              </td>
            </tr>
            <tr>
              <td>Nombre </td>
              <td>
        <input id="NomAlumno" type="text" name="NomAlumno" value="${NomAlumno}"/>
              
              </td>
            </tr>
                <tr>
              <td>Dirección</td>
              <td>
        <input id="DirAlumno" type="text" name="DirAlumno" value="${DirAlumno}" />
              
              </td>
            </tr>
                 <tr>
              <td>Telefono</td>
              <td>
        <input id="TelAlumno" type="text" name="TelAlumno" value="${TelAlumno}" />
              
              </td>
            </tr>
                <tr>
              <td>Email</td>
              <td>
        <input id="EmailAlumno" type="text" name="EmailAlumno" value="${EmailAlumno}" />
              
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
