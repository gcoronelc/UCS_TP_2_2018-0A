package pe.egcc.edutec.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import pe.egcc.edutec.db.AccesoDB;

public class ConsultaService {

	
	

	  public String eliminarEmpleado( String codigo  ){
		  String mensaje = "";
	 	    Connection cn = null;
		    try {
		      cn = AccesoDB.getConnection();
		      // Consulta
		      
		   // Insertar registro
		     String sql = " DELETE FROM  [dbo].[Empleado] "
		     + "  WHERE IdEmpleado = ?";
		     PreparedStatement pstm = cn.prepareStatement(sql);
		       
		      pstm.setString(1, codigo);
		      pstm.executeUpdate();
		      pstm.close();
		      mensaje = "Se Elimino satisfactoriamente";
		      cn.commit();
		    } catch (Exception e) {
		      e.printStackTrace();
		      throw new RuntimeException("Error al acceder vista de cursos programados.");
	 	    } finally {
		      try {
		        cn.close();
		      } catch (Exception e2) {
		    	  mensaje = "0";
		      }
		    }
		    return mensaje;
		    
		  }
  
	  public String actualizarEmpleado( String codigo, String clave, String nombre, String apellido, String cargo,String direccion,String telefono,String email ){
		  String mensaje = "";
	 	    Connection cn = null;
		    try {
		      cn = AccesoDB.getConnection();
		      // Consulta
		      
		   // Insertar registro
		     String sql = " UPDATE  [dbo].[Empleado] "
		          + " SET  Clave = ? , NomEmpleado = ? ,  ApeEmpleado = ? , " 
		     + "  Cargo = ? ,  DirEmpleado = ?  , TelEmpleado = ? , EmailEmpleado = ?  " 
		     + " WHERE IdEmpleado = ? ";
		     PreparedStatement pstm = cn.prepareStatement(sql);
		       
		   
		      pstm.setString(1, clave);
	 	      pstm.setString(2,  nombre  );
		      pstm.setString(3,  apellido);
		      pstm.setString(4, cargo);
		      pstm.setString(5,  direccion);
		      pstm.setString(6,  telefono);
		      pstm.setString(7,  email);
		      pstm.setString(8, codigo);
		      pstm.executeUpdate();
		      pstm.close();
		      mensaje = "Se Actualizaron satisfactoriamente";
		      cn.commit();
		    } catch (Exception e) {
		      e.printStackTrace();
		      throw new RuntimeException("Error al acceder vista de cursos programados.");
	 	    } finally {
		      try {
		        cn.close();
		      } catch (Exception e2) {
		    	  mensaje = "0";
		      }
		    }
		    return mensaje;
		    
		  }
	  
	  

	  public List<Map<String,Object>> consultaEmpleados(String codigo){
	    List<Map<String,Object>> lista = null;
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      // Consulta
	      
	      String sql = " select e.IdEmpleado as idempleado, e.Clave as clave, e.NomEmpleado as nombre, e.ApeEmpleado as apellido, e.Cargo as cargo ,e.DirEmpleado as direccion , e.TelEmpleado as telefono ,e.EmailEmpleado as email "
	              + " from [dbo].[Empleado] e  " ;
	              if(!codigo.equals("")){
	            	  sql =  sql.concat(" WHERE e.IdEmpleado = ? ")  ;
	    		  }
	           
	              ;
	      
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      if(!codigo.equals("")){
	    	  pstm.setString(1, codigo);
	      } 
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

	  public List<Map<String,Object>> consultaCurso(String cursoprog){
	    List<Map<String,Object>> lista = null;
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      // Consulta
	      
	      String sql = " select C.IdCurso as code, c.NomCurso as name "
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
	      pstm.setString(1, cursoprog);
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
  

  public String insertarNota( String alumno , int curso , double parcial , double finall ,double sub , double promedio  ){
	  String mensaje = "";
 	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      // Consulta
	      
	   // Insertar registro
	     String sql = "insert into [dbo].[Matricula]   (IdCursoProg , IdAlumno ,FecMatricula ,ExaParcial , ExaFinal , Subsanacion , Promedio ) "
	          + "values(? , ? , getdate()  , ? ,? , ? , ? )";
	     PreparedStatement pstm = cn.prepareStatement(sql);
	     DecimalFormat decimalFormat = new DecimalFormat("#.00");
	     String x =  decimalFormat.format(parcial);
	     String y =  decimalFormat.format(finall);
	     String l =  decimalFormat.format(sub);
	     String m =  decimalFormat.format(promedio);
	     
	     decimalFormat.format(finall);
	    
	     decimalFormat.format(promedio);
	     decimalFormat.format(sub);
	     Double pa =  Double.parseDouble((x.replace(",", ".")));
	     Double fin = Double.parseDouble((y.replace(",", ".")));
	     Double s = Double.parseDouble((l.replace(",", ".")));
	     Double pro = Double.parseDouble((m.replace(",", ".")));
	     
	     BigDecimal par = new BigDecimal(pa);
	     BigDecimal fina  = new BigDecimal(fin);
	     BigDecimal subs = new BigDecimal(s);
	     BigDecimal pr = new BigDecimal(pro);
	     
	     par = par.setScale(2, BigDecimal.ROUND_HALF_UP);
	     fina = fina.setScale(2, BigDecimal.ROUND_HALF_UP);
	     subs = subs.setScale(2, BigDecimal.ROUND_HALF_UP);
	      pr = pr.setScale(2, BigDecimal.ROUND_HALF_UP);
	     
	      pstm.setInt(1, curso);
	      pstm.setString(2, alumno);
 	      pstm.setDouble(3,   par.doubleValue() + 0);
	      pstm.setDouble(4,  fina.doubleValue());
	      pstm.setDouble(5,subs.doubleValue());
	      pstm.setDouble(6, pr.doubleValue());
	      pstm.executeUpdate();
	      pstm.close();
	      mensaje = "Se registro satisfactoriamente";
	      cn.commit();
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw new RuntimeException("Error al acceder vista de cursos programados.");
 	    } finally {
	      try {
	        cn.close();
	      } catch (Exception e2) {
	    	  mensaje = "0";
	      }
	    }
	    return mensaje;
	    
	  }
  
  public String registrarEmpleado( String codigo, String clave, String nombre, String apellido, String cargo,String direccion,String telefono,String email ){
	  String mensaje = "";
 	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      // Consulta
	      
	   // Insertar registro
	     String sql = " insert into [dbo].[Empleado] (IdEmpleado , Clave , NomEmpleado , ApeEmpleado , Cargo , DirEmpleado , TelEmpleado , EmailEmpleado) "
	          + "values(? , ? ,? , ? ,? , ? , ? , ? )";
	     PreparedStatement pstm = cn.prepareStatement(sql);
	       
	      pstm.setString(1, codigo);
	      pstm.setString(2, clave);
 	      pstm.setString(3,  nombre  );
	      pstm.setString(4,  apellido);
	      pstm.setString(5, cargo);
	      pstm.setString(6,  direccion);
	      pstm.setString(7,  telefono);
	      pstm.setString(8,  email);
	      pstm.executeUpdate();
	      pstm.close();
	      mensaje = "Se registro satisfactoriamente";
	      cn.commit();
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw new RuntimeException("Error al acceder vista de cursos programados.");
 	    } finally {
	      try {
	        cn.close();
	      } catch (Exception e2) {
	    	  mensaje = "0";
	      }
	    }
	    return mensaje;
	    
	  }
  
  
  
  
  public List<Map<String,Object>> consultaFinanciero(String nombre){
	    List<Map<String,Object>> lista = null;
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      // Consulta
	      String sql = " SELECT distinct  (a.NomAlumno +a.ApeAlumno) as nombre , c.nomcurso as nombrecurso ,  "
	          + " p.PreCursoProg as ingreso , (p.PreCursoProg-t.PagoHora) gastos , (p.PreCursoProg+t.PagoHora) ganancias "
	          + " FROM [dbo].[Tarifa] t inner join [dbo].[Curso] c on t.IdTarifa=c.IdTarifa "
	          + " inner join [dbo].[CursoProgramado] p on p.IdCurso=c.IdCurso "
	          + " inner join [dbo].[Matricula] m on p.IdCursoProg = m.IdCursoProg "
	      + "  inner join [dbo].[Alumno] a on m.IdAlumno = a.IdAlumno "
	      + " where  (a.NomAlumno +a.ApeAlumno) like ?";
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      pstm.setString(1, "%" + nombre + "%" );
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
  
  public List<Map<String,Object>>conCursosProfFormciclo(int profesorxciclo){
	  List<Map<String,Object>> lista = null;
	  try {
		  Connection cn = null;
	      cn = AccesoDB.getConnection();
	      String sql = "select Id ciclo, "
	              + "idprofesor, profesor"
	              + "parcial, final, subsanacion, promedio "
	              + "from ciclo "
	              + "where profesorxciclo = ? ";
	          PreparedStatement pstm = cn.prepareStatement(sql);
	          pstm.setInt(1, profesorxciclo);
	          ResultSet rs = pstm.executeQuery();
	          lista = JdbcUtil.rsToList(rs);
	          rs.close();
	          pstm.close();
	  }catch (Exception e) {
      e.printStackTrace();
      //throw new RuntimeException("Error al acceder vista de cursos programados.");
    } finally {
      //try {
        //cn.close();
      //} catch (Exception e2) {
      }
    
    return lista;
  }
  
}
