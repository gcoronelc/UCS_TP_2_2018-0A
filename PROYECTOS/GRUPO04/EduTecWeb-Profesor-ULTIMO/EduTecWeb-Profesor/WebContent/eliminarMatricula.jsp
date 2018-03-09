<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="jquery/jquery.js"></script>
        <title>Eliminar Matricula</title>
    </head>
        <div class="page">
            <div class="header">
                <jsp:include page="header.jsp"/>
            </div>
            <div>
                <jsp:include page="menu.jsp"/>
            </div>
            <div class="contenido">
                
                <h1>ELIMINAR MATRICULA</h1>
                
                <form method="post" action="">
                
                  <table>
                  
                    <tr>
                      <td>Alumno:</td>
                      <td>
                        <select id="alumno" name="alumno">
                        
                          <option value="0000">Seleccionar ...</option>
                          
                          <c:forEach items="${alumnos}" var="r">
                          <option value="${r.code}">${r.name}</option>
                          </c:forEach>
                          
                        </select>
                      </td>
                    </tr>

                    <tr>
                      <td>Curso Prog.:</td>
                      <td>
                        <select id="curso" name="curso">
                        
                          <option value="0000">Seleccionar ...</option>

                          
                        </select>
                      </td>
                    </tr>
                                      
                  </table>
                
                
                </form>
                
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
    	   
    	   $.get("EliminarMatriculaCursos",data,procesar);

       }
    
    
       function procesar( respuesta ){
    	   
    	   $("#curso").val("");
    	   $.each(respuesta, function(key, r) {
    		    var opcion = "<option value='" + r.code + "'>" + r.name + "</option>";
    		    $("#curso").append(opcion);
         });
    	   
    	   
       }
       
    </script>
</html>
