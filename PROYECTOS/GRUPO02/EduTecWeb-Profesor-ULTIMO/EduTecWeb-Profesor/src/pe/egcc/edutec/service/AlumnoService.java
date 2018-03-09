package pe.egcc.edutec.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pe.egcc.edutec.db.AccesoDB;
import pe.egcc.edutec.dto.AlumnoDTO;
import pe.egcc.edutec.dto.ProfesorDTO;

public class AlumnoService {

	  public List<Map<String,Object>> ListarAlumno(String Descripcion){
		    List<Map<String,Object>> lista = null;
		    Connection cn = null;
		    try {
		      cn = AccesoDB.getConnection();
		      // Consulta
		      String sql = "select * from Alumno  ";
		      
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

	  public  void  EliminarAlumno( String IdAlumno){
		  
			String sql="";
			  Connection cn = null;
			    try {
			     
			      cn = AccesoDB.getConnection();
			      
			      
			      // Insertar registro
			      sql = "delete from Alumno where IdAlumno=? ";
			    
			      PreparedStatement pstm = cn.prepareStatement(sql);
			      pstm.setString(1, IdAlumno);	     
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
	  
	  public void RegistrarAlumno(

String ApeAlumno,
String	NomAlumno,
String	DirAlumno,
String	TelAlumno,
String	EmailAlumno,
String	Clave) {
			
			String sql="";
			  Connection cn = null;
			    try {
			      
			     
			      cn = AccesoDB.getConnection();
			      cn.setAutoCommit(false);
			      PreparedStatement pstm = cn.prepareStatement(sql);
			      
			      sql="select max(IdAlumno)as id from Alumno ";
			      pstm = cn.prepareStatement(sql);
			      ResultSet rs = pstm.executeQuery();
			
			      rs.next();
			    String  id = rs.getString("id");
			      rs.close();
			      pstm.close();
			      int Valor=Integer.parseInt( id.substring(1,5));
			     Valor++;
			 
			
			     String dt=String.format("%04d", Valor);
			     sql = "insert into Alumno(IdAlumno,ApeAlumno,NomAlumno,"
				          + "DirAlumno,TelAlumno,EmailAlumno,Clave"
				          + ")"
				          + "values(?,?,?,?,?,?,?)";
				      pstm = cn.prepareStatement(sql);
				      pstm.setString(1, "A"+ dt );
				      pstm.setString(2,ApeAlumno);
				      pstm.setString(3, NomAlumno);
				      pstm.setString(4, DirAlumno);
				      pstm.setString(5, TelAlumno);
				      pstm.setString(6, EmailAlumno);
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
	  public void ModificarAlumno(
			  String	IdAlumno,
			  String ApeAlumno,
			  String	NomAlumno,
			  String	DirAlumno,
			  String	TelAlumno,
			  String	EmailAlumno,
			  String	Clave) {
				String sql="";
				  Connection cn = null;
				    try {
				    cn = AccesoDB.getConnection();
				      cn.setAutoCommit(false);
				      PreparedStatement pstm = cn.prepareStatement(sql);
				      
				      // Insertar registro
				      sql = "update Alumno SET ";		  
				      sql+="ApeAlumno='"+ApeAlumno+"',";
				      sql+="NomAlumno='"+NomAlumno+"',";
				      sql+="DirAlumno='"+DirAlumno+"',";
				      sql+="TelAlumno='"+TelAlumno+"', ";
				      sql+="EmailAlumno='"+EmailAlumno+"', ";
				      sql+="Clave='"+Clave+"' ";
				      sql+="where IdAlumno='"+IdAlumno+"'";
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
	  public AlumnoDTO GetAlumno(String codigo){
		  AlumnoDTO oAlumnoDTO = null;
		    Connection cn = null;
		    try {
		      cn = AccesoDB.getConnection();
		      String sql = "select *"
		          + "from Alumno "
		          + "where IdAlumno = ? ";
		      PreparedStatement pstm = cn.prepareStatement(sql);
		      pstm.setString(1, codigo);
		    
		      ResultSet rs = pstm.executeQuery();
		      if(!rs.next()){
		        throw new SQLException("Datos incorrectos");
		      }
		      oAlumnoDTO = new AlumnoDTO();
		      oAlumnoDTO.setIdAlumno(rs.getString("IdAlumno"));
		      oAlumnoDTO.setApeAlumno(rs.getString("ApeAlumno"));
		      oAlumnoDTO.setNomAlumno(rs.getString("NomAlumno"));
		      oAlumnoDTO.setDirAlumno(rs.getString("DirAlumno"));
		      oAlumnoDTO.setTelAlumno(rs.getString("TelAlumno"));
		      oAlumnoDTO.setEmailAlumno(rs.getString("EmailAlumno"));
		      oAlumnoDTO.setClave(rs.getString("Clave"));
			     
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
		    return oAlumnoDTO;
		  }

	
}
