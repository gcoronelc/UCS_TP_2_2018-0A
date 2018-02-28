<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
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
           <form method="post" action="MantProfesor">
		
		<table>
	 
			<tr>
				<td>Nombres</td>
				<td><input type="text" name="NomProfesor"/></td>
			</tr>
			<tr>
				<td>Apellidos</td>
				<td><input type="text" name="ApeProfesor"/></td>
			</tr>
		
			<tr>
				<td>Dirección</td>
				<td><input type="text" name="DirProfesor"/></td>
			</tr>
				<tr>
				<td>Telefono</td>
				<td><input type="text" name="TelProfesor"/></td>
			</tr>
				<tr>
				<td>Email </td>
				<td><input type="text" name="EmailProfesor"/></td>
			</tr>
				
				<tr>
				<td>Clave </td>
				<td><input type="password" name="Clave"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="RegistrarProfesor"/>
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
</html>