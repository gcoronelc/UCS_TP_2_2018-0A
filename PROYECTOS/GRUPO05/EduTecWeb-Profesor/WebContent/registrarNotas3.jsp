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
      
        <h1>REGISTRAR NOTAS</h1>     
               
          
        <form method="post" action="registrarNotas">
        <fieldset>
        <legend>Datos</legend>
        
          <table>
            <tr>
              <td>Alumno:</td>
              <td>
              
                <select name="alumno" id="alumno">
                  <option id="000">Seleccionar...</option> 
                  <c:forEach items="${alumno}" var="r">
                    <option  value="${r.code}" <c:if test="${r.selected == 1}">selected="selected"</c:if> >${r.name}</option>
                  </c:forEach>
                </select>
               
              </td>
            </tr>
              
              <tr>
              <td>Curso:</td>
              <td>
                
                   <select id="curso" name="curso">
                        
                          <option value="0000">Seleccionar ...</option>

                          
                        </select>
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
      <div class="pie">
          <jsp:include page="pie.jsp"/>
      </div>
    </div>
     <script type="text/javascript">
    	
 		    $("#alumno").change( llenarCombo );
		    function llenarCombo(){
		      
		      var alumno = $("#alumno").val();
		      
		      console.log("alumno: " + alumno);
		      
		      $("#alumno")
		       .empty()
		       .append('<option value="0">Seleccionar ...</option>');
		      
		     
		      
		      var data = "alumno=" + alumno;
		      var url = "comboCurso";
		      
		      $.post(url, data, llenarCombo2);
		      
		    }
		    
		    function llenarCombo2( lista ){
		    	
		    	$.each(lista, function( index, dto ) {
		    	   var datoCombo = "<option value='" + dto.code 
		    	    + "'>" + dto.name + "</option>"; 
		    	   $("#curso").append(datoCombo);
		    	});
		    	
		    	
		    }
    
    
    </script>
    
  </body>
</html>
