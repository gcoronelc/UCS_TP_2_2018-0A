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
