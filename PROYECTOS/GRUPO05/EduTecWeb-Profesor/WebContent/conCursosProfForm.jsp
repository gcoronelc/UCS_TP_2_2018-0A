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
                
                <h1>CONSULTA DE CURSOS ASIGNADOS POR PROFESOR</h1>
                
                <form id="form1" method="post">
                <fieldset>
                <legend>Barra de control</legend>
                  <table>
                    <tr>
                      <td>Ciclo:</td>
                      <td><input id="ciclo" type="text" name="ciclo" size="7" maxlength="7" /></td>
                      
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
                        <th>CURSOS</th>
                        <th>DESCRIPCION</th>
                      </tr>
                    </thead>
                  
                    <tbody id="repoBody">
                    </tbody>
                  
                  </table>
                
                
                </fieldset>
                
            </div>
            <div class="pie">
                <jsp:include page="pie.jsp"/>
            </div>
        </div>
        
        <script>
        
	        $("#ciclo").keyup( llenarCombo );
	        
	        function llenarCombo(){
	          
	          var ciclo = $("#ciclo").val();
	          
	          console.log("Ciclo: " + ciclo);
	          
	          //$("#curso")
	           //.empty()
	           //.append('<option value="0">Seleccionar ...</option>');
	          
	          //if( ciclo.length != 7 ){
	        	//  return;
	         // }
	          
	          var data = "ciclo=" + ciclo;
	          //var url = "ComboAlumnosCursoProg";
	          
	          //$.post(url, data, llenarCombo2);
	          
	        }
	        
	        //function llenarCombo2( lista ){
	        	
	        	//$.each(lista, function( index, dto ) {
	        	  // var datoCombo = "<option value='" + dto.code 
	        	   // + "'>" + dto.name + "</option>"; 
	        	   //$("#curso").append(datoCombo);
	        	//});
	        	
	        	
	        //}
           
	        $("#btnConsultar").click( fnConsultar );
	        
	        function fnConsultar(){
	        	
	        	var curso = $("#curso").val();
	        	
	        	if( curso == "0" ){
	        		return;
	        	}
	        	
	        	var data = "curso=" + curso;
	          var url = "ConCursosProfFormProc";
	            
	          $.post(url, data, fnConsultar2);
	        	
	        }
	        
	        function fnConsultar2( lista ){
	        	
	        	$("#repoBody").empty();
	            
            $.each(lista, function( index, dto ) {
               var dataRow = "<tr><td>";
               dataRow += dto.idalumno + "</td><td>";
               dataRow += dto.alumno + "</td></tr>";
               $("#repoBody").append(dataRow);
            });
            
            
          }
        
        </script>
        
        
    </body>
</html>
