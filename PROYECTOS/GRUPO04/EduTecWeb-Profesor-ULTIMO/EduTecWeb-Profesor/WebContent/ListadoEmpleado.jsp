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
                
                <h1>MANTENIMIENTO DE EMPLEADO</h1>
                
                <form id="form1" method="post">
                <fieldset>
                <legend>Barra de control</legend>
                  <table>
                    <tr>
                      <td>DNI:</td>
                      <td><input id="DNI" type="text" name="DNI"  /></td>
                  
                      <td>
                        <button type="button"  id="btnConsultar">Consultar</button>
                        <button type="button" id="btnCrear" >Nuevo</button>
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
                        <th>IdEmpleado</th>
                           <th>CLAVE</th>
                       
                        <th>APELLIDOS</th>
                        <th>NOMBRES</th>
                            <th>CARGO</th>
                           <th>DIRECCION</th>
                          <th>TELEFONO</th>
                                    <th>EMAIL</th>
                                                </tr>
                    </thead>
                  
                    <tbody id="repoBody">
                    </tbody>
                  
                  </table>
                  <p id="mensaje"></p>
                
                 <br>
                	
                </fieldset>
                
            </div>
            <div class="pie">
                <jsp:include page="pie.jsp"/>
            </div>
        </div>
        
 <script>
 	        $("#btnConsultar").click( fnConsultar );
	        
	        function fnConsultar(){
	        	
	        	var DNI = $("#DNI").val();
	        	
	        
	        	
	        	var data = "DNI=" + DNI;
	          var url = "EmpleadoListarProc";
	            
	          $.post(url, data, fnConsultar2);
	        	
	        }
	        
	        function fnConsultar2( lista ){
	        	
	        	$("#repoBody").empty();
	        	
	        	$("#mensaje").html("Registros encontrados: " + lista.length);
	            
            $.each(lista, function( index, dto ) {
               var dataRow = "<tr><td>";
               dataRow += "<a href=\"javascript:fnClieEditar('" + dto.idempleado + "')\">Editar</a> <a href=\"javascript:fnClieEliminar('" + dto.idempleado + "')\">Eliminar</a></td><td>";
               dataRow += dto.idempleado + "</td><td>";
               dataRow += dto.clave + "</td><td>";
               dataRow += dto.apeempleado + "</td><td>";
               dataRow += dto.nomempleado + "</td><td>";
               dataRow += dto.cargo + "</td><td>";
               dataRow += dto.dirempleado + "</td><td>";
               dataRow += dto.telempleado + "</td><td>";
               dataRow += dto.emailempleado + "</td></tr>";
           
               
               
               $("#repoBody").append(dataRow);
            })
            
            
          }
	        
	    	
	     
			function fnClieEditar(codigo){
				
				document.location.href="EmpleadoModificar?IdEmpleado=" + codigo;
		
			}
			
			function fnClieEliminar(codigo){
	
				document.location.href="EmpleadoEliminar?IdEmpleado=" + codigo;
			    
			}  
	        
	        
	        
	        
		
        </script>
        <script type="text/javascript">
    	$("#btnCrear").click(function () {
    		   document.location.href="EmpleadoNuevo";
    
		});
      
        </script>

</body>
</html>