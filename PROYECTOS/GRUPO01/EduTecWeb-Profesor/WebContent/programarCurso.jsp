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
      
        <h1>PROGRAMAR CURSO</h1>     
               
        <c:if test="${mensaje != null}">
          <div class="msgInfo">${mensaje}</div>
        </c:if>
        
        <c:if test="${error != null}">
          <div class="msgError">${error}</div>
        </c:if>
        
        
        <form method="post" action="ProgramarCursoProc">
        <fieldset>
        <legend>Datos</legend>
        
          <table>
            <tr>
              <td>Ciclo:</td>
              <td>
              
                <select name="ciclo">
                  <option id="000">Seleccionar...</option> 
                  <c:forEach items="${ciclos}" var="r">
                    <option  value="${r.code}" <c:if test="${r.selected == 1}">selected="selected"</c:if> >${r.name}</option>
                  </c:forEach>
                </select>
              
              </td>
            </tr>
            <tr>
              <td>Curso:</td>
              <td>
              
                <select name="curso">
                  <option id="000">Seleccionar...</option> 
                  <c:forEach items="${cursos}" var="r">
                    <option value="${r.code}" <c:if test="${r.selected == 1}">selected="selected"</c:if>>${r.name}</option>
                  </c:forEach>
                </select>
              
              </td>
            </tr>
            <tr>
              <td>Profesor:</td>
              <td>
              
                <select name="profesor">
                  <option id="">Seleccionar...</option> 
                  <c:forEach items="${profesores}" var="r">
                    <option value="${r.code}" <c:if test="${r.selected == 1}">selected="selected"</c:if> >${r.name}</option>
                  </c:forEach>
                </select>
              
              </td>
            </tr>
            <tr>
              <td>Horario:</td>
              <td><input type="text" name="horario" value="${horario}"/></td>
            </tr>
            <tr>
              <td>Vacante:</td>
              <td><input type="text" name="vacantes" value="${vacantes}" /></td>
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
  </body>
</html>
