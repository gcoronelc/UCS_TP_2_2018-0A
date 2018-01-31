package pe.egcc.edutec.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.egcc.edutec.db.AccesoDB;
import pe.egcc.edutec.dto.EmpleadoDTO;

public class LogonService {

  public EmpleadoDTO validarUsuario(String codigo, String clave){
    EmpleadoDTO empleadoDTO = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select idempleado,apeempleado,nomempleado,"
          + "cargo,dirempleado,telempleado,emailempleado "
          + "from empleado "
          + "where idempleado = ? and clave = ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, codigo);
      pstm.setString(2, clave);
      ResultSet rs = pstm.executeQuery();
      if(!rs.next()){
        throw new SQLException("Datos incorrectos");
      }
      empleadoDTO = new EmpleadoDTO();
      empleadoDTO.setCodigo(rs.getString("idempleado"));
      empleadoDTO.setApellido(rs.getString("apeempleado"));
      empleadoDTO.setNombre(rs.getString("nomempleado"));
      empleadoDTO.setCargo(rs.getString("cargo"));
      empleadoDTO.setDireccion(rs.getString("dirempleado"));
      empleadoDTO.setTelefono(rs.getString("telempleado"));
      empleadoDTO.setEmail(rs.getString("emailempleado"));
      empleadoDTO.setClave("");
      rs.close();
      pstm.close();
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException("Error en el proceso");
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return empleadoDTO;
  }
  
}
