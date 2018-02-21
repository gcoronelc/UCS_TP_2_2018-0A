<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                
                <h1>CONSULTA DE ALUMNOS POR CURSO PROGRAMADO</h1>
                
                <form>
                <fieldset>
                <legend>Barra de control</legend>
                  <table>
                    <tr>
                      <td>Ciclo:</td>
                      <td><input type="text" name="ciclo" size="7" maxlength="7" /></td>
                      <td>Curso Prog.:</td>
                      <td>
                        <select name="curso">
                          <option value="000">Seleccionar ...</option>
                        </select>
                      </td>
                      <td>
                        <button id="btnConsultar">Consultar</button>
                        <button id="btnExportarExcel">Exportar Excel</button>
                      </td>
                    </tr>
                  </table>
                </fieldset>
                </form>
                
                <fieldset>
                <legend>Resultado</legend>
                <p> </p>
                </fieldset>
                
            </div>
            <div class="pie">
                <jsp:include page="pie.jsp"/>
            </div>
        </div>
    </body>
</html>
