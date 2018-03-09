package pe.egcc.edutec.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import pe.egcc.edutec.db.AccesoDB;

public class ConsultaService {

  
  public List<Map<String,Object>> conAlumnosCursoPrg(int cursoprog){
    List<Map<String,Object>> lista = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      // Consulta
      String sql = "select cursoprog, ciclo, idcurso, curso, "
          + "horario, activo, idprofesor, profesor, idalumno, "
          + "alumno, parcial, final, subsanacion, promedio "
          + "from v_alumnosmat "
          + "where cursoprog = ? ";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setInt(1, cursoprog);
      ResultSet rs = pstm.executeQuery();
      lista = JdbcUtil.rsToList(rs);
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
  
}
