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
                
                <h1>CONSULTA DE GASTOS</h1>
                
                <form id="form1" method="post">
                <fieldset>
                <legend>Barra de control</legend>
                  <table>
                    <tr>
                      <td>Alumno:</td>
                      <td><input id="alumno" type="text" name="alumno"  /></td>

                      <td>
                        <button type="button" id="btnConsultar">Consultar</button>
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
                         <th>nombre Alumno</th>
                         <th>Nombre Curso</th>
                         <th>Ganancia</th>
                         <th>Ingresos</th>
                         <th>Gastos</th>
             
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
        
	     
	        $("#btnConsultar").click( fnConsultar );
	        
	        function fnConsultar(){
	        	
	        	var alumno = $("#alumno").val();
	        	
	        	if( alumno == "" ){
	        		return;
	        	}
	        	
	        	var data = "alumno=" + alumno;
	           var url = "consultaFinancieraCiclo";
	            
	          $.post(url, data, fnConsultar2);
	        	
	        }
	        
	        function fnConsultar2( lista ){
	        	
	        	$("#repoBody").empty();
	            
            $.each(lista, function( index, dto ) {
               var dataRow = "<tr><td>";
               dataRow += dto.nombre + "</td><td>";
               dataRow += dto.nombrecurso + "</td><td>";
               dataRow += dto.ingreso + "</td><td>";
               dataRow += dto.gastos + "</td><td>";
               dataRow += dto.ganancias + "</td><td>";
                $("#repoBody").append(dataRow);
            });
            
            
          }
        
        </script>
        
        
    </body>
</html>
