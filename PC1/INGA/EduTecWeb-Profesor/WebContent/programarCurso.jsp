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
                 <h1>Programar curso</h1>
                 
                 <c:if test="${mensaje != null}">		
                 <div class="alertMensaje">${mensaje}</div>
                 	</c:if>
                   <c:if test="${error != null}">	
                       <div class="alertMensaje">${error}</div>
                   	</c:if>
            <form action="AsignarProfesorProc">   
             
               <fieldset>
               <legend>Datos</legend>
            <table>
               <tr>
               <td>Ciclo</td>
               <td><input type="text" name="ciclo"></td>
               </tr>
               <tr>
               <td>Curso</td>
               <td><input type="text" name="curso"></td>
               </tr>
                <tr>
               <td>Profesor</td>
               <td><input type="text" name="profesor"></td>
               </tr>
                 <tr>
               <td>Vacantes</td>
               <td><input type="text" name="vacantes"></td>
               </tr>
                
                       <tr>
               <td>Horario</td>
               <td><input type="text" name="horario"></td>
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
