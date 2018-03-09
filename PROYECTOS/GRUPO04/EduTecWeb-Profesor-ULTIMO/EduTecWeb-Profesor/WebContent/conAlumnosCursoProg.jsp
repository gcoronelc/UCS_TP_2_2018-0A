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
                
                <h1>CONSULTA DE ALUMNOS POR CURSO PROGRAMADO</h1>
                
                <form id="form1" method="post">
                <fieldset>
                <legend>Barra de control</legend>
                  <table>
                    <tr>
                      <td>Ciclo:</td>
                      <td><input id="ciclo" type="text" name="ciclo" size="7" maxlength="7" /></td>
                      <td>Curso Prog.:</td>
                      <td>
                        <select id="curso" name="curso">
                          <option value="0">Seleccionar ...</option>
                        </select>
                      </td>
                      <td>
                        <button type="button" id="btnConsultar">Consultar</button>
                        <button type="button" id="btnExportarExcel">Exportar Excel</button>
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
                        <th>CODIGO</th>
                        <th>NOMBRE</th>
                        <th>PARCIAL</th>
                           <th>FINAL</th>
                          <th>SUBSANACION</th>
                            <th>PROMEDIO</th>
                                                                                                
                      </tr>
                    </thead>
                  
                    <tbody id="repoBody">
                    </tbody>
                  
                  </table>
                  <p id="mensaje"></p>
                
                
                </fieldset>
                
            </div>
            <div class="pie">
                <jsp:include page="pie.jsp"/>
            </div>
        </div>
        
        <script>
        
        Document.
	        $("#ciclo").keyup( llenarCombo );
	        
	        function llenarCombo(){
	          
	          var ciclo = $("#ciclo").val();
	          
	          console.log("Ciclo: " + ciclo);
	          
	          $("#curso")
	           .empty()
	           .append('<option value="0">Seleccionar ...</option>');
	          
	          if( ciclo.length != 7 ){
	        	  return;
	          }
	          
	          var data = "ciclo=" + ciclo;
	          var url = "ComboAlumnosCursoProg";
	          
	          $.post(url, data, llenarCombo2);
	          
	        }
	        
	        function llenarCombo2( lista ){
	        	
	        	$.each(lista, function( index, dto ) {
	        	   var datoCombo = "<option value='" + dto.code 
	        	    + "'>" + dto.name + "</option>"; 
	        	   $("#curso").append(datoCombo);
	        	});
	        	
	        	
	        }
           
	        $("#btnConsultar").click( fnConsultar );
	        
	        function fnConsultar(){
	        	
	        	var curso = $("#curso").val();
	        	
	        	if( curso == "0" ){
	        		return;
	        	}
	        	
	        	var data = "curso=" + curso;
	          var url = "ConAlumnosCursoProgProc";
	            
	          $.post(url, data, fnConsultar2);
	        	
	        }
	        
	        function fnConsultar2( lista ){
	        	
	        	$("#repoBody").empty();
	        	
	        	$("#mensaje").html("Registros encontrados: " + lista.length);
	            
            $.each(lista, function( index, dto ) {
               var dataRow = "<tr><td>";
               dataRow += "<a href=\"javascript:fnClieEditar('" + dto.idalumno + "')\">Editar</a> <a href=\"javascript:fnClieEliminar('" + dto.idalumno + "')\">Eliminar</a></td><td>";
               dataRow += dto.idalumno + "</td><td>";
               dataRow += dto.alumno + "</td><td>";
               dataRow += dto.parcial + "</td><td>";
               dataRow += dto.final + "</td><td>";
               dataRow += dto.subsanacion + "</td><td>";
               dataRow += dto.promedio + "</td></tr>";
               

               $("#repoBody").append(dataRow);
            })
            
            
          }
	        
	        
			function fnClieEditar(codigo){
				var urlEliminar = "ClienteEditar?codigo=" + codigo;
				$("#egcc_contenido").load(urlEliminar);
			}
			
			function fnClieEliminar(codigo){
				var urlEliminar = "ClienteEliminar?codigo=" + codigo;
				$("#egcc_contenido").load(urlEliminar);
			}  
	        
	        
	        
	        
	        
        
        </script>
        
        
    </body>
</html>
