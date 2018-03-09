package pe.egcc.edutec.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pe.egcc.edutec.db.AccesoDB;
import pe.egcc.edutec.dto.AlumnoDTO;
import pe.egcc.edutec.dto.EmpleadoDTO;
import pe.egcc.edutec.dto.ProfesorDTO;

public class EmpleadoService {

	  public List<Map<String,Object>> ListaEmpleado(String Descripcion){
		    List<Map<String,Object>> lista = null;
		    Connection cn = null;
		    try {
		      cn = AccesoDB.getConnection();
		      // Consulta
		      String sql = "select * from Empleado  ";
		      
		      PreparedStatement pstm = cn.prepareStatement(sql);
		  
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

	  public  void  EliminarEmpleado( String IdEmpleado){
		  
			String sql="";
			  Connection cn = null;
			    try {
			     
			      cn = AccesoDB.getConnection();
			      
			      
			      // Insertar registro
			      sql = "delete from Empleado where IdEmpleado=? ";
			    
			      PreparedStatement pstm = cn.prepareStatement(sql);
			      pstm.setString(1, IdEmpleado);	     
			      pstm.executeQuery();
			      pstm.close();
			      
			      // Confirmar Tx
			 
			    } catch (SQLException e) {
			      try {
			        cn.rollback();
			      } catch (Exception e2) {
			      }
			      throw new RuntimeException(e.getMessage());
			    } catch (Exception e) {
			      try {
			        cn.rollback();
			      } catch (Exception e2) {
			      }
			      throw new RuntimeException("Se ha producido un error.");
			    } finally{
			      try {
			        cn.close();
			      } catch (Exception e2) {
			      }
			    }
			    
			
		  
		  
	  }
	  
	  public void RegistrarEmpleado(
			  String IdEmpleado,
			  String	Clave,
				String ApeEmpleado,
				String	NomEmpleado,
				String	Cargo,
				String	DirEmpleado,
				String	TelEmpleado,
				String	EmailEmpleado
) {
			
			String sql="";
			  Connection cn = null;
			    try {
			      
			     
			      cn = AccesoDB.getConnection();
			      cn.setAutoCommit(false);
			      PreparedStatement pstm = cn.prepareStatement(sql);
			
			      sql = "insert into Empleado(IdEmpleado,Clave,ApeEmpleado,NomEmpleado,cargo,"
				          + "DirEmpleado,TelEmpleado,EmailEmpleado"
				          + ")"
				          + "values(?,?,?,?,?,?,?,?)";
				      pstm = cn.prepareStatement(sql);
				      pstm.setString(1, IdEmpleado );
				      pstm.setString(2, Clave);
				      pstm.setString(3,ApeEmpleado);
				      pstm.setString(4, NomEmpleado);
				      pstm.setString(5, Cargo);
				      pstm.setString(6, DirEmpleado);
				      pstm.setString(7, TelEmpleado);
				      pstm.setString(8, EmailEmpleado);
				     
				    
				      pstm.executeUpdate();
				      pstm.close();
				    
			      // Confirmar Tx
			      cn.commit();
			    } catch (SQLException e) {
			      try {
			        cn.rollback();
			      } catch (Exception e2) {
			      }
			      throw new RuntimeException(e.getMessage());
			    } catch (Exception e) {
			      try {
			        cn.rollback();
			      } catch (Exception e2) {
			      }
			      throw new RuntimeException("Se ha producido un error.");
			    } finally{
			      try {
			        cn.close();
			      } catch (Exception e2) {
			      }
			    }
		  
	}
	  public void ModificarEmpleado(
			  String IdEmpleado,
			  String	Clave,
				String ApeEmpleado,
				String	NomEmpleado,
				String	Cargo,
				String	DirEmpleado,
				String	TelEmpleado,
				String	EmailEmpleado) {
				String sql="";
				  Connection cn = null;
				    try {
				    cn = AccesoDB.getConnection();
				      cn.setAutoCommit(false);
				      PreparedStatement pstm = cn.prepareStatement(sql);
				      
				      // Insertar registro
				      sql = "update Empleado SET ";		  
				      sql+="Clave='"+Clave+"',";
				      sql+="ApeEmpleado='"+ApeEmpleado+"',";
				      sql+="NomEmpleado='"+NomEmpleado+"',";
				      sql+="Cargo='"+Cargo+"', ";
				      sql+="DirEmpleado='"+DirEmpleado+"', ";
				      sql+="TelEmpleado='"+TelEmpleado+"', ";
				      sql+="EmailEmpleado='"+EmailEmpleado+"' ";
				     
				      sql+="where IdEmpleado='"+IdEmpleado+"'";
				      pstm = cn.prepareStatement(sql);
				    
				  
				      pstm.executeUpdate();
				      pstm.close();
				
				      // Confirmar Tx
				      cn.commit();
				    } catch (SQLException e) {
				      try {
				        cn.rollback();
				      } catch (Exception e2) {
				      }
				      throw new RuntimeException(e.getMessage());
				    } catch (Exception e) {
				      try {
				        cn.rollback();
				      } catch (Exception e2) {
				      }
				      throw new RuntimeException("Se ha producido un error.");
				    } finally{
				      try {
				        cn.close();
				      } catch (Exception e2) {
				      }
				    }
			  
		}
	  public EmpleadoDTO GetEmpleado(String codigo){
		  EmpleadoDTO oEmpleadoDTO = null;
		    Connection cn = null;
		    try {
		      cn = AccesoDB.getConnection();
		      String sql = "select *"
		          + "from Empleado "
		          + "where IdEmpleado = ? ";
		      PreparedStatement pstm = cn.prepareStatement(sql);
		      pstm.setString(1, codigo);
		    
		      ResultSet rs = pstm.executeQuery();
		      if(!rs.next()){
		        throw new SQLException("Datos incorrectos");
		      }
		      oEmpleadoDTO = new EmpleadoDTO();
		      oEmpleadoDTO.setCodigo(rs.getString("IdEmpleado"));
		      oEmpleadoDTO.setClave(rs.getString("Clave"));
		      oEmpleadoDTO.setApellido(rs.getString("ApeEmpleado"));
		      oEmpleadoDTO.setNombre(rs.getString("NomEmpleado"));
		      oEmpleadoDTO.setCargo(rs.getString("Cargo"));
		      oEmpleadoDTO.setDireccion(rs.getString("DirEmpleado"));
		      oEmpleadoDTO.setTelefono(rs.getString("TelEmpleado"));
		      oEmpleadoDTO.setEmail(rs.getString("EmailEmpleado"));
		
		      
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
		    return oEmpleadoDTO;
		  }

	
}
