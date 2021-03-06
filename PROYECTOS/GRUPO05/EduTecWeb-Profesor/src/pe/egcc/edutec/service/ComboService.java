package pe.egcc.edutec.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pe.egcc.edutec.db.AccesoDB;
import pe.egcc.edutec.dto.ComboDTO;

public class ComboService {

  public List<ComboDTO> traerCursosPorCiclo(String ciclo){
    List<ComboDTO> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Consulta
      String sql = "select cursoprog code, "
          + "CONCAT(cursoprog,' - ', idcurso, ' - ', "
          + "curso, ' - ', horario) name "
          + "from v_cursoprog "
          + "where ciclo = ? ";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, ciclo);
      ResultSet rs = pstm.executeQuery();
      lista = rsToListCombo(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error al acceder vista de cursos programados.");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }
  

  public List<ComboDTO> consultaAlumno(){
    List<ComboDTO> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Consulta
      String sql = " select  a.IdAlumno as code , (a.NomAlumno+a.ApeAlumno) name "
          + " from [dbo].[Alumno] a ";
      PreparedStatement pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      lista = rsToListCombo(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error al acceder vista de cursos programados.");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }
 

  public List<ComboDTO> consultaCurso(String idalumno){
    List<ComboDTO> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Consulta
      String sql = " select cu.IdCursoProg as code, c.NomCurso as name "
          + "  from  [dbo].[Alumno] a  "
          + "  inner join [dbo].[Matricula] m  "
          + "   on a.IdAlumno = m.IdAlumno  "
          + "  inner join [dbo].[CursoProgramado] cu "
          + "  on cu.IdCursoProg = m.IdCursoProg  "
          + "  inner join [dbo].[Curso] c  "
          + "   on c.IdCurso = cu.IdCurso "
          + "  where a.IdAlumno = ? "
          ;
      
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, idalumno);
      ResultSet rs = pstm.executeQuery();
      lista = rsToListCombo(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error al acceder vista de cursos programados.");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }
  
  public List<ComboDTO> traerCursosAlumno(String alumno){
    List<ComboDTO> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Consulta
      String sql = "select cursoprog code, "
          + "ciclo + ' - ' + cast(cursoprog as varchar) "
          + "+ ' - '  + curso name "
          + "from v_cursoprog "
          + "where cursoprog in ( "
          + "select idcursoprog from matricula "
          + "where idalumno = ? "
          + "and ciclo >= '2018-02'"
          + ") order by 1";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, alumno);
      ResultSet rs = pstm.executeQuery();
      lista = rsToListCombo(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error al acceder vista de cursos programados.");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }
  
  public List<ComboDTO> traerCiclosActuales(){
    List<ComboDTO> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Ciclo actual
      Calendar fechaActual = Calendar.getInstance();
      int anio = fechaActual.get(Calendar.YEAR);
      int mes = fechaActual.get(Calendar.MONTH) + 1;
      String ciclo = "" + anio + "-" + String.format("%02d", mes);
      // Consulta
      String sql = "select idciclo code, idciclo name "
          + "from ciclo where idciclo >= ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, ciclo);
      ResultSet rs = pstm.executeQuery();
      lista = rsToListCombo(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      throw new RuntimeException("Error al acceder a la tabla de cliclos.");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }
  
  public List<ComboDTO> traerCursos(){
    List<ComboDTO> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Consulta
      String sql = "select idcurso code, NomCurso name from curso";
      PreparedStatement pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      lista = rsToListCombo(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      throw new RuntimeException("Error al acceder a la tabla de cursos.");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }
  
  public List<ComboDTO> traerProfesores(){
    List<ComboDTO> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Consulta
      String sql = "select IdProfesor code, "
          + "NomProfesor + ', ' + ApeProfesor name "
          + "from profesor";
      PreparedStatement pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      lista = rsToListCombo(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      throw new RuntimeException("Error al acceder a la tabla de profesor.");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }
  
  public List<ComboDTO> traerAlumnos(){
    List<ComboDTO> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Consulta
      String sql = "select Idalumno code, "
          + "NomAlumno + ', ' + ApeAlumno name  "
          + "from alumno order by 2";
      PreparedStatement pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      lista = rsToListCombo(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      throw new RuntimeException("Error al acceder a la tabla de profesor.");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }

  private List<ComboDTO> rsToListCombo(ResultSet rs) throws SQLException {
    List<ComboDTO> lista = new ArrayList<>();
    while(rs.next()){
      String code = rs.getString("code");
      String name = rs.getString("name");
      ComboDTO dto = new ComboDTO(code, name);
      lista.add(dto);
    }
    return lista;
  }
  
  
}
