<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="jquery/jquery.js"></script>
        <title>Registrar Notas</title>
    </head>
        <div class="page">
            <div class="header">
                <jsp:include page="header.jsp"/>
            </div>
            <div>
                <jsp:include page="menu.jsp"/>
            </div>
            <div class="contenido">
                
                <h1>REGISTRAR NOTAS</h1>
                
                <form id="form">
                
                  <table>
                  
                    <tr>
                      <td>Alumno:</td>
                      <td>
                        <select id="alumno" name="alumno">
                        
                          <option value="0000">Seleccionar ...</option>
                          
                          <c:forEach items="${alumno}" var="r">
                          <option value="${r.code}">${r.name}</option>
                          </c:forEach>
                          
                        </select>
                      </td>
                    </tr>

                    <tr>
                      <td>Curso Programado:</td>
                      <td>
                        <select id="curso" name="curso">
                        
                          <option value="0000">Seleccionar ...</option>

                          
                        </select>
                      </td>
                    </tr>
                    <tr>
                     <td>Examen Parcial:</td>
                      <td><input id="parcial" type="number" name="parcial" size="5" step="0.01" maxlength="5" /></td>
               
                    </tr>
                      <tr>
                     <td>Examen Final:</td>
                      <td><input id="final" type="text" name="final" size="4" maxlength="4" /></td>
                    </tr>
                      <tr>
                     <td>Subsanacion:</td>
                      <td><input id="subsanacion" type="text" name="subsanacion" size="4" maxlength="4" /></td>
                    </tr>
                    
                    <tr>
                     <td>promedio:</td>
                      <td><input id="promedio" type="text" name="promedio"  /></td>
                    </tr>
                        <tr>
                         <td>
                        <button type="button" id="btnConsultar">Registrar</button>
                       </td>
                        
                        </tr>
                                      
                  </table>
                
                
                </form>
                
                
                <fieldset>
                <legend>Resultado</legend>
                
                  <table border="1">
                  
                    <thead>
                      <tr>
                        <th>CODIGO</th>
                        <th>NOMBRE</th>
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
    </body>
    <script type="text/javascript">
    
       $("#alumno").change( traerCursos );
    
       function traerCursos(){
    	   
    	   var alumno = $("#alumno").val();
    	   var data = "alumno=" + alumno;
    	   
    	   $.get("ComboCurso",data,procesar);

       }
    
    
       function procesar( respuesta ){
    	   
    	   $("#curso").val(0);
    	   $.each(respuesta, function(key, r) {
    		    var opcion = "<option value='" + r.code + "'>" + r.name + "</option>";
    		    $("#curso").append(opcion);
         });
    	   
    	   
       }
       
       $("#btnConsultar").click( fnConsultar );
       
       function fnConsultar(){
       	
       	var parcial = $("#parcial").val();
     	var finall = $("#final").val();
     	var subsanacion = $("#subsanacion").val();
     	var curso = $("#curso").val();
     	var alumno = $("#alumno").val();
     	
     	if( curso == 0  ){
       		return;
       	}
       	
     	
       	if( alumno == "0" || alumno == ""  ){
       		return;
       	}
       	
       	if( parcial == "0" || parcial == ""  ){
       		return;
       	}
       	
    	if( finall == "0" || finall == "" ){
       		return;
       	}
    	
    	if( subsanacion == "0" || subsanacion == ""){
    		subsanacion = "0";
       	}
       	
       //	var data = "parcial=" + parcial + "finall=" + finall + "subsanacion=" + subsanacion + "curso=" + curso + "alumno=" + alumno  ;
       var data =   $("#form").serialize();

       var url = "ingresarNota";
           
         $.get(url, data, fnConsultar2);
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
</html>
