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
      
        <h1>NUEVO EMPLEADO</h1>     
               
        <c:if test="${mensaje != null}">
          <div class="msgInfo">${mensaje}</div>
        </c:if>
        
        <c:if test="${error != null}">
          <div class="msgError">${error}</div>
        </c:if>
        <form method="post" action="EmpleadoNuevoProc">
        <fieldset>
        <legend>Datos</legend>
       <input type="hidden" name="accion" value="${accion}" />
          <table>
            <tr>
              <td>ID EMPLEADO</td>
              <td>
                <td>
                    	<c:if test="${accion == 'MODIFICAR' }">${IdEmpleado}
                    	   <input type="hidden" name="IdEmpleado" value="${IdEmpleado}" />
                    	</c:if>
					<c:if test="${accion != 'MODIFICAR' }"><input type="text" name="IdEmpleado" /></c:if>				
		      
              </td>
					
             
            </tr>
                <tr>
              <td>Clave</td>
              <td>
        <input id="Clave" type="text" name="Clave" value="${Clave}" />
              
              </td>
            </tr>
             <tr>
              <td>Apellido </td>
              <td>
         <input id="ApeEmpleado" type="text" name="ApeEmpleado" value="${ApeEmpleado}"/>
              		
      
              </td>
            </tr>
            <tr>
              <td>Nombre </td>
              <td>
        <input id="NomEmpleado" type="text" name="NomEmpleado" value="${NomEmpleado}"/>
              
              </td>
            </tr>
              <tr>
              <td>CARGO </td>
              <td>
        <input id="Cargo" type="text" name="Cargo" value="${Cargo}"/>
              
              </td>
            </tr>
                <tr>
              <td>Dirección</td>
              <td>
        <input id="DirEmpleado" type="text" name="DirEmpleado" value="${DirEmpleado}" />
              
              </td>
            </tr>
                 <tr>
              <td>Telefono</td>
              <td>
        <input id="TelEmpleado" type="text" name="TelEmpleado" value="${TelEmpleado}" />
              
              </td>
            </tr>
                <tr>
              <td>Email</td>
              <td>
        <input id="EmailEmpleado" type="text" name="EmailEmpleado" value="${EmailEmpleado}" />
              
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
