<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      
        <h1>GENERAR NUEVO CICLO</h1>
        
        <c:if test="${mensaje != null}">
          <div class="msgInfo">${mensaje}</div>
        </c:if>
        
        <c:if test="${error != null}">
          <div class="msgError">${error}</div>
        </c:if>
        
        
        <form method="post" action="GenerarCicloProc">
        <fieldset>
        <legend>NUEVO CICLO</legend>
        
          <table>
            <tr>
              <td>Generara el ciclo siguiente</td>
              
            </tr>
            <tr>
              <td>Fecha Inicio:</td>
              <td><input type="text" name="FecInicio"/></td>
            </tr>
            <tr>
              <td>Fecha Fin:</td>
              <td><input type="text" name="FecTermino"/></td>
            </tr>

            <tr>
              <td colspan="2">
                <input type="submit" value="Procesar"/>
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
