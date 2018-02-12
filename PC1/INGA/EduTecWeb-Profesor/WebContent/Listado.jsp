<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
           
	<table border="1" >
		<tr>
		 <td> ID</td>
		 <td> CODIGO</td>
		 <td> NOMBRE</td>
		 <td>DESCRPICION</td>
		 <td>EXISTENCIA</td>
		 <td>PRECIO</td>
		 <td>PRECIO</td>
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="ProfesorDTO" items="${lista}">
			<tr>
				<td><c:out value="${ProfesorDTO.IdProfesor}"/></td>
				<td><c:out value="${ProfesorDTO.ApeProfesor}"/></td>
				<td><c:out value="${ProfesorDTO.NomProfesor}"/></td>
				<td><c:out value="${ProfesorDTO.DirProfesor}"/></td>
				<td><c:out value="${ProfesorDTO.TelProfesor}"/></td>
						<td><c:out value="${ProfesorDTO.EmailProfesor}"/></td>
				<td><c:out value="${ProfesorDTO.Clave}"/></td>
			</tr>
		</c:forEach>

	</table>
	
             </div>
            <div class="pie">
                <jsp:include page="pie.jsp"/>
            </div>
        </div>
    </body>
</html>