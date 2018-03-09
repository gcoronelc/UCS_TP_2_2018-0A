package pe.egcc.edutec.service;


import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.egcc.edutec.db.AccesoDB;
import pe.egcc.edutec.dto.ProfesorDTO;
import pe.egcc.edutec.dto.TarifaDTO;

public class ProfesorService {

	

	  public List<Map<String,Object>> ListarProfesores(String Descripcion){
		    List<Map<String,Object>> lista = null;
		    Connection cn = null;
		    try {
		      cn = AccesoDB.getConnection();
		      // Consulta
		      String sql = "select * from Profesor  ";
		      if (Descripcion!="") {
					sql+="where NomProfesor like '%"+Descripcion+"%'";
				}
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

	  public  void  EliminarProfesor( String IdProfesor){
		  
			String sql="";
			  Connection cn = null;
			    try {
			     
			      cn = AccesoDB.getConnection();
			      
			      
			      // Insertar registro
			      sql = "delete from Profesor where IdProfesor=? ";
			    
			      PreparedStatement pstm = cn.prepareStatement(sql);
			      pstm.setString(1, IdProfesor);	     
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
	  
	  public void RegistrarProfesor(

String ApeProfesor,
String	NomProfesor,
String	DirProfesor,
String	TelProfesor,
String	EmailProfesor,
String	Clave) {
			
			String sql="";
			  Connection cn = null;
			    try {
			      
			     
			      cn = AccesoDB.getConnection();
			      cn.setAutoCommit(false);
			      PreparedStatement pstm = cn.prepareStatement(sql);
			      
			      sql="select max(IdProfesor)as id from Profesor ";
			      pstm = cn.prepareStatement(sql);
			      ResultSet rs = pstm.executeQuery();
			
			      rs.next();
			    String  id = rs.getString("id");
			      rs.close();
			      pstm.close();
			      int Valor=Integer.parseInt( id.substring(1,4));
			     Valor++;
			 
			
			     String dt=String.format("%03d", Valor);
			     sql = "insert into Profesor(IdProfesor,ApeProfesor,NomProfesor,"
				          + "DirProfesor,TelProfesor,EmailProfesor,Clave"
				          + ")"
				          + "values(?,?,?,?,?,?,?)";
				      pstm = cn.prepareStatement(sql);
				      pstm.setString(1, "P"+ dt );
				      pstm.setString(2,ApeProfesor);
				      pstm.setString(3, NomProfesor);
				      pstm.setString(4, DirProfesor);
				      pstm.setString(5, TelProfesor);
				      pstm.setString(6, EmailProfesor);
				      pstm.setString(7, Clave);
				    
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
	  public void ModificarProfesor(
			  String	IdProfesor,
			  String ApeProfesor,
			  String	NomProfesor,
			  String	DirProfesor,
			  String	TelProfesor,
			  String	EmailProfesor,
			  String	Clave) {
				String sql="";
				  Connection cn = null;
				    try {
				    cn = AccesoDB.getConnection();
				      cn.setAutoCommit(false);
				      PreparedStatement pstm = cn.prepareStatement(sql);
				      
				      // Insertar registro
				      sql = "update Profesor SET ";		  
				      sql+="ApeProfesor='"+ApeProfesor+"',";
				      sql+="NomProfesor='"+NomProfesor+"',";
				      sql+="DirProfesor='"+DirProfesor+"',";
				      sql+="TelProfesor='"+TelProfesor+"', ";
				      sql+="EmailProfesor='"+EmailProfesor+"', ";
				      sql+="Clave='"+Clave+"' ";
				      sql+="where IdProfesor='"+IdProfesor+"'";
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
	  public ProfesorDTO GetProfesor(String codigo){
		  ProfesorDTO oProfesorDTO = null;
		    Connection cn = null;
		    try {
		      cn = AccesoDB.getConnection();
		      String sql = "select *"
		          + "from Profesor "
		          + "where IdProfesor = ? ";
		      PreparedStatement pstm = cn.prepareStatement(sql);
		      pstm.setString(1, codigo);
		    
		      ResultSet rs = pstm.executeQuery();
		      if(!rs.next()){
		        throw new SQLException("Datos incorrectos");
		      }
		      oProfesorDTO = new ProfesorDTO();
		      oProfesorDTO.setIdProfesor(rs.getString("IdProfesor"));
		      oProfesorDTO.setApeProfesor(rs.getString("ApeProfesor"));
		      oProfesorDTO.setNomProfesor(rs.getString("NomProfesor"));
		      oProfesorDTO.setDirProfesor(rs.getString("DirProfesor"));
		      oProfesorDTO.setTelProfesor(rs.getString("TelProfesor"));
		      oProfesorDTO.setEmailProfesor(rs.getString("EmailProfesor"));
		      oProfesorDTO.setClave(rs.getString("Clave"));
			     
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
		    return oProfesorDTO;
		  }

}
