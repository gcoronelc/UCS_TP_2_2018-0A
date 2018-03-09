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
      
        <h1>CURSO</h1>     
               
        <c:if test="${mensaje != null}">
          <div class="msgInfo">${mensaje}</div>
        </c:if>
        
        <c:if test="${error != null}">
          <div class="msgError">${error}</div>
        </c:if>
        <form method="post" action="CursoNuevoProc">
        <fieldset>
        <legend>Datos</legend>
       <input type="hidden" name="accion" value="${accion}" />
          <table>
            <tr>
              <td>Codigo Curso</td>
              <td>
                    	<c:if test="${accion == 'MODIFICAR' }">${IdCurso}
                    	   <input type="hidden" name="IdCurso" value="${IdCurso}" />
                    	</c:if>
					<c:if test="${accion != 'MODIFICAR' }"><input type="text" name="IdCurso" /></c:if>				
		      
              </td>
            </tr>
             <tr>
              <td>Tarifa</td>
              <td>
              	 <select id=IdTarifa name="IdTarifa"  >                        
                          <option value="0000">Seleccionar ...</option>                          
                          <c:forEach items="${Tarifas}"  var="r">
                          <option value="${r.code}">${r.name} </option>
                          </c:forEach>
                    </select>
       
              </td>
            </tr>
            <tr>
              <td>NOMBRE DE CURSO</td>
              <td>
        	<input id="NomCurso" type="text" name="NomCurso" value="${NomCurso}"/>
              
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
