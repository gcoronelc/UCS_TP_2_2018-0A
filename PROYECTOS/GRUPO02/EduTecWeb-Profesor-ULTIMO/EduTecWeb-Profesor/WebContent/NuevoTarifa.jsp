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
        <form method="post" action="TarifaNuevoProc">
        <fieldset>
        <legend>Datos</legend>
       <input type="hidden" name="accion" value="${accion}" />
          <table>
            <tr>
              <td>Codigo Tarifa</td>
              <td>
                    	<c:if test="${accion == 'MODIFICAR' }">${IdTarifa}
                    	   <input type="hidden" name="IdTarifa" value="${IdTarifa}" />
                    	</c:if>
					<c:if test="${accion != 'MODIFICAR' }"><input type="text" name="IdTarifa" /></c:if>				
		      
              </td>
            </tr>
             <tr>
              <td>Precio Venta</td>
              <td>
              	<c:if test="${accion == 'ELIMINAR' }">${PrecioVenta}</c:if>
					<c:if test="${accion != 'ELIMINAR' }"><input type="text" name="PrecioVenta" value="${PrecioVenta}"/></c:if>				
				
       
              </td>
            </tr>
            <tr>
              <td>Descripcion</td>
              <td>
        <input id="Descripcion" type="text" name="Descripcion" value="${Descripcion}"/>
              
              </td>
            </tr>
                <tr>
              <td>Horas</td>
              <td>
        <input id="Horas" type="text" name="Horas" value="${Horas}" />
              
              </td>
            </tr>
                 <tr>
              <td>Pago Hora</td>
              <td>
        <input id="PagoHora" type="text" name="PagoHora" value="${PagoHora}" />
              
              </td>
            </tr>
            <tr>
              
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
