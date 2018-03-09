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
                
                <h1>MANTENIMIENTO DE EMPLEADOS</h1>
                
                <form id="form">
                
                  <table>
                    
                    <tr>
                     <td>ID Empleado:</td>
                      <td><input id="codigo"  name="codigo" type="text" /></td> </tr>
                      <tr>
                     <td>Clave :</td>
                      <td><input id="clave" type="password" name="clave"  /></td>
                    </tr>
                      <tr>
                     <td>Nombres :</td>
                      <td><input id="nombre" type="text" name="nombre" /></td>
                    </tr>
                    
                    <tr>
                     <td>Apellidos : </td>
                      <td><input id="apellido" type="text" name="apellido"  /></td>
                    </tr>
                    <tr>
                     <td>Cargo : </td>
                      <td><input id="cargo" type="text" name="cargo"  /></td>
                    </tr>
                    <tr>
                     <td>Direccion : </td>
                      <td><input id="direccion" type="text" name="direccion"  /></td>
                    </tr>
                     <tr>
                     <td>Telefono : </td>
                      <td><input id="telefono" type="text" name="telefono"  /></td>
                    </tr>
                     <tr>
                     <td>Email : </td>
                      <td><input id="email" type="text" name="email"  /></td>
                    </tr>
                        <tr>
                         
                         <td>
                        <button type="button" id="btnRegistrar">Registrar</button>
                       </td>
                         <td>
                        <button type="button" id="btnBuscar">Buscar</button>
                       </td>
                         <td>
                        <button type="button" id="btnActualizar">Actualizar</button>
                       </td>
                         
                         <td>
                        <button type="button" id="btnEliminar">Eliminar</button>
                       </td>
                        </tr>
                                      
                  </table>
                
                
                </form>
                
                
                <fieldset>
                <legend>Resultado</legend>
                
                  <table border="1">
                  
                    <thead>
                      <tr>
                        <th>ID EMPLEADO</th>
                        <th>CLAVE</th>
                         <th>NOMBRE</th>
                        <th>APELLIDO</th>
                         <th>CARGO</th>
                        <th>DIRECCION</th>
                         <th>TELEFONO</th>
                        <th>EMAIL</th>
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
    

    
    $("#btnBuscar").click( fnConsultar );
    
    function fnConsultar(){
    	
    	var codigo = $("#codigo").val();
    	 
    	var data = "codigo=" + codigo;
       var url = "listarEmpleado";
        
      $.post(url, data, fnListar);
    	
    }
    
       function fnListar(lista){
       	
       	$("#repoBody").empty();
           
       $.each( lista,function( index, dto ) {
          var dataRow = "<tr><td>";
          dataRow += dto.idempleado + "</td><td>";
          dataRow += dto.clave + "</td><td>";
          dataRow += dto.nombre + "</td><td>";
          dataRow += dto.apellido + "</td><td>";
          dataRow += dto.cargo + "</td><td>";
          dataRow += dto.direccion + "</td><td>";
          dataRow += dto.telefono + "</td><td>";
          dataRow += dto.email + "</td></tr>";
           $("#repoBody").append(dataRow);
       });
       }
       
  $("#btnRegistrar").click( btnRegistrar );
       
       function btnRegistrar(){
       	 
        var codigo = $("#codigo").val();
       	var clave = $("#clave").val();
     	var nombre = $("#nombre").val();  
       	var apellido = $("#apellido").val();
     	var cargo = $("#cargo").val();
     	var direccion = $("#direccion").val();
     	var telefono = $("#telefono").val();
     	var email = $("#email").val();
     	
     	if( codigo == "" ){
       		return;
       	}
       	
     	
       	if( clave == ""  ){
       		return;
       	}
       	
       	if( nombre == ""  ){
       		return;
       	}
       	
    	if( apellido == "" ){
       		return;
       	}
    	
    	if(  cargo == ""){
    		return;
       	}
    	if(  direccion == ""){
    		return;
       	}
    	
    	if(  telefono == ""){
    		return;
       	}
    	if(  email == ""){
    		return;
       	}
    	 
        var data =   $("#form").serialize();

       var url = "registrarEmpleado";
           
         $.get(url, data, fnListar);
       }
       
       
       $("#btnActualizar").click( btnActualizar );
       function btnActualizar(){
         	 
           var codigo = $("#codigo").val();
          	var clave = $("#clave").val();
        	var nombre = $("#nombre").val();  
          	var apellido = $("#apellido").val();
        	var cargo = $("#cargo").val();
        	var direccion = $("#direccion").val();
        	var telefono = $("#telefono").val();
        	var email = $("#email").val();
        	
        	if( codigo == "" ){
           		alert("El codigo no puede ser modificable");
        		return;
           	} 
           var data =   $("#form").serialize();

          var url = "actualizarEmpleado";
              
            $.get(url, data, fnListar);
          }
       
       $("#btnEliminar").click( btnEliminar );
       function btnEliminar(){
       	 
           var codigo = $("#codigo").val();
           
        	if( codigo == "" ){
           		alert("Debe Ingresar el codigo");
        		return;
           	} 
           var data = $("#form").serialize();

          var url = "eliminarEmpleado";
              
            $.get(url, data, fnListar);
          }
       
       
       
    </script>
</html>
