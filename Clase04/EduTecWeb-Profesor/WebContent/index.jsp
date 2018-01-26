<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>INGRESO AL SISTEMA</h1>
	<form method="post" action="Ingresar">
		<table>
			<tr>
				<td>Código</td>
				<td><input type="text" name="codigo"/></td>
			</tr>
			<tr>
				<td>Clave</td>
				<td><input type="password" name="clave"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Ingresar"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>