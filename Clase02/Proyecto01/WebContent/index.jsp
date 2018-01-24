<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>PROYECTO 01</h1>

<form method="post" action="ProyectoController">
<fieldset>
  <legend>Datos</legend>
  <table>
    <tr>
      <td>Núnmero 1:</td>
      <td><input type="text" name="num1" /></td>
    </tr>
    <tr>
      <td>Núnmero 2:</td>
      <td><input type="text" name="num2" /></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="Procesar" />
      </td>
    </tr>
  </table>
</fieldset>
</form>

<% if( request.getAttribute("suma") != null ) { %>
<fieldset>
  <legend>Reporte</legend>
  <table>
    <tr>
      <td>Núnmero 1:</td>
      <td><%= request.getAttribute("n1") %></td>
    </tr>
    <tr>
      <td>Núnmero 2:</td>
      <td><%= request.getAttribute("n2") %></td>
    </tr>
    <tr>
      <td>Suma:</td>
      <td><%= request.getAttribute("suma") %></td>
    </tr>
    <tr>
      <td>Producto:</td>
      <td><%= request.getAttribute("producto") %></td>
    </tr>
  </table>
</fieldset>
<% } %>

</body>
</html>