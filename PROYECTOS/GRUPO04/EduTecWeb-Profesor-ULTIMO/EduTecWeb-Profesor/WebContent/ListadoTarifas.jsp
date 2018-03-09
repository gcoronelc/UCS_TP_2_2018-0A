<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="jquery/jquery.js"></script>
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
                
                <h1>MANTENIMIENTO DE TARIFAS</h1>
                
                <form id="form1" method="post">
                <fieldset>
                <legend>Barra de control</legend>
                  <table>
                    <tr>
                      <td>Descripcion:</td>
                      <td><input id="Descripcion" type="Descripcion" name="ciclo"  /></td>
                  
                      <td>
                        <button type="button"  id="btnConsultar">Consultar</button>
                        <button type="button" id="btnCrear" >Nueva TARIFA</button>
                      </td>
                    </tr>
                  </table>
                </fieldset>
                </form>
                
                <fieldset>
                <legend>Resultado</legend>
                
                  <table border="1">
                  
                    <thead>
                      <tr>
                       <th>Acción</th>
                        <th>IdTarifa</th>
                        <th>PrecioVenta</th>
                        <th>Descripcion</th>
                           <th>Horas</th>
                          <th>PagoHora</th>
                                                                                              
                      </tr>
                    </thead>
                  
                    <tbody id="repoBody">
                    </tbody>
                  
                  </table>
                  <p id="mensaje"></p>
                
                 <br>
                	<div id="egcc_contenido">
		<h3></h3>
	</div>
                </fieldset>
                
            </div>
            <div class="pie">
                <jsp:include page="pie.jsp"/>
            </div>
        </div>
        
 <script>
       
	        
	       
           
	        $("#btnConsultar").click( fnConsultar );
	        
	        function fnConsultar(){
	        	
	        	var Desc = $("#Descripcion").val();
	        	
	        
	        	
	        	var data = "Descripcion=" + Desc;
	          var url = "TarifaListarProc";
	            
	          $.post(url, data, fnConsultar2);
	        	
	        }
	        
	        function fnConsultar2( lista ){
	        	
	        	$("#repoBody").empty();
	        	
	        	$("#mensaje").html("Registros encontrados: " + lista.length);
	            
            $.each(lista, function( index, dto ) {
               var dataRow = "<tr><td>";
               dataRow += "<a href=\"javascript:fnClieEditar('" + dto.idtarifa + "')\">Editar</a> <a href=\"javascript:fnClieEliminar('" + dto.idtarifa + "')\">Eliminar</a></td><td>";
               dataRow += dto.idtarifa + "</td><td>";
               dataRow += dto.precioventa + "</td><td>";
               dataRow += dto.descripcion + "</td><td>";
               dataRow += dto.horas + "</td><td>";
               dataRow += dto.pagohora + "</td></tr>";
           

               $("#repoBody").append(dataRow);
            })
            
            
          }
	        
	    	
	     
			function fnClieEditar(codigo){
				
				document.location.href="TarifaModificar?IdTarifa=" + codigo;
		
			}
			
			function fnClieEliminar(codigo){
	
				document.location.href="TarifaEliminar?IdTarifa=" + codigo;
			    
			}  
	        
	        
	        
	        
		
        </script>
        <script type="text/javascript">
    	$("#btnCrear").click(function () {
    		   document.location.href="TarifaNuevo";
    
		});
      
        </script>

</body>
</html>