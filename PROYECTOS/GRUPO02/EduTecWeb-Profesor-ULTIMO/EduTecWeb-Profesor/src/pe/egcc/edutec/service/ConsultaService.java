package pe.egcc.edutec.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import pe.egcc.edutec.db.AccesoDB;
import pe.egcc.edutec.dto.CursoDTO;
import pe.egcc.edutec.dto.EmpleadoDTO;
import pe.egcc.edutec.dto.TarifaDTO;

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
  
  public List<Map<String,Object>> ListarTarifas(String Descripcion){
	    List<Map<String,Object>> lista = null;
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      // Consulta
	      String sql = "select IdTarifa,PrecioVenta,Descripcion,Horas,PagoHora from Tarifa ";
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      //pstm.setInt(1, cursoprog);
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

  
  
  public  void  Registrar( String IdTarifa,
  Double PrecioVenta ,
  String Descripcion,
  Integer Horas ,
  Double PagoHora) {
	String sql="";
	  Connection cn = null;
	    try {
	      
	     
	      cn = AccesoDB.getConnection();
	      cn.setAutoCommit(false);
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      
	      // Insertar registro
	      sql = "insert into Tarifa(IdTarifa,PrecioVenta,"
	          + "Descripcion,Horas,PagoHora"
	          + ")"
	          + "values(?,?,?,?,?)";
	      pstm = cn.prepareStatement(sql);
	      pstm.setString(1, IdTarifa);
	      pstm.setDouble(2,PrecioVenta);
	      pstm.setString(3, Descripcion);
	      pstm.setInt(4,Horas );
	      pstm.setDouble(5, PagoHora);
	    
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
  public TarifaDTO GetIdTarifa(String codigo){
	  TarifaDTO TarifaDTO = null;
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      String sql = "select *"
	          + "from Tarifa "
	          + "where idtarifa = ? ";
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      pstm.setString(1, codigo);
	    
	      ResultSet rs = pstm.executeQuery();
	      if(!rs.next()){
	        throw new SQLException("Datos incorrectos");
	      }
	      TarifaDTO = new TarifaDTO();
	      TarifaDTO.setIdTarifa(rs.getString("idtarifa"));
	      TarifaDTO.setPrecioVenta(rs.getString("precioventa"));
	      TarifaDTO.setDescripcion(rs.getString("descripcion"));
	      TarifaDTO.setHoras(rs.getString("horas"));
	      TarifaDTO.setPagoHora(rs.getString("pagohora"));
	     
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
	    return TarifaDTO;
	  }

  public void ModificarTarifa(
	  String IdTarifa,Double PrecioVenta ,String Descripcion,Integer Horas ,Double PagoHora) {
		String sql="";
		  Connection cn = null;
		    try {
		    cn = AccesoDB.getConnection();
		      cn.setAutoCommit(false);
		      PreparedStatement pstm = cn.prepareStatement(sql);
		      
		      // Insertar registro
		      sql = "UPDATE  Tarifa SET ";		  
		      sql+="PrecioVenta='"+PrecioVenta+"',";
		      sql+="Descripcion='"+Descripcion+"',";
		      sql+="Horas="+Horas+",";
		      sql+="PagoHora='"+PagoHora+"' ";
		      sql+="where IdTarifa='"+IdTarifa+"'";
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
  
  public  void  Eliminar( String IdTarifa){
	  
		String sql="";
		  Connection cn = null;
		    try {
		     
		      cn = AccesoDB.getConnection();
		      
		      
		      // Insertar registro
		      sql = "delete from Tarifa where IdTarifa=? ";
		    
		      PreparedStatement pstm = cn.prepareStatement(sql);
		      pstm.setString(1, IdTarifa);	     
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
  public List<Map<String,Object>> ListarCursos(String Descripcion){
	    List<Map<String,Object>> lista = null;
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      // Consulta
	      String sql = "select b.idcurso,b.nomcurso,a.descripcion,a.pagohora from Tarifa a ";
	      sql+="inner join Curso b on a.IdTarifa=b.IdTarifa ";
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      //pstm.setInt(1, cursoprog);
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

  public  void  EliminarCurso( String IdCurso){
	  
		String sql="";
		  Connection cn = null;
		    try {
		     
		      cn = AccesoDB.getConnection();
		      
		      
		      // Insertar registro
		      sql = "delete from Curso where IdCurso=? ";
		    
		      PreparedStatement pstm = cn.prepareStatement(sql);
		      pstm.setString(1, IdCurso);	     
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
  
  public void RegistrarCurso(String IdTarifa,String NomCurso) {
	
		String sql="";
		  Connection cn = null;
		    try {
		      
		     
		      cn = AccesoDB.getConnection();
		      cn.setAutoCommit(false);
		      PreparedStatement pstm = cn.prepareStatement(sql);
		      
		      sql="select max(IdCurso)as id from Curso ";
		      pstm = cn.prepareStatement(sql);
		      ResultSet rs = pstm.executeQuery();
		
		      rs.next();
		    String  id = rs.getString("id");
		      rs.close();
		      pstm.close();
		      int Valor=Integer.parseInt( id.substring(1,4));
		     Valor++;
		    
		     String dt=String.format("%03d", Valor);
		     sql = "insert into Curso(IdCurso,IdTarifa,"
			          + "NomCurso"
			          + ")"
			          + "values(?,?,?)";
			      pstm = cn.prepareStatement(sql);
			      pstm.setString(1, "C"+ dt );
			      pstm.setString(2,IdTarifa);
			      pstm.setString(3, NomCurso);
			
			    
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
  public CursoDTO GetCurso(String codigo){
	  CursoDTO oCursoDTO = null;
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      String sql = "select *"
	          + "from Curso "
	          + "where idCurso = ? ";
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      pstm.setString(1, codigo);
	    
	      ResultSet rs = pstm.executeQuery();
	      if(!rs.next()){
	        throw new SQLException("Datos incorrectos");
	      }
	      oCursoDTO = new CursoDTO();
	      oCursoDTO.setIdCurso(rs.getString("idCurso"));
	      oCursoDTO.setIdTarifa(rs.getString("idTarifa"));
	      oCursoDTO.setNomCurso(rs.getString("nomCurso"));
	     
	     
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
	    return oCursoDTO;

	  
  }

  public void ModificarCurso(String IdCurso,String IdTarifa,String NomCurso) {
			String sql="";
			  Connection cn = null;
			    try {
			    cn = AccesoDB.getConnection();
			      cn.setAutoCommit(false);
			      PreparedStatement pstm = cn.prepareStatement(sql);
			      
			      // Insertar registro
			      sql = "UPDATE  Curso SET ";		  
			      sql+="IdTarifa='"+IdTarifa+"',";
			      sql+="NomCurso='"+NomCurso+"' ";
			   
			      sql+="where IdCurso='"+IdCurso+"'";
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

}
