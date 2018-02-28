<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
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
                <c:if test="${mensaje != null}">		
                 <div class="alertMensaje">${mensaje}</div>
                 	</c:if>
                   <c:if test="${error != null}">	
                       <div class="alertMensaje">${error}</div>
                   	</c:if>
                 <h1>Asignar Profesor</h1>
                      <form action="AsignarProfesorProc">   
             
               <fieldset>
               <legend>Datos</legend>
            <table>
               <tr>
               <td>IdCursoProg</td>
               <td><input type="text" name="IdCursoProg"></td>
               </tr>
                 <tr>
               <td>Profesor</td>
               <td><input type="text" name="IdProfesor"></td>
               </tr>
              <tr>
               <td colspan="2">
               <input type="submit" value="Registrar">
              </td>
               </tr>
               </table>
            </fieldset>
            </form>
            
         
            </div>
            <div class="pie">
                <jsp:include page="pie.jsp"/>
            </div>
        </div>
    </body>
</html>