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
                
                <h1>MANTENIMIENTO DE ALUMNO</h1>
                
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
                        <th>IdProfesor</th>
                        <th>APELLIDOS</th>
                        <th>NOMBRES</th>
                           <th>DIRECCION</th>
                          <th>TELEFONO</th>
                                    <th>EMAIL</th>
                           <th>CLAVE</th>
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
	          var url = "AlumnoListarProc";
	            
	          $.post(url, data, fnConsultar2);
	        	
	        }
	        
	        function fnConsultar2( lista ){
	        	
	        	$("#repoBody").empty();
	        	
	        	$("#mensaje").html("Registros encontrados: " + lista.length);
	            
            $.each(lista, function( index, dto ) {
               var dataRow = "<tr><td>";
               dataRow += "<a href=\"javascript:fnClieEditar('" + dto.idalumno + "')\">Editar</a> <a href=\"javascript:fnClieEliminar('" + dto.idalumno + "')\">Eliminar</a></td><td>";
               dataRow += dto.idalumno + "</td><td>";
               dataRow += dto.apealumno + "</td><td>";
               dataRow += dto.nomalumno + "</td><td>";
               dataRow += dto.diralumno + "</td><td>";
               dataRow += dto.telalumno + "</td><td>";
               dataRow += dto.emailalumno + "</td><td>";
               dataRow += dto.clave + "</td></tr>";
           
               
               
               
               
               
               
               
               $("#repoBody").append(dataRow);
            })
            
            
          }
	        
	    	
	     
			function fnClieEditar(codigo){
				
				document.location.href="AlumnoModificar?IdAlumno=" + codigo;
		
			}
			
			function fnClieEliminar(codigo){
	
				document.location.href="AlumnoEliminar?IdAlumno=" + codigo;
			    
			}  
	        
	        
	        
	        
		
        </script>
        <script type="text/javascript">
    	$("#btnCrear").click(function () {
    		   document.location.href="AlumnoNuevo";
    
		});
      
        </script>

</body>
</html>